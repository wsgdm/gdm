package com.gdm.rmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static RmiService getRmiService(String port) {
        RmiService rs = null;
        String url = "rmi://123.207.250.214:" + port + "/";
        try {
            rs =  (RmiService) Naming.lookup(url + "hello-" + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void main(String[] args) {
        try {
           // Context context =  new InitialContext();
            //Registry rey = LocateRegistry.getRegistry("123.207.250.214");

            RmiService rs =  (RmiService) Naming.lookup("hello-1099");
            rs.Service("1111");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
