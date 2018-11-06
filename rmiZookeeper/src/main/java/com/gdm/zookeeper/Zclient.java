package com.gdm.zookeeper;

import com.gdm.rmi.Client;
import com.gdm.rmi.RmiService;
import com.gdm.rmi.RmiServiceImpl;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;

public class Zclient {

    private static ZooKeeper zookeeper;
    private static String ip = "123.207.250.214:2181,123.207.250.214:2182,123.207.250.214:2183";

    static {
        try {
            zookeeper = new ZooKeeper(ip,5000,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void getServer(){
        try {
            List<String> lists = zookeeper.getChildren("/gdm",null);

            Collections.shuffle(lists);
            String nodeName = lists.get(0);
            String [] s = nodeName.split("-");
            System.out.println(s[0]);
           RmiService rs = Client.getRmiService(s[1]);
           rs.Service("11111");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Zclient.getServer();
    }
}
