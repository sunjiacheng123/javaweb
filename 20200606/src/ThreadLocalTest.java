public class ThreadLocalTest {
    private static String commStr;
    private static ThreadLocal<String> threadStr = new ThreadLocal<String>();
    public static void main(String[] args) {
        commStr = "main";
        threadStr.set("main");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                commStr = "thread";
                threadStr.set("thread");
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(commStr);
        System.out.println(threadStr.get());
    }
    private static ThreadLocal<String> HOLDER=new ThreadLocal<>();

    public static void main1(String[] args) {
        //ThreadLocal对象都和线程绑定，里边的值每个线程间都是隔离开的
//        HOLDER.get();//获取当前线程中，某个ThreadLocal对象的值
//        HOLDER.remove();//删除当前线程中，某个ThreadLocal对象的值
//        HOLDER.set("abd");//设置当前线程中，某个ThreadLocal对象的值
        try {
            HOLDER.set("abd");
            for(int i=0;i<20;i++){
                final int j=i;
                new Thread(()->{
                    HOLDER.set(String.valueOf(j));
                    try {
                        Thread.sleep(500);
                        System.out.println(HOLDER.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        HOLDER.remove();
                    }
                }).start();
            }
            while (Thread.activeCount()>1){
                Thread.yield();
            }
            System.out.println(HOLDER.get());
        } finally {
            HOLDER.remove();//只要在某个线程设置了ThreadLocal值，在线程结束前，一定要remove
        }
    }
}
