package com.example.place

import org.junit.Assert.assertEquals
import org.junit.Test

class LoginHelperKtTest {

    @Test
    fun validateEmail_return_EMPTY_when_email_is_empty() {
        assertEquals(EmailValidateStatus.EMPTY, validateEmail(""))
    }

    @Test
    fun validateEmail_return_BAD_ADDRESS_when_email_is_wrong() {
        assertEquals(EmailValidateStatus.BAD_ADDRESS, validateEmail("a@a"))
        assertEquals(EmailValidateStatus.BAD_ADDRESS, validateEmail("a.com"))
        assertEquals(EmailValidateStatus.BAD_ADDRESS, validateEmail("@"))
    }

    @Test
    fun validateEmail_return_OK_when_email_is_like_right() {
        assertEquals(EmailValidateStatus.OK, validateEmail("a@a.com"))
    }

    @Test
    fun validatePassword_return_EMPTY_when_password_is_empty() {
        assertEquals(PasswordValidateStatus.EMPTY, validatePassword(""))
    }

    @Test
    fun validatePassword_return_EMPTY_when_password_is_TOO_SHORT() {
        //6文字はだめ
        assertEquals(PasswordValidateStatus.TOO_SHORT, validatePassword("123456"))
        //7文字からOK
        assertEquals(PasswordValidateStatus.OK, validatePassword("123456"))
    }

    @Test
    fun validatePassword_return_OK_when_password_is_like_right() {
        //7文字からOK
        assertEquals(PasswordValidateStatus.OK, validatePassword("1234567"))
    }
}