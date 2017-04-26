import java.net.*;
import java.io.*;
public class WorkerThread implements Runnable{

    Socket client;
    int id;

    public WorkerThread(Socket client, int id){
        this.client = client;
        this.id = id;
    }
    
    public void run(){
        try{
            PrintWriter pout = new  PrintWriter(client.getOutputStream(),true);
            pout.println(new java.util.Date().toString());
            pout.println("finished processing client " + id);
            client.close();
        }catch(Exception e){}
    }
}
