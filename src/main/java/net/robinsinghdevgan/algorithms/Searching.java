package net.robinsinghdevgan.algorithms;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Searching {

    public static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (key == arr[i]) return i;
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int key) {
        int start = 0, end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key == arr[mid]) return mid;
            else if (key < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
}
