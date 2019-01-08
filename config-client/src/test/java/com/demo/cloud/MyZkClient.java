package com.demo.cloud;

import com.alibaba.fastjson.JSON;
import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.framework.api.ACLable;
import org.apache.curator.retry.RetryForever;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.StatsTrack;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.codehaus.jackson.map.util.JSONPObject;
import org.json.JSONObject;

/**
 * 2018/11/19 17:08
 * jiacheng
 */
public class MyZkClient {
    public static void main(String[] args) throws Exception {
        String url="127.0.0.1:2181";
        CuratorZookeeperClient client = new CuratorZookeeperClient(url,20000,20000,null,new RetryForever(20000));
        client.start();
        ZooKeeper zooKeeper = client.getZooKeeper();
        String path = "/testcurator1";
        String data = "jjjjj";
        Stat exists = zooKeeper.exists(path, false);
        if(exists ==null){
            zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.READ_ACL_UNSAFE, CreateMode.PERSISTENT);
        }else{
            System.out.println(JSON.toJSONString(exists));
        }
        byte[] rs = zooKeeper.getData(path, false, new Stat());
        System.out.println("------------"+new String(rs));
    }
}
