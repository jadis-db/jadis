package io.jadisdb;

import io.jadisdb.dataaccess.HashMapDataAccess;
import io.jadisdb.server.JadisNettyServer;
import io.jadisdb.server.JadisServer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Jadis {
    private JadisServer jadisServer;

    public Jadis() {
        this.jadisServer = new JadisNettyServer(new HashMapDataAccess());
    }

    public void run(JadisConfig config) {
        log.info("Starting Jadis Database...");
        log.info("Config - {} ", config.toString());
        jadisServer.runServer(config);
    }
}
