package ru.itmo.commands.utills;

public class CommandSplitService {


    public static String[] splitCommandType(String commandType) {
        String[] commandTypeSplit = commandType.split(" ");

        if (commandTypeSplit.length == 0) {
            return new String[] { null, null };
        }

        else if (commandTypeSplit.length == 1) {
            return new String[] { commandTypeSplit[0], null };
        }

        return new String[] { commandTypeSplit[0], commandTypeSplit[1]};
    }

}
