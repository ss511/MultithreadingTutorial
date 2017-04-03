package demo.Multithreading;

public class VolatileDemo {

	public static void main(String[] args) {
		Counters counters = new Counters();
		Thread t1 = new Thread(new RaceConditions("Thread1", counters));
		Thread t2 = new Thread(new RaceConditions("Thread2", counters));
		t1.setPriority(5);
		t2.setPriority(3);
		t1.start();
		
		t2.start();
		//System.out.println(t1.getName() + "----" + t2.getName());
	}

}

class RaceConditions extends Thread{
	Counters counters;
	public RaceConditions(String threadName, Counters counters) {
		super(threadName);
		this.counters = counters;
	}
	@Override
	public void run() {
		try{
			for(int i = 0; i < 3; i++){
				if("Thread1".equals(getName())){
					counters.setVal();
				}
				else if("Thread2".equals(getName())){
					counters.displayVal();
				}
			}
		}catch(Exception e){}
	}	
}

class Counters{
	private int val;
	public void setVal(){
		val += 10;
		System.out.println("Value is set - "+val);
	}
	public void displayVal(){
		System.out.println("Value = " + val);
	}
	
}
