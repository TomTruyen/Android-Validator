package com.tomtruyen.validation.core

data class ValidationError(
    override var error: String
): IValidationError