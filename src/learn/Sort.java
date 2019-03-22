package learn;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by liubin on 2017/6/5.
 */
public class Sort {

    /**
     * 快速排序
     *
     * @param array
     * @param low
     * @param high
     */
    public static void quickSort(int[] array, int low, int high) {
        int key = array[low];
        int start = low;
        int end = high;

        while (start < end) {

            while (start < end && array[end] >= key)
                end--;
            if (array[end] <= key) {
                swap(array, end, start);
            }

            while (start < end && array[start] <= key)
                start++;

            if (array[start] >= key) {
                swap(array, start, end);
            }

        }

        if (start > low) {
            quickSort(array, low, start - 1);
        }

        if (end < high) {
            quickSort(array, end + 1, high);
        }
    }

    public static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void quickSort2(int[] array, int low, int high) {
        int start = low;
        int end = high;
        int key = array[low];

        while (start < end) {

            while (start < end && array[end] >= key) {
                end--;
            }
            if (array[end] <= key) {
                swap(array, start, end);
            }

            while (start < end && array[start] <= key) {
                start++;
            }
            if (array[start] >= key) {
                swap(array, start, end);
            }
        }
        if (start > low) {
            quickSort(array, low, start - 1);
        }
        if (end < high) {
            quickSort(array, end + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] arr = {6, 5, 9, 8, 2, 3, 2, 10, 15};
//        int[] arr = {3, 7, 2, 8, 5, 9,10};

        heapSort(arr);

//        arr = mergeSort(arr, 0, arr.length - 1);

//        quickSort2(arr, 0, arr.length - 1);
//        quickSort(arr, 0, arr.length - 1);
//        int[] temp = getMinNumbers(arr, 4);
//        heapSort(arr);

        for (int item : arr) {
            System.out.print(item + " ");
        }
    }

    public static void buildMaxHeap0321(int[] arr, int lastIndex) {

        int mid = (lastIndex - 1) / 2;
        for (int i = mid; i >= 0; i--) {
            int k = i;
            while (2 * k + 1 <= lastIndex) {
                int childIndex = 2 * k + 1;
                int rightChildIndex = childIndex + 1;
                if (rightChildIndex <= lastIndex && arr[childIndex] < arr[rightChildIndex]) {
                    childIndex = rightChildIndex;
                }
                if (arr[k] < arr[childIndex]) {
                    swap(arr, k, childIndex);
                    k = childIndex;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 归并排序
     *
     * @param array
     * @param low
     * @param high
     * @return
     */
    public static int[] mergeSort(int[] array, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {

            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);

            merge(array, mid, low, high);
        }

        return array;
    }

    private static void merge(int[] array, int mid, int low, int high) {
        int[] temp = new int[high - low + 1];
        int index1 = low;
        int index2 = mid + 1;
        int tempIndex = 0;

        while (index1 <= mid && index2 <= high) {

            if (array[index1] < array[index2]) {
                temp[tempIndex++] = array[index1];
                index1++;
            } else {
                temp[tempIndex++] = array[index2];
                index2++;
            }
        }

        while (index1 <= mid) {
            temp[tempIndex++] = array[index1++];
        }

        while (index2 <= high) {
            temp[tempIndex++] = array[index2++];
        }

        for (int x = 0; x < temp.length; x++) {
            array[x + low] = temp[x];
        }
    }

    /**
     * 堆排序
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            buildMaxHeap0321(arr, arr.length - 1 - i);
            swap(arr, 0, arr.length - 1 - i);
        }
    }

    /**
     * 构建最大堆
     *
     * @param arr
     * @param lastIndex
     */
    private static void buildMaxHeap(int[] arr, int lastIndex) {

        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            int k = i;
            while (2 * k + 1 <= lastIndex) {
                int childIndex = 2 * k + 1;

                if (childIndex < lastIndex) {
                    if (arr[childIndex] < arr[childIndex + 1]) {
                        childIndex++;
                    }
                }

                if (arr[k] < arr[childIndex]) {
                    swap(arr, k, childIndex);
                    k = childIndex;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 从一个数组中获取k个最小值
     *
     * @param arr
     * @param k
     * @return
     */
    public static int[] getMinNumbers(int[] arr, int k) {
        if (k >= arr.length) {
            throw new RuntimeException("k is too big");
        }

        if (k == arr.length - 1) {
            return arr;
        }
        int[] rangeArr = Arrays.copyOfRange(arr, 0, k);

        heapSort(rangeArr);

        for (int i = k; i < arr.length; i++) {
            if (rangeArr[k - 1] > arr[i]) {
                rangeArr[k - 1] = arr[i];
                heapSort(rangeArr);
            }
        }

        return rangeArr;

    }
}
