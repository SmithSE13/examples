import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        PyramidBuilder pyramidBuilder = new PyramidBuilder();
        pyramidBuilder.printPyramid(pyramidBuilder.buildPyramid(Arrays.asList(1, 5, 9, 2, 3, 4)));
    }
}
