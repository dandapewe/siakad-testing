package com.siakad.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student(
                "ST010",
                "Danda Prayogi Wijaya",
                "dandapw10@gmail.com",
                "RKS",
                5,
                3.9,
                "ACTIVE");
    }

    @Test
    @DisplayName("Test konstruktor Student() tanpa parameter")
    void testStudentNoParameter() {
        Student student1 = new Student();

        assertNull(student1.getStudentId());
        assertNull(student1.getName());
        assertNull(student1.getEmail());
        assertNull(student1.getMajor());
        assertEquals(0, student1.getSemester()); // Nilai default 0 untuk tipe data int dan field class (variabel kelas)
        assertEquals(0, student1.getGpa()); // Nilai default 0 untuk tipe data int dan field class (variabel kelas)
        assertNull(student1.getAcademicStatus());
    }

    @Test
    @DisplayName("Test konstruktor Student() dengan parameter")
    void testStudentWithParameters() {
        assertEquals("ST010", student.getStudentId());
        assertEquals("Danda Prayogi Wijaya", student.getName());
        assertEquals("dandapw10@gmail.com", student.getEmail());
        assertEquals("RKS", student.getMajor());
        assertEquals(5, student.getSemester());
        assertEquals(3.9, student.getGpa());
        assertEquals("ACTIVE", student.getAcademicStatus());
    }

    @Test
    @DisplayName("Test case untuk Getter dan Setter")
    void testGetterAndSetter() {
        student.setStudentId("ST009");
        student.setName("Charlie");
        student.setEmail("charlie09@gmail.com");
        student.setMajor("Kedokteran");
        student.setSemester(5);
        student.setGpa(4.0);
        student.setAcademicStatus("ACTIVE");

        assertEquals("ST009", student.getStudentId());
        assertEquals("Charlie", student.getName());
        assertEquals("charlie09@gmail.com", student.getEmail());
        assertEquals("Kedokteran", student.getMajor());
        assertEquals(5, student.getSemester());
        assertEquals(4.0, student.getGpa());
        assertEquals("ACTIVE", student.getAcademicStatus());
    }
}
