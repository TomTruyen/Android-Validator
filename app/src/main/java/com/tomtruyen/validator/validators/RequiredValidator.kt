package com.tomtruyen.validator.validators

import com.tomtruyen.validation.core.IValidator
import com.tomtruyen.validation.core.IValidationError
import com.tomtruyen.validation.core.ValidationError
import java.lang.reflect.Field

class RequiredValidator: IValidator<String> {
    override fun validate(field: Field, value: String): IValidationError? {
        return if(value.isNotBlank() && value.isNotEmpty()) {
            null
        } else {
            ValidationError(
                error = "${field.name} field is required",
                field = field
            )
        }
    }
}