import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ConvertNum {


    public static void getString(StringBuilder sb, String symbols, int var) {

        if (var < 27) {
            sb.setCharAt(sb.length()-1, symbols.charAt(var));
            return;
        }

        int i = 0;
        while (Math.pow(27, i) < var) {
            i++;
        }

        int e = i - 1;
        if (e < sb.length()-1) {
            int n = (int) (var / Math.pow(27, e));
            sb.append(symbols.charAt(n));
            sb.setCharAt(e, symbols.charAt(n));
            int left = var - 27 * n;
            getString(sb, symbols, left);
        } else  {
            sb.append('1');
            for (int j = 0; j < i; j++) {
                sb.append('0');
            }
        }

    }


    public static void getBits(LinkedList<String> list, int var, String symbols) {

         if (var < 27) {
             list.offerLast(String.valueOf(symbols.charAt(var)));
             return;
         }

         int i = 0;

         while (Math.pow(27, i) < var) i++;

         int n = (int) (var / Math.pow(27, i-1));

        list.offerLast(String.valueOf(symbols.charAt(n)));

         int left = (int) (var - n*Math.pow(27, i-1));
         getBits(list, left, symbols);

    }


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        int var = Integer.valueOf(sc.nextLine());

        String symbols = "0123456789`!@#$%^&*(){}\\<>?";

        int i = 0;

        while (Math.pow(27, i) < var) i++;

        int n = (int) (var / Math.pow(27, i-1));
        int[] bits;
        if (Math.pow(27, i) == var) {
            bits = new int[i+1];
            bits[i] = n;
        }
        else {
            bits = new int[i];
            bits[i-1] = n;
        }



//        bits[i] = n;

        var = (int) (var - n*Math.pow(27, i-1));
        int p = 2;

        while (var >= 27) {

            int j = 0;
            while (Math.pow(27, j) < var) j++;
            n = (int) (var / Math.pow(27, j-1));
            bits[bits.length-p] = n;
            p++;
            var = (int) (var - n*Math.pow(27, j-1));

        }
        bits[0] = var;

        StringBuilder sb = new StringBuilder();

        Arrays.stream(bits).forEach(r -> {
            sb.append(symbols.charAt(r));
        });

        System.out.println(sb.reverse().toString());

//        LinkedList<String> list = new LinkedList<>();
//
//        StringBuilder sb = new StringBuilder();
//
//        getBits(list, var, symbols);
//
//        System.out.println(sb.toString());

    }
}
