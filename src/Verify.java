import java.util.HashMap;

public class Verify {
    public Month month;
    public Year year;

    HashMap<Integer, Integer> checkerProfit = new HashMap<>();
    HashMap<Integer, Integer> checkerSpent = new HashMap<>();

    public Verify(Month month, Year year) {
        this.month = month;
        this.year = year;
    }

    public void check() {
        if (month.hash.size() == 0 || year.saleYear.size() == 0) {
            System.out.println("Для сверки сначала считайте отчет.");
            return;
        }

        month.getIncomeAndSpend();

        if (year.profitOrders.equals(month.profitOrders)) {
            System.out.println();
            System.out.println("Сверка отчетов о доходах прошла успешно.");
        } else {
            checkerProfit = month.profitOrders;
            for (Integer month : year.profitOrders.keySet()) {
                if (checkerProfit.containsKey(month)) {
                    if (!checkerProfit.get(month).equals(year.profitOrders.get(month))) {
                        if (month == 1) {
                            System.out.println("Отчет о доходах за Январь не сошелся.");
                        } else if (month == 2) {
                            System.out.println("Отчет о доходах за Февраль не сошелся.");
                        } else if (month == 3) {
                            System.out.println("Отчет о доходах за Март не сошелся.");
                        }
                    }
                }
            }
        }

        if (year.spendOrders.equals(month.spendOrders)) {
            System.out.println("Сверка отчетов о расходах прошла успешно.");
        } else {
            checkerSpent = month.spendOrders;
            for (Integer month : year.spendOrders.keySet()) {
                if (checkerSpent.containsKey(month)) {
                    if (!checkerSpent.get(month).equals(year.spendOrders.get(month))) {
                        if (month == 1) {
                            System.out.println("Отчет о расходах за Январь не сошелся.");
                        } else if (month == 2) {
                            System.out.println("Отчет о расходах за Февраль не сошелся.");
                        } else if (month == 3) {
                            System.out.println("Отчет о расходах за Март не сошелся.");
                        }
                    }
                }
            }
        }
    }
}