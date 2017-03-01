package com.utopian.samples;

import java.io.*;
import java.util.UUID;

public class TestUUID {

    public static void main(String[] args)
            throws IOException, ClassNotFoundException {

        UUID uuid = UUID.randomUUID();
        System.out.println("Generated UUID :" + uuid);

        byte[] serialized = serialize(uuid);

        UUID object = (UUID) deserialize(serialized);
        System.out.println("Deserialized UUID :" + object);

        System.out.println("UUID from string :" + UUID.fromString(object.toString()));
    }

    private static Object deserialize(byte[] serialized)
            throws IOException, ClassNotFoundException {

        ObjectInputStream objectIn = new ObjectInputStream(new ByteArrayInputStream(serialized));
        return objectIn.readObject();
    }

    private static byte[] serialize(Object object)
            throws IOException {

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
        objectOut.writeObject(object);
        objectOut.close();
        return byteOut.toByteArray();
    }
}