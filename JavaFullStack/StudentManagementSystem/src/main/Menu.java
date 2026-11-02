package main;

import model.Student;
import service.StudentService;
import java.util.Scanner;

public class Menu {

    private StudentService service;
    private Scanner sc;

    public Menu(StudentService service) {
        this.service = service;
        this.sc = new Scanner(System.in);
    }

    public void displayMenu() {

        int choice;

        do {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        addStudent();
                        break;

                    case 2:
                        service.viewStudents();
                        break;

                    case 3:
                        updateStudent();
                        break;

                    case 4:
                        deleteStudent();
                        break;

                    case 5:
                        System.out.println("Thank you for using the system!");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number!");
                choice = 0;
            }

        } while (choice != 5);
    }
    private void addStudent() {

        try {

            System.out.print("Enter Student ID: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            if (id <= 0 || age <= 0 ||
                name.isBlank() || course.isBlank() ||
                name.contains("|") || course.contains("|")) {

                System.out.println("Invalid student details!");
                return;
            }

            Student student = new Student(id, name, age, course);

            service.addStudent(student);

        } catch (NumberFormatException e) {

            System.out.println("ID and Age must be numbers!");
        }
    }
    private void updateStudent() {

        try {

            System.out.print("Enter Student ID to update: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Enter New Name: ");
            String name = sc.nextLine();

            System.out.print("Enter New Age: ");
            int age = Integer.parseInt(sc.nextLine());

            System.out.print("Enter New Course: ");
            String course = sc.nextLine();

            if (age <= 0 || name.isBlank() ||
                course.isBlank() || name.contains("|") ||
                course.contains("|")) {

                System.out.println("Invalid student details!");
                return;
            }

            service.updateStudent(id, name, age, course);

        } catch (NumberFormatException e) {

            System.out.println("Please enter valid numbers!");
        }
    }
    private void deleteStudent() {

        try {

            System.out.print("Enter Student ID to delete: ");
            int id = Integer.parseInt(sc.nextLine());

            service.deleteStudent(id);

        } catch (NumberFormatException e) {

            System.out.println("Please enter a valid ID!");
        }
    }
}