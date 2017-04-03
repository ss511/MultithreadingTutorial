package demo.Multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerBlockingQueue {

	public static void main(String[] args) {
		Messages messages = new Messages();
		BlockingQueue<Messages> sharedQueue = new LinkedBlockingQueue<Messages>();
		Thread producer = new Thread(new Producer(sharedQueue, messages), "producer");
		Thread consumer = new Thread(new Consumer(sharedQueue, messages), "consumer");
		producer.start();
		consumer.start();
	}

}

class Messages{
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}

class Producer implements Runnable{
	
	private BlockingQueue<Messages> queue;
	private Messages messages;
	public Producer(BlockingQueue<Messages> queue, Messages messages){
		this.queue = queue;
		this.messages = messages;
	}
	@Override
	public void run() {
		for(int i = 0; i < 10; i++){
			try {
				Thread.sleep(i);
				messages.setMsg("Messages # " + i);
				queue.put(messages);
				System.out.println("Producer produced : " + messages.getMsg());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		messages.setMsg("exit");
        try {
            queue.put(messages);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
}

class Consumer implements Runnable{

	private BlockingQueue<Messages> queue;
	private Messages messages;
	public Consumer(BlockingQueue<Messages> queue, Messages messages){
		this.queue = queue;
		this.messages = messages;
	}
	@Override
	public void run() {

		try {
			while(!(messages = queue.take()).getMsg().equals("exit")){
				Thread.sleep(10);
				System.out.println("Consumer consumed : " + messages.getMsg());
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}