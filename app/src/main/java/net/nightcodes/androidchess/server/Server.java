package net.nightcodes.androidchess.server;

import android.util.Log;

import net.nightcodes.androidchess.Host;
import net.nightcodes.androidchess.utils.Utils;

import org.snf4j.core.SelectorLoop;
import org.snf4j.core.factory.AbstractSessionFactory;
import org.snf4j.core.handler.IStreamHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {

    private final int port;
    private String serverName;
    private Host host;

    private Server(int port) {
        this.port = port;
    }

    public static Server getInstance(int port) {
        return new Server(port);
    }

    public void start() throws IOException, InterruptedException {
        host.addLogEntry("[SERVER] Lobby has been created! >> " + serverName);
        host.addLogEntry("[SERVER] Initialized server! >> " + Utils.getIPAddress(true) + ":" + port);

        SelectorLoop loop = new SelectorLoop();
        loop.start();

        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(Utils.getIPAddress(true), port));

        loop.register(channel, new AbstractSessionFactory() {
            @Override
            protected IStreamHandler createHandler(SocketChannel channel) {
                return new ServerHandler(host);
            }
        });

        loop.join();

    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getPort() {
        return port;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }
}
