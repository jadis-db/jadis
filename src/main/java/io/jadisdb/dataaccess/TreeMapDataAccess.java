package io.jadisdb.dataaccess;

import java.util.List;

public interface TreeMapDataAccess extends DataAccess {
    List<Data> range(String startKey, String endKey);

    List<Data> range(String startKey, Long size);
}
