package io.jadisdb.dataaccess;

import com.fasterxml.jackson.databind.JsonNode;
import io.jadisdb.exception.NotExistKeyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

public class TreeMapDataAccess implements RangeDataAccess {
    private TreeMap<String, JsonNode> treeMap = new TreeMap<>();

    @Override
    public List<Data> range(String startKey, String endKey) {
        if (treeMap.containsKey(startKey) && treeMap.containsKey(endKey)) {
            List<Data> dataList = new ArrayList<>();
            for (String key : treeMap.keySet()) {
                if (key.equals(endKey)) {
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
        if (treeMap.containsKey(startKey)) {
            List<Data> dataList = new ArrayList<>();
            for (String key : treeMap.keySet()) {
                dataList.add(new Data(key, treeMap.get(key)));
            }

            return dataList;
        }

        throw new NotExistKeyException();
    }

    @Override
    public JsonNode get(String key) {
        JsonNode jsonNode = treeMap.get(key);

        if (Objects.isNull(jsonNode)) {
            throw new NotExistKeyException();
        }

        return jsonNode;
    }

    @Override
    public JsonNode put(String key, JsonNode value) {
        treeMap.put(key, value);
        return value;
    }

    @Override
    public JsonNode remove(String key) {
        JsonNode jsonNode = treeMap.get(key);

        if (Objects.isNull(jsonNode)) {
            throw new NotExistKeyException();
        }

        treeMap.remove(key);
        return jsonNode;
    }
}
