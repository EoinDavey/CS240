// This is the Server code, save as DateServer.java
import java.net.*;
import java.io.*;
 
public class DateServer {
   public static void main(String[] args) throws IOException {
       int cnt = 0;
      try {
         // This creates a listener socket 
         ServerSocket sock = new ServerSocket(6013);
         while (true) {
            Socket client = sock.accept();
            cnt++;
            WorkerThread t = new WorkerThread(client, cnt);
            t.run();
         }
      }catch (IOException ioe) {
         System.err.println(ioe);
      }
   }
}

