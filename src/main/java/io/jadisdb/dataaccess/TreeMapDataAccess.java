package io.jadisdb.dataaccess;

import com.fasterxml.jackson.databind.JsonNode;
import io.jadisdb.exception.NotExistKeyException;

import java.util.*;

public class TreeMapDataAccess implements RangeDataAccess {
    private TreeMap<String, JsonNode> treeMap = new TreeMap<>();

    @Override
    public List<Data> range(String startKey, String endKey) {
            List<Data> dataList = new ArrayList<>();
            for (String key : treeMap.navigableKeySet().subSet(startKey, endKey)) {
                if (key.equals(endKey)) {
                    dataList.add(new Data(key, treeMap.get(key)));
                    break;
                }

                dataList.add(new Data(key, treeMap.get(key)));
            }

            return dataList;
    }

    @Override
    public List<Data> range(String startKey, Long size) {
            int count = 0;
            List<Data> dataList = new ArrayList<>();

            for (String key : treeMap.navigableKeySet().tailSet(startKey)) {
                if(count >= size) {
                    break;
                }

                dataList.add(new Data(key, treeMap.get(key)));
                count += 1;
            }

            return dataList;
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
