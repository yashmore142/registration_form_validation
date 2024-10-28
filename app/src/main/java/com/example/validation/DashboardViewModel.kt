package com.example.validation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {
    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]{2,4}+"
    private val _requestBodyRegistration: MutableLiveData<RequestBodyRegistration> =
        MutableLiveData(
            RequestBodyRegistration()
        )
    private val requestBodyRegistration: LiveData<RequestBodyRegistration> =
        _requestBodyRegistration

    private val _responseBodyRegistration: MutableLiveData<ValidationResult> =
        MutableLiveData(
            ValidationResult()
        )
    val responseBodyRegistration: LiveData<ValidationResult> =
        _responseBodyRegistration

    var errorName: MutableLiveData<ValidationResult> = MutableLiveData(ValidationResult())
    var errorEmail: MutableLiveData<ValidationResult> = MutableLiveData(ValidationResult())
    var errorNumber: MutableLiveData<ValidationResult> = MutableLiveData(ValidationResult())

    fun onRegistrationEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.Name -> {
                _requestBodyRegistration.value = _requestBodyRegistration.value?.copy(
                    name = event.name
                )
                nameValidation()
            }

            is RegistrationEvent.Email -> {
                _requestBodyRegistration.value = _requestBodyRegistration.value?.copy(
                    email = event.email
                )
                emailValidation()
            }

            is RegistrationEvent.Number -> {
                _requestBodyRegistration.value = _requestBodyRegistration.value?.copy(
                    number = event.number
                )
                numberValidation()
            }

            RegistrationEvent.SaveClick -> {
                if (errorName.value?.status!! && errorEmail.value?.status!! && errorNumber.value?.status!!) {
                    _responseBodyRegistration.value!!.copy(
                        status = true,
                        errorMessage = "Now Api Call"
                        )
                    ///api call
                } else {
                    nameValidation()
                    numberValidation()
                    emailValidation()
                }
            }
        }

    }

    private fun numberValidation() {
        errorNumber.value =
            Validator.numberValidation(_requestBodyRegistration.value?.number.toString())
    }

    private fun emailValidation() {
        errorEmail.value =
            Validator.emailValidation(_requestBodyRegistration.value?.email.toString())

    }

    private fun nameValidation() {
        errorName.value =
            Validator.userNameValidator(_requestBodyRegistration.value?.name.toString())

    }
}