package com.herokuapp.remitlytask.ErrorHandlers;

import com.herokuapp.remitlytask.Exceptions.SencondaryApiException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

public class RTRestTemplateResponseErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        throw new SencondaryApiException(response.getStatusText());
    }
}