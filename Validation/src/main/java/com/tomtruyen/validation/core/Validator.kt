package com.tomtruyen.validation.core

import org.apache.commons.lang3.reflect.FieldUtils
import java.lang.reflect.Field

object Validator {
    fun validate(value: Any?): List<IValidationError> {
        if(value == null) throw IllegalArgumentException("Can't validate a null value")
        val errors = mutableListOf<IValidationError>()
        getFields(value.javaClass).forEach { field: Field ->
            if(field.isAnnotationPresent(ValidateWith::class.java)) {
                val annotation = field.getAnnotation(ValidateWith::class.java)
                if (annotation != null) {
                    errors += validateFields(field, FieldUtils.readField(field, value, true), annotation)
                }
            }
        }
        return errors
    }

    private fun getFields(classRef: Class<*>): List<Field> {
        return classRef.declaredFields.toList()
    }

    @Suppress("UNCHECKED_CAST")
    private fun validateFields(field: Field, value: Any, annotation: ValidateWith): List<IValidationError> {
        val validators = annotation.validators.map { validatorClass ->
            validatorClass.java.getDeclaredConstructor().newInstance() as IValidator<Any>
        }

        val errors = mutableListOf<IValidationError>()
        for(validator in validators) {
            val result = validator.validate(field, value)
            if(result != null) {
                errors += result
            }
        }

        return errors
    }
}