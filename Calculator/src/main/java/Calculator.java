import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {

    /**
     * Калькулятор вычисляет оператор, представленный в виде строки.
     *
     * Математическое выражение может содержать точки '.', знаки операций '*', '/', '-', '+' и скобки '(', ')'
     *
     *                  Пример: "(5 + 89) + (6 - 3 ) * 9.2 - 7 / 21"
     *
     */

    private DecimalFormat df = new DecimalFormat("##########.####");
    private ArrayList<Integer> listIndexCharOpenBracket = new ArrayList<>();
    private ArrayList<Integer> listIndexCharCloseBracket = new ArrayList<>();

    public String evaluate(String statement) {
        try {
            String patternErrorEnter = "[+\\-*/]{2}+";
            String expression = statement.replaceAll(patternErrorEnter, "");
            if (expression.length() == statement.length() && expression.length() > 0) {
                expression = statement.replaceAll("\\(-", "(0-");
                if (checkOnParentheses(expression)) {
                    expression = solutionExpressionInParentheses(expression);
                } else if (listIndexCharOpenBracket.size() > 0) {
                    return null;
                }
                expression = solutionExpressionWithoutParentheses(expression);
                expression = df.format(Double.parseDouble(expression));
                expression = expression.replace(",", ".");
                return expression;
            } else {
                return null;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private String solutionExpressionInParentheses(String statement) {
        String expression = statement;
        for(int i = listIndexCharOpenBracket.size() - 1; i >= 0; i--) {
            String s = expression.substring(listIndexCharOpenBracket.get(i));
            String sForReplace = s.substring(0, s.indexOf(')') + 1);
            s = divide(sForReplace);
            s = multiplication(s);
            s = sum(s);
            expression = expression.replace(sForReplace, s);
            listIndexCharOpenBracket.remove(listIndexCharOpenBracket.size() - 1);
            listIndexCharCloseBracket.remove(listIndexCharCloseBracket.size() - 1);
        }
        return expression;
    }

    private String solutionExpressionWithoutParentheses(String statement) {
        String expression = statement;
        expression = divide(expression);
        expression = multiplication(expression);
        expression = sum(expression);
        return expression;
    }

    private String multiplication(String statement) {
        String expression = statement;
        String[] exp = expression.split("[+/\\-]");
        return getResultMultiplicationDivision(exp, statement, '*');
    }

    private String divide(String statement) {
        String expression = statement;
        expression = expression.replaceAll("/-", "/minus");
        String[] exp = expression.split("[+*\\-]");
        return getResultMultiplicationDivision(exp, expression, '/');
    }

    private String sum(String statement) {
        String expression = statement;
        expression = expression.replaceAll("\\+", "p");
        expression = expression.replaceAll("-", "p-");
        expression = expression.replaceAll("[()]", "");
        String[] elementsExpression = expression.split("p");
        return Arrays.stream(elementsExpression)
                .map(this::checkAndParseNegativeString)
                .reduce(Double::sum).map(Object::toString).get();
    }

    private double checkAndParseNegativeString(String statement) {
        String expression = statement;
        if (expression.length() > 0 && expression.charAt(0) == '-') {
            expression = expression.replace("-", "");
            return 0 - Double.parseDouble(expression);
        } else if (expression.length() > 0 && expression.charAt(0) == 'm') {
            expression = expression.replaceAll("minus", "");
            return 0 - Double.parseDouble(expression);
        } else if (expression.length() == 0) {
            return 0;
        } else {
            return Double.parseDouble(expression);
        }
    }

    private boolean checkOnSymbol(String statement, char ch) {
        for(int i = 0; i < statement.length(); i++) {
            if (statement.charAt(i) == ch) {
                return true;
            }
        }
        return false;
    }

    private boolean checkOnParentheses(String statement) {
        for(int i = 0; i < statement.length(); i++) {
            if (statement.charAt(i) == '(') {
                listIndexCharOpenBracket.add(i);
            }
            if (statement.charAt(i) == ')') {
                listIndexCharCloseBracket.add(i);
            }
        }
        return listIndexCharOpenBracket.size() > 0 && listIndexCharOpenBracket.size() == listIndexCharCloseBracket.size();
    }

    private String getResultMultiplicationDivision(String[] exp, String expression, char charOperation) {
        double operation;
        for(String fromMassive : exp) {
            if (checkOnSymbol(fromMassive, charOperation)) {
                String sForReplace = fromMassive.replaceAll("[()]", "");
                if (charOperation == '*') {
                    String[] stringDigital = sForReplace.split("\\*");
                    operation = checkAndParseNegativeString(stringDigital[0]);
                    for(int i = 1; i < stringDigital.length; i++) {
                        operation *= checkAndParseNegativeString(stringDigital[i]);
                    }
                    expression = expression.replace(sForReplace, Double.toString(operation));
                } else {
                    if (checkOnSymbol(fromMassive, '/')) {
                        String[] stringDigital = sForReplace.split("/");
                        operation = checkAndParseNegativeString(stringDigital[0]);
                        for(int i = 1; i < stringDigital.length; i++) {
                            if (checkAndParseNegativeString(stringDigital[i]) != 0) {
                                operation /= checkAndParseNegativeString(stringDigital[i]);
                            } else {
                                return null;
                            }
                        }
                        expression = expression.replace(sForReplace, Double.toString(operation));
                    }
                }
            }
        }
        return expression;
    }
}
