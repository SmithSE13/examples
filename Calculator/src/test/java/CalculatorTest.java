import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    public void evaluate0() {
        String input = "5+2";
        String expectedResult = "7";

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate1() {
        String input = "9-10";
        String expectedResult = "-1";

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate2() {
        String input = "4*5";
        String expectedResult = "20";

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate3() {
        String input = "6/2";
        String expectedResult = "3";

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate4() {
        String input = "1+2*4";
        String expectedResult = "9";

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate5() {
        String input = "20/4-6+2*6";
        String expectedResult = "11";

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate6() {
        String input = "20/(4-5+2)*6";
        String expectedResult = "120";

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate7() {
        String input = "30/2*5.7777";
        String expectedResult = "86.6655";

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate8() {
        String input = "30/2*5.123";
        String expectedResult = "76.845";

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate9() {
        String input = "30/2*5,123";
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate10() {
        String input = "+ 144)7//(";
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate11() {
        String input = "20/(2-2)";
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate12() {
        String input = "(2*(9-4)";
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate13() {
        String input = null;
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate14() {
        String input = "";
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate15() {
        String input = "2-1..13+6";
        String expectedResult = null;

        String result = calculator.evaluate(input);


        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate16() {
        String input = "9--6+9";
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate17() {
        String input = "9++54+2";
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate18() {
        String input = "9**54+2";
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate19() {
        String input = "9//54+2";
        String expectedResult = null;

        String result = calculator.evaluate(input);

        Assert.assertEquals(expectedResult, result);
    }

}