package demo.Multithreading;


class ThreadDemo extends Thread{
	private Thread thread;
	private String threadName;
	public ThreadDemo(String threadName){
		this.threadName = threadName;
	}
	public void run(){
		System.out.println("Running "+threadName);
		try{
			for(int i=4; i>0; i--){
				System.out.println("Thread "+threadName+" "+i);
				Thread.sleep(50);
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
public class ThreadBasicDemo{

	public static void main(String[] args) {
		ThreadDemo t1 = new ThreadDemo("1st Thread Demo");
		ThreadDemo t2 = new ThreadDemo("2nd Thread Demo");
		t1.start();
		t2.start();
	}

}
