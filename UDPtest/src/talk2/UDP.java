package talk2;

public class UDP {

	public static void main(String[] args) {
		
		Service ser = new Service(10000);
		client cli = new client("192.168.70.1", 10001);
		
		ser.start();
		cli.start();
	}
}
