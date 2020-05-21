package fr.leroideskiwis.command;

import fr.leroideskiwis.commands.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelpCommand extends CommandExecutor<CustomContext> {

    @Override
    public void run(CommandHandler<CustomContext> commandData) {
        if(commandData.args.length == 0){
            List<CommandInfo<CustomContext>> registry = commandData.commandRegistry.getInfos();

            System.out.println("Il y a "+registry.size()+" commandes.");

            for(CommandInfo<CustomContext> commandInfo : registry){

                System.out.println("-> "+commandInfo.name+" : "+commandInfo.description);

            }
            return;
        }

        String commandString = commandData.args[0];
        if(commandData.commandRegistry.getCommand(commandString).isPresent()){
            CommandInfo<CustomContext> command = commandData.commandRegistry.getInfos(commandString);
            System.out.println("--- Infos sur la commande "+command.name+" ---");
            System.out.println(command.description);
            System.out.println("Usage : "+command.usage);
            System.out.println("Aliases : "+String.join(" - ", command.aliases));
            return;
        }
        System.out.println("Aucune commande ne correspond à ce nom.");

    }

}
