import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


public class SubsequenceTest {

    private Subsequence subsequence = new Subsequence();

    @Test
    public void find0() {
        List x = Stream.of(1, 3, 5, 7, 9).collect(toList());
        List y = Stream.of(10, 1, 2, 3, 4, 3, 5, 7, 9, 20).collect(toList());

        boolean result = subsequence.find(x, y);

        Assert.assertTrue(result);
    }

    @Test
    public void find1() {

        List x = Stream.of(3, 9, 1, 5, 7).collect(toList());
        List y = Stream.of(1, 2, 3, 4, 5, 7, 9).collect(toList());

        boolean result = subsequence.find(x, y);

        Assert.assertFalse(result);
    }

    @Test
    public void find2() {
        List x = Stream.of("B", "A", "C").collect(toList());
        List y = Stream.of("BD", "A", "ABC", "B", "M", "M", "C", "DC", "D").collect(toList());

        boolean result = subsequence.find(x, y);

        Assert.assertFalse(result);
    }

    @Test
    public void find5() {
        List x = Stream.of("B", "A", "C").collect(toList());
        List y = Stream.of("BD", "ABC", "B", "M", "M", "C", "DC", "D").collect(toList());

        boolean result = subsequence.find(x, y);

        Assert.assertFalse(result);
    }

    @Test
    public void find6() {
        List x = new ArrayList();
        List y = Stream.of("BD", "ABC", "B", "M", "M", "C", "DC", "D").collect(toList());

        boolean result = subsequence.find(x, y);

        Assert.assertTrue(result);
    }

    @Test
    public void find7() {
        List x = new ArrayList();
        List y = new ArrayList();

        boolean result = subsequence.find(x, y);

        Assert.assertTrue(result);
    }

    @Test
    public void find8() {
        List x = Stream.of("B", "A", "C").collect(toList());
        List y = new ArrayList();

        boolean result = subsequence.find(x, y);

        Assert.assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void find9() {
        List x = null;
        List y = new ArrayList();

        subsequence.find(x, y);

        //assert: exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void find10() {
        List x = new ArrayList();
        List y = null;

        subsequence.find(x, y);

        //assert: exception
    }
}