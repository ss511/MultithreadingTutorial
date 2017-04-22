package demo.Multithreading;

public class VersesThread {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Start:: "+System.currentTimeMillis());
		Thread t1 = new Thread(new MyThread(), "Thread1");
		Thread t2 = new Thread(new MyThread(), "Thread2");
		Thread t3 = new Thread(new MyThread(), "Thread3");
		t1.start();
		t2.start();
		t3.start();
		
		//t1.join();
		//t2.join();
		//t3.join();
		
		System.out.println("End:: "+System.currentTimeMillis());
		
		System.out.println("Start 1:: "+System.currentTimeMillis());
		
		for(int i = 0; i <3000; i++){
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println("End 1:: "+System.currentTimeMillis());
	}

}

class MyThread implements Runnable{

	@Override
	public void run() {
		
		if(Thread.currentThread().getName().equals("Thread1")){
			for(int i = 0; i < 1000; i++){
				System.out.print(i + " ");
			}
		}
		if(Thread.currentThread().getName().equals("Thread2")){
			for(int i = 1000; i < 2000; i++){
				System.out.print(i + " ");
			}
		}
		if(Thread.currentThread().getName().equals("Thread3")){
			for(int i = 2000; i < 3000; i++){
				System.out.print(i + " ");
			}
		}
		System.out.println();
	}
	
}
