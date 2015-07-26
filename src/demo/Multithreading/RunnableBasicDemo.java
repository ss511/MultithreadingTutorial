package demo.Multithreading;

class RunnableDemo implements Runnable{
	private Thread thread;
	private String threadName;
	public RunnableDemo(String threadName){
		this.threadName = threadName;
	}
	public void run(){
		System.out.println("Running "+threadName);
		try{
			for(int i=4; i>0; i--){
				System.out.println("Thread "+threadName+" "+i);
				Thread.sleep(500);
			}
		}
		catch(InterruptedException e){
			System.out.println("Thread "+threadName+" interrupted");
		}
		System.out.println("Thread " +  threadName + " exiting.");
	}
	public void start(){
		System.out.println("Starting "+threadName );
		if(thread == null){
			thread = new Thread(this, threadName);
			thread.start();
		}
	}

}
public class RunnableBasicDemo {

	public static void main(String[] args) {

		RunnableDemo r1 = new RunnableDemo("1st Runnable Demo");
		RunnableDemo r2 = new RunnableDemo("2nd Runnable Demo");
		r1.start();
		r2.start();
	
	}

}
