package com.example.validation

object Validator {
    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]{2,4}+"

    fun userNameValidator(name: String): ValidationResult {
        return ValidationResult(
            status = (name.isNotEmpty() && name.length >= 3),
            errorMessage = "Please Enter Valid User name"
        )
    }

    fun numberValidation(number: String): ValidationResult {
        return ValidationResult(
            status = (number.isNotEmpty() && number.length == 10),
            errorMessage = "Please Enter Valid number"
        )
    }

    fun emailValidation(email: String): ValidationResult {
        return if (!email.matches(Regex(emailPattern))) {
            ValidationResult(
                status = false,
                errorMessage = "Enter a valid Email"
            )
        } else {
            ValidationResult(
                    status = true
            )
        }
    }
}

data class ValidationResult(
    val status: Boolean = false,
    val errorMessage: String = "",
)