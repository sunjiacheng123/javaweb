import jdk.nashorn.internal.ir.WithNode;

//线程等待
public class ThreadJoin {
    public static void without() throws InterruptedException {
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                    System.out.println(Thread.currentThread().getName());
            }
        });
        t.start();
        t.join();//当前线程等待，直到t线程执行完毕
        System.out.println(Thread.currentThread().getName());
    }

    public static void with() throws InterruptedException {
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t.join(2000);
        //当前线程main线程等待2秒钟就往下执行了（t线程等待时间更长）
        System.out.println(Thread.currentThread().getName());
    }

    public static void  main(String[] args) throws InterruptedException {
        //先将t线程执行完毕，再往下执行
        //当前线程：代码行执行时，所在的线程
        //t线程：线程引用对象
        //当前线程阻塞（运行态-->阻塞态）等待（满足一定条件），t线程（不做限制，自由调度）
        //一定条件：以下条件哪个先执行完，就满足
        // 1.传入时间（时间值+时间单位毫秒）
        // 2.线程引用对象执行完毕
        without();
        with();
    }
}
