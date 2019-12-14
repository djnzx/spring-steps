## Spring 02. Thymeleaf template engine (server-side rendering)

endpoint for testing

- http://localhost:8080/tl     

## Syntax to refer to the variables:

- `${x}` will return a variable x stored into the Thymeleaf context or as a request attribute. 
- `${param.x}` will return a request parameter called x (which might be multivalued). 
- `${session.x}` will return a session attribute called x .
- `${application.x}` will return a servlet context attribute called x .
- `${@myBean.doSomething()}`
- `@{/styles.css}` will be replaced with `path.prefix`, for ex: `th:src="@{/images/1.jpg}"`
- `...`
- `${#mvc.uri}`
- `${#themes.code}`
- `${#fields.hasErrors}`

### working with tag body:
- `th:text` - will replace tag body.
- `th:utext`  

`<p th:text="${user.name}"></p>` will become `<p>Alex</p>` implying that `user` object has field `name` and it is set to `"Alex"` from Java code and `model.addAttribute("user", user)` is called prior the `return "viewName"` statement

### working with HTML fragments:
- `th:fragment`
- `th:replace`
- `th:insert`
- `th:remove`

### iteration / conditional:
- `th:each`
- `th:if`

### working with other tag properties:
- `th:with`
- `th:value`
- `th:href`
- `th:src`
- `th:alt`
- `th:class`
- `th:object`
- `th:action`

### interop with other Spring components:
- `th:errors`
- `th:errorclass`
- `th:field`
