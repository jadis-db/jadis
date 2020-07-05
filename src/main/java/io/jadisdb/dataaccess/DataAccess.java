package io.jadisdb.dataaccess;

import com.fasterxml.jackson.databind.JsonNode;

public interface DataAccess {
    JsonNode get(String key);

    JsonNode put(String key, JsonNode value);

    JsonNode remove(String key);
}
