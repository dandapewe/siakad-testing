package com.siakad.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourseFullExceptionTest {

    @Test
    void testCourseFullExceptionWithMessage() {
        String message = "Course is full";
        CourseFullException exception = new CourseFullException(message);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testCourseFullExceptionWithMessageAndCause() {
        String message = "Course is full";
        Throwable cause = new RuntimeException("Database error");
        CourseFullException exception = new CourseFullException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}