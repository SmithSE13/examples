import exception.CannotBuildException;
import java.util.Comparator;
import java.util.List;

public class PyramidBuilder {

    /**
     * Строит пирамиду с отсортированными значениями (с минимальным значением в верхней строке и максимальным в нижней,
     * слева направо). Все свободные позиции в массиве являются нулями.
     *
     * Принимает последовательность чисел:                   1, 15, 2
     *
     * возвращает двумерный массив:                          0  1  0
     *                                                       2  0  15
     *
     * или выбрасывает исключение CannotBuildException()
     */

    private int countRows;
    private int countColumns;

    public int[][] buildPyramid(List<Integer> inputNumbers) {
        try {
            inputNumbers.sort(Comparator.reverseOrder());
        } catch (NullPointerException | OutOfMemoryError e) {
            e.printStackTrace();
            throw new CannotBuildException();
        }
        int countItems = 0;
        for (int i = 1; countItems < inputNumbers.size(); i++) {
            countItems += i;
            countRows++;
        }
        countColumns = countRows + (countRows - 1);
        int[][] pyramid = new int[countRows][countColumns];
        if (countItems == inputNumbers.size()) {
            int count = 0;
            for(int i = 0; i < countRows; i++) {
                int j = 0;
                for(j = j + i; j < countColumns - i; j = j + 2) {
                    pyramid[i][j] = inputNumbers.get(count);
                    count++;
                }
            }
        } else {
            throw new CannotBuildException();
        }
        return reverseArrayOfArrays(pyramid);
    }

    private int[][] reverseArrayOfArrays(int[][] array) {
        int[][] temp = new int[countRows][countColumns];
        for(int i = 0; i < countRows; i++) {
            for(int j = 0; j < countColumns; j++) {
                temp[i][j] = array[countRows - 1 - i][countColumns - 1 - j];
            }
        }
        return temp;
    }

    public void printPyramid(int[][] array) {
        for(int[] a : array) {
            for (int b : a) {
                System.out.print(b);
            }
            System.out.println();
        }
    }
}