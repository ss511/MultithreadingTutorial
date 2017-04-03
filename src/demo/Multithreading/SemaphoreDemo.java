package demo.Multithreading;

public class SemaphoreDemo {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore();
		Thread s1 = new Thread(new Sender(semaphore), "sender1");
		Thread r1 = new Thread(new Receiver(semaphore), "receiver1");
		Thread s2 = new Thread(new Sender(semaphore), "sender2");
		Thread s3 = new Thread(new Sender(semaphore), "sender3");
		Thread s4 = new Thread(new Sender(semaphore), "sender4");
		Thread r2 = new Thread(new Receiver(semaphore), "receiver2");
		Thread r3 = new Thread(new Receiver(semaphore), "receiver3");
		Thread r4 = new Thread(new Receiver(semaphore), "receiver4");
		Thread r5 = new Thread(new Receiver(semaphore), "receiver5");
		s1.start();
		s2.start();
		r1.start();
		s3.start();
		s4.start();
		r2.start();
		r3.start();
		r4.start();
		//r5.start();
	}

}

class Semaphore{
	private int signal = 0;
	
	public synchronized void take(){
		this.signal++;
		System.out.println("Semaphore in take is::: " + this.signal);
		notify();
	}
	
	public synchronized void release() throws InterruptedException{
		while(this.signal == 0) wait();
		System.out.println("Signal received");
		this.signal--;
		System.out.println("Semaphore in release is::: " + this.signal);
	}
}

class Sender implements Runnable{
	Semaphore semaphore = null;
	public Sender(Semaphore semaphore) {
		this.semaphore = semaphore;
	}
	@Override
	public void run() {
		int i = 0;
		while(true){
			System.out.println(Thread.currentThread().getName() + " ---- " + i++);
			if(i == 5)
				break;
		}
		this.semaphore.take();
		
	}
}

class Receiver implements Runnable{
	Semaphore semaphore = null;
	public Receiver(Semaphore semaphore) {
		this.semaphore = semaphore;
	}
	@Override
	public void run() {
		try {
			this.semaphore.release();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		int i = 0;
		while(true){
			System.out.println(Thread.currentThread().getName() + " ----- " + i++);
			if(i == 5)
				break;
		}
	}
}