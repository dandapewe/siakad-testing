package com.siakad.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnrollmentExceptionTest {

    @Test
    @DisplayName("Test case exception yang dilempar ketika terjadi kesalahan dalam proses enrollment dengan message")
    void testEnrollmentExceptionWithMessage() {
        String message = "Enrollment failed";
        EnrollmentException exception = new EnrollmentException(message);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    @DisplayName("Test case exception yang dilempar ketika terjadi kesalahan dalam proses enrollment dengan message dan cause")
    void testEnrollmentExceptionWithMessageAndCause() {
        String message = "Enrollment failed";
        Throwable cause = new RuntimeException("Database error");
        EnrollmentException exception = new EnrollmentException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
