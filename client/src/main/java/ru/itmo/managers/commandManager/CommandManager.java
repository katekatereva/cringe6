package ru.itmo.managers.commandManager;



import ru.itmo.commands.Command;

import ru.itmo.request.CommandRequest;
import ru.itmo.request.RequestType;
import ru.itmo.response.CommandResponse;
import ru.itmo.commands.utills.CommandSplitService;
import ru.itmo.response.ResponseType;
import ru.itmo.utilsCommon.DeserializerOfCommandRequestAndResponse;
import ru.itmo.utilsCommon.SerializerOfCommandRequestAndResponse;


import java.io.*;
import java.net.Socket;
import java.util.*;

public class CommandManager {
    private Map<String, Command> commands;
    private Scanner scanner;
    private Socket socket;
    private int bufferSize;

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public CommandManager(Map<String, Command> commands) {
        this.commands = commands;
    }

    public CommandManager() {
        this.commands = new HashMap<>();
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }


    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    public void addCommand(Command command) {
        commands.put(command.getTargetTitleForUserInput(), command);
    }
    public Map<String, Command> getCommands() {
        return new HashMap<>(commands);
    }

    public CommandResponse handleCommandType(String commandType){

        String[] commandSplit = CommandSplitService.splitCommandType(commandType);

        String commandName = commandSplit[0];
        String commandArguments = commandSplit[1];

        if (commands.containsKey(commandName)) {

            CommandRequest commandRequest = commands.get(commandName).execute(this, commandArguments);
            return getResponse(commandRequest);
        }

        CommandResponse commandResponse = new CommandResponse();
        commandResponse.setResponseType(ResponseType.NOT_FOUND_COMMAND);


        return commandResponse;

    }



    public CommandResponse getResponse(CommandRequest commandRequest){

        if (commandRequest == null) {
            return null;
        }

        CommandResponse commandResponse = new CommandResponse();

        if (commandRequest.getRequestType() == RequestType.INTERNAL) {
            commandResponse.setResponseType(ResponseType.NONE);
        }

        else {

            try {
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(SerializerOfCommandRequestAndResponse.serializeObject(commandRequest));

                InputStream inputStream = socket.getInputStream();
                byte[] buffer = new byte[bufferSize];
                inputStream.read(buffer);

                commandResponse = DeserializerOfCommandRequestAndResponse.deserializeCommandResponse(buffer);

            } catch (IOException e) {
                System.out.println("Не удалось установить соединение с сервером");
            }

        }

        return commandResponse;

    }



}
