package org.webbitserver.handler;

import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathMatchHandler implements HttpHandler {

    private final Pattern pattern;
    private final HttpHandler httpHandler;

    public PathMatchHandler(Pattern pattern, HttpHandler httpHandler) {
        this.pattern = pattern;
        this.httpHandler = httpHandler;
    }

    public PathMatchHandler(String path, HttpHandler httpHandler) {
        this(Pattern.compile(path), httpHandler);
    }

    @Override
    public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control) throws Exception {
        Matcher matcher = pattern.matcher(request.uri());
        if (matcher.matches()) {
            httpHandler.handleHttpRequest(request, response, control);
        } else {
            control.nextHandler();
        }
    }
}
