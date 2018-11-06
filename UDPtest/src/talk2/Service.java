package talk2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Service extends Thread {

	public int port;
	public void run() {
		
		try {
			System.out.println("服务器端启动中...");
			
			DatagramPacket dp = null;
			DatagramSocket ds = new DatagramSocket(port);
			while(true){	
				byte[] buf = new byte[1024];
				dp = new DatagramPacket(buf, buf.length);
				ds.receive(dp);
				
				buf = dp.getData();
				System.out.println(new String(buf));
			}
			
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public Service(int port) {
		super();
		this.port = port;
	}
}
