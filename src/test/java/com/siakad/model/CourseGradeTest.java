package com.siakad.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseGradeTest {
    private CourseGrade grade;

    @BeforeEach
    void setUp() {
        grade = new CourseGrade(
                "CS001",
                2,
                4.0);
    }

    @Test
    @DisplayName("Test case konstruktor tanpa parameter")
    void testCourseGradeNoParameter() {
        CourseGrade grade1 = new CourseGrade();

        assertNull(grade1.getCourseCode());
        assertEquals(0, grade1.getCredits());
        assertEquals(0.0, grade1.getGradePoint());
    }

    @Test
    @DisplayName("Test case konstruktor dengan parameter")
    void testCourseGradeWithParameters() {
        assertEquals("CS001", grade.getCourseCode());
        assertEquals(2, grade.getCredits());
        assertEquals(4.0, grade.getGradePoint());
    }

    @Test
    @DisplayName("Test case Getter dan Setter")
    void testGetterAndSetter() {
        grade.setCourseCode("CS002");
        grade.setCredits(4);
        grade.setGradePoint(2.0);

        assertEquals("CS002", grade.getCourseCode());
        assertEquals(4, grade.getCredits());
        assertEquals(2.0, grade.getGradePoint());
    }
}
