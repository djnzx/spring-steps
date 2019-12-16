## Spring Security 42. Secure your API. Stateless Authentication

this is full copy op `step41` plus

- `jjwt` dependency
- register implementation
- extra spring security configuration (exception handling)
- `UserDetailsService.loadById()`
- login implementation
- filter
- and everything else...

### Registration flow

- `POST` request to `/api/register`
- `JPA query` whether user already registered
- `JPA query` insert user

### Authorization flow

- `POST` request to `/api/login`
- `JPA query` to extract `user details` by username
- call to `Spring AuthenticationManager` and try to register user
- issue a `JWT token`
- return the `token` for further requests

### Request flow

- any `GET` / `POST` request to any endpoint 
- run the `Filter`
  - extract `token` from the `HTTP request` (parameter or header)
  - extract `user_id` from the `token`
  - `JPA query` to extract user details by `user_id`
  - call to `Spring AuthenticationManager` with `user details`
  - set current user in `SecurityContextHolder`
- passing `Spring Security` rules according to provided configuration
 
