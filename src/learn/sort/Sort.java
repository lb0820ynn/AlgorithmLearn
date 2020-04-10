package learn.sort;

import learn.Utils;

import java.util.*;

/**
 * Created by liubin on 2017/6/5.
 */
public class Sort {

    public static void main(String[] args) {
        int[] arr = {6, 5, 9, 8, 2, 3, 2, 10, 15};
//        int[] arr = {3, 7, 2, 8, 5, 9,10};

//        quickSortPartition(arr, 0, arr.length - 1);
//        getMinNumbersWithQuickSort(arr, 5);
//        int[] arr2 = {1, -2, 3, 10, -4, 7, 2, -5};
        getMinNumbers(arr, 5);


//        quickSort2(arr, 0, arr.length - 1);
//        quickSort(arr, 0, arr.length - 1);
//        int[] temp = getMinNumbers(arr, 4);
//        heapSort(arr);

        for (int item : arr) {
            System.out.print(item + " ");
        }
    }


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


    public static void quickSortPartition(int[] arr, int low, int high) {
        if (low == high) {
            return;
        }
        int index = partition(arr, low, high);

        if (index > low) {
            quickSortPartition(arr, low, index - 1);
        }
        if (index < high) {
            quickSortPartition(arr, index + 1, high);
        }
    }

    public static int partition(int[] arr, int start, int end) {
        if (arr == null || arr.length <= 0 || start < 0 || end >= arr.length) {
            throw new RuntimeException("data is error");
        }
        int key = start;
        swap(arr, key, end);

        int small = start - 1;
        for (int i = start; i < end; i++) {
            if (arr[i] < arr[end]) {
                ++small;
                if (small != i) {
                    swap(arr, small, i);
                }
            }
        }
        ++small;
        swap(arr, small, end);
        Utils.printIntArr(arr);
        return small;
    }

    public static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
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
            buildMaxHeap(arr, arr.length - 1 - i);
            swap(arr, 0, arr.length - 1 - i);
        }
    }

    /**
     * 构建最大堆
     *
     * @param arr
     * @param lastIndex
     */
    public static void buildMaxHeap(int[] arr, int lastIndex) {
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
     * 利用最大堆
     *
     * @param arr
     * @param k
     * @return
     */
    public static int[] getMinNumbers2(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < k; i++) {
            queue.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (queue.peek() > arr[i]) {
                queue.poll();
                queue.add(arr[i]);
            }
        }
        int res[] = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }
        Utils.printIntArr(res);
        return res;
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

        buildMaxHeap(rangeArr, k - 1);

        for (int i = k; i < arr.length; i++) {
            if (rangeArr[0] > arr[i]) {
                rangeArr[0] = arr[i];
                buildMaxHeap(rangeArr, k - 1);
            }
        }

        Utils.printIntArr(rangeArr);
        return rangeArr;

    }

    /**
     * 从一个数组中获取k个最小值
     *
     * @param arr
     * @param k
     * @return
     */
    public static int[] getMinNumbersWithQuickSort(int[] arr, int k) {
        if (arr == null || k >= arr.length) {
            throw new RuntimeException("k is too big");
        }
        if (k == arr.length - 1) {
            return arr;
        }
        int start = 0;
        int end = arr.length - 1;
        int index = partition(arr, start, end);
        while (index != k - 1) {
            if (index > k - 1) {
                end = index - 1;
                index = partition(arr, start, end);
            } else {
                start = index + 1;
                index = partition(arr, start, end);
            }
        }
        int[] outPut = new int[k];
        for (int i = 0; i < k; i++) {
            outPut[i] = arr[i];
        }
        Utils.printIntArr(outPut);
        return outPut;
    }

    /**
     * 035-数组中的逆序对(归并排序)
     * @param array
     * @return
     */
    public int InversePairs(int [] array) {
        if(array == null || array.length <= 0){
            return 0;
        }
        return merge(array, 0, array.length - 1);
    }

    public int merge(int [] arr, int l, int r){
        if(l >= r){
            return 0;
        }
        int mid = (l + r) >> 1;
        int res = merge(arr, l, mid) + merge(arr, mid+1, r);
        int i = l;
        int j = mid + 1;
        List<Integer> temp = new ArrayList<>();
        while(i <= mid && j<= r){
            if(arr[i] <= arr[j]){
                temp.add(arr[i++]);
            }else{
                res += mid - i + 1;
                temp.add(arr[j++]);
            }
        }
        while(i <= mid){
            temp.add(arr[i++]);
        }
        while(j <= r){
            temp.add(arr[j++]);
        }
        i = l;
        for(int k:temp){
            arr[i++] = k;
        }
        return res;
    }

}
