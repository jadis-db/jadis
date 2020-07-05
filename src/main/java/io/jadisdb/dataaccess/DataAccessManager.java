package io.jadisdb.dataaccess;

import io.jadisdb.exception.NotExistKeyException;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class DataAccessManager implements TreeMapDataAccess {
    private TreeMap<String, String> treeMap = new TreeMap<>();

    @Override
    public List<Data> range(String startKey, String endKey) {
        if(treeMap.containsKey(startKey) && treeMap.containsKey(endKey)) {
            List<Data> dataList = new ArrayList<>();
            for(String key : treeMap.keySet()) {
                if(key.equals(endKey)) {
                    dataList.add(new Data(key, treeMap.get(key)));
                    break;
                }

                dataList.add(new Data(key, treeMap.get(key)));
            }

            return dataList;
        }

        throw new NotExistKeyException();
    }

    @Override
    public List<Data> range(String startKey, Long size) {
        if(treeMap.containsKey(startKey)) {
            List<Data> dataList = new ArrayList<>();
            for(String key : treeMap.keySet()) {
                dataList.add(new Data(key, treeMap.get(key)));
            }

            return dataList;
        }

        throw new NotExistKeyException();
    }

    @Override
    public String get(String key) {
        return treeMap.get(key);
    }

    @Override
    public String put(String key, String value) {
        return treeMap.put(key, value);
    }

    @Override
    public String remove(String key) {
        return treeMap.remove(key);
    }
}
