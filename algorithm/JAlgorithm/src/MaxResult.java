import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MaxResult {


    public static void add(String deadline, String time, String[]starts) {
        String[] var1 = deadline.split(":");
        String[] var2 = time.split(":");

        int deadline_hour = Integer.parseInt(var1[0]);
        int deadline_minutes = Integer.parseInt(var1[1]);

        int time_hour = Integer.parseInt(var2[0]);
        int time_minutes = Integer.parseInt(var2[1]);

        int res_hour = deadline_hour - time_hour;
        int res_minutes = 0;

        if (deadline_minutes < time_minutes) {
            res_hour --;
            res_minutes = 60 - time_minutes;
        } else {
            res_minutes = deadline_minutes - time_minutes;
        }

        int res = 60*res_hour + res_minutes;
        int d = Integer.MAX_VALUE;
        int ans_hour = 0;
        int ans_minutes = 0;

        int index = 0;

        int i = 0;
        for (String var3 : starts) {

            String[] var4 = var3.split(":");
            int hour = Integer.parseInt(var4[0]);
            int minutes = Integer.parseInt(var4[1]);


            int total_minutes = 60 * hour + minutes;

            int dminutes = Math.abs(total_minutes - res);
            if (dminutes < d) {
                ans_hour = hour;
                ans_minutes = minutes;
                d = dminutes;
                index = i;
            }
            i++;


        }


        System.out.println(starts[index]);

    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int var1 = Integer.valueOf(sc.nextLine());
//
//        int limit = (var1 % 2 == 0) ? var1 >> 1 : ((var1 >> 1) + 1);
//
//
//        String[] var2 = sc.nextLine().split(" ");
//
//        int result = Arrays.stream(var2).mapToInt(Integer::valueOf)
//                .boxed().sorted((a, b) -> {return b - a;})
//                .mapToInt(Integer::valueOf)
//                .limit(limit)
//                .sum();
//
//        System.out.println(result);
//
//        int [][]voteRecordArr = new int[10][10];
//        int rows = 10;
//        HashMap<Integer, Integer> result = new HashMap<>();
//
//        for (int i = 0; i < rows; i++) {
//
//            int[] vars = voteRecordArr[i];
//
//            Arrays.stream(vars).forEach(r -> {
//                int var1 = result.getOrDefault(r, 0);
//                result.put(r, var1+1);
//            });
//
//        }
//
//        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
//            int num = entry.getKey();
//            int counts = entry.getValue();
//            System.out.println(num+"-"+counts+"票");
//        }


        String deadline = "11:10";
        String time = "01:00";

        String[] starts = {"09:50", "10:31"};

        MaxResult.add(deadline, time, starts);
    }

}
