package com.example.place

fun validateEmail(email: String): EmailValidate {
    val emailRegex = Regex("^[A-Za-z](.*)(@)(.+)(\\.)(.+)")
    return if (email.isEmpty()) {
        EmailValidate.EMPTY
    } else if (!email.matches(emailRegex)) {
        EmailValidate.BAD_ADDRESS
    } else {
        EmailValidate.OK
    }
}

enum class EmailValidate {
    EMPTY, BAD_ADDRESS, OK
}


fun validatePassword(password: String): PasswordValidate {
    return if (password.isEmpty()) {
        PasswordValidate.EMPTY
    } else if (password.length < 6) {
        // パスワードは7文字以上ないといけない
        PasswordValidate.TOO_SHORT
    } else {
        PasswordValidate.OK
    }
}

enum class PasswordValidate {
    EMPTY, TOO_SHORT, OK
}