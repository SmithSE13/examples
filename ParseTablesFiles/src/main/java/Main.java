import java.math.BigDecimal;
import java.util.Map;

public class Main {
    private static String pathParseFile = "FileToParse/movementList.csv";
    private static ParseTablesFiles parseTablesFiles = new ParseTablesFiles(pathParseFile);

    public static void main(String[] args) {
        parseTablesFiles.parseFile();
        System.out.println("Сумма расходов: " + parseTablesFiles.getCommonComing() + " руб.");
        System.out.println("Сумма доходов: " + parseTablesFiles.getCommonExpenditure() + " руб.");
        System.out.println("Суммы расходов по организациям: ");
        printContractorsSum(parseTablesFiles.getContractorsSum());
        if(parseTablesFiles.checkOnWrongLines()) {
            parseTablesFiles.printAmountWrongLines();
        }
    }

    private static void printContractorsSum(Map<String, BigDecimal> expenses) {
        for(Map.Entry<String, BigDecimal> entry : expenses.entrySet()) {
            String key = entry.getKey();
            BigDecimal value = entry.getValue();
            System.out.printf("%s : %s руб.\n", key, value);
        }
    }
}
