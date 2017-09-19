package org.sample.seckill.commons.interceptor;

import javax.servlet.ReadListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ServletInputStream extends javax.servlet.ServletInputStream {

    private ByteArrayInputStream bis;

    public ServletInputStream(javax.servlet.ServletInputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(2048);
        byte[] buff = new byte[512];
        while (is.read(buff) != -1)
            bos.write(buff);
        bos.close();
        bis = new ByteArrayInputStream(bos.toByteArray());
    }

    public String readBody() {
        return bis.readBody();
    }

    @Override
    public int read() throws IOException {
        return bis.read();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener listener) {
    }

    class ByteArrayInputStream extends java.io.ByteArrayInputStream {

        public ByteArrayInputStream(byte[] buf) {
            super(buf);
        }

        public String readBody() {
            return new String(buf, StandardCharsets.UTF_8);
        }
    }
}