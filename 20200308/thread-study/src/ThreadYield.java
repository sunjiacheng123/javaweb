//线程让步
public class ThreadYield {
    public static void main1(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
//        while (Thread.activeCount()>1){
//            Thread.yield();//将当前线程有运行态--->就绪态
//        }
        System.out.println(Thread.currentThread().getName());
    }



}
