package com.gdm.rmi;


import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMISocketFactory;

public class Server {

    public Server(String port){
        try {
            System.setProperty("java.rmi.server.hostname","123.207.250.214");
            RMISocketFactory.setSocketFactory(new SMRMISocket());
            RmiServiceImpl rs = new RmiServiceImpl("server1");
            LocateRegistry.createRegistry(new Integer(port));
            Naming.rebind( "hello-"+ port, rs);
            System.out.println(port+"服务启动了!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RmiServiceImpl rs = null;
        try {
            rs = new RmiServiceImpl("server1");
            LocateRegistry.createRegistry(1099);
            Naming.rebind( "hello-1099", rs);
            System.out.println("服务启动了!");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
