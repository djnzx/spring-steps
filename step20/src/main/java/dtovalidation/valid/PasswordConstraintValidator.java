package dtovalidation.valid;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

  private final List<Rule> rules = Arrays.asList(
    new LengthRule(8, 1024),
    new CharacterRule(EnglishCharacterData.UpperCase, 1),
    new CharacterRule(EnglishCharacterData.LowerCase, 1),
    new CharacterRule(EnglishCharacterData.Digit, 1),
    new WhitespaceRule()
  );

  private PasswordValidator validator;

  @Override
  public void initialize(ValidPassword constraintAnnotation) {
    this.validator = new PasswordValidator(rules);
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext ctx) {
    RuleResult validated = validator.validate(new PasswordData(value));

    if (validated.isValid()) {
      return true;
    } else {
      List<String> messages = validator.getMessages(validated);

      String message = String.join(",", messages);

      ctx.buildConstraintViolationWithTemplate(message)
        .addConstraintViolation()
        .disableDefaultConstraintViolation();
      return false;
    }

  }
}
