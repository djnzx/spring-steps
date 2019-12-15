All of these DTO must have

- empty constructor
- all setters

because they are being created (deserialized) by Spring (jackson.fasterxml)
and...

- all getters

because after deserialization we need to work with them
