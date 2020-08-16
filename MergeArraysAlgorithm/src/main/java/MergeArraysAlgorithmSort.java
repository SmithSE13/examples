public class MergeArraysAlgorithmSort {

    public int[] sort(int[] array) {
        int conditionEndRecursion = 1;
        //Condition for continue the recursion
        if (array.length != conditionEndRecursion) {
            int halfArrayIndex = array.length/2;
            int[] firstHalfArray = new int[halfArrayIndex];
            int[] secondHalfArray = new int[array.length - halfArrayIndex];
            for (int i = 0; i < array.length; i++) {
                if(i < firstHalfArray.length) {
                    firstHalfArray[i] = array[i];
                } else {
                    secondHalfArray[i - halfArrayIndex] = array[i];
                }
            }
            //Recursion
            firstHalfArray = sort(firstHalfArray);
            secondHalfArray = sort(secondHalfArray);
            return merge(firstHalfArray, secondHalfArray);
        }
        return array;
    }

    private int[] merge(int[] firstArray, int[] secondArray) {
        int indexFirstArray = 0;
        int indexSecondArray = 0;
        int[] mergeArray = new int[firstArray.length + secondArray.length];
        //Fulling the array
        for (int i = 0; i < mergeArray.length; i++) {
            if (firstArray.length == indexFirstArray) {
                mergeArray[i] = secondArray[indexSecondArray];
                indexSecondArray++;
            } else if (secondArray.length == indexSecondArray){
                mergeArray[i] = firstArray[indexFirstArray];
                indexFirstArray++;
            } else if (secondArray[indexSecondArray] < firstArray[indexFirstArray]) {
                mergeArray[i] = secondArray[indexSecondArray];
                indexSecondArray++;
            } else {
                mergeArray[i] = firstArray[indexFirstArray];
                indexFirstArray++;
            }
        }
        return mergeArray;
    }
}
