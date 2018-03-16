package com.mycompany.httpserver;


import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
public class HttpServer implements Runnable{
    private Socket socket;
    public HttpServer(Socket socketN){
       this.socket = socketN; 
    }

    @Override
    public void run() {
        try {     
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine, outputLine, format, data;
            byte[] bytes;
            inputLine = in.readLine();            
            if(inputLine != null){
                    inputLine = inputLine.split(" ")[1];
                    if(inputLine.contains(".html")){
                        bytes = Files.readAllBytes(new File("./"+inputLine).toPath());
                        data = "" + bytes.length;
                        format = "text/html";                   
                    }else if(inputLine.contains(".jpg")){
                        bytes = Files.readAllBytes(new File("./"+inputLine).toPath());
                        data = "" + bytes.length;
                        format = "image/html";                                   
                    }else{
                        bytes = Files.readAllBytes(new File("./index.html").toPath());
                        data = "" + bytes.length;
                        format = "text/html";                   
                    }
            }else{
                bytes = Files.readAllBytes(new File("./index.html").toPath());
                data = "" + bytes.length;
                format = "text/html";                   

            }
            outputLine = "HTTP/1.1 200 OK\r\n" 
                                + "Content-Type: " + format + "\r\n"
                                + "Content-Length: " + data
                                + "\r\n\r\n";   
            socket.getOutputStream().write(getHeader(outputLine, bytes));
            socket.close();
        } catch (IOException ex) {
                Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public byte[] getHeader(String outputLine, byte [] bytes){
        byte [] bytesEn = outputLine.getBytes();
        byte[] answ = new byte[bytes.length + bytesEn.length];
        for (int i = 0; i < bytesEn.length; i++) {
            answ[i] = bytesEn[i];
        }
        for (int i = bytesEn.length; i < bytesEn.length + bytes.length; i++) {
            answ[i] = bytes[i - bytesEn.length];
        } 
        return answ;
    }
}
