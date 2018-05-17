记录关于spring-cloud使用过程
1、eureka-server单独使用部署
2、服务提供者eureka-provider,服务消费者feign-consumer之间可以相互调用，即可以同时是服务
的提供者和消费者，相互调用；
3、config-server单独部署管理，集群容错；
4、config-client不单独使用，整合进服务提供者和消费者中才有意义，需要注意bootstrap.properties中对config-server
中git库文件的映射关系：
  /{application}/{profile}[/{label}]
  /{application}-{profile}.yml
  /{label}/{application}-{profile}.yml
  /{application}-{profile}.properties
  /{label}/{application}-{profile}.properties
  ——application 根据spring.config.name来获得
  ——profile 激活的剖面，比如spring.profiles.active = test
  ——label git资源的label 默认是master

5、使用feign+zipkin时不需要特别配置，单独使用ribbon需要RestTemplate来调用http服务
