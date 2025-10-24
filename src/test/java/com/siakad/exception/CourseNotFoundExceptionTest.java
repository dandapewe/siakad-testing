package com.siakad.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CourseNotFoundExceptionTest {

    @Test
    @DisplayName("Test case exception yang dilempar ketika mata kuliah tidak ditemukan dengan message")
    void testCourseNotFoundExceptionTestWithMessage() {
        String message = "Course is not found";
        CourseNotFoundException exception = new CourseNotFoundException(message);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    @DisplayName("Test case exception yang dilempar ketika mata kuliah tidak ditemukan dengan message dan cause")
    void testCourseNotFoundExceptionTestWithMessageAndCause() {
        String message = "Course is not found";
        Throwable cause = new RuntimeException("Databse error");
        CourseNotFoundException exception = new CourseNotFoundException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
