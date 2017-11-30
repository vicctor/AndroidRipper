package it.unina.android.ripper.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Artur
 */
public class StreamAsynchReader implements Closeable {

    private Thread t = null;

    public void read(final InputStream input, OutputStream os) {
        t = new Thread() {
            @Override
            public void run() {
                try {
                    byte b[] = new byte[128];
                    int n;
                    while ((n = input.read(b)) > 0) {
                        os.write(b, 0, n);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        t.start();
    }

    @Override
    public void close() throws IOException {
        try {
            if (t != null) {
                t.join();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(StreamAsynchReader.class.getName()).log(Level.SEVERE, "Could not properly stop input reader thread", ex);
        }
    }

}
