package com.whymeman.rpgbot.src.manager;

import com.whymeman.rpgbot.src.RPGBot;
import com.whymeman.rpgbot.src.commands.Command;
import com.whymeman.rpgbot.src.commands.*;
import com.whymeman.rpgbot.src.exceptions.CommandFailureException;
import org.jibble.pircbot.User;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 4/1/13
 * Time: 2:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommandManager extends Manager
{
    private ArrayList<Command> commands;
    public CommandManager()
    {
        super("CommandManager");
        commands = new ArrayList<Command>();
        setupCommands();
    }
    private void setupCommands()
    {
        commands.add(new Start());
        commands.add(new Stop());
        commands.add(new Quit());
        commands.add(new Register());
        commands.add(new Stats());
        commands.add(new Upgrade());
    }
    public void processCommand(User user, String command, RPGBot rpgBot) throws CommandFailureException
    {
        boolean running = rpgBot.isRunning;
        for (Command c : commands)
        {
            if (c.isCommand(command.split(" ")[0]))
            {
                if (c.canExecute(user,running))
                {
                    String[] args = command.split(" ");
                    c.execute(rpgBot, user.getNick(), args);
                    return;
                }
                else
                {
                    CommandFailureException e = new CommandFailureException();
                    if (!c.hasPermission(user))
                    {
                        e.hasPermission = false;
                        throw e;
                    }
                    else if (!running)
                    {
                        e.isGameRunning = false;
                        throw e;
                    }
                    else if (running)
                        throw e;
                }
            }
        }
        CommandFailureException e = new CommandFailureException();
        e.commandNotFound = true;
        throw e;
    }
}
