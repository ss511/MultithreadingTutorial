package demo.Multithreading;

public class RaceConditionDemo {

	public static void main(String[] args) {
		Counter counter = new Counter();
		Thread t1 = new Thread(new RaceCondition(counter));
		Thread t2 = new Thread(new RaceCondition(counter));
		t1.start();
		t2.start();
	}

}

class RaceCondition implements Runnable{
	Counter counter;
	public RaceCondition(Counter counter){
		this.counter = counter;
	}
	@Override
	public void run() {
		counter.add(20);
		/*for(int i = 0; i < 10; i++){
			counter.add(i);
		}*/
	}
		
	
}

class Counter{
	protected long count = 0;
	StringBuilder sb = new StringBuilder();
	public void add(long value){
		synchronized (this) {
			this.count = this.count + value;
			System.out.println(this.count);
			//sb.append("Hello");
			System.out.println(sb.append("Hello").toString());
		}
	}
}
