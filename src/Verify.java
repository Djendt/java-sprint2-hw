import java.util.ArrayList;

public class Verify {

    MonthReport monthReport;
    YearReport yearReport;
    ArrayList<Integer> incomeYR = new ArrayList<>();
    ArrayList<Integer> expenseYR = new ArrayList<>();
    ArrayList<Integer> incomeMR = new ArrayList<>();
    ArrayList<Integer> expenseMR = new ArrayList<>();
    int allIncomesYR;
    int allExpensesYR;
    String wrongMonth;

    public Verify(MonthReport monthReport, YearReport yearReport) {
        this.monthReport = monthReport;
        this.yearReport = yearReport;

        int sumOfIncome = 0;
        int sumOfExpense = 0;
        for (int i = 0; i < yearReport.allData.size(); i++) {
            int expense;
            int income;
            if (yearReport.allData.get(i).isExpense) {
                expense = yearReport.allData.get(i).sumOfOne;
                sumOfExpense += expense;
                expenseYR.add(expense);
            }
            else {
                income = yearReport.allData.get(i).sumOfOne;
                sumOfIncome += income;
                incomeYR.add(income);
            }
        }
        allIncomesYR = sumOfIncome;
        allExpensesYR = sumOfExpense;

        for (int i = 0; i < yearReport.allData.size() / 2; i++) {
            int sumOfIncomeMR = 0;
            int sumOfExpenseMR = 0;
            for (Record recordCh : monthReport.allData.get(yearReport.namesMonth[i])) {
                if (recordCh.isExpense) {
                    sumOfExpenseMR += recordCh.quantity * recordCh.sumOfOne;
                }
                else {
                    sumOfIncomeMR += recordCh.quantity * recordCh.sumOfOne;
                }
            }
            expenseMR.add(sumOfExpenseMR);
            incomeMR.add(sumOfIncomeMR);
        }
    }

    public boolean check() {
        boolean tmpBoolean = false;
        for (int j = 0; j < yearReport.allData.size() / 2; j++) {
            if ((incomeYR.get(j).equals(incomeMR.get(j))) & (expenseMR.get(j).equals(expenseMR.get(j)))) {
                tmpBoolean = true;
            }
            else {
                wrongMonth = yearReport.namesMonth[j];
                tmpBoolean = false;
            }
        }
        return tmpBoolean;
    }



}
