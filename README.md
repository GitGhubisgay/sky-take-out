# 苍穹外卖

## 一.项目介绍

本项目（苍穹外卖）是专门为餐饮企业（餐厅、饭店）定制的一款软件产品，包括 系统管理后台 和 小程序端应用 两部分。其中系统管理后台主要提供给餐饮企业内部员工使用，可以对餐厅的分类、菜品、套餐、订单、员工等进行管理维护，对餐厅的各类数据进行统计，同时也可进行来单语音播报功能。小程序端主要提供给消费者使用，可以在线浏览菜品、添加购物车、下单、支付、催单等。

## 二.功能架构图



![aritecture.png](assert/aritecture.png)

### (1).管理端功能

员工登录/退出 , 员工信息管理 , 分类管理 , 菜品管理 , 套餐管理 , 菜品口味管理 , 订单管理 ，数据统计，来单提醒。

### (2).用户端功能

微信登录 , 收件人地址管理 , 用户历史订单查询 , 菜品规格查询 , 购物车功能 , 下单 , 支付、分类及菜品浏览。

## 三.技术选型

![technology.png](assert/technology.png)

### (1). 用户层

本项目中在构建系统管理后台的前端页面，我们会用到H5、Vue.js、ElementUI、apache echarts(展示图表)等技术。而在构建移动端应用时，我们会使用到微信小程序。

### (2). 网关层

Nginx是一个服务器，主要用来作为Http服务器，部署静态资源，访问性能高。在Nginx中还有两个比较重要的作用： 反向代理和负载均衡， 在进行项目部署时，要实现Tomcat的负载均衡，就可以通过Nginx来实现。

### (3). 应用层

SpringBoot： 快速构建Spring项目, 采用 "约定优于配置" 的思想, 简化Spring项目的配置开发。 SpringMVC：SpringMVC是spring框架的一个模块，springmvc和spring无需通过中间整合层进行整合，可以无缝集成。 Spring Task: 由Spring提供的定时任务框架。 httpclient: 主要实现了对http请求的发送。 Spring Cache: 由Spring提供的数据缓存框架 JWT: 用于对应用程序上的用户进行身份验证的标记。 阿里云OSS: 对象存储服务，在项目中主要存储文件，如图片等。 Swagger： 可以自动的帮助开发人员生成接口文档，并对接口进行测试。 POI: 封装了对Excel表格的常用操作。 WebSocket: 一种通信网络协议，使客户端和服务器之间的数据交换更加简单，用于项目的来单、催单功能实现。

### (4). 数据层

MySQL： 关系型数据库, 本项目的核心业务数据都会采用MySQL进行存储。 Redis： 基于key-value格式存储的内存数据库, 访问速度快, 经常使用它做缓存。 Mybatis： 本项目持久层将会使用Mybatis开发。 pagehelper: 分页插件。 spring data redis: 简化java代码操作Redis的API。

### (5). 工具

git: 版本控制工具, 在团队协作中, 使用该工具对项目中的代码进行管理。 maven: 项目构建工具。 junit：单元测试工具，开发人员功能实现完毕后，需要通过junit对功能进行单元测试。 postman: 接口测工具，模拟用户发起的各类HTTP请求，获取对应的响应结果。


## 四.技术栈

- 后端框架
    - SpringBoot
    - SpringMVC
    - Spring Task
    - httpclient
    - Spring Cache
    - mybatis
    - Swagger
    - Websocket
    - aliyun oss
    - JWT
    - POI
    - wechatpay(模拟)
- 数据库
    - MySql
    - Redis
- 前端框架
    - Vue
    - ElementUI
    - Vue-Router
    - Axios
    - ECharts
    - 微信小程序

## 五.配置文件修改

 ```yml
    sky:
       datasource:
         driver-class-name: com.mysql.cj.jdbc.Driver
         host: localhost
         port: 3306
         database: sky_take_out
         username: 本机账号
         password: 本机密码
    aliyun:   #阿里云配置,用于文件上传
        access-key-id: xxxxxxxxxxxxxxxxxxxx  #阿里云accessKey
        access-key-secret: xxxxxxxxxxxxxxxxxxxxxxxxxxxx #阿里云secretKey
        endpoint: oss-cn-beijing.aliyuncs.com  #阿里云endpoint
        bucket-name: xxxxxxxx  #阿里云bucketName
    redis:
        host: 192.168.100.128  #redis地址
        port: 6379  #redis端口
        password: xxxxxx  #redis密码
        database: 0
    wx:   #微信配置
       app-id: wxffb3637a228223b8 #微信公众号appid
       app-secret: 84311df9199ecacdf4f12d27b6b9522d  #微信公众号appsecret
       mchid: 1561414331  #微信商户号mchid
       mchSerialNo: 4B3B3DC35414AD50B1B755BAF8DE9CC7CF407606  #微信商户号密钥
       privateKeyFilePath:  D:\pay\apiclient_key.pem      #微信商户号私钥文件路径
       apiV3Key: CZBK51236435wxpay435434323FFDuv3         #微信商户号v3密钥
       weChatPayCertFilePath: D:\pay\wechatpay_166D96F876F45C7D07CE98952A96EC980368ACFC.pem   #微信商户号证书文件路径
       notifyUrl: http://xxxxxxxxx/notify/paySuccess  #微信支付成功回调地址(xxxx部分需要替换成使用本项目启动的cpolar软件中的域名)
       refundNotifyUrl: https://xxxxxxxxx/notify/refundSuccess  #微信退款成功回调地址(xxxx部分需要替换成使用本项目启动的cpolar软件中的域名)
    shop:
      address: 北京市朝阳区北苑东路11号院
    baidu:
      ak: xxxxxxxxxxxxxxxxxxx #百度地图ak(需要自己去百度地图开发平台申请应用获取ak)
   ```
## 六.项目演示

- 管理员端

工作台：
![工作台.png](assert/%E5%B7%A5%E4%BD%9C%E5%8F%B0.png)
数据统计：
![数据统计.png](assert/%E6%95%B0%E6%8D%AE%E7%BB%9F%E8%AE%A1.png)
订单管理：
![订单管理.png](assert/%E8%AE%A2%E5%8D%95%E7%AE%A1%E7%90%86.png)
套餐管理：
![套餐管理.png](assert/%E5%A5%97%E9%A4%90%E7%AE%A1%E7%90%86.png)
菜品管理：
![菜品管理.png](assert/%E8%8F%9C%E5%93%81%E7%AE%A1%E7%90%86.png)
分类管理：
![分类管理.png](assert/%E5%88%86%E7%B1%BB%E7%AE%A1%E7%90%86.png)
员工管理：
![员工管理.png](assert/%E5%91%98%E5%B7%A5%E7%AE%A1%E7%90%86.png)
- 用户端

![小程序首页.png](assert/%E5%B0%8F%E7%A8%8B%E5%BA%8F%E9%A6%96%E9%A1%B5.png)

![用户界面.png](assert/%E7%94%A8%E6%88%B7%E7%95%8C%E9%9D%A2.png)
