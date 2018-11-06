package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPclient{
	
	private String ip;
	private int port;
	
	public TCPclient(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
		tcpp t = new tcpp();
		Thread tt = new Thread(t);
		tt.start();
	}

	public class tcpp  implements Runnable {


		public void run() {
			System.out.println("客户端正在运行中...");
			String str;
			InetAddress ipp;
			BufferedReader br = null;
			Socket sc = null;
			OutputStream op = null;
			try {
				ipp = InetAddress.getByName(ip);
				sc = new Socket(ipp,port);
				sc.connect(new InetSocketAddress(ipp,port));
				op = sc.getOutputStream();
				while(true){
					br= new BufferedReader(new InputStreamReader(System.in));
					str = null;
					while((str = br.readLine()) != null){
						op.write(str.getBytes());
					}
					op.flush();
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					op.close();
					br.close();
					sc.close();
					System.out.println("客户端终止!");
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

	}
	}

	public static void main(String[] args) {
		new TCPclient("192.168.70.1", 10000);
	
	}
}
