package com.whymeman.rpgbot.src.commands;

import com.whymeman.rpgbot.src.RPGBot;
import com.whymeman.rpgbot.src.io.GameIOManager;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 4/2/13
 * Time: 4:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Register extends Command
{
    public Register()
    {
        super("register");
        this.setRequiresOP(false);
        this.setRequiresRunning(true);
    }

    @Override
    public void execute(RPGBot rpgBot, String sender, String[] args)
    {
        if (!GameIOManager.doesUserExist(sender))
        {
            System.out.println("Attempting to create a new user for: " + sender);
            GameIOManager.createNewUser(sender);
            System.out.println("New user created: " + sender);
            rpgBot.sendNotice(sender, "Your account has now been created!");
        }
        else
            rpgBot.sendNotice(sender, "You have already registered!");
    }
}
