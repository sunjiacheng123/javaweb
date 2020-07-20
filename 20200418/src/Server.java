import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT=9000;
    private static final ExecutorService pool=Executors.newCachedThreadPool();
    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(9000);
        while(true){
            //阻塞等待，直到有新的客户端连接
            Socket client=server.accept();
            pool.execute(new Task(client));
        }

    }
    private static class Task implements Runnable{
        private Socket client;
        public Task(Socket client){
            this.client=client;
        }
        public void run(){
            try {
                BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out=new PrintWriter(client.getOutputStream(),true);
                String str;
                int i=0;
                while ((str=in.readLine())!=null){
                    System.out.println(str);
                    out.println(i+"确实牛逼");
                    i++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
