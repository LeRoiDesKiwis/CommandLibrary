package fr.leroideskiwis.commands;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class CommandRegistry<T> {

    private final HashMap<String, Command<T>> commands = new HashMap<>();

    public void registerCommand(Command<T> command){
        commands.put(command.toString(), command);
    }

    public CommandInfo<T> getInfos(String name){
        return commands.get(name).getInfos();
    }

    public Command.CommandBuilder<T> createCommand(String name){
        return new Command.CommandBuilder<T>(this).withName(name);
    }

    public boolean hasCommand(String command){
        return commands.containsKey(command);
    }

    public boolean execute(String message, T t){
        if(message.trim().equals("")) return false;
        String[] split = message.split(" ");
        String[] args = new String[split.length-1];
        if (args.length >= 0) System.arraycopy(split, 1, args, 0, args.length);

        if(hasCommand(split[0])){
            Command<T> command = commands.get(split[0]);
            command.run(t, args, this);
            return true;
        }
        return false;
    }

    public List<CommandInfo<T>> getInfos(){
        return commands.values().stream().map(Command::getInfos).collect(Collectors.toList());
    }

}
