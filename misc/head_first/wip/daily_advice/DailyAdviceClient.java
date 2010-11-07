import java.io.*;
import java.net.*;

public class DailyAdviceClient {
    public void go() {
        try {
            Socket sock = new Socket("127.0.0.1", 4242);
            InputStreamReader stream = new InputStreamReader(sock.getInputStream());
            BufferedReader reader = new BufferedReader(stream);
            System.out.println("Today you should " + reader.readLine());
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        DailyAdviceClient client = new DailyAdviceClient();
        client.go();
    }
}
