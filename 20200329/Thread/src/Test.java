import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class Test {
    public static void main0(String[] args) throws InterruptedException {
        ExecutorService pool = new ThreadPoolExecutor(//线程池---快递公司
                3,// 核心线程数（正式员工）：创建好线程池，正式员工就开始取快递

                // 临时工雇佣：正式员工忙不过来，就会创建临时工
                // 临时工解雇：空闲时间超出设置的时间范围，就解雇
                5,// 最大线程数（最多数量的员工：正式员工+临时工）

                30,// 时间数量
                TimeUnit.SECONDS,// 时间单位（时间数量+时间单位表示一定范围的时间）

                // 阻塞队列：存放包裹的仓库（存放任务的数据结构）
                new ArrayBlockingQueue<>(1000),

                // （了解）线程池创建Thread线程的工厂类。没有提供的话，就使用线程池内部默认的创建线程的方式
//                new ThreadFactory() {
//                    @Override
//                    public Thread newThread(Runnable r) {
//                        return null;
//                    }
//                },

                // 拒绝策略：
                // CallerRunsPolicy：谁（execute代码行所在的线程）让我（快递公司）送快递，不好意思，你自己去送
                // AbortPolicy：直接抛出异常RejectedExecutionException
                // DiscardPolicy：从阻塞队列丢弃最新的任务（队尾）
                // DiscardOldestPolicy：从阻塞队列丢弃最旧的任务（队首）
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("送快递到北京，A同学");
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("送快递到新疆，B同学");
            }
        });

        System.out.println("我正在做事情");
    }

    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(4);//正式员工
//        ExecutorService pool = Executors.newCachedThreadPool();//正式员工为0，临时工数量不限制
        //延迟一秒执行
//        pool.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("起床了");
//            }
//        }, 1, TimeUnit.SECONDS);//延迟时间，执行任务
//        //延迟1秒执行，每隔一秒执行
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("起床了");
            }
        }, 1, 1, TimeUnit.SECONDS);

    }
}
