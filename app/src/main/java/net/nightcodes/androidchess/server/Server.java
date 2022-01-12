package net.nightcodes.androidchess.server;

import org.snf4j.core.SelectorLoop;
import org.snf4j.core.factory.AbstractSessionFactory;
import org.snf4j.core.handler.IStreamHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {

    private final int port;

    private Server(int port) {
        this.port = port;
    }

    public static Server getInstance(int port) {
        return new Server(port);
    }

    public void start() throws IOException, InterruptedException {

        SelectorLoop loop = new SelectorLoop();
        loop.start();

        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(port));

        loop.register(channel, new AbstractSessionFactory() {
            @Override
            protected IStreamHandler createHandler(SocketChannel channel) {
                return new ServerHandler();
            }
        });

        loop.join();

    }

}
