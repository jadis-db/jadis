package io.jadisdb.server;

import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.annotation.Get;
import com.linecorp.armeria.server.annotation.Param;
import io.jadisdb.JadisConfig;
import io.jadisdb.dataaccess.DataAccess;

import java.util.concurrent.CompletableFuture;


public class JadisArmeriaServer implements JadisServer {
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
            public HttpResponse get(@Param("key") String key) {
                return HttpResponse.of(dataAccess.get(key) == null ? "null" : dataAccess.get(key));
            }
        });


        Server server = sb.build();
        CompletableFuture<Void> future = server.start();
        future.join();
    }
}