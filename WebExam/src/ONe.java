import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
/*
找出数组中只出现一次的数字
 */
public class ONe {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            int n=sc.nextInt();
            Map<Integer,Integer> map=new TreeMap<>();
            while (n>0){
                n--;
                int a=sc.nextInt();
                if(map.containsKey(a)){
                    map.remove(a);
                }else {
                    map.put(a,1);
                }
            }
            Iterator it=map.keySet().iterator();
            while (it.hasNext()){
                System.out.print(it.next()+" ");
            }
        }
    }
}
