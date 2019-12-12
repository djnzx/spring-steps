## Spring Security 33. Configuration class added. Users hardcoded

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
- EVERYTHING is secured with users hardcoded in `MySecurityConfig` class
    - user `jim` password `123`
    - user `john` password `234`

- no any management
- the password is being changed every time after restarting the application
- http://localhost:8080/login  - login link added by spring security
- http://localhost:8080/logout - logout link added by spring security

- configuration class added
- users hardcoded
