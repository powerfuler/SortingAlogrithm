package sorting;

import java.util.Arrays;

/**
 * 6、归并排序
 * 从小到大
 */

public class MergeSort6_2 {

    public static void mergeSort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    /**
     * 递归分解数组
     * @param nums
     * @param low
     * @param high
     */
    public static void sort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = (low + high) / 2;
        sort(nums, low, mid);
        sort(nums, mid + 1, high);

        merge(nums, low, mid, high);

        for (int item : nums) { // 显示每次合并数组的结果
            System.out.print(item + " ");
        }
        System.out.println();
    }

    /**
     * 合并数组
     * @param nums
     * @param low
     * @param mid
     * @param high
     */
    public static void merge(int[] nums, int low, int mid, int high) {
        int[] temp = new int[nums.length];
        // 把数组nums当前合并部分复制到临时数组对应位置
        for (int k = low; k <= high; k++) {
            temp[k] = nums[k];
        }
        int i = low; // 第一个合并数组的起点
        int j = mid + 1;// 第二个合并数组的起点
        // 合并并排序
        for (int k = low; k <= high; k++) {
            // k为当前位置
            if (i > mid) {
                // 左边数组为空
                nums[k] = temp[j];
                j++;
            } else if (j > high) {
                // 右边数组为空
                nums[k] = temp[i];
                i++;
            } else if (temp[i] > temp[j]) {
                // 左边元素大于右边元素，插入右边元素
                nums[k] = temp[j];
                j++;
            } else {
                // 左边元素小于右边元素，插入左边
                nums[k] = temp[i];
                i++;
            }
        }
    }


    static public void mergeSort(int[] array, int start, int end) {
        if (start < end) {
            //折半成两个小集合，分别进行递归
            int mid = (start + end) / 2;
            mergeSort(array, start, mid);
            mergeSort(array, mid + 1, end);
            //把两个有序小集合，归并成一个大集合
            merge1(array, start, mid, end);

            for (int item : array) { // 显示每次合并数组的结果
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    static private void merge1(int[] array, int start, int mid, int end) {
        //开辟额外大集合，设置指针
        int[] tempArray = new int[end - start + 1];
        int p1 = start, p2 = mid + 1, p = 0;
        //比较两个小集合的元素，依次放入大集合

        while (p1 <= mid && p2 <= end) {
            if (array[p1] <= array[p2])
                tempArray[p++] = array[p1++];
            else
                tempArray[p++] = array[p2++];
        }
        //左侧小集合还有剩余，依次放入大集合尾部

        while (p1 <= mid)
            tempArray[p++] = array[p1++];

        //右侧小集合还有剩余，依次放入大集合尾部

        while (p2 <= end)
            tempArray[p++] = array[p2++];
        //把大集合的元素复制回原数组
        for (int i = 0; i < tempArray.length; i++)
            array[i + start] = tempArray[i];
    }

    public static void main(String[] args) {
//        int nums[] = {5, 2, 6, 3, 1, 4, 0, 7};
        int nums[] = {2, 1, 3, 0};
        System.out.println(Arrays.toString(nums));
//        mergeSort(nums);
//        mergeSort(nums, 0, nums.length - 1);
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }


    public static void sort(int []arr){
        int []temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        sort(arr,0,arr.length-1,temp);
    }
    private static void sort(int[] arr,int left,int right,int []temp){
        if(left<right){
            int mid = (left+right)/2;
            sort(arr,left,mid,temp);//左边归并排序，使得左子序列有序
            sort(arr,mid+1,right,temp);//右边归并排序，使得右子序列有序
            merge(arr,left,mid,right,temp);//将两个有序子数组合并操作
            System.out.println(Arrays.toString(arr));
        }
    }
    private static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;//左序列指针
        int j = mid+1;//右序列指针
        int t = 0;//临时数组指针
        while (i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        while(i<=mid){//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while(j<=right){//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while(left <= right){
            arr[left++] = temp[t++];
        }
    }
}
