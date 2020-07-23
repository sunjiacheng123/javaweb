import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class ThreadWaitThenDo {

    public static void main1(String[] args) {
        for(int i=0;i<20;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName());
            }).start();
        }
        //入口线程执行t1方法：入口线程阻塞等待，知道US哦呦子线程执行完毕
        while (Thread.activeCount()>1){
            Thread.yield();
        }
        System.out.println("执行完毕"+Thread.currentThread().getName());
    }

    public static void main2(String[] args) throws InterruptedException {
        List<Thread> threads=new ArrayList<>();
        for(int i=0;i<20;i++){
            Thread t=new Thread(()->{
                System.out.println(Thread.currentThread().getName());
            });
            threads.add(t);
            t.start();
        }
        //入口线程阻塞等待，知道US哦呦子线程执行完毕
        for(Thread t:threads){
            t.join();
        }
        System.out.println("执行完毕"+Thread.currentThread().getName());
    }
    public static void main3(String[] args) throws InterruptedException {
        CountDownLatch cd1=new CountDownLatch(20);//计数器的初始值
        for(int i=0;i<20;i++){
            Thread t=new Thread(()->{
                System.out.println(Thread.currentThread().getName());
                cd1.countDown();//计数器的值-1
            });

            t.start();
        }
        cd1.await();//当前线程阻塞等待，知道计数器的值为0
        System.out.println("执行完毕"+Thread.currentThread().getName());
    }

    public static void main4(String[] args) throws InterruptedException {
        Semaphore s=new Semaphore(0);
        for(int i=0;i<20;i++){
            Thread t=new Thread(()->{
                System.out.println(Thread.currentThread().getName());
                s.release();//资源量+1
            });
            t.start();
        }
        s.acquire(20);//获取到20个资源后，才会继续往下执行
        System.out.println("执行完毕"+Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        //模拟服务端接收客户端黄体酮请求：只有1000个并发
        // （在一个时间点，客户端任务数达到1000，再有客户端请求，将阻塞等待）
        Semaphore s=new Semaphore(1000);
        for(;;){
            Thread t=new Thread(()->{
                try {
                    s.acquire();
                    //模拟每个线程处理客户端http请求
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    s.release();
                }


            });
            t.start();
        }
    }
}
