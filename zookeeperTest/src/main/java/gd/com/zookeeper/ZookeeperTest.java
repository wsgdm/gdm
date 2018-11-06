package gd.com.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZookeeperTest {
    public static void main(String[] args) {
       final CountDownLatch cd = new CountDownLatch(1);
        try {
            final ZooKeeper zooKeeper = new ZooKeeper("123.207.250.214:2181,123.207.250.214:2182,123.207.250.214:2183",
                    4000, new Watcher() {
                public void process(WatchedEvent watchedEvent) {
                    if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
                        System.out.println("111111");
                        cd.countDown();

                    }
                }
            });
            cd.await();
            System.out.println(zooKeeper.getState());
            Stat stat = new Stat();
            zooKeeper.create("/gdm","0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zooKeeper.getData("/gdm", new Watcher() {
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("222222");
                    try {
                        zooKeeper.exists("/gdm",true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, stat);
            stat = zooKeeper.setData("/gdm","1".getBytes(),stat.getVersion());
            stat = zooKeeper.setData("/gdm","2".getBytes(),stat.getVersion());
            zooKeeper.delete("/gdm",stat.getVersion());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
