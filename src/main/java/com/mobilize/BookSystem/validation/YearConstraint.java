package com.mobilize.BookSystem.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


/**
 * A custom validation constraint annotation for checking the validity of a year value.
 * This annotation is associated with the YearConstraintValidator class for validation.
 *
 * Usage Example:
 *   @YearConstraint(message = "Invalid year or year exceeds current year")
 *   private int publicationYear;
 *
 * The annotation can be applied to fields or methods to enforce year validation rules.
 */
@Documented
@Constraint(validatedBy = YearConstraintValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface YearConstraint {
	/**
	 * Specifies the error message to be displayed when the validation fails.
	 *
	 * @return The error message for invalid year values.
	 */
	String message() default "Invalid year or year exceeds current year";

	/**
	 * Specifies validation groups, if any. Default is an empty array.
	 *
	 * @return An array of validation groups.
	 */
	Class<?>[] groups() default {};

	/**
	 * Specifies payload classes for extending metadata about the validation.
	 * Default is an empty array.
	 *
	 * @return An array of payload classes.
	 */
	Class<? extends Payload>[] payload() default {};
}
