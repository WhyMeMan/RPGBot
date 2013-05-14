package com.whymeman.rpgbot.src.commands;

import com.whymeman.rpgbot.src.RPGBot;
import com.whymeman.rpgbot.src.manager.RPGManager;
import com.whymeman.rpgbot.src.threads.MainThread;
import com.whymeman.rpgbot.src.util.ColorCode;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 4/1/13
 * Time: 2:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class Start extends Command
{
    public Start()
    {
        super("start");
        this.setRequiresOP(true);
        this.setRequiresRunning(false);
        this.setRequiresStopped(true);
    }

    @Override
    public void execute(RPGBot rpgBot, String sender, String[] args)
    {
        rpgBot.sendMessage(rpgBot.activeChannel, ColorCode.GREEN + "Starting up the RPG.");
        rpgBot.isRunning = true;
        rpgBot.sendMessage(rpgBot.activeChannel, ColorCode.GREEN + "Game Status: " + (rpgBot.isRunning ? "On" : (ColorCode.RED + "OFF")));
        RPGManager rpgManager = (RPGManager) MainThread.getManagerByName("RPGManager");
        rpgManager.start();
    }
}
