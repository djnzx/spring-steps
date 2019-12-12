## Spring Security 34. Configuration class added. Users moved to separate class without proper encoding

endpoints for testing

- http://localhost:8080/       - any unregistered
- http://localhost:8080/guest  - any unregistered
- http://localhost:8080/home   - any authenticated
- http://localhost:8080/admin  - authorized with ADMIN role
- http://localhost:8080/me     - authorized with USER role
- http://localhost:8080/news   - authorized with any USER or ADMIN role 
- full configuration will be finished in step 37 

- dependency to `pom.xml` added:
```        
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
- configuration class `MySecurityConfig` created
- EVERYTHING is secured

- no any management
- http://localhost:8080/login  - login link added by spring security
- http://localhost:8080/logout - logout link added by spring security

- class `UserDetailsHashMap` added
- users moved to separate class, still without proper password encoding
    - user `jim` password `123`
    - user `john` password `234`
