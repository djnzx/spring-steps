## Spring 23B. Extra tables. Relations 1:N. Extra (3rd) Table Join Approach 

- 1:N mapping
- one `main entity` with unique `PK`
- one `related entity` with unique `PK`
- `main entity` **does not contain** extra filed to reference on `related entity`
- `related entity` **does not contain** extra filed to reference on `main entity`
- **1** `main entity` can have relation with **N** `related entity`
- we **do have** 3-rd table to handle relations between `main entity` and `related entity`
- in our example `main entity` is a `Person`, related entity is a `Phone`. **One** `Person` can have **several** `Phone`
