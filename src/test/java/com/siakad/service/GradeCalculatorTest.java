package com.siakad.service;

import com.siakad.model.CourseGrade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GradeCalculatorTest {
    private GradeCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new GradeCalculator();
    }

    /*Test case untuk calculateGPA
    * Kondisi yang akan di test:
    * List `null`
    * List kosong
    * Grade point negatif
    * Grade point > 4.0
    * Total SKS = 0
    * Perhitungan normal
    * Pembulatan dua desimal
    * */

    @Test
    @DisplayName("Test case untuk calculateGPA dengan parameter grades null")
    void testCalculateGPAWIthListNull() {
        assertEquals(0.0, calculator.calculateGPA(null));
    }

    @Test
    @DisplayName("Test case untuk calculateGPA dengan parameter grades list kosong")
    void testCalculateGPAWithListEmpty() {
        assertEquals(0.0, calculator.calculateGPA(List.of()));
    }

    @Test
    @DisplayName("Test case untuk calculateGPA dengan grade point negatif")
    void testCalculateGPAWithGradePointNegatif() {
        List<CourseGrade> grades = List.of(new CourseGrade("CS001", 3, -1.0));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateGPA(grades));
    }

    @Test
    @DisplayName("Test case untuk calculateGPA dengan grade point > 4")
    void testCalculateGPAWithGradePointLebihDariEmpat() {
        List<CourseGrade> grades = List.of(new CourseGrade("CS001", 3, 4.5));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateGPA(grades));
    }

    @Test
    @DisplayName("Test case untuk calculateGPA dengan credits = 0")
    void testCalculateGPAWithCreditZero() {
        List<CourseGrade> grades = List.of(new CourseGrade("CS001", 0, 3.5));
        assertEquals(0.0, calculator.calculateGPA(grades));
    }

    @Test
    @DisplayName("Test case calculateGPA dengan perhitungan normal")
    void testCalculateGPA() {
        List<CourseGrade> grades = List.of(
                new CourseGrade("CS010", 3, 4.0),
                new CourseGrade("CS009", 3, 3.5));

        assertEquals(3.75, calculator.calculateGPA(grades));
    }

    @Test
    @DisplayName("Test case calculateGPA dengan membuktiak desimal 2 digit")
    void testCalculateGPARoundingTwoDecimals() {
        List<CourseGrade> grades = List.of(new CourseGrade("CS001", 3, 3.333));

        assertEquals(3.33, calculator.calculateGPA(grades));
    }

    /* Test case determineAcademicStatus dengan kondisi yang perlu ditest adalah:
    GPA < 0
    GPA > 4
    Semester < 1
    Semester 1–2, GPA ≥ 2.0
    Semester 1–2, GPA < 2.0
    Semester 3–4, GPA ≥ 2.25
    Semester 3–4, 2.0 ≤ GPA < 2.25
    Semester 3–4, GPA < 2.0
    Semester 5+, GPA ≥ 2.5
    Semester 5+, 2.0 ≤ GPA < 2.5
    Semester 5+, GPA < 2.0
    */

    @Test
    @DisplayName("Test case determineAcademicStatus dengan GPA < 0")
    void testDetermineAcademicStatusGPAKurangDariNol() {
        assertThrows(IllegalArgumentException.class, () -> calculator.determineAcademicStatus(-1, 5));
    }

    @Test
    @DisplayName("Test case determineAcademicStatus dengan GPA > 4.00")
    void testDetermineAcademicStatusGPALebihDariEmpat() {
        assertThrows(IllegalArgumentException.class, () -> calculator.determineAcademicStatus(5, 5));
    }

    @Test
    @DisplayName("Test case determineAcademicStatus dengan Semester < 1")
    void testDetermineAcademicStatusSemesterKurangDariSatu() {
        assertThrows(IllegalArgumentException.class, () -> calculator.determineAcademicStatus(2.0, -1));
    }

    @Test
    @DisplayName("Test case determineStatusAcademic Semester 1-2 dengan GPA >= 2")
    void testDetermineStatusAcademicSem12Active() {
        assertEquals("ACTIVE", calculator.determineAcademicStatus(2, 2));
    }

    @Test
    @DisplayName("Test case dengan determineAcademicStatus Semester 1-2 dengan GPA < 2")
    void testDetermineStatusAcademicSem12Probation() {
        assertEquals("PROBATION", calculator.determineAcademicStatus(1, 1));
    }

    @Test
    @DisplayName("Test case dengan determineAcademicStatus Semester 3-4 dengan GPA >= 2.25")
    void testDetermineStatusAcademicSem34Active() {
        assertEquals("ACTIVE", calculator.determineAcademicStatus(2.5, 3));
    }

    @Test
    @DisplayName("Test case dengan determineAcademicStatus Semester 3-4 dengan GPA >= 2.00")
    void testDetermineStatusAcademicSem34Probation() {
        assertEquals("PROBATION", calculator.determineAcademicStatus(2, 4));
    }

    @Test
    @DisplayName("Test case dengan determineAcademicStatus Semester 3-4 dengan GPA < 2.00")
    void testDetermineStatusAcademicSem34Suspended() {
        assertEquals("SUSPENDED", calculator.determineAcademicStatus(1.5, 4));
    }

    @Test
    @DisplayName("Test case dengan determineAcademicStatus Semester 5++ dengan GPA >= 2.5")
    void testDetermineStatusAcademicSem5Active() {
        assertEquals("ACTIVE", calculator.determineAcademicStatus(2.5, 5));
    }

    @Test
    @DisplayName("Test case dengan determineAcademicStatus Semester 5++ dengan GPA >= 2.0")
    void testDetermineStatusAcademicSem5Probation() {
        assertEquals("PROBATION", calculator.determineAcademicStatus(2, 6));
    }

    @Test
    @DisplayName("Test case dengan determineAcademicStatus Semester 5++ dengan GPA < 2.0")
    void testDetermineStatusAcademicSem5Suspended() {
        assertEquals("SUSPENDED", calculator.determineAcademicStatus(1, 7));
    }

    /*
    Test case calculateMaxCredits dengan aturan sebagai berikut
     * - IPK >= 3.0: maksimal 24 SKS
     * - IPK 2.5-2.99: maksimal 21 SKS
     * - IPK 2.0-2.49: maksimal 18 SKS
     * - IPK < 2.0: maksimal 15 SKS
    */

    @Test
    @DisplayName("test case calculateMaxCredits dengan GPA < 1")
    void testCalculateMaxCreditsGPAKurangDariSatu() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateMaxCredits(-1));
    }

    @Test
    @DisplayName("test case calculateMaxCredits dengan GPA > 4")
    void testCalculateMaxCreditsGPALebihDariEmpat() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateMaxCredits(4.1));
    }

    @Test
    @DisplayName("test case calculateMaxCredits dengan GPA > 3.0")
    void testCalculateMaxCredits24SKS() {
        assertEquals(24, calculator.calculateMaxCredits(3));
    }

    @Test
    @DisplayName("test case calculateMaxCredits dengan 2.5 <= GPA < 3")
    void testCalculateMaxCredits21SKS() {
        assertEquals(21, calculator.calculateMaxCredits(2.5));
    }

    @Test
    @DisplayName("test case calculateMaxCredits dengan 2 <= GPA < 2.5")
    void testCalculateMaxCredits18SKS() {
        assertEquals(18, calculator.calculateMaxCredits(2));
    }

    @Test
    @DisplayName("test case calculateMaxCredits dengan GPA < 2")
    void testCalculateMaxCredits15SKS() {
        assertEquals(15, calculator.calculateMaxCredits(1));
    }
}
