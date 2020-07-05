package io.jadisdb.server;

import com.linecorp.armeria.common.HttpRequest;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.HttpStatus;
import com.linecorp.armeria.server.ServiceRequestContext;
import com.linecorp.armeria.server.annotation.ExceptionHandlerFunction;
import io.jadisdb.exception.NotExistKeyException;

public class NotExistKeyExceptionHandler implements ExceptionHandlerFunction {
    @Override
    public HttpResponse handleException(ServiceRequestContext ctx, HttpRequest req, Throwable cause) {

        if (cause instanceof NotExistKeyException) {
            return HttpResponse.of(HttpStatus.NOT_FOUND);
        }

        // To the next exception handler.
        return ExceptionHandlerFunction.fallthrough();
    }
}
