import java.util.Arrays;

public class Sort {



    public static void InsertSort(int[] arr) {

        int len = arr.length;

        if (len <= 1) {
            return;
        }

        for (int i = 0; i < len; i++) {

            int temp = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > temp) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = temp;
        }

        Arrays.stream(arr).forEach(System.out::print);

    }

    public static int partition(int [] arr, int start, int end) {
        int i = start;
        int j = end;

        while (i < j) {

            while (i < j && arr[i] <= arr[j]) {
                i++;
            }
            if (i < j) {
                arr[i] ^= arr[j];
                arr[j] ^= arr[i];
                arr[i] ^= arr[j];
                j--;
            }

            while (i < j && arr[i] <= arr[j]) {
                j--;
            }
            if (i < j) {
                arr[i] ^= arr[j];
                arr[j] ^= arr[i];
                arr[i] ^= arr[j];
                i++;
            }
        }

        return i;
    }

    public static void doQuickSort(int[] arr, int start, int end) {
        if (start < end) {
            int pivot = partition(arr, start, end);
            doQuickSort(arr, start, pivot-1);
            doQuickSort(arr, pivot, end);
        }
    }
    public static void QuickSort(int[] arr) {

        int len = arr.length;

        if (len <= 1) {
            return;
        }

        int start = 0;
        int end = len-1;
        doQuickSort(arr, start, end);

        Arrays.stream(arr).forEach(System.out::print);

    }

    public static void BubbleSort(int[] arr) {
        int len = arr.length;

        if (len <= 1) {
            return;
        }

        int exchange = len-1;

        while (exchange > 0) {

            int top = exchange;
            for (int i = 0; i < top; i++) {
                if (arr[i] > arr[i+1]) {
                    arr[i] ^= arr[i+1];
                    arr[i+1] ^= arr[i];
                    arr[i] ^= arr[i+1];
                    exchange = i;
                }
            }
        }

        Arrays.stream(arr).forEach(System.out::print);
    }


    public static void sift(int[]arr, int start, int end) {

        int parent = start;
        int lchild = leftchildof(parent);

        while (lchild <= end) {

            lchild = leftchildof(parent);
            int child = lchild;
            if (lchild < end) {
                int rchild = lchild + 1;
                child = (arr[lchild] > arr[rchild]) ? lchild : rchild;
            }

            if (arr[parent] < arr[child]) {
                arr[parent] ^= arr[child];
                arr[child] ^= arr[parent];
                arr[parent] ^= arr[child];
                parent = child;
                lchild = leftchildof(parent);
            } else {
                break;
            }
        }

    }

    public static int leftchildof(int i) {
        return i << 1 + 1;
    }

    public static void HeapSort(int[] arr) {

        int len = arr.length;
        if (len <= 1) {
            return;
        }

        for (int i = len>>1; i >=0; i--) {
            sift(arr, i, len-1);
        }



    }


    public static void main(String[] args) {
        int [] arr = {9,8,4,5,6,2,7,1};
//        Sort.InsertSort(arr);
//        Sort.QuickSort(arr);
        Sort.BubbleSort(arr);
    }
}
