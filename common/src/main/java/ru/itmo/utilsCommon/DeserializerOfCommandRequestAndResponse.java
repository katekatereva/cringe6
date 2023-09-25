package ru.itmo.utilsCommon;

import ru.itmo.request.CommandRequest;
import ru.itmo.response.CommandResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializerOfCommandRequestAndResponse {

    public static CommandRequest deserializeCommandRequest(byte[] bytes) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            CommandRequest commandRequest = (CommandRequest) ois.readObject();
            ois.close();
            return commandRequest;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
    public static CommandResponse deserializeCommandResponse(byte[] bytes) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            CommandResponse commandResponse = (CommandResponse) ois.readObject();
            ois.close();
            return commandResponse;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

}
