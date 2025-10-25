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

}
