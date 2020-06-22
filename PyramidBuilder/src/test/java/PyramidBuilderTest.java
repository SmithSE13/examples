import exception.CannotBuildException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PyramidBuilderTest {

    private PyramidBuilder pyramidBuilder = new PyramidBuilder();

    @Test
    public void buildPyramid0() {
        List<Integer> input = Arrays.asList(1, 3, 2, 9, 4, 5);
        int[][] expected = new int[][]{
                {0, 0, 1, 0, 0},
                {0, 2, 0, 3, 0},
                {4, 0, 5, 0, 9}};

        int[][] pyramid = pyramidBuilder.buildPyramid(input);

        comparePyramids(expected, pyramid);
    }

    @Test
    public void buildPyramid1() {
        List<Integer> input = Arrays.asList(11, 1, 12, 3, 2, 13, 9, 4, 5, 14, 10, 8, 7, 15, 6);
        int[][] expected = new int[][]{
                {0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 2, 0, 3, 0, 0, 0},
                {0, 0, 4, 0, 5, 0, 6, 0, 0},
                {0, 7, 0, 8, 0, 9, 0, 10, 0},
                {11, 0, 12, 0, 13, 0, 14, 0, 15}
        };

        int[][] pyramid = pyramidBuilder.buildPyramid(input);

        comparePyramids(expected, pyramid);
    }

    @Test
    public void buildPyramid2() {
        List<Integer> input = Arrays.asList(11, 1, 21, 12, 3, 16, 2, 13, 9, 4, 17, 5, 14, 10, 18, 8, 7, 19, 15, 6, 20);
        int[][] expected = new int[][]{
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 2, 0, 3, 0, 0, 0, 0},
                {0, 0, 0, 4, 0, 5, 0, 6, 0, 0, 0},
                {0, 0, 7, 0, 8, 0, 9, 0, 10, 0, 0},
                {0, 11, 0, 12, 0, 13, 0, 14, 0, 15, 0},
                {16, 0, 17, 0, 18, 0, 19, 0, 20, 0, 21}
        };

        int[][] pyramid = pyramidBuilder.buildPyramid(input);

        comparePyramids(expected, pyramid);
    }


    @Test(expected = CannotBuildException.class)
    public void buildPyramid3() {
        List<Integer> input = Arrays.asList(1, 3, 2, 5, 4, null);

        int[][] pyramid = pyramidBuilder.buildPyramid(input);
    }

    @Test(expected = CannotBuildException.class)
    public void buildPyramid4() {
        List<Integer> input = Arrays.asList(1, 2, 4, 8, 3, 9, null);

        int[][] pyramid = pyramidBuilder.buildPyramid(input);
    }

    private void comparePyramids(int[][] expected, int[][] pyramid) {
        Assert.assertEquals(expected.length, pyramid.length);
        for (int i = 0; i < expected.length; i++) {
            Assert.assertArrayEquals(expected[i], pyramid[i]);
        }
    }

}