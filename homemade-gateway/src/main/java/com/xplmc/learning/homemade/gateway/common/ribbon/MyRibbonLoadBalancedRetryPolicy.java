package com.xplmc.learning.homemade.gateway.common.ribbon;

import com.netflix.client.config.IClientConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.classify.BinaryExceptionClassifier;
import org.springframework.cloud.client.loadbalancer.LoadBalancedRetryContext;
import org.springframework.cloud.client.loadbalancer.ServiceInstanceChooser;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancedRetryPolicy;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerContext;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 定制的RibbonLoadBalancedRetryPolicy
 * 支持连接超时、连接拒绝的情况下，重试所有请求
 *
 * @author luke
 */
public class MyRibbonLoadBalancedRetryPolicy extends RibbonLoadBalancedRetryPolicy {

    private static final BinaryExceptionClassifier retryableClassifier;
    private static final String CONNECT_TIMED_OUT = "connect timed out";

    static {
        Collection<Class<? extends Throwable>> exceptionClasses = new ArrayList<>();
        // 根据RestTemplate的RequestFactory设置对应的连接异常类
        exceptionClasses.add(ConnectException.class);
        retryableClassifier = new BinaryExceptionClassifier(exceptionClasses);
    }


    public MyRibbonLoadBalancedRetryPolicy(String serviceId, RibbonLoadBalancerContext context, ServiceInstanceChooser loadBalanceChooser) {
        super(serviceId, context, loadBalanceChooser);
    }

    public MyRibbonLoadBalancedRetryPolicy(String serviceId, RibbonLoadBalancerContext context, ServiceInstanceChooser loadBalanceChooser,
                                           IClientConfig clientConfig) {
        super(serviceId, context, loadBalanceChooser,
                clientConfig);
    }

    /**
     * 支持连接超时、连接拒绝的情况下，重试所有请求
     *
     * @param context
     * @return
     */
    @Override
    public boolean canRetry(LoadBalancedRetryContext context) {
        return retryForException(context.getLastThrowable()) || super.canRetry(context);
    }

    /**
     * 判断某异常能否重试
     *
     * @param ex
     * @return
     */
    private boolean retryForException(Throwable ex) {
        // SimpleClientHttpRequestFactory连接超时返回：java.net.SocketTimeoutException: connect timed out
        // 这里做特殊处理
        if (ex != null && StringUtils.contains(ex.getMessage(), CONNECT_TIMED_OUT)) {
            return true;
        }
        return retryableClassifier.classify(ex);
    }

}
