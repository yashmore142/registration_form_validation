package com.example.validation

sealed class RegistrationEvent {

    data class Name(val name: String) : RegistrationEvent()
    data class Email(val email: String) : RegistrationEvent()
    data class Number(val number: String) : RegistrationEvent()


    object SaveClick : RegistrationEvent()
}