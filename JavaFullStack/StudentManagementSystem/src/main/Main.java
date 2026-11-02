package main;

import service.StudentService;

public class Main {

    public static void main(String[] args) {

        StudentService service = new StudentService();

        Menu menu = new Menu(service);

        menu.displayMenu();
    }
}