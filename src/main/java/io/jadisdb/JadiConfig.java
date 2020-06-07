package io.jadisdb;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JadiConfig {
    private Integer port;

    @Override
    public String toString() {
        return "port = " + port;
    }
}
