import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MonthReport {
    YearReport yearReport = new YearReport();
    HashMap<String, ArrayList<Record>> allData = new HashMap<String, ArrayList<Record>>();
    void stringSeparator() {
        for (int i = 1; i < 4; i++) {
            String readFile = readFileContentsOrNull("resources/" + "m.20210" + i + ".csv");
            String[] monthArray = (readFile).split("\r?\n|\r");
            ArrayList<Record> storageForAll = new ArrayList<Record>();
            for (int j = 1; j < monthArray.length; j++) {
                String[] tmpStringArray = monthArray[j].split(",");
                String keyTitle = tmpStringArray[0];
                int quantity = Integer.parseInt(tmpStringArray[3]);
                int price = Integer.parseInt(tmpStringArray[2]);
                boolean isExpense = Boolean.parseBoolean(tmpStringArray[1]);
                Record record = new Record(keyTitle, quantity, price, isExpense);
                storageForAll.add(record);
            }
            allData.put(yearReport.namesMonth[i - 1], storageForAll);
        }
        System.out.println("Месячные отчёты загружены!");
    }

    public void printResultMonth() {
        for (int i = 0; i < allData.size(); i++) {
            System.out.println("Отчётный месяц " + yearReport.namesMonth[i]);
            System.out.println("   Самый прибыльный товар:");
            printBestSellerExpenser(yearReport.namesMonth[i], allData.get(yearReport.namesMonth[i]) , false);
            System.out.println("   Самые большие затраты:");
            printBestSellerExpenser(yearReport.namesMonth[i], allData.get(yearReport.namesMonth[i]), true);
        }

    }

    void printBestSellerExpenser(String month, ArrayList<Record> monthIE, boolean isExpense) {
        int maxCostExpense = 0;
        String bestItemExpense = "";
        int maxCostIncome = 0;
        String bestItemIncome = "";
        for (Record record : monthIE) {
            if (record.isExpense & (record.quantity * record.sumOfOne > maxCostExpense)){
                maxCostExpense = record.quantity * record.sumOfOne;
                bestItemExpense = record.name;
            }
            else if (!record.isExpense & (record.quantity * record.sumOfOne > maxCostIncome)){
                maxCostIncome = record.quantity * record.sumOfOne;
                bestItemIncome = record.name;
            }
        }
        if (isExpense) {
            System.out.println(bestItemExpense + ". Затраты составили: " + maxCostExpense + " рублей.");
        }
        else{
            System.out.println(bestItemIncome + ". Выручка составила: " + maxCostIncome + " рублей.");
        }
    }

    String readFileContentsOrNull (String path){
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}