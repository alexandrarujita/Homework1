package processing;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {

	private BlockingQueue<Task> queue;
	private AtomicInteger waitingTime;

	public Server() {
		waitingTime = new AtomicInteger(0);
		queue = new LinkedBlockingQueue<Task>();
	}

	@Override
	public void run() {
		while (true) {
			Task t;
			try {
				t = queue.take();
				Thread.sleep(t.getProcessTime()*1000);
				waitingTime.addAndGet((-1) * t.getProcessTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void addTask(Task t) {
		queue.add(t);
		waitingTime.addAndGet(t.getProcessTime());

	}
	
	public Task[] getTasks(){
		
		Task[] task = new Task[queue.size()];
		queue.toArray(task);
		
		return task;
	}
	public boolean isEmpty(){
		return queue.isEmpty();
	}

}