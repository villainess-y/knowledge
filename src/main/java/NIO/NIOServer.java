package NIO;

import Utils.SleepHelper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class NIOServer {

    public static void main(String[] args) throws IOException {
        //1.打开一个服务端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2.绑定对应端口号
        serverSocketChannel.bind(new InetSocketAddress(9999));
        //3.通道默认是阻塞的，需要设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        System.out.println("服务端启动成功:.......");
        while (true){
            //4.检查是否有客户端连接
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(socketChannel == null){
                System.out.println("暂时没有客户端。。。。我去干别的");
                SleepHelper.sleepMilli(600);
                continue;
            }
            //5.获取客户端传过来的数据
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            //返回值:
            //正数: 表示本次读到的有效字节个数.
            //0 : 表示本次没有读到有效字节.
            //-1 : 表示读到了末尾
            int read = socketChannel.read(allocate);
            System.out.println("客户端消息:" + new String(allocate.array(),0,read, StandardCharsets.UTF_8));
            //6.返回消息给客户端
            socketChannel.write(ByteBuffer.wrap("您拨打的用户正忙，请稍后再拨。。。".getBytes(StandardCharsets.UTF_8)));
            //7.释放资源
            socketChannel.close();
        }

    }
}
