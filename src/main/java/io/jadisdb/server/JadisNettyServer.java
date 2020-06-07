package io.jadisdb.server;

import io.jadisdb.JadisConfig;
import io.jadisdb.dataaccess.DataAccess;

public class JadisNettyServer implements JadisServer {
    private DataAccess dataAccess;

    public JadisNettyServer(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public void runServer(JadisConfig jadisConfig) {

    }
}
