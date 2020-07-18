/*
重排序：线程内代码是JVM，CPU都进行重排序，给我们的感觉是线程内的代码是有序的，是因为重排序优化方案会保证线程内代码执行的依赖关系
线程内开自己的代码运行，都是有序的，但是看其他线程代码运行，都是无序的
如果都是私有变量，最终结果都是正确的
 */

/*
解决线程安全
synchronized关键字：
1.静态方法
2.实例方法
3.代码块：synchronized（对象）{ //TOOD }

进入synchronized代码行时，需要获取对象锁：
1.获取成功：往下执行代码
2.获取失败，阻塞在synchronized代码行

退出synchronized代码块，或synchronized方法：
1.把对象锁还给系统
2.通知JVM及系统，其他线程可以来竞争这把锁

synchronized加锁的关注点：
1.对哪个对象进行加锁
2.只有同一个对象，才会有同步互斥的现象
3.对于synchronized内的代码来说，在同一时间点，只有一个县城在运行（没有并发，并行）
4.运行的线程数量越多，性能下降越快（归还对象锁的时候，就有越多的线程被唤醒、阻塞状态切换）
5.同步代码执行时间越短，性能下降越快

多线程操作考虑：1.安全 2.效率
在保证安全的前提下，尽可能的提高效率:
1.代码执行时间比较长，考虑多线程（线程的创建，销毁的时间消耗）
2.如果不能保证安全，所有代码都没有意义--先保证安全，再保证效率

 */

public class Text {
    private static int count=0;
    public static void main(String[] args) {

        //同时启动20个线程，每个线程对同一个变量执行操作，循环10000次，每次循环++操作
        for(int i=0;i<20;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10000;j++){
                        //increment();
//                        synchronized (Text.class){
//                            count++;
//                        }//同步代码块，和静态方法increment（）一样，都是对Text.class对象进行加锁
                        synchronized (this){//对Runnable对象进行加锁，不可以
                            count++;
                        }//同步代码块，和实力方法increment2一样，对this对象进行加锁
                    }
                }
            }).start();
        }
        //1.不是预期结果200000
        //2.每次运行结果不一样
        while (Thread.activeCount()>1){
            Thread.yield();
        }
        System.out.println(count);
    }
    public  static synchronized void increment(){
        count++;
    }


    public synchronized void increment2(){
        count++;
    }
}
