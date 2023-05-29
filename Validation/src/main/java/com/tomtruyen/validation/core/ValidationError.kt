package com.tomtruyen.validation.core

import java.lang.reflect.Field

data class ValidationError(
    override var error: String,
    override var field: Field? = null
): IValidationError