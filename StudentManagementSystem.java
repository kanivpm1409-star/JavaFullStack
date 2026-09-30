import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
class Student {
    String id;
    String name;
    int age;

    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}

public class StudentManagementSystem {
    static String FILE_NAME = "students.txt";
    static ArrayList<Student> studentList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadFromFile(); 

        while (true) {
            System.out.println("\n=== STUDENT RECORD SYSTEM ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                addStudent();
            } else if (choice.equals("2")) {
                viewStudents();
            } else if (choice.equals("3")) {
                updateStudent();
            } else if (choice.equals("4")) {
                deleteStudent();
            } else if (choice.equals("5")) {
                saveToFile();
                System.out.println("Data saved. Goodbye!");
                break;
            } else {
                System.out.println("Invalid option! Try again.");
            }
        }
    }

    static void addStudent() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        for (Student s : studentList) {
            if (s.id.equals(id)) {
                System.out.println("Error: ID already exists!");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        Student newStudent = new Student(id, name, age);
        studentList.add(newStudent);
        System.out.println("Student added!");
    }

    static void viewStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        for (Student s : studentList) {
            System.out.println("ID: " + s.id + " | Name: " + s.name + " | Age: " + s.age);
        }
    }

    static void updateStudent() {
        System.out.print("Enter ID to update: ");
        String id = scanner.nextLine();

        for (Student s : studentList) {
            if (s.id.equals(id)) {
                System.out.print("Enter New Name: ");
                s.name = scanner.nextLine();
                System.out.print("Enter New Age: ");
                s.age = Integer.parseInt(scanner.nextLine());
                System.out.println("Record updated!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    static void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        String id = scanner.nextLine();

        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).id.equals(id)) {
                studentList.remove(i);
                System.out.println("Student deleted!");
                return;
            }
        }
        System.out.println("Student not found!");
    }
    static void saveToFile() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME));
            for (Student s : studentList) {
                writer.println(s.id + "," + s.name + "," + s.age);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not save data.");
        }
    }
    static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String id = data[0];
                    String name = data[1];
                    int age = Integer.parseInt(data[2]);
                    studentList.add(new Student(id, name, age));
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Could not load data.");
        }
    }
}