class DiningPhilosophers1 {
	public static void main(String args[]) {
		Semaphore chopSticks[];
		Philosopher workerThread[];

		chopSticks = new Semaphore[5];

		for (int i=0; i<5; i++)
			chopSticks[i] = new Semaphore(1);

		workerThread = new Philosopher[5];

		for (int i=0; i<5; i++) {
			workerThread[i] = new Philosopher(i, chopSticks);
			workerThread[i].start();
		}
	}
}





class Philosopher extends Thread {
	private int myName;
	private Semaphore chopSticks[];

	public Philosopher(int myName, Semaphore chopSticks[]) {
		this.myName = myName;
		this.chopSticks = chopSticks;
	}

	public void run() {
		while (true) {
			System.out.println("Philosopher "+myName+" thinking.");
			try {
				sleep ((int)(Math.random()*20000));
			} catch (InterruptedException e) {}

			System.out.println("Philosopher "+myName+" hungry.");
            if(((myName)&1)!=0){
                chopSticks[myName].acquire();
                chopSticks[(myName+1)%5].acquire();
            } else {
                chopSticks[(myName+1)%5].acquire();
                chopSticks[myName].acquire();
            }
			System.out.println("Philosopher "+myName+" eating.");
			try { 
				sleep ((int)(Math.random()*10000));
			} catch (InterruptedException e) {}

			chopSticks[myName].release();
			chopSticks[(myName+1)%5].release();
		}
	}
}
