public class Main{
    public static void main(String[] args) {
        //创建线程的方式1
        MyThread myThread=new MyThread();
        //myThread.start();
        //run方法直接调用，不会启动线程，只是在当前main线程中，调用了run方法
        myThread.run();
        //new MyThread().start();
        //创建线程的方式2
        new Thread(new MyThread()).start();//线程启动时通过start方法启动的
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        //run是线程运行的时候执行的代码块
        System.out.println(Thread.currentThread().getName());
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run() {
        //run是线程运行的时候执行的代码块
        System.out.println(Thread.currentThread().getName());
    }
}