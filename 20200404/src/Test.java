import java.util.Scanner;

/*
多线程使用场景：
1.执行比较耗时的操作时
2.执行阻塞式代码，会对当前线程造成阻塞时
 */
public class Test {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int num=sc.nextInt();
        }
        //没有输入，则后序代码不能执行----阻塞式代码
        System.out.println("已经启动");

    }

}
