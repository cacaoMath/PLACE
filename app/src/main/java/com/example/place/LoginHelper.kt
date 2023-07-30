package com.example.place

fun validateEmail(email: String): EmailValidateStatus {
    val emailRegex = Regex("^[A-Za-z](.*)(@)(.+)(\\.)(.+)")
    return if (email.isEmpty()) {
        EmailValidateStatus.EMPTY
    } else if (!email.matches(emailRegex)) {
        EmailValidateStatus.BAD_ADDRESS
    } else {
        EmailValidateStatus.OK
    }
}

enum class EmailValidateStatus {
    EMPTY, BAD_ADDRESS, OK
}


fun validatePassword(password: String): PasswordValidateStatus {
    return if (password.isEmpty()) {
        PasswordValidateStatus.EMPTY
    } else if (password.length < 7) {
        // パスワードは7文字以上ないといけない
        PasswordValidateStatus.TOO_SHORT
    } else {
        PasswordValidateStatus.OK
    }
}

enum class PasswordValidateStatus {
    EMPTY, TOO_SHORT, OK
}