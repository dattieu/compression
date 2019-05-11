package com.utils.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public enum CompressorFactory {

    COMPRESSION_TYPE;

    Map<String, Compressor> compressorMap = new HashMap<>();

    CompressorFactory() {
        ServiceLoader<Compressor> compressors = ServiceLoader.load(Compressor.class);
        for (Compressor compressor : compressors) {
            CompressorType type = compressor.getClass().getAnnotation(CompressorType.class);
            if (type != null) {
                String name = type.value();
                if (compressorMap.containsKey(name)) {
                    throw new RuntimeException("The @SPI value(" + name
                            + ") repeat, for class(" + compressor.getClass()
                            + ") and class(" + compressorMap.get(name).getClass()
                            + ").");
                }

                compressorMap.put(name, compressor);
            }
        }
    }

    public Compressor getExtension(String name) {
        return compressorMap.get(name);
    }

}
