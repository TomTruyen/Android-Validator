package com.tomtruyen.validator.models

import com.tomtruyen.validation.core.ValidateWith
import com.tomtruyen.validator.validators.EmailValidator
import com.tomtruyen.validator.validators.RequiredValidator

data class User(
    @ValidateWith(RequiredValidator::class, EmailValidator::class) var email: String = "",
)