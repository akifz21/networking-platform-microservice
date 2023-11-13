package com.akifozdemir.apigateway.security;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    private static final PathPatternParser pathPatternParser = new PathPatternParser();
    private static final List<PathPattern> openApiEndpoints = List.of(
            pathPatternParser.parse("/users/**"),
            pathPatternParser.parse("/eureka/**")
    );

    public Predicate<ServerHttpRequest> isSecured = request ->
            openApiEndpoints.stream().noneMatch(pattern -> pattern.matches(request.getPath().pathWithinApplication()));
}
