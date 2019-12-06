# A step-by-step spring-boot guide

based on the info taken from the [spring.io/guides](https://spring.io/guides) 

and modified a bit according to questions being asked during the course

## Prerequisites

- Java Core
- Java 8
- OOP
- Maven
- ...
- a bit of a life experience and patience :)

## Content:

### step0

- minimal application in one file

### step01

- minimal application separated by folders

#### component marking:
* @Component
* @Controller
* @Service
* @ResponseBody
* @RestController
* @Autowired

#### request binding:
* @RequestMapping
* @PostMapping
* @PutMapping
* @PatchMapping
* @GetMapping

#### params binding:
* @PathVariable - "/users/add1/{name}"
* @RequestParam - "/users/add2?name=Johny"

### step02

* the same as step01 +
* resolving different implementation for one interface as a dependency
* @Primary
* @Bean
* @Qualifier
