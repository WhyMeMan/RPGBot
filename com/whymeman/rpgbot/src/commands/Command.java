package com.whymeman.rpgbot.src.commands;

import com.whymeman.rpgbot.src.RPGBot;
import org.jibble.pircbot.User;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 4/1/13
 * Time: 2:04 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Command
{
    private String command;
    private boolean requiresOP;
    private boolean requiresRunning;
    private boolean requiresStopped;

    public Command(String command)
    {
        this.command = command;
        requiresOP = false;
        requiresRunning = true;
        requiresStopped = false;
    }
    public void execute(RPGBot rpgBot, String sender, String[] args) { }
    public String getCommandName()
    {
        return command;
    }
    public boolean canExecute(User user, boolean running)
    {
        if (this.hasPermission(user))
        {
            if (running)
            {
                return this.requiresRunning || !this.requiresStopped;
            }
            else
            {
                return this.requiresStopped || !this.requiresRunning;
            }
        }
        return false;
    }
    public boolean isCommand(String commandTest)
    {
        return command.equalsIgnoreCase(commandTest);
    }
    public boolean hasPermission(User user)
    {
        return user.isOp() || !requiresOP;
    }
    protected void setRequiresOP(boolean requiresOP)
    {
        this.requiresOP = requiresOP;
    }
    protected void setRequiresRunning(boolean requiresRunning)
    {
        this.requiresRunning = requiresRunning;
    }
    protected void setRequiresStopped(boolean requiresStopped)
    {
        this.requiresStopped = requiresStopped;
    }
}
