package io.jadisdb;

public interface DataAccess {
    String get(String key);

    String put(String key, String value);

    String remove(String key);
}
