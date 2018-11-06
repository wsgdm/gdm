package com.gdm.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiServiceImpl extends UnicastRemoteObject implements RmiService{

    private String a;

    public RmiServiceImpl(String a) throws RemoteException{
        super();
        this.a = a;
    }


    public void Service(String data) throws RemoteException {
        System.out.println("使用了rmi服务" + data + a);
    }
}
