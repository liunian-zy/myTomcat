package io.liunian;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    public static void StartServer(int port) throws Exception {
        // 定义服务端套接字
        ServerSocket serverSocket = new ServerSocket(port);
        // 定义客户端套接字
        Socket socket = null;
        while (true){

            socket = serverSocket.accept();
            // 获取输入输出流
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // 定义请求和响应对象
            MyRequest myRequest = new MyRequest(inputStream);
            MyResponse myResponse = new MyResponse(outputStream);

            // 获取mapping对应类
            String clazz = new MyMapping().getMapping().get(myRequest.getRequestUrl());
            if (clazz!=null){
                Class<MyServlet> myServletClass = (Class<MyServlet>)Class.forName(clazz);
                MyServlet myServlet = myServletClass.newInstance();
                myServlet.service(myRequest,myResponse);
            }
        }
    }

    public static void main(String[] args) {
        try {
            StartServer(10086);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
