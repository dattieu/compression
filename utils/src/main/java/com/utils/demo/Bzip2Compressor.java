package com.utils.demo;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@CompressorType("bzip2")
public class Bzip2Compressor implements Compressor {

    @Override
    public byte[] compress(final byte[] data) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BZip2CompressorOutputStream bcos = new BZip2CompressorOutputStream(out);
        bcos.write(data);
        bcos.close();

        return out.toByteArray();
    }

    @Override
    public byte[] uncompress(final byte[] data) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(data);

        try {
            @SuppressWarnings("resource")
            BZip2CompressorInputStream unzip = new BZip2CompressorInputStream(in);
            byte[] buffer = new byte[2048];
            int n;
            while ((n = unzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }
}
