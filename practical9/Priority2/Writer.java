public class Writer extends Thread {
    DataAccessPolicyManager lockManager;
    int id;

    public Writer (DataAccessPolicyManager lockManager, int id) {
        this.lockManager = lockManager;
        this.id = id;
    }
    public void run(){
        while(true){
            lockManager.acquireWriteLock();
            System.out.println("Writer " + id + " writing\n");
            try {
                sleep ((int)(Math.random()*2000));
            } catch(Exception e){}
            System.out.println("Writer " + id + " stopped writing\n");
            lockManager.releaseWriteLock();
            try {
                sleep ((int)(Math.random()*5000));
            } catch(Exception e){}
        }
    }
}
