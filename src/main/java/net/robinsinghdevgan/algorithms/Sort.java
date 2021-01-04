package net.robinsinghdevgan.algorithms;

import java.util.TreeMap;

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
        // sortedPartitionIndex starts from 1 as 0th is already a sorted element.
        for (int sortedPartitionIndex = 1; sortedPartitionIndex < size; sortedPartitionIndex++) {
            int current = arr[sortedPartitionIndex];
            // index i runs backwards, starts from sortedPartitionIndex - 1
            int i = sortedPartitionIndex - 1;
            while (i > -1 && arr[i] > current) {
                arr[i + 1] = arr[i];
                --i;
            }
            // (i+1)th is the correct Index for 'current' element
            // finally make the (i+1)th value as arr[sortedPartitionIndex]
            arr[i + 1] = current;
        }
    }

    public static void doSelectionSort(int[] arr) {
        int size = arr.length;

        for (int i = 0; i < size; i++) {
            int currentMin = arr[i], currentMinIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (arr[j] < currentMin) {
                    currentMinIndex = j;
                    currentMin = arr[j];
                }
            }
            swap(arr, i, currentMinIndex);
        }
    }

    // https://www.geeksforgeeks.org/heap-sort/
    // Java program for implementation of Heap Sort
    private static class HeapSort {
        protected static void sort(int arr[]) {
            int n = arr.length;

            // Build heap (rearrange array)
            for (int i = n / 2 - 1; i >= 0; i--)
                heapify(arr, n, i);

            // One by one extract an element from heap
            for (int i = n - 1; i > 0; i--) {
                // Move current root to end
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;

                // call max heapify on the reduced heap
                heapify(arr, i, 0);
            }
        }

        // To heapify a subtree rooted with node i which is
        // an index in arr[]. n is size of heap
        private static void heapify(int arr[], int n, int i) {
            int largest = i; // Initialize largest as root
            int l = 2 * i + 1; // left = 2*i + 1
            int r = 2 * i + 2; // right = 2*i + 2

            // If left child is larger than root
            if (l < n && arr[l] > arr[largest])
                largest = l;

            // If right child is larger than largest so far
            if (r < n && arr[r] > arr[largest])
                largest = r;

            // If largest is not root
            if (largest != i) {
                int swap = arr[i];
                arr[i] = arr[largest];
                arr[largest] = swap;

                // Recursively heapify the affected sub-tree
                heapify(arr, n, largest);
            }
        }
    }

    public static void doHeapSort(int[] arr) {
        HeapSort.sort(arr);
    }

    public static void doCountingSort(int[] arr) {
        var dict = new TreeMap<Integer, Integer>();
        for (int i : arr) {
            if (dict.containsKey(i)) {
                dict.put(i, dict.get(i) + 1);
            } else {
                dict.put(i, 1);
            }
        }
        int i = 0;
        for(var entry: dict.entrySet()) {
            int num = entry.getKey();
            int occurrences = entry.getValue();
            while(occurrences-- > 0) {
                arr[i++] = num;
            }
        }
    }
}
