import java.util.Scanner;

public  class Main {
    public  static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Year year = new Year();
        Month month = new Month();
        Verify verify = new Verify(month, year);



        while (true) {
            System.out.println();
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {
                month.loadFile();
            } else if (command == 2) {
                year.yearLoadFile("y.2021.csv");
            } else if (command == 3) {
                verify.check();
            } else if (command == 4) {
                month.getMontyReport();
            } else if (command == 5) {
                year.printAll();
            } else if (command == 0) {
                System.out.println("Выход из приложения");
                return;
            } else {
                System.out.println("К сожалению, такая команда отсутствует.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 - Считать месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }

}

