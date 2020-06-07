package io.jadisdb;

import java.util.HashMap;

public class HashMapDataAccess implements DataAccess {
    private final HashMap<String, String> data = new HashMap<>();

    @Override
    public String get(String key) {
        return data.get(key);
    }

    @Override
    public String put(String key, String value) {
        return data.put(key, value);
    }

    @Override
    public String remove(String key) {
        return data.remove(key);
    }
}
