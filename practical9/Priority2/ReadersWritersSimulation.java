public class ReadersWritersSimulation {
    public static void main (String args[]){
        DataAccessPolicyManager policyManager = new DataAccessPolicyManager();
        if(args.length < 2)
            return;
        int r = Integer.parseInt(args[0]);
        int w = Integer.parseInt(args[1]);
        Reader readers[] = new Reader[r];
        Writer writers[] = new Writer[w];
        for(int i = 0; i < w; i++){
            writers[i] = new Writer(policyManager,i+1);
            writers[i].start();
        }
        for(int i = 0; i < r; i++){
            readers[i] = new Reader(policyManager,i+1);
            readers[i].start();
        }
    }
}
