package fr.leroideskiwis.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class CommandRegistry<T> {

    private final HashMap<String, Command<T>> commands = new HashMap<>();
    private String prefixe;

    public CommandRegistry(String prefixe){
        this.prefixe = prefixe;
    }

    public void registerCommand(Command<T> command){
        commands.put(command.toString(), command);
    }

    public CommandInfo<T> getInfos(String name){
        return getCommand(name).get().getInfos();
    }

    public Command.CommandBuilder<T> createCommand(String name){
        return new Command.CommandBuilder<T>(this).withName(name);
    }

    public boolean execute(String message, T t){
        if(message.trim().equals("") || !message.startsWith(prefixe)) return false;
        String[] split = message.replaceFirst(prefixe, "").split(" ");
        String[] args = new String[split.length-1];
        if (args.length >= 0) System.arraycopy(split, 1, args, 0, args.length);

        Optional<Command<T>> command = getCommand(split[0]);

        command.ifPresent(cmd -> cmd.run(t, args, this));

        return command.isPresent();
    }

    public Optional<Command<T>> getCommand(String name){
        if(!commands.containsKey(name)) return commands.values().stream().filter(command -> command.hasAlias(name)).findAny();
        return Optional.of(commands.get(name));
    }

    public List<CommandInfo<T>> getInfos(){
        return commands.values().stream().map(Command::getInfos).collect(Collectors.toList());
    }

}
