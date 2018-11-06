package talk1;

public class UDP {

	public static void main(String[] args) {
		
		Service ser = new Service(10001);
		client cli = new client("192.168.70.1", 10000);
		
		ser.start();
		cli.start();
	}
}
