package io.jadisdb.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.HttpStatus;
import com.linecorp.armeria.common.MediaType;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.annotation.Delete;
import com.linecorp.armeria.server.annotation.Get;
import com.linecorp.armeria.server.annotation.Param;
import com.linecorp.armeria.server.annotation.Post;
import io.jadisdb.JadisConfig;
import io.jadisdb.dataaccess.DataAccess;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class JadisArmeriaServer implements JadisServer {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private DataAccess dataAccess;

    public JadisArmeriaServer(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public void runServer(JadisConfig jadisConfig) {
        ServerBuilder sb = Server.builder();
        sb.http(jadisConfig.getPort());
        sb.annotatedService(new Object() {
            @Get("/data/:key")
            public HttpResponse get(@Param("key") String key) throws JsonProcessingException {
                final JsonNode content = dataAccess.get(key);
                return HttpResponse.of(HttpStatus.OK, MediaType.JSON, OBJECT_MAPPER.writeValueAsString(content));
            }

            @Post("/data/:key")
            public HttpResponse post(@Param("key") String key, JsonNode body) throws JsonProcessingException {
                final JsonNode content = dataAccess.put(key, body);
                return HttpResponse.of(HttpStatus.OK, MediaType.JSON, OBJECT_MAPPER.writeValueAsString(content));
            }

            @Delete("/data/:key")
            public HttpResponse delete(@Param("key") String key) throws JsonProcessingException {
                final JsonNode content = dataAccess.remove(key);
                return HttpResponse.of(HttpStatus.OK, MediaType.JSON, OBJECT_MAPPER.writeValueAsString(content));
            }
        });

        Server server = sb.build();
        CompletableFuture<Void> future = server.start();
        future.join();
    }
}