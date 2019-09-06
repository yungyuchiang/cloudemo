### Hello Spring
 
##### 私人学习 spring boot + spring cloud + spring security + vue 项目

### Spring Cloud 简介
Spring Cloud包含了多个子项目（针对分布式系统中涉及的多个不同开源产品），比如：Spring Cloud Config、Spring Cloud Netflix、Spring Cloud0 CloudFoundry、Spring Cloud AWS、Spring Cloud Security、Spring Cloud Commons、Spring Cloud Zookeeper、Spring Cloud CLI等项目。

### 微服务架构
微服务(Microservices Architecture)是一种架构风格，一个大型复杂软件应用由一个或多个微服务组成。系统中的各个微服务可被独立部署，各个微服务之间是松耦合的。每个微服务仅关注于完成一件任务并很好地完成该任务。在所有情况下，每个任务代表着一个小的业务能力。

微服务架构的核心思想是，一个应用是由多个小的、相互独立的、微服务组成，这些服务运行在自己的进程中，开发和发布都没有依赖。不同服务通过一些轻量级交互机制来通信，例如 RPC、HTTP 等，服务可独立扩展伸缩，每个服务定义了明确的边界，不同的服务甚至可以采用不同的编程语言来实现，由独立的团队来维护。简单的来说，一个系统的不同模块转变成不同的服务！而且服务可以使用不同的技术加以实现！

### 微服务设计
微服务的设计指南：
* 职责单一原则（Single Responsibility Principle）：把某一个微服务的功能聚焦在特定业务或者有限的范围内会有助于敏捷开发和服务的发布。
* 设计阶段就需要把业务范围进行界定。
* 需要关心微服务的业务范围，而不是服务的数量和规模尽量小。数量和规模需要依照业务功能而定。
* 于SOA不同，某个微服务的功能、操作和消息协议尽量简单。
* 项目初期把服务的范围制定相对宽泛，随着深入，进一步重构服务，细分微服务是个很好的做法。

### Spring Cloud Eureka
Spring Cloud Eureka来实现服务治理。

Spring Cloud Eureka是Spring Cloud Netflix项目下的服务治理模块。而Spring Cloud Netflix项目是Spring Cloud的子项目之一，主要内容是对Netflix公司一系列开源产品的包装，它为Spring Boot应用提供了自配置的Netflix OSS整合。通过一些简单的注解，开发者就可以快速的在应用中配置一下常用模块并构建庞大的分布式系统。它主要提供的模块包括：服务发现（Eureka），断路器（Hystrix），智能路由（Zuul），客户端负载均衡（Ribbon）等。

## DAY 01 hello world

#### Eureka Server
* 提供服务注册和发现

#### Service Provider
* 服务提供方
* 将自身服务注册到 Eureka 注册中心，从而使服务消费方能够找到

## DAY 02 Ribbon

#### Ribbon
Ribbon是Netflix发布的开源项目，主要功能是提供客户端的软件负载均衡算法，将Netflix的中间层服务连接在一起。Ribbon客户端组件提供一系列完善的配置项如连接超时，重试等。简单的说，就是在配置文件中列出Load Balancer（简称LB）后面所有的机器，Ribbon会自动的帮助你基于某种规则（如简单轮询，随即连接等）去连接这些机器。我们也很容易使用Ribbon实现自定义的负载均衡算法。

目前主流的LB方案可分成两类：一种是集中式LB, 即在服务的消费方和提供方之间使用独立的LB设施(可以是硬件，如F5, 也可以是软件，如nginx), 由该设施负责把访问请求通过某种策略转发至服务的提供方；另一种是进程内LB，将LB逻辑集成到消费方，消费方从服务注册中心获知有哪些地址可用，然后自己再从这些地址中选择出一个合适的服务器。Ribbon就属于后者，它只是一个类库，集成于消费方进程，消费方通过它来获取到服务提供方的地址。

## Day 03 Feign
### Netflix Feign
由于Spring Cloud 版本问题，导致Feign启动报错，最好使用start.spring.io去构建maven配置文件

### Feign简介
Feign是一个声明式的伪Http客户端，它使得写Http客户端变得更简单。

