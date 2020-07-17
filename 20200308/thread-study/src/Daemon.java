/*
java进程的退出：至少有一个非守护线程没有退出
非守护线程一般可以成为工作线程，守护线程可以称为后台线程

优先级更高的线程更有可能先执行，但不是一定，只是几率更大

 */

public class Daemon {
    public static void main(String[] args) {
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(9999999999L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //设置线程为守护线程
        t.setDaemon(true);
        //t.start();
    }
}
