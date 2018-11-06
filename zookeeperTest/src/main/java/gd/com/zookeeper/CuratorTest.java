package gd.com.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class CuratorTest {
    public static void main(String[] args) throws Exception {
        CuratorFramework cf = CuratorFrameworkFactory.builder().
                                connectString("123.207.250.214:2181,123.207.250.214:2182,123.207.250.214:2183").
                                sessionTimeoutMs(4000).retryPolicy(new ExponentialBackoffRetry(1000,3)).
                                namespace("curator").build();
        cf.start();

        cf.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).
                forPath("/gdm/haha","1".getBytes());

        cf.delete().deletingChildrenIfNeeded().forPath("/gdm/haha");
    }
}
