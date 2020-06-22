import java.util.ArrayList;
import java.util.List;

public class Subsequence {

    /**
     * Проверяет, можно ли получить последовательность, равную первой
     * один, удалив некоторые элементы из второго.
     *
     * x - первая последовательность
     * y - вторая последовательность
     *  Возвращает true если возможно, иначе false
     */

    public boolean find(List x, List y) {
        try {
            ArrayList<Object> indexesItems = new ArrayList<>();
            if (x.size() <= y.size()) {
                for(Object itemX : x) {
                    if (y.contains(itemX)) {
                        indexesItems.add(y.indexOf(itemX));
                    }
                }
                return checkIndexesSequence(parseListIndexes(indexesItems), x);
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException();
        }
    }

    private boolean checkIndexesSequence(List<Integer> listIndexes, List<Object> listSequence) {
        int count = 0;
        if(listIndexes.size() == listSequence.size()) {
            for(int i = 0; i < listIndexes.size(); i++) {
                if (i != listIndexes.size() - 1 && listIndexes.get(i) <= listIndexes.get(i + 1)) {
                    count++;
                } else if (i == listIndexes.size() - 1) {
                    count++;
                } else {
                    break;
                }
            }
        }
        return count == listIndexes.size();
    }

    private List<Integer> parseListIndexes(List<Object> list) {
        List<Integer> ints = new ArrayList<>();
        for(Object element : list) {
            ints.add((int) element);
        }
        return ints;
    }
}