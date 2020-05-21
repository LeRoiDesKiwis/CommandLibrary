package fr.leroideskiwis.commands;

public class CommandHandler<T> {

    public final CommandRegistry<T> commandRegistry;
    public final T t;
    public final String[] args;

    public CommandHandler(CommandRegistry<T> commandRegistry, T t, String[] args) {
        this.commandRegistry = commandRegistry;
        this.t = t;
        this.args = args;
    }

}
