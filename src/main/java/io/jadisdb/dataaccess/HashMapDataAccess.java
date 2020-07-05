package io.jadisdb.dataaccess;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Objects;

public class HashMapDataAccess implements DataAccess {
    private final HashMap<String, JsonNode> data = new HashMap<>();

    @Override
    public JsonNode get(String key) {
        return data.get(key);
    }

    @Override
    public JsonNode put(String key, JsonNode value) {
        data.put(key, value);
        return value;
    }

    @Override
    public JsonNode remove(String key) {
        JsonNode jsonNode = data.get(key);

        if (Objects.isNull(jsonNode)) {
            return null;
        }

        data.remove(key);
        return jsonNode;
    }
}