package com.siakad.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrerequisiteNotMetExceptionTest {

    @Test
    @DisplayName("Test case exception yang dilempar ketika prasyarat mata kuliah tidak terpenuhi dengan message")
    void testPrerequisiteNotMetExceptionWithMessage() {
        String message = "Prerequisite not met";
        PrerequisiteNotMetException exception = new PrerequisiteNotMetException(message);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    @DisplayName("Test case exception yang dilempar ketika prasyarat mata kuliah tidak terpenuhi dengan message dan cause")
    void testPrerequisiteNotMetExceptionWithMessageAndCause() {
        String message = "Prerequisite not met";
        Throwable cause = new RuntimeException("Database error");
        PrerequisiteNotMetException exception = new PrerequisiteNotMetException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
