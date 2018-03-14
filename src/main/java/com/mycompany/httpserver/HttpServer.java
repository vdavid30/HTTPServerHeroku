package com.mycompany.httpserver;


import java.net.*;
import java.io.*;
public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        serverSocket = new ServerSocket(new Integer(System.getenv("PORT")));
        Socket clientSocket = null; 
        clientSocket = serverSocket.accept();       
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            //System.out.println("Received: " + "ENTRO AL WHILE ");
            if (!in.ready()) {
                System.out.println("NO ESTA LISTO  ");
            }else{
                System.out.println("ESTA LISTO");
            }
        }
        outputLine = "HTTP/1.1 200 OK \n"
            +"Content-Type: text/html; charset=utf-8\n"
            +"Cache-Control: public, max-age=60, s-maxage=300\n"
            +"Vary: Accept-Encoding\n"
            +"Content-Encoding: raw\n"
            +"Server: DPS/1.3.5\n"
            +"X-SiteId: 2000\n"
            +"Set-Cookie: dps_site_id=2000; path=/\n"
            +"ETag: a1be084ec13bc207c17c49955f875fab\n"
            +"Date: Mon, 26 Feb 2018 21:47:54 GMT\n"
            +"Connection: keep-alive\n"
            +"Transfer-Encoding: chunked\n" 
            +"\n"
            +"3c6f"
            + "<!DOCTYPE html>"
            + "<html>"
            + "<head>"
            + "<meta charset=\"UTF-8\">"
            + "<title>Title of the document</title>\n"
            + "</head>"
            + "<body>"
            + "My Web Site"
            + "</body>"
            + "</html>"                                    
            + inputLine;
        out.println(outputLine);

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
