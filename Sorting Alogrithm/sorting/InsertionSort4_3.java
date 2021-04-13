package sorting;

public class InsertionSort4_3 {
    /**
     * 4、插入排序
     * 从小到大
     * @param nums
     */
    public static void insertionSort(int[] nums) {
        int j;
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            for (j = i - 1; j >= 0 && temp < nums[j]; j--) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = temp;
        }
    }

    public static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int insertValue = array[i];
            int j = i - 1;
            //从右向左比较元素的同时，进行元素复制
            for (; j >= 0 && insertValue < array[j]; j--) {
                array[j + 1] = array[j];
            }
            //insertValue的值插入适当位置
            array[j + 1] = insertValue;
        }
    }


    public static void main(String[] args) {
        int nums[] = {8, 5, 2, 6, 9, 3, 1, 4, 0, 7};
        insertionSort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
