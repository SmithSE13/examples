public class Main {
    public static void main(String[] args) {
        ParseTablesFiles parseTablesFiles = new ParseTablesFiles();
        parseTablesFiles.parseFile();
        System.out.println("Сумма расходов: " + parseTablesFiles.getCommonComing() + " руб.");
        System.out.println("Сумма доходов: " + parseTablesFiles.getCommonExpenditure() + " руб.");
        System.out.println("Суммы расходов по организациям: ");
        parseTablesFiles.printListExpenditure();
    }
}
