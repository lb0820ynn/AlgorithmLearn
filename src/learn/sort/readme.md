## 最小的k个数

### 题目描述

输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。

### 思路

思路1：

- 最小堆建立(需要o(n)时间复杂度)
- 取出前k个数(每次取需要用logn时间重建堆)。时间复杂度为o(n)+o(k*logn)

思路2:

- 用快排partition划分，一直划中第k个数 最差情况o(kn)

```java
		/**
     * 利用最大堆
     *
     * @param arr
     * @param k
     * @return
     */
    public static int[] getMinNumbers(int[] arr, int k) {
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
        return res;
    }
```



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

### 035-数组中的逆序对(归并排序)

## 题目描述

在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007

```java
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
```