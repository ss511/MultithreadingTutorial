package demo.Multithreading;

public class LockDemo {

	public static void main(String[] args) {
		Index index = new Index();
		Thread t1 = new Thread(new LockThread(index), "Thread1");
		Thread t2 = new Thread(new LockThread(index), "Thread2");
		Thread t3 = new Thread(new LockThread(index), "Thread3");
		
		t1.start();
		t2.start();
		t3.start();
	}
	
}

class LockThread implements Runnable{
	Index index;
	public LockThread(Index index) {
		this.index = index;
	}
	
	public void run() {
		int i = index.inc();
		System.out.println("Index is::: " + i);
		
	}
}

class Index{
	private Lock lock = new Lock();
	private int count = 0;
	
	public int inc(){
		int newCount = 0;
		try {
			lock.lock();
			newCount = ++count;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return newCount;
	}
}

class Lock{
	private boolean isLocked = false;
	
	public synchronized	void lock() throws InterruptedException{
		while(isLocked){
			wait();
		}
		isLocked = true;
	}
	
	public synchronized void unlock(){
		isLocked = false;
		notify();
	}
}