public class MonthRecord {
        public String name;
        public Boolean isExpense;
        public int quantity;
        public int price;

        public MonthRecord(String name, Boolean isExpense, int quantity, int price) {
            this.name = name;
            this.isExpense = isExpense;
            this.quantity = quantity;
            this.price = price;
        }
}
