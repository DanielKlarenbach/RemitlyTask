package com.herokuapp.remitytask.remitytask;

import com.herokuapp.remitytask.remitytask.Exceptions.SencondaryApiException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

public class RestTemplateResponseErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        throw new SencondaryApiException(response.getStatusText());
    }
}