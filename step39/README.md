## Spring Security 39. The same as 38. plus extra things explained and added in `MySecurityConfig.configure(http)`

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
```        
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
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
  - now - unused
- class `UserDetailsJPA` added, JPA implementation with password encoding
  - now - unused
- class `UserDetailsServiceJPA` added, JPA as clean implementation of `UserDetailsService` based on `UserDetailsJPA`
  - user `jim`  password `123`
  - user `john` password `234`
  - user `jack` password `345`
  - user `joe`  password `456`

- we got rid of `@Bean` annotation in the `UserDetailsHashMap` file because we need only one bin with the type `UserDetailsService`
- we got rid of `@Bean` annotation in the `UserDetailsHashMapEncoded` file because we need only one bin with the type `UserDetailsService`
- we got rid of `@Bean` annotation in the `UserDetailsJPA` file because we need only one bin with the type `UserDetailsService`
- we set annotation `@Bean` on appropriate method in the `UserDetailsServiceJPA` class
- class `DbUsers` added (Entity)
- class `DbUsersRepo` added (JPA CrudRepository)
- class `DbUsersInitial` - creating initial users
- class `MyDelegatedEncoder` - factory of all possible encoders

- method `MySecurityConfig.configure(http)` added
