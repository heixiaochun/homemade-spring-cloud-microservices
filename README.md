# homemade-spring-cloud-microservice
## homemade-eureka-service
By default, using application.yml's single server mode.

In production, using cluster mode, examples are in application-peer1.yml/application-peer2.yml/application-peer3.yml

In cluster mode, you need to config hosts in you server.
```text
# if you are using windows ,at below to your hosts file
127.0.0.1 peer1
127.0.0.1 peer2
127.0.0.1 peer3
```
