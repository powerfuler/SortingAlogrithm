package sorting;

import java.util.Arrays;
import java.util.Random;

/**
 * 7、堆排序
 *
 * 每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆；
 * 或者每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆。
 *
 * 大顶堆：arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]
 * 小顶堆：arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2]
 *
 * 堆排序的基本思想是：将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。将其与末尾元素进行交换，此时末尾就为最大值。
 * 然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了。
 *
 * 再简单总结下堆排序的基本思路：
 * 　　a.将无需序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
 * 　　b.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
 * 　　c.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
 */
public class HeapSort7_2 {
     final static int HeapSize=9; //待排序数组大小
    
    /**
     * 调整堆->大顶堆
     * @param array 待排序数组
     * @param top 堆顶元素下标
     * @param length 待调整的堆长度
     */
    public static void adjustHeap(int array[],int top,int length){
        int temp=array[top]; //暂存堆顶元素
        //比较左右子树根结点，从大的子树向下遍历调整堆
        for(int i=2*top+1;i<length;i=i*2+1){
            //保证i为较大的子树下标
            if(i<length-1&&array[i]<array[i+1]){
                i++;
            }
            if(temp>array[i]){
                break;
            }
            array[top]=array[i];
            top=i;//向下搜索
        }
        array[top]=temp;
    }

    /**
     * 堆排序 
     * @param array 待排序数组
     */
    public static void heapSort(int array[]){
        int length=array.length;
        //初始化大顶堆
        for(int i=(length-2)/2;i>=0;i--){
            adjustHeap(array,i,length);
        }
        //每次取堆顶元素与堆尾元素交换，再重新调整成大顶堆
        for(int i=length-1;i>0;i--){
            int temp=array[0];
            array[0]=array[i];
            array[i]=temp;
            adjustHeap(array,0,i);
        }
    }


    public static void swap(int []arr,int a ,int b){
        int temp=arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // 1、构建小顶堆
    public static void MinHeapSort(int []arr){
        for(int i=arr.length/2-1;i>=0;i--){
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustMinHeap(arr,i,arr.length);
//            System.out.println(Arrays.toString(arr));
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=arr.length-1;j>0;j--){
            swap(arr,0,j);//将堆顶元素与末尾元素进行交换
            adjustMinHeap(arr,0,j);//重新对堆进行调整
//            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 调整堆->小顶堆
     * @param array 待排序数组
     * @param top 堆顶元素下标
     * @param length 待调整的堆长度
     * arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2]
     * 每个结点的值都小于或等于其左右孩子结点的值
     */
    public static void adjustMinHeap(int array[],int top,int length){
        int temp=array[top]; //暂存堆顶元素
        //比较左右子树根结点，从大的子树向下遍历调整堆
        for(int i=2*top+1;i<length;i=i*2+1){
            //保证i为较大的子树下标
            if(i<length-1&&array[i]>array[i+1]){
                i++;
            }
            if(temp<array[i]){
                break;
            }
            array[top]=array[i];
            top=i;//向下搜索
        }
        array[top]=temp;
    }

    // 2、构建大顶堆
    public static void MaxHeapSort(int []arr){
        for(int i=arr.length/2-1;i>=0;i--){
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustMaxHeap(arr,i,arr.length);
//            System.out.println(Arrays.toString(arr));
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=arr.length-1;j>0;j--){
            swap(arr,0,j);//将堆顶元素与末尾元素进行交换
            adjustMaxHeap(arr,0,j);//重新对堆进行调整
//            System.out.println(Arrays.toString(arr));
        }
    }
    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     * @param arr
     * @param i
     * @param length
     *
     * 每个结点的值都大于或等于其左右孩子结点的值
     * arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]
     */
    public static void adjustMaxHeap(int []arr,int i,int length){
        int temp = arr[i];//先取出当前元素i
        for(int k=i*2+1;k<length;k=k*2+1){//从i结点的左子结点开始，也就是2i+1处开始
            if(k+1<length && arr[k]<arr[k+1]){//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if(arr[k] >temp){//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[HeapSize];
        //生成随机数测试
        for (int i = 0; i < HeapSize; i++) {
            arr[i] = random.nextInt(20);
        }
//        int[] arr = {17, 7, 15, 2, 16, 0, 4, 12};
        System.out.println(Arrays.toString(arr));
        MinHeapSort(arr);
        System.out.println(Arrays.toString(arr));

        MaxHeapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
