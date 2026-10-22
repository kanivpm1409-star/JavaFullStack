
import java.util.*;
import java.io.*;

public class StudentManagementSystem1 {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();
    static final String FILE_NAME = "students.dat";
    static class Student implements Serializable {
        private static final long serialVersionUID = 1L;

        int id;
        String name;
        int age;
        String department;
        double marks;

        Student(int id, String name, int age,
                String department, double marks) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.department = department;
            this.marks = marks;
        }

        void display() {
            System.out.println("ID         : " + id);
            System.out.println("Name       : " + name);
            System.out.println("Age        : " + age);
            System.out.println("Department : " + department);
            System.out.println("Marks      : " + marks);
            System.out.println("----------------------------");
        }
    }
    static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = Integer.parseInt(sc.nextLine());

        if (findStudent(id) != null) {
            System.out.println("Student ID already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Department: ");
        String department = sc.nextLine();

        System.out.print("Enter Marks: ");
        double marks = Double.parseDouble(sc.nextLine());

        students.add(new Student(id, name, age, department, marks));
        saveData();

        System.out.println("Student added successfully!");
    }
    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        for (Student s : students) {
            s.display();
        }
    }
    static Student findStudent(int id) {
        for (Student s : students) {
            if (s.id == id) {
                return s;
            }
        }
        return null;
    }
    static void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        int id = Integer.parseInt(sc.nextLine());

        Student s = findStudent(id);

        if (s != null) {
            s.display();
        } else {
            System.out.println("Student not found.");
        }
    }
    static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = Integer.parseInt(sc.nextLine());

        Student s = findStudent(id);

        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter New Name: ");
        s.name = sc.nextLine();

        System.out.print("Enter New Age: ");
        s.age = Integer.parseInt(sc.nextLine());

        System.out.print("Enter New Department: ");
        s.department = sc.nextLine();

        System.out.print("Enter New Marks: ");
        s.marks = Double.parseDouble(sc.nextLine());

        saveData();
        System.out.println("Student updated successfully!");
    }
    static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());

        Student s = findStudent(id);

        if (s != null) {
            students.remove(s);
            saveData();
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }
    static void saveData() {
        try (ObjectOutputStream out =
                 new ObjectOutputStream(
                     new FileOutputStream(FILE_NAME))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving records: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    static void loadData() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream in =
                 new ObjectInputStream(
                     new FileInputStream(FILE_NAME))) {
            students = (ArrayList<Student>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading records: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        loadData();

        while (true) {
            System.out.println("\n===== STUDENT RECORD MANAGEMENT =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewStudents();
                        break;
                    case 3:
                        searchStudent();
                        break;
                    case 4:
                        updateStudent();
                        break;
                    case 5:
                        deleteStudent();
                        break;
                    case 6:
                        saveData();
                        System.out.println("Thank you! Goodbye.");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
