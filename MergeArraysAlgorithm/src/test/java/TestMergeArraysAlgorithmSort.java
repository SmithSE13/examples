import junit.framework.TestCase;
import org.junit.Assert;

public class TestMergeArraysAlgorithmSort extends TestCase {

    MergeArraysAlgorithmSort mergeArraysAlgorithmSort;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mergeArraysAlgorithmSort = new MergeArraysAlgorithmSort();
    }

    //With not the ordering sequence, an even number of indexes
    public void testSort1() {
        int[] array = {1, 2, 3, 5, 4, 6};
        int[] actualArray = mergeArraysAlgorithmSort.sort(array);
        int[] expectedArray = {1, 2, 3, 4, 5, 6};
        Assert.assertArrayEquals(expectedArray, actualArray);
    }

    //With not the ordering sequence, an odd number of indexes
    public void testSort2() {
        int[] array = {12, 23, 31, 53, 40, 66, 34, 74, 1};
        int[] actualArray = mergeArraysAlgorithmSort.sort(array);
        int[] expectedArray = {1, 12, 23, 31, 34, 40, 53, 66, 74};
        Assert.assertArrayEquals(expectedArray, actualArray);
    }

    //With equals numbers in the sequence, an odd number of indexes
    public void testSort3() {
        int[] array = {12, 23, 31, 53, 40, 66, 34, 74, 1, 31, 23};
        int[] actualArray = mergeArraysAlgorithmSort.sort(array);
        int[] expectedArray = {1, 12, 23, 23, 31, 31, 34, 40, 53, 66, 74};
        Assert.assertArrayEquals(expectedArray, actualArray);
    }

    //With equals numbers in the sequence, an odd number of indexes
    public void testSort4() {
        int[] array = {12, 23, 23, 31, 31, 53, 40, 66, 34, 74, 1};
        int[] actualArray = mergeArraysAlgorithmSort.sort(array);
        int[] expectedArray = {1, 12, 23, 23, 31, 31, 34, 40, 53, 66, 74};
        Assert.assertArrayEquals(expectedArray, actualArray);
    }

    //With equals numbers in the sequence, an even number of indexes
    public void testSort5() {
        int[] array = {12, 23, 23, 31, 31, 53, 40, 66, 34, 74};
        int[] actualArray = mergeArraysAlgorithmSort.sort(array);
        int[] expectedArray = {12, 23, 23, 31, 31, 34, 40, 53, 66, 74};
        Assert.assertArrayEquals(expectedArray, actualArray);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mergeArraysAlgorithmSort = null;
    }
}
