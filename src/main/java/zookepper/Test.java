package zookepper;

import org.apache.zookeeper.*;

import java.io.IOException;

public class Test {
    private static String connectString = "dfs01:2181,dfs02:2181,dfs03:2181";
    private static int sessionTimeout = 2000;
    private ZooKeeper zk;

    public Test() throws IOException {
        if (zk == null) {
            zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println(event.getType() + "--" + event.getPath());
                    try {
                        zk.getChildren("/", true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }

    public void createnode() throws Exception {
        String s = zk.create("/lyf4", "sanguo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public void deleteNode() throws Exception {
        zk.delete("/lyf1", -1);
    }

    public void modify() throws Exception {
        zk.setData("/lyf4", "newdata".getBytes(), -1);
    }

    public void getData() throws Exception {
        byte[] data = zk.getData("/lyf4", false, null);

        System.out.println(new String(data));

    }

    public static void main(String[] args) throws Exception {
        Test test = new Test();
//        test.createnode();
//        test.modify();
        test.getData();
    }
}


