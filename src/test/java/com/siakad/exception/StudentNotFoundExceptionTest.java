package com.siakad.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentNotFoundExceptionTest {

    @Test
    @DisplayName("Test case exception yang dilempar ketika mahasiswa tidak ditemukan dengan message")
    void testStudentNotFoundExceptionWithMessage() {
        String message = "Student not found";
        StudentNotFoundException exception = new StudentNotFoundException(message);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    @DisplayName("Test case exception yang dilempar ketika mahasiswa tidak ditemukan dengan message dan cause")
    void testStudentNotFoundExceptionWithMessageAndCause() {
        String message = "Student not found";
        Throwable cause = new RuntimeException("Database error");
        StudentNotFoundException exception = new StudentNotFoundException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
