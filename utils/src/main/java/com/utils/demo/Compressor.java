package com.utils.demo;

import java.io.IOException;

public interface Compressor {

    /**
     * Compress data.
     *
     * @param data data to compress
     * @return compressed data as byte array
     * @throws IOException
     */
    byte[] compress(final byte[] data) throws IOException;

    /**
     * The Uncompress data.
     *
     * @param data data to uncompress
     * @return return uncompressed data as byte array
     * @throws IOException
     */
    byte[] uncompress(final byte[] data) throws IOException;

}
