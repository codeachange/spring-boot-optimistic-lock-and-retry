### Optimistic Lock

#### 给Entity添加version属性（使用@Version注解），会自动开启乐观锁

```java
@Document
public class User {
    @Id
    private String id;
    private String name;
    private Integer age;
    private Date createTime;
    private Date updateTime;
    @Version
    private Integer _version;
    
    // ...
}
```

### Retry

#### pom.xml

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.retry</groupId>
    <artifactId>spring-retry</artifactId>
</dependency>
```

#### 需要重试的方法加 @Retryable 注解

```java
@Service
public class UserService {
    @Retryable(OptimisticLockingFailureException.class)
    public void updateWithRetry() {
        System.out.println("updateWithRetry called.");
        User user = this.getOrCreateUser();
    
        User sameUser = this.getOrCreateUser();
        sameUser.setName("Another Tom Cat");
        sameUser.setUpdateTime(new Date());
        userRepository.save(sameUser);
    
        user.setAge(12);
        userRepository.save(user);
    }
}
```

