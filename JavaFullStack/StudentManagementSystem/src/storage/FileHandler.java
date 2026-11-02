package storage;

import model.Student;
import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    private static final String FILE_NAME = "students.txt";
    public static void saveStudents(ArrayList<Student> students) {

        try (BufferedWriter writer =
             new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Student s : students) {

                writer.write(
                    s.getId() + "|" +
                    s.getName() + "|" +
                    s.getAge() + "|" +
                    s.getCourse()
                );

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving student records!");
        }
    }
    public static ArrayList<Student> loadStudents() {

        ArrayList<Student> students = new ArrayList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader =
             new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length == 4) {

                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    int age = Integer.parseInt(data[2]);
                    String course = data[3];

                    Student student =
                        new Student(id, name, age, course);

                    students.add(student);
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading student records!");
        }

        return students;
    }
}