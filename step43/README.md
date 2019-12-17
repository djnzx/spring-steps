## Spring Security 43. Secure your API. Stateless Authentication + Static resources (css, img, js, etc) mappings

this is the exact copy of `step42` plus:

- we got rid of MVC part
- we added folders `classpath:/static/css`, `classpath:/static/js`, `classpath:/static/img`, `classpath:/static/html` to the server mappings `/css/**`, `/js/**`, `/img/**`, `/html/**` respectively 
- see `app/mvc/StaticResourcesConfig` for details
- class `app/security/MyAppSecurityConfig` polished a bit

see the output by using the links:
- http://localhost:8080/img/forever.png
- http://localhost:8080/js/bootstrap.js
- http://localhost:8080/css/bootstrap.css
- http://localhost:8080/html/1.html

API authorization part accessible by `POST` requests, and located at:
- http://localhost:8080/api/register
- http://localhost:8080/api/login
- http://localhost:8080/api/logout

API endpoints accessible by `GET` requests (by providing auth token), and located at:
- http://localhost:8080/api/guest - any unregistered (without any authentication)
- http://localhost:8080/api/home  - any authenticated
- http://localhost:8080/api/admin - any authorized with ADMIN role
- http://localhost:8080/api/me    - any authorized with USER role
- http://localhost:8080/api/news  - any authorized with any USER or ADMIN role
 
