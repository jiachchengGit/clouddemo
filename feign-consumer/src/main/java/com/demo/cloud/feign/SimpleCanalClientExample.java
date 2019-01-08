package com.demo.cloud.feign;

import java.net.InetSocketAddress;
import java.util.List;


import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.impl.SimpleCanalConnector;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.google.protobuf.ByteString;


public class SimpleCanalClientExample {


    public static void main(String args[]) {
        System.out.println(System.currentTimeMillis());
        // 创建链接
//        String hostname = "10.28.17.148";
        String hostname = "10.28.17.174";
//        int port = 8080;
//        String hostname = "10.28.6.111";
//        String hostname = "127.0.0.1";
        int port = 11111;
        String example = "example";
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(hostname, port), example, "", "");
        if(connector instanceof SimpleCanalConnector){
            SimpleCanalConnector simpleCanalConnector = (SimpleCanalConnector) connector;
            System.out.println("------####---"+simpleCanalConnector.getUsername()+","+simpleCanalConnector.getPassword());
        }
        int batchSize = 1;
        int emptyCount = 0;
        try {
            connector.connect();
            connector.subscribe(".*\\..*");
            connector.rollback();
            int totalEmptyCount = 12000;
            while (emptyCount < totalEmptyCount) {
                try{
                    Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                    long batchId = message.getId();
                    int size = message.getEntries().size();
                    List<ByteString> rawEntries = message.getRawEntries();
                    printRawEntry(rawEntries);
                    if (batchId == -1 || size == 0) {
                        emptyCount++;
                        System.out.println("batchId="+batchId+",empty count : " + emptyCount);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
                    } else {
                        emptyCount = 0;
                        // System.out.printf("message[batchId=%s,size=%s] \n", batchId, size);
                        printEntry(message.getEntries());
                    }
                    connector.ack(batchId); // 提交确认
                    // connector.rollback(batchId); // 处理失败, 回滚数据
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println("empty too many times, exit");

        } finally {
            connector.disconnect();
        }
    }

    private static void printRawEntry(List<ByteString> rawEntries) {
        if (rawEntries != null){
            for(ByteString bs:rawEntries){
                System.out.println("----##--"+bs.toStringUtf8());
            }
        }
    }

    private static void printEntry(List<Entry> entrys) {
        for (Entry entry : entrys) {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                continue;
            }
            RowChange rowChage = null;
            try {
                rowChage = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            EventType eventType = rowChage.getEventType();
            System.out.println(String.format("================&gt; binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));

            if(eventType == EventType.CREATE){
                System.out.println("---------------sql:"+rowChage.getSql());
            }
            if(eventType == EventType.ERASE){
                System.out.println("---------------sql:"+rowChage.getSql());
            }
            if(eventType == EventType.ALTER){
                System.out.println("---------------sql:"+rowChage.getSql());
            }
            for (RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == EventType.DELETE) {
                    printColumn(rowData.getBeforeColumnsList());
                } else if (eventType == EventType.INSERT) {
                    printColumn(rowData.getAfterColumnsList());
                } else {
                    System.out.println("-------&gt; before");
                    printColumn(rowData.getBeforeColumnsList());
                    System.out.println("-------&gt; after");
                    printColumn(rowData.getAfterColumnsList());
                }
            }
        }
    }

    private static void printColumn(List<Column> columns) {
        for (Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
        }
    }
}