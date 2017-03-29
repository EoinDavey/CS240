import java.util.Date;
public class Producer implements Runnable {
    private Buffer buffer;
    int ID;

    public Producer(Buffer buffer, int id) {
        this.buffer = buffer;
        this.ID = id;
    }

    public void run() {
        Date message;
        while (true) {
            message = new Date(); // produce an item
            try {
                Thread.sleep(1000); // sleep for 1000 ms
            } catch (InterruptedException e) {}
            buffer.insert(message);
            System.out.println("Producer " + ID + " Inserted "+ message);
        }
    }
}
