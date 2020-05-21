package fr.leroideskiwis.command;

import fr.leroideskiwis.commands.CommandExecutor;
import fr.leroideskiwis.commands.CommandHandler;
import fr.leroideskiwis.commands.CommandRegistry;

public class TestCommand extends CommandExecutor<CustomContext> {

    @Override
    public void run(CommandHandler<CustomContext> commandHandler) {
        System.out.println(String.join(" ", commandHandler.args));
    }

}
