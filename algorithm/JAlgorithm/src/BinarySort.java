public class BinarySort {


    public static int binsort(int[]a, int target) {

        int start = 0;
        int end = a.length-1;

        while (start < end) {

            int mid = (start + end) >>> 1;

            if (a[mid] < target)
                end = mid - 1;
            else if (a[mid] > target)
                start = mid + 1;
            else
                return a[mid];
        }

        return a[end]; //return the first element less than target

    }
}
