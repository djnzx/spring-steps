## Spring 24d. M:N. Working with data 

- M:N mapping
- one `main entity` with unique `PK`
- one `related entity` with unique `PK`
- `main entity` **does not contain** extra filed to reference on `related entity`
- `related entity` **does not contain** extra filed to reference on `main entity`
- **any number** of `main entity` can have **any number** of relations with `related entity`
- in our example we will stick to example `Author` - `Book`
- **one** `Author` can have **any number** of `Book` written
- **one** `Book` can be written by **any number** of `Author`
- we **do have** 3-rd table to handle relations between `main entity` and `related entity`

see the `app/beans/AutoRun.java` how to work with such kind of entities
