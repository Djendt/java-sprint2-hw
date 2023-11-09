import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class YearReport {
    String[] namesMonth = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    ArrayList<Record> allData = new ArrayList<>();
    Verify verify;

    void stringSeparator() {
        String pathToFile = readFileContentsOrNull("resources/y.2021.csv");
        String[] yearArray = (pathToFile).split("\r?\n|\r");

        for (int i = 1; i < yearArray.length; i++) {
            String[] tmpStringArray = yearArray[i].split(",");
            String keyMonth = tmpStringArray[0];
            int amount = Integer.parseInt(tmpStringArray[1]);
            boolean isExpense = Boolean.parseBoolean(tmpStringArray[2]);
            Record record = new Record(keyMonth, 1, amount, isExpense);
            allData.add(record);
        }
        System.out.println("Годовой отчёт загружен в программу");
    }

    public void printResultYear() {
        int sumOfIncome = 0;
        int sumOfExpense = 0;
        ArrayList<Integer> incomeYR = new ArrayList<>();
        ArrayList<Integer> expenseYR = new ArrayList<>();
        for (int i = 0; i < allData.size(); i++) {
            int expense;
            int income;
            if (allData.get(i).isExpense) {
                expense = allData.get(i).sumOfOne;
                sumOfExpense += expense;
                expenseYR.add(expense);
            }
            else {
                income = allData.get(i).sumOfOne;
                sumOfIncome += income;
                incomeYR.add(income);
            }
        }


        System.out.println("По итогам 2021-го года:");
        System.out.println("   прибыль по каждому месяцу составила: ");

        for (int i = 0; i < allData.size() / 2; i++) {
            System.out.println("        " + namesMonth[i] + "  " + (incomeYR.get(i) - expenseYR.get(i)) + " рублей.");
        }
        System.out.println("   средний расход за все месяцы составил: " + sumOfExpense / expenseYR.size() + " рублей.");
        System.out.println("   средний доход за все месяцы составил: " + sumOfIncome / incomeYR.size() + " рублей.");
    }


    String readFileContentsOrNull (String path){
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}
