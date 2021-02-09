package com.example.service;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {
    public static final String BASE_URI = "http://localhost:8080/repository/";
    public static final String CORE_URI = "http://localhost:9090/core/";
    public static final String SERVLET_PATH = "http://localhost:7070/servlet";

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("com.example.service");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static HttpServer startCoreServer() {
        final ResourceConfig rc1 = new ResourceConfig().packages("com.example.core");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(CORE_URI), rc1);
    }


    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        final  HttpServer coreServer = startCoreServer();

        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
        coreServer.stop();
    }
}

