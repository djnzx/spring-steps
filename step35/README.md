## Spring Security 35. Configuration class added. Users moved to separate class with proper encoding

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

- class `UserDetailsHashMap` added, still without proper password encoding
  - now - unused
- class `UserDetailsHashMapEncoded` added, with password encoding
  - user `jim` password `123`
  - user `john` password `234`
- we got rid of `@Bean` annotation in the `UserDetailsHashMap` file because we need only one bin with the type `UserDetailsService`
- we set annotation `@Bean` on appropriate method in the `UserDetailsHashMapEncoded` class
