public class DataAccessPolicyManager {
    private int readCount, writeCount;
    private Semaphore mutexReadCount, mutexWriteCount;
    private Semaphore wrt, rdr;

    public DataAccessPolicyManager() {
        readCount = 0;
        writeCount = 0;
        mutexReadCount = new Semaphore(1);
        mutexWriteCount = new Semaphore(1);
        wrt = new Semaphore(1);
        rdr = new Semaphore (1);
    }

    public void acquireReadLock() {
        rdr.acquire(); // Reading can enter if no writers waiting
        mutexReadCount.acquire();
        readCount = readCount + 1;
        if (readCount == 1) wrt.acquire(); // block future writers
        mutexReadCount.release();
        rdr.release(); // allow another reader in unless writer took it
    }

    public void releaseReadLock() {
        mutexReadCount.acquire();
        readCount = readCount - 1;
        if (readCount == 0) wrt.release();
        mutexReadCount.release();
    }

    public void acquireWriteLock() {
        mutexWriteCount.acquire();
        writeCount = writeCount+1;
        if (writeCount == 1) rdr.acquire();
        mutexWriteCount.release();
        wrt.acquire();
    }

    public void releaseWriteLock() {
        wrt.release();
        mutexWriteCount.acquire();
        writeCount = writeCount - 1;
        if (writeCount==0) rdr.release();
        mutexWriteCount.release();
    }
}

