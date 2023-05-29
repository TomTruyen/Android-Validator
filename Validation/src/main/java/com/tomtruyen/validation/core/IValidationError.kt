package com.tomtruyen.validation.core

import java.lang.reflect.Field

interface IValidationError {
    val error: String
    val field: Field?
}