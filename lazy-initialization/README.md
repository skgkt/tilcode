# Lazy Initialization in Spring Boot

Spring Boot 2.2.0 よりアプリケーション内のすべての Bean に対して遅延初期化を設定できるようになりました。

`application.yml` :

```yaml
spring:
  main:
    lazy-initialization: true
```

参考: [Lazy Initialization in Spring Boot 2.2 | Baeldung](https://www.baeldung.com/spring-boot-lazy-initialization)
