package github.javaguide;


import github.javaguide.remoting.transport.socket.SocketRpcRequestHandlerRunnable;
import github.javaguide.utils.concurrent.threadpool.ThreadPoolFactoryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;

public class TcpSocketServer {

    private static final ExecutorService threadPool = ThreadPoolFactoryUtil.createCustomThreadPoolIfAbsent("socket-server-rpc-pool");

    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        SelectionKey sscKey = ssc.register(selector, 0, null);
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        System.out.println(sscKey);

        ssc.bind(new InetSocketAddress(8080));
        while(true){
            selector.select();

            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while(iter.hasNext()) {

                SelectionKey key = iter.next();
                System.out.println(key.channel().hashCode());
                iter.remove();
                System.out.println("pre===== "+key.isReadable());
                if (key.isAcceptable()) {
                    System.out.println("key: " + key);
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    SelectionKey sckey = sc.register(selector, 0, null);
                    sckey.interestOps(SelectionKey.OP_READ);
                    System.out.println(sc);
                    System.out.println("scKey: " + sckey);
                } else if (key.isReadable()) {

                    try {

                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(16);

                        System.out.println(channel.read(buffer));
                        System.out.println("mid==== "+key.isReadable());
                        buffer.flip();
                        byte[] dst = new byte[buffer.limit()];
                        buffer.get(dst);
                        System.out.println(new String(dst,0,dst.length));
                        System.out.println("last===== "+key.isReadable());
                    }catch (IOException e){
                        e.printStackTrace();
                        key.cancel();
                    }
                }
                System.out.println("llll st ======"+ key.isReadable());

            }

        }

    }
}
