package com.mobilize.BookSystem.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.time.DateTimeException;
import java.time.Year;

import com.mobilize.BookSystem.exception.InvalidYearException;

/**
 * A custom validator for the YearConstraint annotation.
 * This validator checks if the provided integer value represents a valid year
 * and whether it is before or equal to the current year.
 */
public class YearConstraintValidator implements ConstraintValidator<YearConstraint, Integer> {

	/**
	 * Initializes the validator.
	 *
	 * @param constraintAnnotation The YearConstraint annotation to be initialized.
	 */
	@Override
	public void initialize(YearConstraint constraintAnnotation) {
	}

	/**
	 * Validates whether the provided integer value is a valid year and is before or equal to the current year.
	 *
	 * @param value    The integer value representing the year to be validated.
	 * @param context  The validation context.
	 * @return True if the value is a valid year and meets the validation criteria; false otherwise.
	 * @throws InvalidYearException If the input value cannot be converted to a Year object or is greater than the current year.
	 */
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if (value == null) {
			return true; // Null values are handled by @NotNull annotation
		}

		try {
			Year currentYear = Year.now();
			Year inputYear = Year.of(value);

			return inputYear.isBefore(currentYear) || inputYear.equals(currentYear);
		} catch (DateTimeException e) {
			// Handle the exception by returning false for validation failure
			throw new InvalidYearException("Publication year cannot be greater than the current year.");
		}
	}
}










