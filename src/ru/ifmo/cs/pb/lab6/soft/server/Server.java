/*
 * Copyright (c) 2020, Saint-Petersburg, ITMO University, P3130
 * Laboratory work #6, Server-Client Application(Single-Threaded mode)
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package ru.ifmo.cs.pb.lab6.soft.server;



import org.apache.log4j.Logger;
import ru.ifmo.cs.pb.lab6.soft.source.Collection;
import ru.ifmo.cs.pb.lab6.soft.source.command.AbstractCommand;
import ru.ifmo.cs.pb.lab6.soft.source.command.Save;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * The {@code Server} final class can work with one or many
 * clients in single-threaded mode. The class consists aby methods:
 * for accepting new client, for reading message of new class, for
 * analysing messages and sending of the results. Our {@code Server}
 * class was build in base of protocol TCP.
 *
 *
 *
 * @author Bobur  Zakirov
 * @since  1.0
 */

public final class Server {


    private Selector selector;

    // port of this server
    private static Integer PORT = 40004;

    // collection
    private final Collection collection;

    // address of the server
    private final SocketAddress address;

    // set to reister clients
    private final Set<SocketChannel> channels;

    // to logging logs of server
    private static final Logger logger =
            Logger.getLogger(Server.class.getSimpleName());


    //Constructor
    public Server(String[] args) {

        if (args.length == 2) try {
            PORT = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format of Port!");
            System.exit(0);
        }

        this.address = new InetSocketAddress(PORT);
        this.channels = new HashSet<SocketChannel>();
        this.collection = new Collection(args.length > 0
                ? args[0] : "Collection.csv");

        Runtime.getRuntime().addShutdownHook(new Thread(()
                -> new Save().execute(collection)));
    }

    /**
     * The {@code main()} void method begins server!
     *
     * @param args          an array of {@code String}
     */

    public static void main(String[] args) {

        try {
            new Server(args).start();
        } catch (IOException e) {
            logger.error("- " + e.toString());
        }
    }

    /**
     * The {@code start()} method begins the server and waits to connecting
     * clients and continues it.
     *
     * @throws              IOException if something wrong with input/output
     */

    private void start() throws IOException {

        this.selector = Selector.open();

        ServerSocketChannel channel = ServerSocketChannel.open();

        channel.bind(this.address);
        channel.configureBlocking(false);
        channel.register(this.selector, SelectionKey.OP_ACCEPT);

        Server.logger.info("/###.###.###.###:##### - server is waiting to users...");


        while (true) { //start of infinity loop

            this.selector.select();

            Iterator keys = this.selector.selectedKeys().iterator();

            while (keys.hasNext()) {

                SelectionKey key = (SelectionKey) keys.next();
                keys.remove();

                if (! key.isValid()) continue;

                if (key.isAcceptable()) {
                    this.accept(key); //accepting new client
                } else

                    if (key.isReadable()) {
                        this.read(key); //reading messages and answering
                    }
            }
        }
    }

    /**
     * The {@code accept()} method registers  new client to the server
     *
     * @param key           a {@code Selection Key}
     * @throws              IOException if something wrong with input/output
     */

    private void accept(SelectionKey key) throws IOException {

        ServerSocketChannel server = (ServerSocketChannel) key.channel();

        SocketChannel channel = server.accept();

        channel.configureBlocking(false);
        channel.register(this.selector, SelectionKey.OP_READ);

        this.channels.add(channel);

        Server.logger.info(channel.socket().getRemoteSocketAddress() + " - new user joined.");
    }

    /**
     * The {@code read()} method reads data, which are send to client,
     * and analysis it, then sends it back to clients.
     *
     * @param key           a {@code Selection Key}
     * @throws              IOException if something wrong with input/output
     */

    private void read(SelectionKey key)
            throws IOException {

        SocketChannel channel = (SocketChannel) key.channel();

        ByteBuffer buffer = ByteBuffer.allocate(Short.MAX_VALUE);

        try {
            byte[] bytes = new byte[channel.read(buffer)];
            System.arraycopy(buffer.array(), 0, bytes, 0, bytes.length);

            Server.logger.info(channel.socket().getRemoteSocketAddress() + " - command is analysing...");

            AbstractCommand command = (AbstractCommand) Deserializer.deserialize(bytes);

            channel.write(ByteBuffer.wrap((command.execute(collection) + "\b").getBytes()));

            Server.logger.info(channel.socket().getRemoteSocketAddress() +
                        " - successfully executed <" + command.getName().toUpperCase() + "> command!");

        } catch (Exception e) {

            this.channels.remove(channel);

            Server.logger.warn(channel.socket().getRemoteSocketAddress() + " - user left.");

            channel.close();
            key.cancel();
        }
    }
}