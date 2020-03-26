import java.util.Scanner;

public class MaskEmail {


    public static String getMaskString(String var) {

        StringBuilder sb = new StringBuilder();

        char[] chars = var.toCharArray();
        String mask = "MASK";

        int i = 0;

        for (char c : chars) {
            sb.append(c);
            int index = i % 4;
            char appendchar = mask.charAt(index);
            sb.append(appendchar);
            i++;
        }

        return sb.substring(0, sb.length()-1);

    }
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        String var = sc.nextLine();

        String[] vars = var.split("@");

        String newvar = getMaskString(vars[0]);

        StringBuilder sb = new StringBuilder();

        sb.append(newvar);
        sb.append("@");
        sb.append(vars[1]);

        System.out.println(sb.toString());


    }
}
