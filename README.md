# springboot-layui-generator

本项目基于springBoot2.0,用户只需简单配置即可生成一个增删改查的应用的基础代码

#开发笔记

基础模板后端为springmvc+swagger restful api风格
前端模板为 layui风格
支持扩展

#使用技术

1. springBoot
2. springMvc
3. swagger-ui
4. freemarker
5. lombok
6. tk通用mapper
7. thymeleaf
8. slf4j
9. javaparser
10. 前端框架 layui

#生成的代码结构

- html页面（包含列表页、编辑页）
- Controller.java后端控制层
- Service.java 后端服务层
```
生成的Service和Controller对大部分基础方法进行了封装，对于用户自定义的功能实现，可以按照sprngmvc的方式自行新增接口进行实现
Mapper、model和Mapper.xml文件使用mybatis-generator生成,在我的另一个项目spring-layui中有实现案例
```
#操作指南

cmd切换到springboot-layui-generator项目目录，安装mvn相关依赖(以下为使用案例,具体路径以本地为准)
 >   cd D:\work\springboot-layui-generator
 >   mvn clean install
 >   mvn spring-boot:run

到此生成完毕，就是这么简单

#application.yml配置说明

```
crudgen:
  project:
    java-project-path: F:\workspace\demo #生成的代码所在的工程路径 demo为工程名称
    view-project-path: F:\workspace\demo #视图生成所在的工程路径 demo为工程名称
  template-path: /templates/layui  #模板路径  默认为本项目 resources/templates/layui下
  controller-enabled: true #是否生成controller
  service-enabled: true #是否生成service服务
  view-enabled: true #是否生成视图 默认thymeleaf html
  edit-view-enabled: true #是否生成编辑视图 默认thymeleaf html
  model-attributes:
    - model-name: RssInsideSuper #model名称 支持配置多张表 参考yml数组配置
      extend-attr-map: #自定义扩展属性
        author: wubing
      java-attributes:
        model-package: com.example.data #model包路径
        service-package: com.example.service #service包路径
        controller-package: com.example.controller #controller包路径
        controller-request-mapping: /rssinsidesuper #controller requestMapping路径
      view-attributes:
              view-path: jcy/rssinsidesuper #html等视图文件相对路径
#    - model-name: Tuser #model名称 支持配置多张表 参考yml数组配置
#      extend-attr-map: #自定义扩展属性
#        author: wubing
#      java-attributes:
#        model-package: com.example.data #model包路径
#        service-package: com.example.service #service包路径
#        controller-package: com.example.controller #controller包路径
#        controller-request-mapping: /tuser #controller requestMapping路径
#      view-attributes:
#              view-path: jcy/tuser #html等视图文件相对路径
```

#待实现功能
1. 可视化页面配置
2. vue模板实现