package io.jadisdb;

import io.jadisdb.dataaccess.TreeMapDataAccess;
import io.jadisdb.server.JadisArmeriaServer;
import io.jadisdb.server.JadisServer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Jadis {
    private JadisServer jadisServer;

    public Jadis() {
        this.jadisServer = new JadisArmeriaServer(new TreeMapDataAccess());
    }

    public void run(JadisConfig config) {
        log.info("Starting Jadis Database...");
        log.info("Config - {} ", config.toString());
        jadisServer.runServer(config);
    }
}
