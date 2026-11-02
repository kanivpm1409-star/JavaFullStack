package service;

import model.Student;
import storage.FileHandler;
import java.util.ArrayList;

public class StudentService {

    private ArrayList<Student> students;
    public StudentService() {
        students = FileHandler.loadStudents();
    }
    public boolean addStudent(Student student) {

        for (Student s : students) {
            if (s.getId() == student.getId()) {
                System.out.println("Student ID already exists!");
                return false;
            }
        }

        students.add(student);
        FileHandler.saveStudents(students);

        System.out.println("Student added successfully!");
        return true;
    }
    public void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No student records found!");
            return;
        }

        System.out.println("\n----- STUDENT RECORDS -----");

        for (Student s : students) {
            System.out.println(s);
        }
    }
    public boolean updateStudent(int id, String name,
                                 int age, String course) {

        for (Student s : students) {

            if (s.getId() == id) {

                s.setName(name);
                s.setAge(age);
                s.setCourse(course);

                FileHandler.saveStudents(students);

                System.out.println("Student updated successfully!");
                return true;
            }
        }

        System.out.println("Student not found!");
        return false;
    }
    public boolean deleteStudent(int id) {

        for (Student s : students) {

            if (s.getId() == id) {

                students.remove(s);

                FileHandler.saveStudents(students);

                System.out.println("Student deleted successfully!");
                return true;
            }
        }

        System.out.println("Student not found!");
        return false;
    }
}