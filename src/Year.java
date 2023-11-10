import java.util.ArrayList;
import java.util.HashMap;

public class Year {
    FileReader reader = new FileReader();
    ArrayList<YearRecord> saleYear = new ArrayList<>();
    public HashMap<Integer, Integer> profitOrders = new HashMap<>();
    public HashMap<Integer, Integer> spendOrders = new HashMap<>();

    public void yearLoadFile(String path) {

        ArrayList<String> content = reader.readFileContents(path);

        for (int i = 1; i < content.size(); i++) {
            String line = content.get(i);
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            YearRecord yearReport = new YearRecord(month, amount, isExpense);
            saleYear.add(yearReport);

            if (yearReport.isExpense) {
                spendOrders.put(yearReport.month, yearReport.amount);
            } else {
                profitOrders.put(yearReport.month, yearReport.amount);
            }
        }
        System.out.println();
        System.out.println("Годовые отчёты загружены!");
    }

    void getMonthsAmount() {
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (YearRecord year : saleYear) {
            if (year.isExpense) {
                hash.put(year.month, hash.getOrDefault(year.month, 0) - year.amount);
            } else {
                hash.put(year.month, hash.getOrDefault(year.month, 0) + year.amount);
            }
        }
        for (Integer month : hash.keySet()) {
            if (month == 1) {
                System.out.println("Прибыль за Январь: " + hash.get(month) + " рублей");
            } else if (month == 2) {
                System.out.println("Прибыль за Февраль: " + hash.get(month) + " рублей");
            } else if (month == 3) {
                System.out.println("Прибыль за Март: " + hash.get(month) + " рублей");
            }
        }
    }

    void avgExpense() {
        int allExpense = 0;
        int count = 0;
        for (YearRecord transaction : saleYear) {
            if (transaction.isExpense == true) {
                count++;
                allExpense += transaction.amount;
            }
        }
        double avg = allExpense / count;
        System.out.println("Среднии расходы за год составили: " + avg + " рублей");
    }

    void avgProfit() {
        int count = 0;
        int allProfit = 0;
        for (YearRecord transaction : saleYear) {
            if (transaction.isExpense == false) {
                count++;
                allProfit += transaction.amount;
            }
        }
        double avg = (double) allProfit / count;
        System.out.println("Среднии доходы за год составили: " + avg + " рублей");
    }

    void printAll() {
        if (saleYear.size() == 0) {
            System.out.println("Считайте сначала годовой отчет!");
        } else {
            getMonthsAmount();
            avgExpense();
            avgProfit();
        }
    }
}