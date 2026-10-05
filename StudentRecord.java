import java.util.*;
import java.io.*;

class Student {
    int id;
    String name;
    int age;
    String department;
    int mark;

    Student(int id, String name, int age, String department, int mark) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.mark = mark;
    }

    void display() {
        System.out.println("ID         : " + id);
        System.out.println("Name       : " + name);
        System.out.println("Age        : " + age);
        System.out.println("Department : " + department);
        System.out.println("Mark       : " + mark);
        System.out.println("----------------------");
    }
}

public class StudentRecord {

    static ArrayList<Student> students = new ArrayList<>();

    static void addStudent(Scanner sc) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        System.out.print("Enter Name: ");
        String name = sc.next();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        System.out.print("Enter Department: ");
        String dept = sc.next();

        System.out.print("Enter Mark: ");
        int mark = sc.nextInt();

        Student s = new Student(id, name, age, dept, mark);
        students.add(s);

        System.out.println("Student added successfully!");
    }

    static void viewStudents() {

        if (students.size() == 0) {
            System.out.println("No student records found.");
            return;
        }

        for (Student s : students) {
            s.display();
        }
    }

    static void updateStudent(Scanner sc) {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        for (Student s : students) {

            if (s.id == id) {

                System.out.print("Enter new name: ");
                s.name = sc.next();

                System.out.print("Enter new age: ");
                s.age = sc.nextInt();

                System.out.print("Enter new mark: ");
                s.mark = sc.nextInt();

                System.out.println("Record updated!");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    static void deleteStudent(Scanner sc) {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).id == id) {
                students.remove(i);
                System.out.println("Student deleted!");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    static void saveFile() throws Exception {

        FileWriter file = new FileWriter("students.txt");

        for (Student s : students) {
            file.write(s.id + " " + s.name + " "
                    + s.age + " " + s.department
                    + " " + s.mark + "\n");
        }

        file.close();
        System.out.println("Records saved to file.");
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== STUDENT RECORD SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Save to File");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addStudent(sc);
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    updateStudent(sc);
                    break;

                case 4:
                    deleteStudent(sc);
                    break;

                case 5:
                    saveFile();
                    break;

                case 6:
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}