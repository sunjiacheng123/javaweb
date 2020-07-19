package lesson1;

import java.util.Scanner;

public class ScannerTest {

    /**
     * （1）理解题目的要求——10组测试用例，每个测试用例完成某一块业务/逻辑
     * （2）每个测试用例：
     *                 先输入一行数字（整形）4
     *                 再输入一行数字（一组数字）4个数字：5 6 2 1
     *                 再做。。。。业务
     */
    public static void main(String[] args) {
        // 每个测试用例要干的事情
        Scanner sc = new Scanner(System.in);
        // 10组测试用例
        for(int i=0; i<10; i++){
            // 多线程的阻塞也是需要满足一定条件才返回：synchronized，join，sleep，wait
            // 1.都是阻塞式方法：造成当前线程的阻塞
            // 2.满足一定的条件才返回：
            // hasNext满足换行符特殊字符，next满足换行符/空格/特殊字符
            // hasNextLine满足换行符,
            // hasNextInt读取到不满足Int数值的字符

            // io流中的数据，进行读/写操作只能执行一次
//            sc.hasNext();dfsjfsd sdfsf\n
//            while(sc.hasNext()){//非读取数据的操作
////                sc.next();
//                System.out.println(sc.next());//读取的操作，返回数据不包含特殊字符
//            }

            int count = sc.nextInt();//读取第一行数字：4
            for(int j=0; j<count; j++){
                int num = sc.nextInt();
                System.out.println(num);
            }
        }
    }
}
