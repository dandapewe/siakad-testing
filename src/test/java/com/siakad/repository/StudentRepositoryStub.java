// test/java/com/praktikum/whitebox/testdoubles/StudentRepositoryStub.java
package com.siakad.repository;

import com.siakad.model.Course;
import com.siakad.model.Student;
import com.siakad.repository.StudentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRepositoryStub implements StudentRepository {
    private Map<String, Student> students = new HashMap<>();
    private Map<String, List<Course>> completedCourses = new HashMap<>();

    public void addStudent(Student student) {
        students.put(student.getStudentId(), student);
    }

    public void addCompletedCourses(String studentId, List<Course> courses) {
        completedCourses.put(studentId, new ArrayList<>(courses));
    }

    @Override
    public Student findById(String studentId) {
        return students.get(studentId);
    }

    @Override
    public void update(Student student) {
        if (students.containsKey(student.getStudentId())) {
            students.put(student.getStudentId(), student);
        }
    }

    @Override
    public List<Course> getCompletedCourses(String studentId) {
        return completedCourses.getOrDefault(studentId, new ArrayList<>());
    }

    public void clear() {
        students.clear();
        completedCourses.clear();
    }
}