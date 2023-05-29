
# Android Validator

A simple validator for your objects and field using Annotations. This library can be used in both Jetpack Compose and XML Android projects. 


## Installation

Add the Jitpack dependency to your module's `build.gradle` or `settings.gradle` 
```groovy
maven { url 'https://jitpack.io' }
```

Add the library to your app's `build.gradle` 

```groovy
implementation 'com.github.TomTruyen:Android-Validator:<Latest-Version>'
```
    
## Usage/Examples

As the user of this library you have the liberty to choose when and what you want to validate. You can for example validate on the click of a button or when the text in an input field changes. Furthermore you can choose what fields you want to validate which is perfect for when validating on input field changes e.g.: when typing in the email input field you would only want this field to be validated, other fields should not be validated yet at that point. Where as when you press a button you most likely would want all fields to be validated

### Create a validator
```kotlin
// The type passed to the generic IValidator should be the type of your fields value
class MyEmailValidator: IValidator<String> {
    override fun validate(field: Field, value: String): IValidationError? {
        // Your validation logic
        return if(value.matches(Regex(Constants.EMAIL_REGEX))) {
            // Validation successful
            null
        } else {
            // Validation failed. Return the error message and what field caused it
            ValidationError(
                error = "${field.name} field is not a valid email",
                field = field // this is optional
            )
        }
    }
}
```

### Use the validator in your objects
```kotlin
data class User(
  @ValidateWith(MyEmailValidator::class) val email: String,
  // You can also use multiple validators on a single field
  @ValidateWith(MyValidator1::class, MyValidator2::class, MyValidator3::class) val password: String
)
```

### Validate a single field
```kotlin
val invalidEmailUser = User("This is not a valid email")

myButton.setOnClickListener {
  val validationErrors = Validator.validateField(user, User::email.name)

  if(validationErrors == null) {
    // No errors --> Continue
  } else {
    // Validation error - Get all errors
    val errorMessages = validationErrors.map { it.error }
    println(errorMessages)
  }
}
```

### Validate a selection of fields
```kotlin
val invalidEmailUser = User("This is not a valid email")

myButton.setOnClickListener {
  val validationErrors = Validator.validateFields(user, arrayOf(User::email.name, User::password.name))

  if(validationErrors == null) {
    // No errors --> Continue
  } else {
    // Validation error - Get all errors
    val errorMessages = validationErrors.map { it.error }
    println(errorMessages)
  }
}
```

### Validate all fields
```kotlin
val invalidEmailUser = User("This is not a valid email")

myButton.setOnClickListener {
  val validationErrors = Validator.validate(user)

  if(validationErrors == null) {
    // No errors --> Continue
  } else {
    // Validation error - Get all errors
    val errorMessages = validationErrors.map { it.error }
    println(errorMessages)
  }
}
```
## License

[MIT](https://choosealicense.com/licenses/mit/)

