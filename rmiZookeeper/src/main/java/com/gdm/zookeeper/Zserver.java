package com.gdm.zookeeper;

import com.gdm.rmi.Server;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Scanner;

public class Zserver {

    private static ZooKeeper zookeeper;
    private static String ip = "123.207.250.214:2181,123.207.250.214:2182,123.207.250.214:2183";
    static {
        try {
            zookeeper = new ZooKeeper(ip,5000,null);
            Stat stat = zookeeper.exists("/gdm",null);
            if(stat == null) {
                zookeeper.create("/gdm", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            System.out.println("zookeeper服务启动了!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void creatNode(String port){
        try {
            Stat stat = zookeeper.exists("/gdm/hello-" + port,null);
            if(stat == null){
                zookeeper.create("/gdm/hello-" + port, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void removeNode(String port){
        try {
            Stat stat = zookeeper.exists("/gdm/hello-"+ port,null);
            if(stat != null){
                zookeeper.delete("/gdm/hello-"+ port,-1);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Zserver();
        Zserver.creatNode(args[0]);
        new Server(args[0]);
        Scanner sc = new Scanner(System.in);
        if(sc.nextLine().equals("exit")){
            Zserver.removeNode(args[0]);
            System.exit(0);
        }

    }
}
