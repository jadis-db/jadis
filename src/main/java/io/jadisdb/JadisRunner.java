package io.jadisdb;

public class JadisRunner {
    public static void main(String[] args) {
        Jadis jadis = new Jadis();
        JadiConfig jadiConfig = JadiConfig.builder()
                .port(8080)
                .build();
        jadis.run(jadiConfig);
    }
}
