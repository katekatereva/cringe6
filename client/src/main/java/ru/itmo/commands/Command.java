package ru.itmo.commands;



import ru.itmo.managers.commandManager.CommandManager;
import ru.itmo.request.CommandRequest;

public abstract class Command {

    private String targetTitleForUserInput;
    private String description;
    public String getTargetTitleForUserInput() {
        return targetTitleForUserInput;
    }

    public void setTargetTitleForUserInput(String targetTitleForUserInput) {
        this.targetTitleForUserInput = targetTitleForUserInput;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract CommandRequest execute(CommandManager commandManager, String arguments);

}
