import java.util.Scanner;

public class ComplexMultipy {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String var1 = sc.nextLine();
        String var2 = sc.nextLine();

        String[] vars1 = var1.split("\\+");
        String[] vars2 = var2.split("\\+");

        int creal1 = Integer.valueOf(vars1[0]);
        int creal2 = Integer.valueOf(vars2[0]);

        String cimage1str = vars1[1].substring(0, vars1[1].lastIndexOf("i"));
        String cimage2str  = vars2[1].substring(0, vars2[1].lastIndexOf("i"));

        int cimage1 = (null == cimage1str || "".equals(cimage1str)) ? 1 : "-".equals(cimage1str) ? -1 : Integer.valueOf(cimage1str);
        int cimage2 = (null == cimage2str || "".equals(cimage2str)) ? 1 : "-".equals(cimage1str) ? -1 : Integer.valueOf(cimage2str);


        int resreal = creal1*creal2 - cimage1*cimage2;
        int resimage = creal1*cimage2 + cimage1*creal2;

        StringBuilder sb = new StringBuilder();

        sb.append(resreal).append("+").append(resimage).append("i");
        System.out.println(sb.toString());

    }
}
