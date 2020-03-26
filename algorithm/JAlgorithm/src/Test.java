import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;

import java.util.Arrays;
import java.util.List;

public class Test implements Runnable {


    public void run() {

        String[] a = new String[] {"1", "2"};

        List list = Arrays.asList(a);
        list.clear();
        list.add(3);
        System.out.println(list.toString());
    }


}
