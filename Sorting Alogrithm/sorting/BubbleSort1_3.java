package sorting;

/**
 * 1、冒泡排序
 * 从小到大
 */
public class BubbleSort1_3 {

	/**
	 * 一般排序
	 * @param nums
	 */
	public static void bubbleSort(int[] nums) {
		int n = nums.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				// 位置交换
				if (nums[j] > nums[j + 1]) {
					int temp = nums[j];
					nums[j] = nums[j + 1];
					nums[j + 1] = temp;
				}
			}
            printArray(i, nums);
		}
	}

	/**
	 * 改进的冒泡排序
	 * 设置flag标志，判断排序是否提前结束 避免运行过多次数，优化代码
	 * @param nums
	 */
	public static void BubbleSortImprove(int[] nums) {
		int n = nums.length;
		for (int i = 0; i < n - 1; i++) {
			int flag = 0;
			for (int j = 0; j < n - i - 1; j++) {
				if (nums[j] > nums[j + 1]) {
					int temp = nums[j];
					nums[j] = nums[j + 1];
					nums[j + 1] = temp;
					// 进行了元素交换的标志
					flag = 1;
				}
			}
			if (flag == 0) {
				break;
			}
            printArray(i, nums);
		}
	}

	/**
	 * 改进的冒泡排序
	 * 设置flag标志，判断排序是否提前结束 避免运行过多次数，优化代码
	 * 记录最后一次的交换边界,减少了交换次数
	 * @param array
	 */
	public static void sort(int[] array) {
		//记录最后一次交换的位置
		int lastExchangeIndex = 0;
		//无序数列的边界，每次比较只需要比到这里为止
		int sortBorder = array.length - 1;
		for (int i = 0; i < array.length; i++) {
			//有序标记，每一轮的初始是true
			boolean isSorted = true;
			for (int j = 0; j < sortBorder; j++) {
				if (array[j] > array[j + 1]) {
					int tmp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = tmp;
					// 有元素交换，所以不是有序，标记变为false
					isSorted = false;
					// 把无序数列的边界更新为最后一次交换元素的位置
					lastExchangeIndex = j;
				}
			}
			sortBorder = lastExchangeIndex;
			if (isSorted) {
				break;
			}
            printArray(i, array);
		}
	}

	// 鸡尾酒排序
	public static void sort1(int[] array) {
		int tmp = 0;
		for (int i = 0; i < array.length / 2; i++) {
			//有序标记，每一轮的初始是true
			boolean isSorted = true;
			//奇数轮，从左向右比较和交换
			for (int j = i; j < array.length - i - 1; j++) {
				if (array[j] > array[j + 1]) {
					tmp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = tmp;
					//有元素交换，所以不是有序，标记变为false
					isSorted = false;
				}
			}
			if (isSorted) {
				break;
			}
			//偶数轮之前，重新标记为true
			isSorted = true;
			//偶数轮，从右向左比较和交换
			for (int j = array.length - i - 1; j > i; j--) {
				if (array[j] < array[j - 1]) {
					tmp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = tmp;
					//有元素交换，所以不是有序，标记变为false
					isSorted = false;
				}
			}
			if (isSorted) {
				break;
			}
			printArray(i, array);
		}
	}

	public static void main(String[] args) {
		int nums[] = {8, 5, 2, 6, 9, 3, 1, 4, 0, 7, 11, 12, 13};

		// 鸡尾酒排序
		int nums1[] = {2,3,4,5,6,7,8,1};
//		bubbleSort(nums);
//      BubbleSortImprove(nums);
		sort1(nums1);
	}

	private static void printArray(int j, int[] nums) {
		System.out.print("第" + (j + 1) + "轮比较，序列为:");
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

}
