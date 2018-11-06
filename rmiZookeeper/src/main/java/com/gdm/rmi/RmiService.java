package com.gdm.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiService extends Remote {
    public void Service(String data) throws RemoteException;


}
