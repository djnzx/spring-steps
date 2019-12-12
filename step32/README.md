## Template for spring security steps (31-38)

endpoints for testing

- http://localhost:8080/       - any unregistered
- http://localhost:8080/guest  - any unregistered
- http://localhost:8080/home   - any authenticated
- http://localhost:8080/admin  - authorized with ADMIN role
- http://localhost:8080/me     - authorized with USER role
- http://localhost:8080/news   - authorized with any USER or ADMIN role 

- dependency to `pom.xml` added:
```        
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
- EVERYTHING is secured with user `user` and password like `9ff592b8-6c05-4529-8ef4-6063bcc0b1ba` provided in console log:
```
Using generated security password: 9ff592b8-6c05-4529-8ef4-6063bcc0b1ba
```
- no any management
- the password is being changed every time after restarting the application
- http://localhost:8080/login  - login link added by spring security
- http://localhost:8080/logout - logout link added by spring security
