## The step-by-step guide for SpringBoot and Spring 

Initially based on the students' questions during conducting Java BootCamp in [IBA Tech Academy](https://ibatech.az/en/#about)

### Prerequisites

- Java Core
- Java 8
- OOP
- Maven
- ...
- a bit of a life experience and patience :)

### Content

- please see corresponding `readme.md` files in appropriate sub-folders
- step0 :: intro to spring
- step01 :: request mapping
- step02 :: thymeleaf template engine
- step03 :: parameters binding (request param, request body, path fragment)

...

- step05 :: application structure, dependency injection 3 different ways
- step06 :: consuming POST
  - Params
  - Http Forms
  - JSON body
  - File Uploading
  - File Downloading
  
...

- step08 :: redirect different ways, passing data

...

- step10 :: Heroku deployment (actually separate project)
- step11 :: Exceptions handling

...

- step13 :: HttpSession #1 - Basics + Attributes + Spring.
- step14 :: HttpSession #2 - Real Word example.

...

- step15 :: spring MVC + spring data. basic example

...

- step21 :: spring data. basics. intro
- step22 :: spring data. relations. 1:1 (**1** `Person` entity has a relation with **1** `Extra` entity)
- step22a :: implementation via `FK`
- step22b :: implementation via `shared PK`
- step22c :: implementation via `3rd table`
- step23 :: spring data. relations. 1:N (**1** `Person` entity has a relation with **N** `Phone` entity)
- step23a :: implementation via `FK`
- step23b :: implementation via `3rd table`
- step24 :: spring data. relations. M:N (**M** `Author` entities have a relations with **N** `Book` entities)
implementation via `3rd table`
- step24a :: bidirectional (authors >-< books) via `one` reference table
- step24b :: unidirectional (author -< books + authors >- book) via `two` reference tables
- step24d :: spring data. relations. M:N. working with data. (insertion data/relation, deletion data/relations)
- step24e :: spring data. relations. M:N. working with data. (data selection)

...

- step31 :: spring security. intro 
- step32 :: spring security. dependency
- step33 :: spring security. users hardcoded
- step34 :: spring security. users in the class
- step35 :: spring security. users in the class. password encoded
- step36 :: spring security. users in the database
- step37 :: spring security. users in the database without fetching all by implementing `UserDetailsService`
- step38 :: spring security. authorization configuration
- step39 :: spring security. extra configuration examples
- step41 :: spring security. Secure your API. basic idea
- step42 :: spring security. Secure your API. Stateless implementation base on JWT-token
- step43 :: spring security. Secure your API. get rid of MVC part + static resources mappings

### Links

- Excellent resource: [Vlad Mihalcea's Blog](https://vladmihalcea.com/postgresql-serial-column-hibernate-identity/)
- Cool tool: [Vlad CLI](https://maciejwalkowiak.com/blog/how-i-built-vlad-cli/)
- Spring official guides: [spring.io/guides](https://spring.io/guides)
- Baeldung site with enormous amount of useful information: [www.baeldung.com](https://www.baeldung.com)
- Official repositories to book **Spring in Action 5**: [github.com/habuma](https://github.com/habuma/spring-in-action-5-samples.git)
