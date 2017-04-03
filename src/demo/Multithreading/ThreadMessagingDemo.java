package demo.Multithreading;

public class ThreadMessagingDemo {

	public static void main(String[] args) {
		Message msg = new Message("Process It");
		Thread waiter1 = new Thread(new Waiter(msg), "Waiter1");
		Thread waiter2 = new Thread(new Waiter(msg), "Waiter2");
		Thread notifier = new Thread(new Notifier(msg), "Notifier");
		waiter1.start();
		waiter2.start();
		notifier.start();
		System.out.println("All the threads have started");
	}

}

class Message{
	private int counter;

	private String msg;

	public Message(String msg){
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter += counter;
	}
}

class Waiter implements Runnable{

	private Message msg;
	
	public Waiter(Message msg){
		this.msg = msg;
	}
	
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		synchronized (msg) {
			try{
				System.out.println(name + " waiting to get notified at time: " + System.currentTimeMillis());
				msg.wait();
			}catch(Exception e){e.printStackTrace();}
			msg.setCounter(1);
			System.out.println(name + " got notified at time: " + System.currentTimeMillis());
			System.out.println(name + " processed: " + msg.getMsg() + " on counter: " + msg.getCounter());
		}
	}
}

class Notifier implements Runnable{
	private Message msg;
	
	public Notifier(Message msg){
		this.msg = msg;
	}
	
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println(name + " started");
		try{
			Thread.sleep(2000);
			synchronized (msg) {
				msg.setCounter(1);
				msg.setMsg(name + " Notifier work done on counter:" + msg.getCounter());
				//msg.notify();
				msg.notifyAll();
			}
		}catch(Exception e){e.printStackTrace();}
	}
	
}