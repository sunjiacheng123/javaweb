public class Sington {
    //库存面包数量：上限100
    public static volatile int sum;

    public static void main(String[] args) {
        //启动5个生产者，生产面包
        for(int i=0;i<5;i++){
            new Thread(new Producer(),"面包师傅"+i).start();

        }
        //启动消费者线程，消费面包
        for(int i=0;i<20;i++){
            new Thread(new Consumer(),"消费者"+i).start();
        }
    }
    //默认生产者：面包师傅生产面包：一次生产3个面包,每个面包师傅生产20次
    private static class Producer implements Runnable{
        @Override
        public void run() {
            for(int i=0;i<20;i++){
                synchronized (Sington.class){
                    //生产完后，库存大于100不行，所以库存在97以上不能生产
                    while (sum+3>100){
                        //释放对象锁，需要让其他线程进入同步代码块，当前线程需要进入阻塞状态
                        try {
                            Sington.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    sum+=3;//生产面包
                    Sington.class.notify();
                    System.out.println(Thread.currentThread().getName()+"，生产了，库存为："+sum);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //默认消费者：消费面包：一次消费一个面包，消费者一直消费
    private static class Consumer implements Runnable{
        @Override
        public void run() {
            while (true){
                synchronized (Sington.class){
                    //库存为0，不能继续消费，阻塞当前线程
                    //不能用if判断，
                    while (sum==0){
                        try {
                            Sington.class.wait();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    sum--;
                    //notify和notifyAll都是通知调用wait（）被阻塞的线程
                    //notify随机唤醒一个wait（）阻塞的线程
                    //notifyAll唤醒全部wait（）阻塞的线程
                    //在synchronized代码块结束，也就是释放对象锁之后，才会唤醒
                    //等于说，synchronized结束之后，wait（）和synchronized代码行阻塞的线程，都会被唤醒

                    Sington.class.notifyAll();
                    //最好是notifyAll，唤醒所有
                    System.out.println(Thread.currentThread().getName()+"，消费了，库存为："+sum);

                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
