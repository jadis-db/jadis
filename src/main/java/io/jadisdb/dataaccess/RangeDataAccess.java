package io.jadisdb.dataaccess;

import java.util.List;

public interface RangeDataAccess extends DataAccess {
    List<Data> range(String startKey, String endKey);

    List<Data> range(String startKey, Long size);
}
