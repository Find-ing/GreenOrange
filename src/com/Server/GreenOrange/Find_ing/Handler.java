package com.Server.GreenOrange.Find_ing;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("InfiniteLoopStatement")

public class Handler extends Thread{
    Socket sock;
    public Handler(Socket sock) {
        this.sock = sock;
    }

    @Override
    public void run() {
        try (InputStream inp = this.sock.getInputStream()) {
            try (final OutputStream oup = this.sock.getOutputStream()) {
                handle(inp, oup);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handle(InputStream inputStream, OutputStream outputStream) throws IOException{

        System.out.println("连接成功现在可以开始发消息了");

        while (true) {
        byte[] bytes = new byte[4096];
        int len;
            len = inputStream.read(bytes);
            String inp = new String(bytes, 0, len);

            System.out.println("客户端信息：" + inp);

            outputStream.write(("ServerResponse回应: "+inp).getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }

    }
}
