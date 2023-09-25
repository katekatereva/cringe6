package ru.itmo.utilsCommon;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializerOfCommandRequestAndResponse {

    public static byte[] serializeObject(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.close();
            return baos.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

}
