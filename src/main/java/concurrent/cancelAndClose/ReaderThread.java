package concurrent.cancelAndClose;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ReaderThread extends Thread {
    private static final int BUFSIZE = 1024;
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
            //Any thread currently blocked in an I/O operation upon this socket will throw a SocketException.
            //SocketException extends IOException
        } catch (IOException ignored) {}
        finally {
            super.interrupt();
        }
    }

    public void run() {
        try {
            byte[] buf = new byte[BUFSIZE];
            while (true) {
                int count = in.read(buf);
                if (count < 0) {
                    break;
                } else if (count > 0) {
                    processBuffer(buf, count);
                }
            }
        } catch (IOException e) {
            /* 允许线程退出 */
        }
    }

    private void processBuffer(byte[] buf, int count) {
    }
}
