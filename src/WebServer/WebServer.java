package WebServer;

import com.sun.net.httpserver.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import redis.clients.jedis.Jedis;

public class WebServer {
    public static void startServer() throws Exception {
        HttpServer server
                = HttpServer.create(new InetSocketAddress(3333), 0);
        server.createContext( "/active.kml", new KmlHandler () );
        server.createContext( "/", new GetHandler());
        server.setExecutor(null); // create a default executor
        server.start();


    }

    static class GetHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            String url = t.getRequestURI().toString();

            Path file;
            try {
                file = Paths.get("." + url);
            } catch (Exception e) {
                t.sendResponseHeaders(404, 0);
                OutputStream os = t.getResponseBody();
                os.close();
                return;
            }

            if (!file.toFile().isFile()) {
                t.sendResponseHeaders(404, 0);
                OutputStream os = t.getResponseBody();
                os.close();
                return;
            }

            byte[] fileArray;
            fileArray = Files.readAllBytes(file);

            t.sendResponseHeaders(200, fileArray.length);
            OutputStream os = t.getResponseBody();
            os.write(fileArray);
            os.close();
        }
    }

    static class KmlHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            //new connection to redis
            Jedis jed = new Jedis("localhost");
            //read kml String
            String kmlData = jed.get("kmlDataString");

            String response;
            if (kmlData == null){
                response = "a";
            }
            else {
                response = kmlData;
            }

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();

        }
    }
}
