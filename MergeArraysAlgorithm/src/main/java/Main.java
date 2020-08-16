import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = {5,2,4,6,1,3,2,6};
        MergeArraysAlgorithmSort instance = new MergeArraysAlgorithmSort();
        array = instance.sort(array);
        Arrays.stream(array).forEach(System.out::print);
    }
}
