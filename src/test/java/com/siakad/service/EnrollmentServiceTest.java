package com.siakad.service;

import com.siakad.exception.*;
import com.siakad.model.*;
import com.siakad.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnrollmentServiceTest {

    private StudentRepository studentRepo;
    private CourseRepository courseRepo;
    private NotificationService notificationService;
    private GradeCalculator gradeCalculator;
    private EnrollmentService service;

    @BeforeEach
    void setUp() {
        studentRepo = mock(StudentRepository.class);
        courseRepo = mock(CourseRepository.class);
        notificationService = mock(NotificationService.class);
        gradeCalculator = mock(GradeCalculator.class);
        service = new EnrollmentService(studentRepo, courseRepo, notificationService, gradeCalculator);
    }

    // === enrollCourse() ===
    @Test
    void testEnroll_StudentNotFound() {
        when(studentRepo.findById("S001")).thenReturn(null);
        assertThrows(StudentNotFoundException.class, () -> service.enrollCourse("S001", "CS101"));
    }

    @Test
    void testEnroll_StudentSuspended() {
        Student s = new Student();
        s.setAcademicStatus("SUSPENDED");
        when(studentRepo.findById("S001")).thenReturn(s);
        assertThrows(EnrollmentException.class, () -> service.enrollCourse("S001", "CS101"));
    }

    @Test
    void testEnroll_CourseNotFound() {
        Student s = new Student();
        when(studentRepo.findById("S001")).thenReturn(s);
        when(courseRepo.findByCourseCode("CS101")).thenReturn(null);
        assertThrows(CourseNotFoundException.class, () -> service.enrollCourse("S001", "CS101"));
    }

    @Test
    void testEnroll_CourseFull() {
        Student s = new Student();
        Course c = new Course();
        c.setCapacity(30);
        c.setEnrolledCount(30);
        when(studentRepo.findById("S001")).thenReturn(s);
        when(courseRepo.findByCourseCode("CS101")).thenReturn(c);
        assertThrows(CourseFullException.class, () -> service.enrollCourse("S001", "CS101"));
    }

    @Test
    void testEnroll_PrerequisiteNotMet() {
        Student s = new Student();
        Course c = new Course();
        c.setCapacity(30);
        c.setEnrolledCount(20);
        when(studentRepo.findById("S001")).thenReturn(s);
        when(courseRepo.findByCourseCode("CS101")).thenReturn(c);
        when(courseRepo.isPrerequisiteMet("S001", "CS101")).thenReturn(false);

        assertThrows(PrerequisiteNotMetException.class, () -> service.enrollCourse("S001", "CS101"));
    }

    @Test
    void testEnroll_Success() {
        Student s = new Student();
        s.setEmail("test@mail.com");
        Course c = new Course();
        c.setCapacity(30);
        c.setEnrolledCount(20);
        c.setCourseName("Pemrograman Java");

        when(studentRepo.findById("S001")).thenReturn(s);
        when(courseRepo.findByCourseCode("CS101")).thenReturn(c);
        when(courseRepo.isPrerequisiteMet("S001", "CS101")).thenReturn(true);

        Enrollment e = service.enrollCourse("S001", "CS101");

        assertNotNull(e);
        verify(notificationService).sendEmail(eq("test@mail.com"), anyString(), contains("Pemrograman Java"));
    }

    // === validateCreditLimit() ===
    @Test
    void testValidateCreditLimit_StudentNotFound() {
        when(studentRepo.findById("S001")).thenReturn(null);
        assertThrows(StudentNotFoundException.class, () -> service.validateCreditLimit("S001", 20));
    }

    @Test
    void testValidateCreditLimit_WithinLimit() {
        Student s = new Student();
        s.setGpa(3.5);
        when(studentRepo.findById("S001")).thenReturn(s);
        when(gradeCalculator.calculateMaxCredits(3.5)).thenReturn(24);

        assertTrue(service.validateCreditLimit("S001", 20));
    }

    @Test
    void testValidateCreditLimit_ExceedsLimit() {
        Student s = new Student();
        s.setGpa(2.0);
        when(studentRepo.findById("S001")).thenReturn(s);
        when(gradeCalculator.calculateMaxCredits(2.0)).thenReturn(18);

        assertFalse(service.validateCreditLimit("S001", 22));
    }

    // === dropCourse() ===
    @Test
    void testDropCourse_StudentNotFound() {
        when(studentRepo.findById("S001")).thenReturn(null);
        assertThrows(StudentNotFoundException.class, () -> service.dropCourse("S001", "CS101"));
    }

    @Test
    void testDropCourse_CourseNotFound() {
        Student s = new Student();
        when(studentRepo.findById("S001")).thenReturn(s);
        when(courseRepo.findByCourseCode("CS101")).thenReturn(null);
        assertThrows(CourseNotFoundException.class, () -> service.dropCourse("S001", "CS101"));
    }

    @Test
    void testDropCourse_Success() {
        Student s = new Student();
        s.setEmail("test@mail.com");
        Course c = new Course();
        c.setEnrolledCount(10);
        c.setCourseName("Basis Data");

        when(studentRepo.findById("S001")).thenReturn(s);
        when(courseRepo.findByCourseCode("CS101")).thenReturn(c);

        service.dropCourse("S001", "CS101");

        verify(courseRepo).update(c);
        verify(notificationService).sendEmail(eq("test@mail.com"), anyString(), contains("Basis Data"));
    }
}
