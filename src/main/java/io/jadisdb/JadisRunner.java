package io.jadisdb;

public class JadisRunner {
    public static void main(String[] args) {
        Jadis jadis = new Jadis();
        JadisConfig jadisConfig = JadisConfig.builder()
                .port(8080)
                .build();
        jadis.run(jadisConfig);
    }
}
