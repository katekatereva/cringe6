package ru.itmo.commands;



import ru.itmo.managers.commandManager.CommandManager;
import ru.itmo.request.CommandRequest;
import ru.itmo.request.RequestType;

public class SaveCommand extends Command{
    public SaveCommand() {
        setTargetTitleForUserInput("save");
        setDescription("save : сохранить коллекцию в файл");
    }
    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {
        CommandRequest commandRequest = new CommandRequest();
        commandRequest.setRequestType(RequestType.SAVE);
        return commandRequest;
    }
}
