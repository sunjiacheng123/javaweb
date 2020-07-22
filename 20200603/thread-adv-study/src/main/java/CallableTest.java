import java.util.concurrent.*;

/*
callable 创建线程：

 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> c=new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("call");
                return 123;
            }
        };

        FutureTask<Integer> task=new FutureTask<Integer>(c);
        //thread使用Callable
        new Thread(task).start();
        System.out.println("main");
        //当前线程阻塞等待，直到线程执行完毕（join效果差不多），但可以获取线程的返回值
        Integer r=task.get();
        System.out.println(r);
        //线程池使用callable
        ExecutorService pool= Executors.newFixedThreadPool(4);
        Future<Integer> future=pool.submit(c);
        System.out.println("main");
        Integer r1=future.get();
        System.out.println(r1);
    }
}
