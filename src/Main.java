import java.util.Scanner;

public  class Main {
    public  static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        YearReport yearReport = new YearReport();
        MonthReport monthlyReport = new MonthReport();
        Verify verify;

        printMenu();
        while (true) {
            int command = scanner.nextInt();

            if (command == 1) {
                monthlyReport.stringSeparator();
            } else if (command == 2) {
                yearReport.stringSeparator();
            } else if (command == 3) {
                if (monthlyReport.allData.isEmpty() | yearReport.allData.isEmpty()) {
                    System.out.println("Отчёты не загружены. Для возврата в меню нажмите 6");
                } else {
                    verify = new Verify(monthlyReport, yearReport);
                    if (verify.check()) {
                        System.out.println("Сверка успешно завершена. Данные корректны");
                    } else {
                        System.out.println(verify.wrongMonth + " содержит ошибочные данные!");
                    }
                }
            } else if (command == 4) {
                if (monthlyReport.allData.isEmpty()) {
                    System.out.println("Загрузка отчета не выполнена. Для возврата в меню нажмите 6.");
                } else {
                    monthlyReport.printResultMonth();
                    System.out.println("");
                    System.out.println("Для возврата в меню нажмите 6.");
                }
            } else if (command == 5) {
                if (yearReport.allData.isEmpty()) {
                    System.out.println("Загрузка отчета не выполнена. Для возврата в меню нажмите 6.");
                } else {
                    yearReport.printResultYear();
                    System.out.println("");
                    System.out.println("Для возврата в меню нажмите 6.");
                }
            } else if (command == 6) {
                System.out.println("");
                printMenu();
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

