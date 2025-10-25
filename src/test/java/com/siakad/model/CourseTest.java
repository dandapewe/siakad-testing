package com.siakad.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    private Course course;

    @BeforeEach
    void setUp() {
        course = new Course(
                "CS001",
                "Pengujian Perangkat Lunak",
                2,
                32,
                31,
                "Krisna Nuresa Qodri");
    }

    @Test
    @DisplayName("Test kontruktor Course tanpa parameter")
    void testCourseTanpaParameter() {
        Course course1 = new Course();

        assertNotNull(course1.getPrerequisites()); // Cek apakah objek course1 berhasil dibuat
        assertTrue(course1.getPrerequisites().isEmpty()); // Cek apakah benar course1 prerquisitenya belum ada data atau masih kosong
    }

    @Test
    @DisplayName("Test konstruktor Course dengan parameter")
    void testCourseDenganParameter() {
        // Cek setiap parameter apakah benar
        assertEquals("CS001", course.getCourseCode());
        assertEquals("Pengujian Perangkat Lunak", course.getCourseName());
        assertEquals(2, course.getCredits());
        assertEquals(32, course.getCapacity());
        assertEquals(31, course.getEnrolledCount());
        assertEquals("Krisna Nuresa Qodri", course.getLecturer());

        assertNotNull(course.getPrerequisites()); // Cek apakah objek course berhasil dibuat
        assertTrue(course.getPrerequisites().isEmpty()); // Cek apakah benar course prerquisitenya belum ada data atau masih kosong
    }

    @Test
    @DisplayName("Test case getter dan setteer")
    void testGetterAndSetter() {
        // Mengubah (Set) setiap parameter
        course.setCourseCode("CS002");
        course.setCourseName("Forensik Digital");
        course.setCredits(4);
        course.setCapacity(25);
        course.setEnrolledCount(20);
        course.setLecturer("Muhammad Nur Faiz");

        // Mengambil (Get) setiap parameter yang sudah diubah dan melakukan pengecekan
        assertEquals("CS002", course.getCourseCode());
        assertEquals("Forensik Digital", course.getCourseName());
        assertEquals(4, course.getCredits());
        assertEquals(25, course.getCapacity());
        assertEquals(20, course.getEnrolledCount());
        assertEquals("Muhammad Nur Faiz", course.getLecturer());
    }

    @Test
    @DisplayName("Test case untuk setPrerequisites")
    void testSetPrerequisites() {
        List<String> prereqList = new ArrayList<>();
        prereqList.add("CS002");
        prereqList.add("CS003");

        course.setPrerequisites(prereqList);

        assertEquals(2, course.getPrerequisites().size());
        assertTrue(course.getPrerequisites().contains("CS002"));
        assertTrue(course.getPrerequisites().contains("CS003"));
    }

    @Test
    @DisplayName("Test case untuk addPrerequisite ketika prerequisite sudah ada")
    void testPrerequisiteWhenListExists() {
        course.addPrerequisite("CS100");

        assertEquals(1, course.getPrerequisites().size());
        assertTrue(course.getPrerequisites().contains("CS100"));
    }

    @Test
    @DisplayName("Test case untuk addPrerequisite ketika prerequisite null")
    void testPrerequisiteWhenListNull() {
        course.setPrerequisites(null);
        course.addPrerequisite("CS100");

        assertNotNull(course.getPrerequisites());
        assertEquals(1, course.getPrerequisites().size());
        assertTrue(course.getPrerequisites().contains("CS100"));
    }
}
