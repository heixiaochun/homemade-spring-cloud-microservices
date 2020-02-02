package com.xplmc.learning.homemade.whitelist;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * unit test for WhitelistApplication
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WhitelistApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void contextLoads() {
        Assert.assertNotNull("application context should not be null", applicationContext);
    }

}
