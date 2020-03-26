import java.util.*;
import java.util.stream.Collectors;

public class Baidu_2_TimeSchedule {


    public static boolean checkTask(Map<Integer, List<Integer>> timemap) {


        List<Map.Entry<Integer, List<Integer>>> entries = timemap.entrySet().stream().sorted((a, b) -> {

            return a.getKey() - b.getKey();

        }).collect(Collectors.toList());

        int curtime = 0;

        for (Map.Entry<Integer, List<Integer>> entry : entries) {


            int deadline = entry.getKey();
            List<Integer> var2 = entry.getValue().stream().sorted().collect(Collectors.toList());

            int sumtime = var2.stream().mapToInt(Integer::valueOf).sum();

            if ((curtime + sumtime) > deadline)
                return false;

            curtime += sumtime;

        }
        return true;

    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int groups = Integer.valueOf(sc.nextLine());

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < groups; i++) {

            int lines = Integer.valueOf(sc.nextLine());
            Map<Integer, List<Integer>> timemap = new HashMap<>();

            for (int j = 0; j < lines; j++) {

                String[] vars = sc.nextLine().split(" ");
                int time = Integer.valueOf(vars[0]);
                int deadline = Integer.valueOf(vars[1]);

                List<Integer> tasks = timemap.getOrDefault(deadline, new ArrayList<>());
                tasks.add(time);
                timemap.put(deadline, tasks);
            }

            String result = checkTask(timemap) ? "YES" : "NO";
            ans.add(result);
        }

        ans.stream().forEach(System.out::println);
    }
}
