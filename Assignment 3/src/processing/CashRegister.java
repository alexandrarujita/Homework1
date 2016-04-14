package processing;

public class CashRegister {
	private Server server;
	
	public CashRegister(){
		server = new Server();
		Thread th = new Thread(server);
		th.start();
	}
	
	public void processTask(Task t){
		server.addTask(t);
	}
	
	public Server getServer(){
		return server;
	}
}