使用Feign，只需要创建一个接口并注解，它具有可插拔的注解特性，可使用Feign 注解和JAX-RS注解，Feign支持可插拔的编码器和解码器，Feign默认集成了Ribbon，并和Eureka结合，默认实现了负载均衡的效果。

**Feign 具有如下特性：** 
- 可插拔的注解支持，包括Feign注解和JAX-RS注解
- 支持可插拔的HTTP编码器和解码器
- 支持Hystrix和它的Fallback
- 支持Ribbon的负载均衡
- 支持HTTP请求和响应的压缩Feign是一个声明式的Web Service客户端，它的目的就是让Web Service调用更加简单。它整合了Ribbon和Hystrix，从而不再需要显式地使用这两个组件。Feign还提供了HTTP请求的模板，通过编写简单的接口和注解，就可以定义好HTTP请求的参数、格式、地址等信息。接下来，Feign会完全代理HTTP的请求，我们只需要像调用方法一样调用它就可以完成服务请求。

简而言之：Feign能干Ribbon和Hystrix的事情，但是要用Ribbon和Hystrix自带的注解必须要引入相应的jar包才可以。

## Day 04 Hystrix
### Hystrix 简介
Spring Cloud Hystrix实现了线程隔离、断路器等一系列的服务保护功能。它也是基于Netflix的开源框架 Hystrix实现的，该框架目标在于通过控制那些访问远程系统、服务和第三方库的节点，从而对延迟和故障提供更强大的容错能力。Hystrix具备了服务降级、服务熔断、线程隔离、请求缓存、请求合并以及服务监控等强大功能。

断路器在服务消费者使用

## Day 05 Zuul
服务网关是微服务架构中一个不可或缺的部分。通过服务网关统一向外系统提供REST API的过程中，除了具备服务路由、均衡负载功能之外，它还具备了权限控制等功能。Spring Cloud Netflix中的Zuul就担任了这样的一个角色，为微服务架构提供了前门保护的作用，同时将权限控制这些较重的非业务逻辑内容迁移到服务路由层面，使得服务集群主体能够具备更高的可复用性和可测试性。

路由在微服务体系结构的一个组成部分。例如，/可以映射到您的Web应用程序，/api/users映射到用户服务，并将/api/shop映射到商店服务。Zuul是Netflix的基于JVM的路由器和服务器端负载均衡器。

**Netflix使用Zuul进行以下操作：**
- 认证
- 洞察
- 压力测试
- 金丝雀测试
- 动态路由
- 服务迁移
- 负载脱落
- 安全
- 静态响应处理
- 主动/主动流量管理

#### 什么是服务网关
**服务网关 = 路由转发 + 过滤器**
1. 路由转发：接收一切外界请求，转发到后端的微服务上去；

2. 过滤器：在服务网关中可以完成一系列的横切功能，例如权限校验、限流以及监控等，这些都可以通过过滤器完成（其实路由转发也是通过过滤器实现的）。

![](http://www.ymq.io/images/2017/SpringCloud/zuul/11.png)

## Day 05 Zuul Filter
#### Zuul执行流程
![](http://www.ymq.io/images/2017/SpringCloud/zuulFilter/11.png)
**Zuul** 大部分功能都是通过过滤器来实现的。Zuul中定义了四种标准过滤器类型，这些过滤器类型对应于请求的典型生命周期。

**PRE** 这种过滤器在请求被路由之前调用。我们可利用这种过滤器实现身份验证、在集群中选择请求的微服务、记录调试信息等。

**ROUTING** 这种过滤器将请求路由到微服务。这种过滤器用于构建发送给微服务的请求，并使用Apache HttpClient或Netfilx Ribbon请求微服务。

**OST** 这种过滤器在路由到微服务以后执行。这种过滤器可用来为响应添加标准的HTTP Header、收集统计信息和指标、将响应从微服务发送给客户端等。

**ERROR** 在其他阶段发生错误时执行该过滤器。

除了默认的过滤器类型，Zuul还允许我们创建自定义的过滤器类型。例如，我们可以定制一种STATIC类型的过滤器，直接在Zuul中生成响应，而不将请求转发到后端的微服务。

通过重写ZuulFilter, 在启动其中添加@Bean

### filterType
**filterType**：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下:

- pre：路由之前
- routing：路由之时
- post： 路由之后
- error：发送错误调用
- filterOrder：过滤的顺序
- shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
- run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问