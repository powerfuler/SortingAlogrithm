package sorting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 快速排序 升序排序
 */
public class QuickSort {

    public static void quickSort(int[] nums) {
        partition(nums, 0, nums.length - 1);
    }

    /**
     * 1、分治法、递归、挖坑法
     * @param left
     * @param right
     * @param array
     */
    public static void partition(int[] array, int left, int right) {
        //        打印输出测试
        for (int item : array) {
            System.out.print(item + " ");
        }
        System.out.println();
        if (left >= right)
            return;
        int target = array[left];
        int i = left;
        int j = right;
        while (i < j) {
            // 从后向前扫描
            while (i < j && array[j] >= target)
                j--;
            if (array[j] < target) {
                array[i] = array[j];
                i++;
            }
            // 从前往后扫描
            while (i < j && array[i] <= target) {
                i++;
            }
            if (array[i] > target) {
                array[j] = array[i];
                j--;
            }
        }
        array[i] = target;
        partition(array, left, i - 1);
        partition(array, i + 1, right);
    }

    /**
     * 2、指针交换法
     * @param startIndex
     * @param endIndex
     * @param arr
     */
    public static void quickSortPointer(int[] arr, int startIndex, int endIndex) {
        // 递归结束条件：startIndex大等于endIndex的时候
        if (startIndex >= endIndex) {
            return;
        }
        // 得到基准元素位置
        int pivotIndex = partition2(arr, startIndex, endIndex);

        // 根据基准元素，分成两部分递归排序
        quickSortPointer(arr, startIndex, pivotIndex - 1);
        quickSortPointer(arr, pivotIndex + 1, endIndex);
    }

    /**
     * 3、非递归实现，栈排序
     * @param startIndex
     * @param endIndex
     * @param arr
     */
    public static void quickSortStack(int[] arr, int startIndex, int endIndex) {
        // 用一个集合栈来代替递归的函数栈
        Stack<Map<String, Integer>> quickSortStack = new Stack<Map<String, Integer>>();
        // 整个数列的起止下标，以哈希的形式入栈
        Map rootParam = new HashMap();
        rootParam.put("startIndex", startIndex);
        rootParam.put("endIndex", endIndex);
        quickSortStack.push(rootParam);

        // 循环结束条件：栈为空时结束
        while (!quickSortStack.isEmpty()) {
            // 栈顶元素出栈，得到起止下标
            Map<String, Integer> param = quickSortStack.pop();
            // 得到基准元素位置
            int pivotIndex = partition2(arr, param.get("startIndex"), param.get("endIndex"));

            // 根据基准元素分成两部分, 把每一部分的起止下标入栈
            if (param.get("startIndex") < pivotIndex - 1) {
                Map<String, Integer> leftParam = new HashMap<String, Integer>();
                leftParam.put("startIndex", param.get("startIndex"));
                leftParam.put("endIndex", pivotIndex - 1);
                quickSortStack.push(leftParam);
            }
            if (pivotIndex + 1 < param.get("endIndex")) {
                Map<String, Integer> rightParam = new HashMap<String, Integer>();
                rightParam.put("startIndex", pivotIndex + 1);
                rightParam.put("endIndex", param.get("endIndex"));
                quickSortStack.push(rightParam);
            }
        }
    }

    private static int partition2(int[] arr, int startIndex, int endIndex) {
        // 取第一个位置的元素作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;

        while (left != right) {
            //控制right指针比较并左移
            while (left < right && arr[right] > pivot) {
                right--;
            }
            //控制right指针比较并右移
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            //交换left和right指向的元素
            if (left < right) {
                int p = arr[left];
                arr[left] = arr[right];
                arr[right] = p;
            }
        }
        //pivot和指针重合点交换
        int p = arr[left];
        arr[left] = arr[startIndex];
        arr[startIndex] = p;

        return left;
    }


    public static void main(String[] args) {
        int nums[] = {4, 7, 6, 5, 3, 2, 8, 1, 0};
        // 1、分治法、递归、挖坑法
//        quickSort(nums);
//        for (int i : nums) {
//            System.out.print(i + " ");
//        }

        int nums1[] = {4, 7, 6, 5, 3, 2, 8, 1, 0};
        // 2、指针交换法
//        quickSortPointer(nums1, 0, nums1.length- 1);
        System.out.println(Arrays.toString(nums1));

        // 3、非递归实现 、栈
        int nums2[] = {4, 7, 6, 5, 3, 2, 8, 1, 0};
        quickSortStack(nums2, 0, nums2.length - 1);
        System.out.println(Arrays.toString(nums2));
    }

}
