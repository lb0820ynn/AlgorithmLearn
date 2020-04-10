## 排序相关

### 1.快速排序

```java
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
```

### 2.归并排序

```java
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
```

3.堆排序

```java
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
```

