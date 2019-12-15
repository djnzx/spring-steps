## Spring Security 41. Secure your API.

as a basis we take `step38` app

we add `/api/login` with `POST` for logging in
we add `/api/logout` with `POST` for logging out
we add `/api/register` with `POST` for logging out


endpoints ready to test from step 38:

- http://localhost:8080/       - any unregistered
- http://localhost:8080/guest  - any unregistered
- http://localhost:8080/home   - any authenticated
- http://localhost:8080/admin  - authorized with ADMIN role
- http://localhost:8080/me     - authorized with USER role
- http://localhost:8080/news   - authorized with any USER or ADMIN role 
- full configuration will be finished in step 37 

endpoints created, but not configured yet:

- http://localhost:8080/api/login    - POST: authentication
- http://localhost:8080/api/logout   - POST: invalidation
- http://localhost:8080/api/register - POST: registration
- http://localhost:8080/api/guest  - GET - any unregistered
- http://localhost:8080/api/home   - GET - any authenticated
- http://localhost:8080/api/admin  - GET - authorized with ADMIN role
- http://localhost:8080/api/me     - GET - authorized with USER role
- http://localhost:8080/api/news   - GET - authorized with any USER or ADMIN role

classes created:

- classes `ApiController`, `AuthController`
- classes `dto/rq/LoginRq` and `dto/rs/LoginRs`
- classes `dto/rq/LogoutRq` and `dto/rs/LogoutRs`  
- classes `dto/rq/RegisterRq` and `dto/rs/RegisterRs` 
- class `dto/rs/ApiMessageRs`

configuration according `/api/*` added to `MyAppSecurityConfig` class
