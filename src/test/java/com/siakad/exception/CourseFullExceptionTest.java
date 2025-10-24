package com.siakad.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourseFullExceptionTest {

    @Test
    @DisplayName("Uji exception dengan tidak ada cause")
    void testCourseFullExceptionWithMessage() {
        String message = "Course is full";
        CourseFullException exception = new CourseFullException(message);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    @DisplayName("Uji exception dengan ada cause")
    void testCourseFullExceptionWithMessageAndCause() {
        String message = "Course is full";
        Throwable cause = new RuntimeException("Database error");
        CourseFullException exception = new CourseFullException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}