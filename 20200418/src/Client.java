import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Client {
    private static final String Host="127.0.0.1";
    private static final int PORT=9000;

    public static void main(String[] args) throws IOException {
        Socket client=new Socket(Host,PORT);
        BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out=new PrintWriter(client.getOutputStream(),true);
        Scanner sc=new Scanner(System.in);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (sc.hasNextLine()){
                    String line=sc.nextLine();
                    out.println(line);
                }
            }
        }).start();
        String str;
        while ((str=in.readLine())!=null){
            System.out.println(str);
        }

    }
}
