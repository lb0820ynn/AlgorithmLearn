### 001-二维数组查找

```java
/**
 * 001-二维数组查找
 *
 * @param target
 * @param array
 * @return
 */
public boolean Find(int target, int[][] array) {
    int row = 0;
    int col = array[0].length - 1;
    while (row <= array.length - 1 && col >= 0) {
        if (target == array[row][col])
            return true;
        else if (target > array[row][col])
            row++;
        else
            col--;
    }
    return false;
}
```

### 006-旋转数组的最小数字（二分查找）

```java
/**
 * 006-旋转数组的最小数字（二分查找）
 * <p>
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 * @param array
 * @return
 */
public static int minNumberInRotateArray(int[] array) {
    if (array == null || array.length <= 0) {
        return 0;
    }
    if (array.length == 1) {
        return array[0];
    }
    int left = 0;
    int right = array.length - 1;
    int mid = 0;
    while (array[left] >= array[right]) {
        if (right - left == 1) {
            return array[right];
        }
        mid = (left + right) / 2;
        if (array[mid] > array[left]) {
            left = mid;
        } else if (array[mid] < array[left]) {
            right = mid;
        } else {
            return minNum(array, left, right);
        }
    }
    return array[mid];
}

private static int minNum(int[] array, int left, int right) {
    int min = array[left];
    for (int i = left + 1; i <= right; i++) {
        if (array[i] < min) {
            min = array[i];
        }
    }
    return min;
}
```

### 037-数字在排序数组中出现的次数（二分查找）

```java
/**
 * 037-数字在排序数组中出现的次数
 *
 * @param array
 * @param k
 * @return
 */
public int GetNumberOfK(int[] array, int k) {
    int i1 = biSearch(array, k + 0.5d);
    int i2 = biSearch(array, k - 0.5d);
    if (array[array.length - 1] == k) {
        i1 += 1;
    }
    return i1 - i2;
}

public int biSearch(int[] arr, double n) {
    int l = 0;
    int r = arr.length - 1;
    while (l < r) {
        int mid = l + r >> 1;
        if (arr[mid] < n) {
            l = mid + 1;
        } else if (arr[mid] > n) {
            r = mid;
        }
    }
    return l;
}
```