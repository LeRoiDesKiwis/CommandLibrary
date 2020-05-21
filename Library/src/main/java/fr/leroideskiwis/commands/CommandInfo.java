package fr.leroideskiwis.commands;

import java.util.List;

public class CommandInfo<T> {

    protected final CommandExecutor<T> commandExecutor;
    public final String name, description, usage;
    public final List<String> aliases;
    public final List<Command<T>> subCommands;
    protected final CommandRegistry<T> commandRegistry = new CommandRegistry<>();

    CommandInfo(Command.CommandBuilder<T> commandBuilder){
        this.name = commandBuilder.name;
        this.description = commandBuilder.description;
        this.usage = commandBuilder.usage;
        this.subCommands = commandBuilder.subCommands;
        this.commandExecutor = commandBuilder.commandExecutor;
        this.aliases = commandBuilder.aliases;
    }

    public String toString(){
        return name;
    }



}
