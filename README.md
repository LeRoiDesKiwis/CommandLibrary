# command library
This library help you to create commands

# usage
First, you must have an instance of CommandRegistry.
```java
CommandRegistry<...> commandRegistry = new CommandRegistry(); //you have to replace "..." by what you want. For example MessageReceivedEvent for a bot or anything else.
```
Then, you will be able to create commands like that :
```java
commandRegistry.createCommand("test") //create the command and give it a name
          .withDescription("test") //set the description
          .withUsage("test <truc> [bidule]") //set the usage
          .executes(new HelpCommand()) //set what the command will execute
          .addAliase("test1") //add the aliase test1
          .addAliase("test2") //add the aliase test2
          .addSubcommand(commandRegistry.createCommand("rgr")...) //add a subcommand
          .register(); //register the command in the registry
```

To define what the command will execute, you have to create a class that extends CommandExecutor :
```java
public class TestCommand extends Command<...>{
        @Override
        public void run(CommandHandler<CustomContext> commandHandler) {
            System.out.println(String.join(" ", commandHandler.args));
        }
}
```

You can define your own help command, in relation to your implemention of the library. You can see an example [here](https://github.com/LeRoiDesKiwis/CommandLibrary/blob/master/Example/src/main/java/fr/leroideskiwis/command/HelpCommand.java).

# questions or trouble ?
If you are any question, please open an issue [here](https://github.com/LeRoiDesKiwis/CommandLibrary/issues/new).