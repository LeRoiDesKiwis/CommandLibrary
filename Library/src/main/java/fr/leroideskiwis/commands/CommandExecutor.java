package fr.leroideskiwis.commands;

public abstract class CommandExecutor<T> {

    public abstract void run(CommandHandler<T> commandData);
}
