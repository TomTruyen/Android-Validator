package com.tomtruyen.validator.validators

import com.tomtruyen.validation.core.IValidationError
import com.tomtruyen.validation.core.IValidator
import com.tomtruyen.validation.core.ValidationError
import com.tomtruyen.validator.Constants
import java.lang.reflect.Field

class EmailValidator: IValidator<String> {
    override fun validate(field: Field, value: String): IValidationError? {
        return if(value.matches(Regex(Constants.EMAIL_REGEX))) {
            null
        } else {
            ValidationError(
                error = "${field.name} field is not a valid email",
                field = field
            )
        }
    }
}