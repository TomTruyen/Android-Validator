package com.tomtruyen.validation.core

import java.lang.reflect.Field

interface IValidator<T> {
    fun validate(field: Field, value: T): IValidationError?
}