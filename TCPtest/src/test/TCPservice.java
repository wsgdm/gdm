package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPservice{

	private int port;

	public TCPservice(int port) {
		super();
		this.port = port;
		tcpse t = new tcpse();
		Thread tt = new Thread(t);
		tt.start();
	}
	
	class tcpse implements Runnable{
		public void run() {
			System.out.println("服务器正在运行中...");
			String str = null;
			Socket sc = null;
			InputStream in = null;
			BufferedReader br = null;
			BufferedWriter bw = null;
			try {
				ServerSocket ss = new ServerSocket(port);
				while(true){
					sc = ss.accept();
					in = sc.getInputStream();
					br = new BufferedReader(new InputStreamReader(in));
					bw = new BufferedWriter(new OutputStreamWriter(System.out));
					str = null;
					while((str = br.readLine()) != null){
						bw.write(str);
					}
					bw.flush();
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					bw.close();
					br.close();
					in.close();
					sc.close();
					System.out.println("服务器终止!");
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
	public static void main(String[] args) {
		
		new TCPservice(10001);
	}
	
}
