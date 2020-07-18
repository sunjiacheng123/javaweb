/*
volatile修饰的共享变量，可以保证可见性，部分保证顺序性
volatile注意点：
1.不能保证原子性
2.volatile修饰的变量，进行赋值不能依赖变量（常量赋值可以保证线程安全）

使用场景：可以结合线程加锁的一些手段，提高线程效率
 */

public class volatileThread {
    private  static volatile  boolean IS_INTERRUPTED;
    //
    public static void main1(String[] args) {
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
        //直接修改到主内存
        //此处修改保证Runnable线程可以看见
    }


    //加上volatile修饰，不能保证原子性
    private static  volatile int count=0;
    public static void main2(String[] args) {

        //同时启动20个线程，每个线程对同一个变量执行操作，循环10000次，每次循环++操作
        for(int i=0;i<20;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10000;j++){
                         count++;
                         //count++分解为三条指令
                        //1.读取主内存count变量
                        //2.count=count+1
                        //3.写回主内存

                        //三条指令之间可能运行别的指令，破坏了原子性
                        //volatile对变量进行赋值操作时，需要时常量（不能依赖变量）
                    }
                }
            }).start();
        }
        while (Thread.activeCount()>1){
            Thread.yield();
        }
        System.out.println(count);
    }


    private static  volatile int count1=0;
    public static void main(String[] args) {

        //同时启动20个线程，每个线程对同一个变量执行操作，循环10000次，每次循环++操作
        for(int i=0;i<20;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10000;j++){

                            synchronized (volatileThread.class){
                                if(count<100000){
                                    count++;
                                }
                            }

                    }
                }
            }).start();
        }
        while (Thread.activeCount()>1){
            Thread.yield();
        }
        System.out.println(count);
    }
}
