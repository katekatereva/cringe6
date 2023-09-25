package ru.itmo.commands;


import ru.itmo.commands.interactives.add.AddInteractiveLabWork;
import ru.itmo.interactives.question.QuestionInteractive;
import ru.itmo.managers.commandManager.CommandManager;
import ru.itmo.models.Coordinates;
import ru.itmo.models.LabWork;
import ru.itmo.models.Person;
import ru.itmo.request.CommandRequest;
import ru.itmo.request.RequestType;

import java.util.Scanner;

public class AddCommand extends Command {

    private Scanner scanner;

    public AddCommand(Scanner scanner) {
        setTargetTitleForUserInput("add");
        setDescription("add {element} : добавить новый элемент в коллекцию");
        this.scanner = scanner;
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {

        LabWork labWork = new LabWork();

        AddInteractiveLabWork.interactiveName(labWork, scanner);

        Coordinates coordinates = new Coordinates();

        AddInteractiveLabWork.interactiveX(coordinates, scanner);
        AddInteractiveLabWork.interactiveY(coordinates, scanner);

        labWork.setCoordinates(coordinates);

        AddInteractiveLabWork.interactiveMinimalPoint(labWork, scanner);
        AddInteractiveLabWork.interactiveMaximumPoint(labWork, scanner);


        if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить сложность работы?", scanner)) {
            AddInteractiveLabWork.interactiveDifficulty(labWork, scanner);
        }

        if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить автора?", scanner)) {
            Person author = new Person();
            AddInteractiveLabWork.interactivePerson(author, scanner);
            labWork.setAuthor(author);
        }

        CommandRequest commandRequest = new CommandRequest();

        commandRequest.setRequestType(RequestType.CREATE);
        commandRequest.setObject(labWork);
        commandRequest.setParameters(null);

        return commandRequest;
    }
}
