import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SubtreeMaxSum {

    public static double getMaxSubval(double[][]matrix, int row) {

        double[] childs = matrix[row];

        double[] filtered_childs = Arrays.stream(childs).filter(r -> {return r != 0;}).toArray();
        if (filtered_childs.length == 0)
            return 0;
        else {
            double maxvar = 0;

            for (int j = 0; j < childs.length; j++) {
                if (childs[j] != 0) {
                    double var1 = matrix[row][j] + getMaxSubval(matrix, j);
                    maxvar = Math.max(maxvar, var1);
                }
            }
            return maxvar;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int nums = Integer.parseInt(sc.nextLine());

        double[][] nmatrix = new double [nums][nums];


        for (int i = 0; i < nums-1; i++) {

            String[] vars1 = sc.nextLine().split(" ");
            int[] vars2 = Arrays.stream(vars1).mapToInt(Integer::valueOf).toArray();
            int row = vars2[0]-1;
            int col = vars2[1]-1;
            int var1 = vars2[2];
            nmatrix[row][col] = var1;

        }

        List<Double> ans = new ArrayList<>();

        for (int i = 0; i < nmatrix.length; i++) {
            ans.add(getMaxSubval(nmatrix, i));
        }

        StringBuilder sb = new StringBuilder();
        ans.stream().forEach(r -> {
            sb.append(r+" ");
        });

        System.out.println(sb.substring(0, sb.length()-1).toString());

    }
}
