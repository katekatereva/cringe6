package ru.itmo.network.SelectorsOfServer;

import ru.itmo.dao.DAO;
import ru.itmo.dataManager.DataManager;
import ru.itmo.methods.HandlerOfRequests;
import ru.itmo.models.LabWork;
import ru.itmo.request.CommandRequest;
import ru.itmo.utilsCommon.DeserializerOfCommandRequestAndResponse;
import ru.itmo.utilsCommon.SerializerOfCommandRequestAndResponse;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;



public class ServerConnect {

    private int port;
    private int bufferSize;
    private DataManager<LabWork> dataManager;
    private DAO<LabWork> dao;

    public ServerConnect(int port, int bufferSize, DataManager<LabWork> dataManager, DAO<LabWork> dao) {
        this.port = port;
        this.bufferSize = bufferSize;
        this.dataManager = dataManager;
        this.dao = dao;
    }

    public void work() {
        try {
            Selector selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            ByteBuffer buffer = ByteBuffer.allocate(bufferSize);

            System.out.println("Сервер готов к работе");
            while (true) {
                selector.select();

                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();

                    if (key.isAcceptable()) {
                        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                        SocketChannel clientChannel = serverChannel.accept();
                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isWritable()) {
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        CommandRequest commandRequest = (CommandRequest) key.attachment();
                        buffer.clear();
                        buffer.put(SerializerOfCommandRequestAndResponse.serializeObject(HandlerOfRequests.handleRequestType(commandRequest, dataManager, dao)));
                        buffer.flip();
                        clientChannel.write(buffer);
                        clientChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        buffer.clear();
                        int bytesRead = clientChannel.read(buffer);
                        if (bytesRead == -1) {
                            clientChannel.close();
                            continue;
                        }
                        CommandRequest commandRequest = DeserializerOfCommandRequestAndResponse.deserializeCommandRequest(buffer.array());

                        clientChannel.register(selector, SelectionKey.OP_WRITE, commandRequest);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
