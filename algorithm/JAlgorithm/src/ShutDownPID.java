import java.util.*;

public class ShutDownPID {

    public static int getChilds(Map<Integer, List<Integer>>map, int node, Set<Integer> visited) {

        if (visited.contains(node))
            return 0;

        visited.add(node);
        int var = 1;
        List<Integer> list = map.get(node);

        for (Integer i : list) {
            var += getChilds(map, node, visited);
        }

        return var;

    }


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);


        String var1 = sc.nextLine();
        String var2 = sc.nextLine();
        String var3 = sc.nextLine();

        String[] pids = var1.split(" ");
        String[] parentpids = var2.split(" ");
        int pid = Integer.valueOf(var3);

        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, new ArrayList<>());

        for (int i = 0; i < pids.length; i++) {
            int nodevar = Integer.valueOf(pids[i]);
            int parentvar = Integer.valueOf(parentpids[i]);
            List<Integer> list = map.getOrDefault(parentvar, new ArrayList<>());
            list.add(nodevar);
            map.put(parentvar, list);

        }

        int nodes = 1;

        List<Integer> childs = map.get(pid);
        Set<Integer> visited = new HashSet<>();

        for (Integer i : childs) {
            visited.add(i);
            nodes++;
            nodes += getChilds(map, i, visited);
        }

        System.out.println(nodes);

    }
}
