package 回溯_BackTracking;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static int getSum(int base, int count) {
        int sum = base;
        for(int i = 0; i < count; i++) {
            sum += (base+count);
        }
        return sum;
    }


    public static void getAns(int[]days, int index, int base, int sum, List<Integer>allnums) {

        if (index == days.length-1) {
            if (days[index] == 0) {
                allnums.add(sum);
            } else if (days[index] == 1) {
                allnums.add(sum+base+1);
            } else {
                allnums.add(sum+base+1);
                allnums.add(sum);
            }
        } else {
            int tempsum = sum;
            int tempbase = base;
            for (int i = index; i <= days.length-1; i++) {
                if (days[i] == 0) {
                    tempbase = 0;
                    continue;
                } else if (days[i] == 1) {
                    tempbase += 1;
                    tempsum += tempbase;
                } else {
                    if (i >= days.length - 1) {
                        allnums.add(tempsum+tempbase+1);
                        allnums.add(tempsum);

                    } else {
                        getAns(days, i+1, tempbase+1, tempsum+tempbase+1, allnums);
                        getAns(days, i+1, 0, tempsum, allnums);
                    }
                    return;
                }
            }
            allnums.add(tempsum);

        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String line1 = sc.nextLine();
        String line2 = sc.nextLine();

        String[] lines1 = line1.split(" ");

        int n = Integer.valueOf(lines1[0]);
        BigInteger bigInteger = BigInteger.valueOf(Long.valueOf(lines1[1]));

        String[] lines2 = line2.split(" ");

        BigInteger cirsum = BigInteger.valueOf(0);

        List<Long>costs = Arrays.stream(lines2).mapToLong(Long::valueOf).boxed().collect(Collectors.toList());

        costs.stream().forEach(r->cirsum.add(BigInteger.valueOf(r)));

        BigInteger cirs = bigInteger.divide(cirsum);

        BigInteger ans = cirs.multiply(BigInteger.valueOf(n));

        BigInteger left = bigInteger.mod(cirsum);

        BigInteger zero = BigInteger.ZERO;
        for (Long cost:costs) {
            if (left.subtract(BigInteger.valueOf(cost)).compareTo(zero) >= 0)
                ans.add(BigInteger.ONE);
            else
                break;
        }
        System.out.println(ans.toString());

//        Scanner sc = new Scanner(System.in);
//
//
//            int n = Integer.valueOf(sc.nextLine());
//            String var = sc.nextLine();
//
//            String[] vars = var.split(" ");
//            HashMap<Integer, Integer> map = new HashMap<>();
//
//            int counts = 0;
//
//            int len = vars.length;
//
//            int[] arrs = Arrays.stream(vars).mapToInt(Integer::valueOf).toArray();
//            List<Integer> ans = new ArrayList<>();
//
//            getAns(arrs, 0, 0, 0, ans);
//
//
//            double[] result = new double[1];
//            ans.stream().forEach(r -> result[0] += r);
//            System.out.println(result[0] / ans.size());
//

    }

}
