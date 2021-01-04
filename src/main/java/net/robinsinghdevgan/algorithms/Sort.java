package net.robinsinghdevgan.algorithms;

public class Sort {
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void doBubbleSort(int[] arr) {
        int size = arr.length;
        for (int i = 0; i < size; ++i) {
            int current = arr[i];
            for (int j = i + 1; j < size; ++j) {
                if (current > arr[j])
                    swap(arr, i, j);
            }
        }
    }

    public static void doInsertionSort(int[] arr) {
        int size = arr.length;
        //sortedPartitionIndex starts from 1 as 0th is already a sorted element.
        for (int sortedPartitionIndex = 1; sortedPartitionIndex < size; sortedPartitionIndex++) {
            int current = arr[sortedPartitionIndex];
            //index i runs backwards, starts from sortedPartitionIndex - 1
            int i = sortedPartitionIndex - 1;
            while(i > -1 && arr[i] > current) {
                arr[i+1] = arr[i];
                --i;
            }
            // (i+1)th is the correct Index for 'current' element
            //finally make the (i+1)th value as arr[sortedPartitionIndex]
            arr[i+1] = current;
        }
    }

    public static void doSelectionSort(int[] arr) {
        int size = arr.length;

        for (int i = 0; i < size; i++) {
            int currentMin = arr[i], currentMinIndex = i;
            for(int j=i+1; j < size; j++) {
                if(arr[j] < currentMin) {
                    currentMinIndex = j;
                    currentMin = arr[j];
                }
            }
            swap(arr, i, currentMinIndex);
        }
    }
}
