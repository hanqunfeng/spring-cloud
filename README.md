# spring-cloud
1. eureka-server:spring-cloud-starter-netflix-eureka-server 服务注册中心，需要集群部署，前端nginx分发
2. admin-server:spring-boot-admin-starter-server 监控中心，可以集群部署
3. config-server:spring-cloud-config-server 配置中心，需要集群部署
4. eureka-provider:spring-cloud-starter-netflix-eureka-client 服务提供者，需要集群部署
5. eureka-ribbon:spring-cloud-starter-netflix-eureka-client 服务提供者及消费者，需要集群部署，ribbon+restTemplate，对外提供接口层
6. feign:spring-cloud-starter-netflix-eureka-client spring-cloud-starter-openfeign 服务提供者及消费者，需要集群部 feign，对外提供接口层
7. oauth2-jwt:spring-cloud-starter-oauth2 对外接口资源安全认证，这里负责保护eureka-ribbon和feign的
8. oauth2-jwt-common:为接口服务提供oauth2+jwt支持，具体配置方式见feign
9. turbine:spring-cloud-starter-netflix-turbine 聚合监控
10. zipkin-server:链路追踪，这里是下载的源码，增加了服务注册功能
11. zuul:spring-cloud-starter-netflix-zuul 路由服务，这里为eureka-ribbon和feign提供路由，前端用nginx做负载分发



 
