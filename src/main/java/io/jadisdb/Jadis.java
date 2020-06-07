package io.jadisdb;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Jadis {

    public void run(JadiConfig config) {
        log.info("Starting Jadis Database...");
        log.info("Config - {} ", config.toString());
    }
}
