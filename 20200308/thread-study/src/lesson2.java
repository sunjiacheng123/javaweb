public class lesson2 {
    private static final int NUM = 2;
        private static  void  increment(){
        int count=10_0000_0000;
        for(int i=0;i<count;i++){
            count++;
        }
    }
    //串行
    private static  void  serial(){
        long start=System.currentTimeMillis();//1970-01-01开始，到当前时间的毫秒数
        for(int i =0;i<NUM;i++){
            increment();
        }
        long end=System.currentTimeMillis();
        System.out.printf("串行执行时间：%s毫秒\n",end-start);
    }

    //并行
    private static  void  parallel(){
        long start=System.currentTimeMillis();
        for(int i =0;i<NUM;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    increment();
                }
            }).start();
        }
        //等待new thread所有线程执行完毕，否则一直等待
        while ((Thread.activeCount()>1)){
            Thread.yield();//将当前线程有运行态--->就绪态
        }
        long end=System.currentTimeMillis();
        System.out.printf("并行执行时间：%s毫秒\n",end-start);
    }
    public static void main(String[] args) {
        serial();
        parallel();
        System.out.println(1);
    }
}
