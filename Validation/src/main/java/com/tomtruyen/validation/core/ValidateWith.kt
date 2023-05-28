package com.tomtruyen.validation.core

import kotlin.reflect.KClass

@Retention
@Target(AnnotationTarget.FIELD)
annotation class ValidateWith(vararg val validators: KClass<*>)