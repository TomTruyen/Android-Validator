package com.tomtruyen.validator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tomtruyen.validation.core.Validator
import com.tomtruyen.validator.models.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userInvalid = User()
        Log.d("@@@", "User Invalid: ${Validator.validate(userInvalid)}")

        val validUser = User("tom.truyen@gmail.com")
        Log.d("@@@", "User Valid: ${Validator.validate(validUser)}")
    }
}