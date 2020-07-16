public class lesson1 {
//    public static void main(String[] args) throws InterruptedException {
//        Thread.sleep(999999999);
//    }
//    public static void main(String[] args) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(999999999);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());//打印线程的名字
            }
        }).start();
        System.out.println(Thread.currentThread().getName());//打印当前线程的名字
    }
}
