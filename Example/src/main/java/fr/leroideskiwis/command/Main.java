package fr.leroideskiwis.command;

import fr.leroideskiwis.commands.Command;
import fr.leroideskiwis.commands.CommandInfo;
import fr.leroideskiwis.commands.CommandRegistry;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    private static CommandRegistry<CustomContext> commandRegistry = new CommandRegistry<>();

    public static void main(String... args){
        commandRegistry.createCommand("help")
                .withDescription("Aide")
                .withUsage("help <commande>")
                .executes(new HelpCommand())
                .register();

        makeFastCommand("test", "test <lol>", "mdr", Arrays.asList("lol", "ptdr"));
        makeFastCommand(":c:c:c:", ":c:c:c: override", ":mais:", Collections.singletonList("damn"));
        makeFastCommand("kiwi", "kiwi kiwi", "kiwi", Arrays.asList("meilleurfruit", "oiseau"," kiui"));

        execute("help");
        execute("help miam");

        for(CommandInfo<CustomContext> commandInfo : commandRegistry.getInfos()){
            execute("help "+commandInfo.name);
        }

        execute("ergreg");
        execute("damn coucou");

    }

    public static void execute(String command){
        System.out.println("--- Exécution de "+command+" : ---\n\n");
        commandRegistry.execute(command, new CustomContext("opire mange mwa le poiro"));
        System.out.println("\n\n--- Fin de l'exécution ---");
    }

    public static void makeFastCommand(String name, String usage, String description,
                                List<String> aliases){
        Command.CommandBuilder<CustomContext> commandBuilder = commandRegistry.createCommand(name)
                .withUsage(usage)
                .withDescription(description)
                .executes(new TestCommand());

        aliases.forEach(commandBuilder::addAliase);
        commandBuilder.register();
    }

}
