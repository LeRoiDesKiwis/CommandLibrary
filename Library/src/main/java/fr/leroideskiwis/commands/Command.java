package fr.leroideskiwis.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Command<T> {

    private CommandInfo<T> commandInfo;

    private Command(CommandBuilder<T> commandBuilder){
        this.commandInfo = new CommandInfo<>(commandBuilder);
        commandInfo.subCommands.forEach(command -> commandInfo.commandRegistry.registerCommand(command));
    }

    public void run(T t, String[] args, CommandRegistry<T> globalRegistry){
        if(commandInfo.commandRegistry.execute(String.join(" ", args), t)) return;
        commandInfo.commandExecutor.run(new CommandHandler<>(globalRegistry, t, args));
    }

    public CommandInfo<T> getInfos(){
        return commandInfo;
    }

    @Override
    public String toString() {
        return commandInfo.toString();
    }

    public boolean hasAlias(String name){
        return commandInfo.aliases.contains(name);
    }

    public static class CommandBuilder<T>{
        protected CommandExecutor<T> commandExecutor;
        protected String name, description, usage;
        protected List<String> aliases = new ArrayList<>();
        protected List<Command<T>> subCommands = new ArrayList<>();
        protected CommandRegistry<T> commandRegistry;

        public CommandBuilder(CommandRegistry<T> registry){
            this.commandRegistry = registry;
        }

        protected CommandBuilder<T> withName(String name){
            this.name = name;
            return this;
        }

        public CommandBuilder<T> withUsage(String usage){
            this.usage = usage;
            return this;
        }

        public CommandBuilder<T> withDescription(String description){
            this.description = description;
            return this;
        }

        public CommandBuilder<T> addAliase(String alias){
            aliases.add(alias);
            return this;
        }

        public CommandBuilder<T> addSubCommand(CommandBuilder<T> builder){
            subCommands.add(builder.build());
            return this;
        }

        public CommandBuilder<T> executes(CommandExecutor<T> commandExecutor){
            this.commandExecutor = commandExecutor;
            return this;
        }

        /*public CommandBuilder<T> executes(BiConsumer<T, String[]> consumer, Consumer<T> help){
            return executes(new CommandExecutor<T>(commandRegistry) {
                @Override
                public void run(T t, String[] args) {
                    consumer.accept(t, args);
                }

                @Override
                public void sendHelp(T t) {
                    help.accept(t);
                }
            });
        }

        public CommandBuilder<T> executes(BiConsumer<T, String[]> consumer){
            return executes(consumer, t -> {});
        }*/

        private Command<T> build() {
            return new Command<T>(this);
        }

        public void register(){
            commandRegistry.registerCommand(build());
        }

    }
}
