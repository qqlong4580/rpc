package github.javaguide;

import github.javaguide.factory.SingletonFactory;
import github.javaguide.remoting.dto.RpcRequest;
import github.javaguide.remoting.dto.RpcResponse;
import github.javaguide.remoting.handler.RpcRequestHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

/**
 * @author shuang.kou
 * @createTime 2020年05月10日 09:18:00
 */
@Slf4j
public class Handler implements Runnable {
    private final Socket socket;


    public Handler(Socket socket) {
        this.socket = socket;
    }

    @SneakyThrows
    @Override
    public void run() {

        InputStream fis = socket.getInputStream();
        int content;
        byte[] buf = new byte[1024];
        while ((content = fis.read(buf))!=-1) {
            System.out.println(new String(buf, 0, content));
        }
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello, client!".getBytes());
        Thread.sleep(5000);
        socket.close();
        fis.close();
        outputStream.close();
    }

}
