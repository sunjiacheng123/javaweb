/*
线程安全问题：
原子性：
* */

public class ThreadUnsafe {
    private static int count=0;
    public static void main(String[] args) {

        //同时启动20个线程，每个线程对同一个变量执行操作，循环10000次，每次循环++操作
        for(int i=0;i<20;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10000;j++){
                        count++;
                    }
                }
            }).start();
        }
        //1.不是预期结果200000
        //2.每次运行结果不一样
        while (Thread.activeCount()>1){
            Thread.yield();
        }
        System.out.println(count);
    }
    //我们看到的 n++，其实是由三步操作组成的：
    //1. 从内存把数据读到 CPU
    //2. 进行数据更新
    //3. 把数据写回到 CPU
    //对象的new操作
    //分解为三条指令：分配对象的内存，初始化对象，将对象赋值给变量
}
