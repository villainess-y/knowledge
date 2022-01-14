package NIO;

import Utils.SleepHelper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class NIOClient {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 5; i++) {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1",9999));
            socketChannel.write(ByteBuffer.wrap(("this is " + i +" time").getBytes(StandardCharsets.UTF_8)));
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            SleepHelper.sleepMilli(650);
            int read=socketChannel.read(readBuffer);
            System.out.println("服务端消息:" + new String(readBuffer.array(), 0, read,
                    StandardCharsets.UTF_8));
            socketChannel.close();
        }
    }
}
