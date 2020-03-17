public class Customer {

	private String name;
	private int timeEntered;
	private int timeNeeded;
	private int timeWhenDone;

	public Customer(String name, int timeEntered, int timeNeeded){
		this.name = name;
		this.timeEntered = timeEntered;
		this.timeNeeded = timeNeeded;
	}

	public String getName(){
		return this.name;
	}

	public void setTimeWhenDone(int a){
		timeWhenDone = a;
	}

	public int getTimeWhenDone(){
		return this.timeWhenDone;
	}

	public int getTimeEntered(){
		return this.timeEntered;
	}

	public int getTimeNeeded(){
		return this.timeNeeded;
	}
}
