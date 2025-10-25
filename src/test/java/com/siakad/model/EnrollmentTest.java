package com.siakad.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class EnrollmentTest {
    private Enrollment enrollment;
    private LocalDateTime date;

    @BeforeEach
    void setUp() {
        date = LocalDateTime.of(2025, 10, 25, 11, 30, 0);
        enrollment = new Enrollment(
                "ER001",
                "ST001",
                "CS001",
                date,
                "PENDING");
    }

    @Test
    @DisplayName("Test case untuk konstruktor tanpa aprameter")
    void testEnrollmentWithNoParameter() {
        Enrollment enrollment1 = new Enrollment();

        assertNull(enrollment1.getEnrollmentId());
        assertNull(enrollment1.getStudentId());
        assertNull(enrollment1.getCourseCode());
        assertNull(enrollment1.getEnrollmentDate());
        assertNull(enrollment1.getStatus());
    }

    @Test
    @DisplayName("Test case untuk konstruktor dengan parameter")
    void testEnrollmentWithParameters() {
        assertEquals("ER001", enrollment.getEnrollmentId());
        assertEquals("ST001", enrollment.getStudentId());
        assertEquals("CS001", enrollment.getCourseCode());
        assertEquals(date, enrollment.getEnrollmentDate());
        assertEquals("PENDING", enrollment.getStatus());
    }

    @Test
    @DisplayName("Test case untuk Getter dan Setter")
    void testGetterAndSetter() {
        LocalDateTime date1 = LocalDateTime.of(2025, 10, 26, 23, 59, 59);

        enrollment.setEnrollmentId("ER009");
        enrollment.setStudentId("ST009");
        enrollment.setCourseCode("CS009");
        enrollment.setEnrollmentDate(date1);
        enrollment.setStatus("APPROVED");

        assertEquals("ER009", enrollment.getEnrollmentId());
        assertEquals("ST009", enrollment.getStudentId());
        assertEquals("CS009", enrollment.getCourseCode());
        assertEquals(date1, enrollment.getEnrollmentDate());
        assertEquals("APPROVED", enrollment.getStatus());
    }
}
