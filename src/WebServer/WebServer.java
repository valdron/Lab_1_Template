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

            /*response = "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" +
                    "<Document>\n" +
                    "<Style id=\"40612C\">\n" +
                    "<IconStyle>\n" +
                    "<scale>0.7</scale>\n" +
                    "<heading>137</heading>\n" +
                    "<Icon>\n" +
                    "<href>http://localhost:3333/icons/plane09.png</href>\n" +
                    "</Icon>\n" +
                    "</IconStyle>\n" +
                    "</Style>\n" +
                    "<Placemark>\n" +
                    "<name>40612C</name>\n" +
                    "<description>\n" +
                    "EZY19PE Lon: 9.645923815275493 Lat: 49.78056335449219 Alt: 11292m Dir: 137deg Vel: 483kn Clm: 0ft/min\n" +
                    "</description>\n" +
                    "<styleUrl>#40612C</styleUrl>\n" +
                    "<Point>\n" +
                    "<coordinates>9.64592382, 49.78056335, 11292</coordinates>\n" +
                    "<altitudeMode>relativeToGround</altitudeMode>\n" +
                    "<extrude>1</extrude>\n" +
                    "</Point>\n" +
                    "</Placemark>\n" +
                    "</Document>\n" +
                    "</kml>";*/

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();

        }
    }
}
