import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class bankSimulator {
	private static int time = 900;
	private static double totalTimeSpentWaiting = 0;
	private static double totalPeopleToday = 0;
	public static void main(String[] args) throws IOException{
		if(args.length == 0){
			System.out.println("Please import a file.");
		}else{
			//using a while loop to represent the minutes in a day
			Queue<Customer> temporaryQueue = new LinkedList<Customer>();
			try{
				String file = args[0];
				BufferedReader bufferedreader = new BufferedReader(new FileReader(file));
				String line = "";
				//reading in a file then adding it to a DoubleList
				while((line = bufferedreader.readLine()) != null) {
					String[] getStuff = line.split(" ");
					if(getStuff.length < 3){
						break;
					}
					Customer a = new Customer(getStuff[0], Integer.valueOf(getStuff[1]), Integer.valueOf(getStuff[2]));
					temporaryQueue.add(a);
				}
			}catch (IOException e){
				System.out.println("Please enter a valid file.");
			}
		
			/*
			while(queue.peek() != null){
				Customer b = queue.peek();
				System.out.println(b.getName());
				queue.remove();
			}
			*/
			Queue<Customer> queue = new LinkedList<Customer>();
			while(time <= 1700){
				Customer nextPerson = temporaryQueue.peek();
				if(nextPerson != null && nextPerson.getTimeEntered() == time){
					if(queue.size() == 0){
						//making sure the time when done is an actual time
						int temp = time + nextPerson.getTimeNeeded();
						int placeholder = (temp) % 100;
						if(placeholder > 59){
							temp += 40;
						}
						nextPerson.setTimeWhenDone(temp);
					}else{
						//making sure the time when done is an actual time
						Customer queue1 = queue.peek();
						int temp = queue1.getTimeWhenDone() + nextPerson.getTimeNeeded();
						int placeholder = (temp) % 100;
						if(placeholder > 59){
							temp += 40;
						}
						nextPerson.setTimeWhenDone(temp);
						totalTimeSpentWaiting += (nextPerson.getTimeWhenDone() - nextPerson.getTimeEntered() - nextPerson.getTimeNeeded());
						System.out.println(totalTimeSpentWaiting);
					}
					queue.add(nextPerson);

					//incrementing to be able to keep track of average wait
					totalPeopleToday += 1;

					temporaryQueue.remove();
					System.out.println(nextPerson.getName() + " got in line at " + time);
				}

				Customer personBeingHelped = queue.peek();
				if(personBeingHelped != null){
					if(time == personBeingHelped.getTimeWhenDone()){
						System.out.println(personBeingHelped.getName() + " is done at " + time);
						queue.remove();
					}
				}

				//incrementing time after an action is completed 
				time++;

				//makeshift base 60 system because whoever designed the time system
				//makes programming slightly harder
				int placeholder = time % 100;
				placeholder = placeholder % 60;
				if(placeholder == 0){
					time += 40;
				}
			}
			while(true){
				if(queue.size() > 0){
					Customer placeHolder = queue.peek();
					System.out.println(placeHolder.getName() + " is done at " + placeHolder.getTimeWhenDone());
					queue.remove();
				}else{
					break;
				}
			}
			System.out.println("total people: " + totalPeopleToday);
			System.out.println("total wait: " + totalTimeSpentWaiting);
			System.out.println("The average wait time today was: " + (totalTimeSpentWaiting/totalPeopleToday));
		}
	}
}
