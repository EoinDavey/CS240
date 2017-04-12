public class Reader extends Thread {
    DataAccessPolicyManager lockManager;
    int id;

    public Reader (DataAccessPolicyManager lockManager, int id) {
        this.lockManager = lockManager;
        this.id = id;
    }
    public void run() {
        while(true){
            lockManager.acquireReadLock();
            System.out.println("Reader " + id + " reading\n");
            try{
                sleep ((int)(Math.random()*2000));
            }catch(Exception e){}
            System.out.println("Reader " + id + " stopped Reading\n");
            lockManager.releaseReadLock();
            try{
                sleep ((int)(Math.random()*5000));
            }catch(Exception e){}
        }
    }
}

