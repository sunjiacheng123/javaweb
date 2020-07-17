/*
1.	Thread.interrupted() 判断当前线程的中断标志被设置，清除中断标志
2.	Thread.currentThread().isInterrupted() 判断指定线程的中断标志被设置，不清除中断标志

* */

public class Thread1 {
    public static void main1(String[] args) {
//        Thread thread=new Thread(new Runnable() {
//            @Override
//            public void run() {
        //线程运行时，需要自行判断线程中断标志位
//                while(!Thread.currentThread().isInterrupted()){
//                    System.out.println();
//                }
//                while (!Thread.interrupted()){
//                    System.out.println(Thread.currentThread().getName());
//                }
//            }
//        });
//        thread.start();//中断标志位=false
//        thread.interrupt();//中断标志位=true

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().isInterrupted());
                    Thread.sleep(30000);
                    //线程调用wait（）/join（）/sleep（）方法阻塞当前线程，会直接抛出异常
                    //阻塞状态是，通过捕获及处理异常，来处理线程的中断逻辑
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //捕获到异常之后，线程的中断标志位，被重置
                    System.out.println(Thread.currentThread().isInterrupted());
                }
            }
        });
        thread.start();//中断标志位=false
        thread.interrupt();//中断标志位=true


    }
    //自己创建标志位
    private  static volatile  boolean IS_INTERRUPTED;
    //
    public static void main(String[] args) {
         Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (!IS_INTERRUPTED){
                    System.out.println(Thread.currentThread().getName());
                }
                try {
                    //自定义的标志位满足不了线程处于阻塞状态时，中断操作
                    Thread.sleep(99999);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();//中断标志位=false
        IS_INTERRUPTED=true;//中断标志位=true
    }

}
