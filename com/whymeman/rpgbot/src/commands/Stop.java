package com.whymeman.rpgbot.src.commands;

import com.whymeman.rpgbot.src.RPGBot;
import com.whymeman.rpgbot.src.util.ColorCode;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 4/1/13
 * Time: 7:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Stop extends Command
{
    public Stop()
    {
        super("stop");
        this.setRequiresOP(true);
        this.setRequiresRunning(true);
        this.setRequiresStopped(false);
    }

    @Override
    public void execute(RPGBot rpgBot, String sender, String[] args)
    {
        rpgBot.sendMessage(rpgBot.activeChannel, ColorCode.RED + "Stopping the RPG.");
        rpgBot.isRunning = false;
        rpgBot.sendMessage(rpgBot.activeChannel, ColorCode.GREEN + "Game Status: " + (rpgBot.isRunning ? "On" : (ColorCode.RED + "OFF")));
    }
}
