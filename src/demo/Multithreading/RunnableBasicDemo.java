package demo.Multithreading;

import java.util.concurrent.Semaphore;

class RunnableDemo implements Runnable{
	private String threadName;
	public RunnableDemo(String threadName){
		this.threadName = threadName;
	}
	public void run(){
		System.out.println("Running "+threadName);
		try{
			for(int i=5; i>0; i--){
				System.out.println("Thread- "+threadName+" "+i);
				//Thread.sleep(1);
			}
		}
		catch(Exception e){
			System.out.println("Thread- "+threadName+" interrupted");
		}
		System.out.println("Thread- " +  threadName + " exiting.");
	}

}
public class RunnableBasicDemo {

	public static void main(String[] args) {

		Thread t1 = new Thread(new RunnableDemo("Thread 1 Demo"));
		Thread t2 = new Thread(new RunnableDemo("Thread 2 Demo"));
		Thread t3 = new Thread(new RunnableDemo("Thread 3 Demo"));
		t1.setPriority(1);
		t2.setPriority(10);
		t3.setPriority(7);
		t1.start();
		try{
			t1.join();
		}catch(Exception e){
			e.printStackTrace();
		}
		t2.start();
		t3.start();
	
	}

}
