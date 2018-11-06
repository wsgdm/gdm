package talk1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class client extends Thread{

	public String ip;
	public int port;
	public void run() {
		String str = null;
		BufferedReader br = null;
		try {	
			DatagramSocket ds = new DatagramSocket();
			InetAddress ipp = InetAddress.getByName("192.168.70.1");
			System.out.println("客户端正在运行...");
			while(true){
				br = new BufferedReader(new InputStreamReader(System.in));
				while((str = br.readLine())!= null){
					if("exit".equals(str)){
						return;
					}	
					byte[] by = new byte[1024];
					by = str.getBytes();
					DatagramPacket dp = new DatagramPacket(by,by.length,ipp,port);
					ds.send(dp);
				}
				
			}
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("客户端结束运行!");
		}
		
	}
	public client(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}
}
