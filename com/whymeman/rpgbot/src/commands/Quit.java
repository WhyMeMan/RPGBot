package com.whymeman.rpgbot.src.commands;

import com.whymeman.rpgbot.src.RPGBot;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 4/1/13
 * Time: 7:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class Quit extends Command
{
    public Quit()
    {
        super("quit");
        this.setRequiresOP(true);
        this.setRequiresRunning(false);
        this.setRequiresStopped(false);
    }

    @Override
    public void execute(RPGBot rpgBot, String sender, String[] args)
    {
        rpgBot.quitServer();
    }
}
