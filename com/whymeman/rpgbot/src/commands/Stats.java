package com.whymeman.rpgbot.src.commands;

import com.whymeman.rpgbot.src.RPGBot;
import com.whymeman.rpgbot.src.manager.RPGManager;
import com.whymeman.rpgbot.src.rpg.Player;
import com.whymeman.rpgbot.src.threads.MainThread;
import com.whymeman.rpgbot.src.util.ColorCode;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 4/2/13
 * Time: 6:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class Stats extends Command
{
    public Stats()
    {
        super("stats");
        this.setRequiresStopped(false);
        this.setRequiresRunning(true);
        this.setRequiresOP(false);
    }

    @Override
    public void execute(RPGBot rpgBot, String sender, String[] args)
    {
        RPGManager rpgManager = (RPGManager) MainThread.getManagerByName("RPGManager");
        ArrayList<Player> players = rpgManager.loadedPlayers;
        if (args.length >= 2)
        {
            if (args.length >= 5)
            {
                rpgBot.sendNotice(sender, "Whoa there, please limit the amount of users you want to get stats for");
                return;
            }
            for (int i = 0; i < args.length-1; i++)
            {
                String username = args[i+1];

                rpgBot.sendNotice(sender,"Gathering stats for: " + args[i+1]);
            }
        }
        else
        {
            Player player = rpgManager.getPlayerByName(sender);
            displayPlayerContent(rpgBot, sender,player);
        }
    }
    private void displayPlayerContent(RPGBot rpgBot, String sender, Player player)
    {
        System.out.println("Player level: " + player.getLevel());
        //name,level,health,maxhealth,attack,defense,magic,skillpoints,exp
        rpgBot.sendNotice(sender,ColorCode.GREEN + "Stats for " + player.getUsername() + ": " +
                ColorCode.ORANGE + "Health" + ColorCode.DARKRED + "- " + String.valueOf(player.getHealth()) + "/" + player.getMaxHP() + " | " +
                ColorCode.ORANGE + "Attack" + ColorCode.DARKRED + "- " + String.valueOf(player.getAttack()) + " | " +
                ColorCode.ORANGE + "Defense" + ColorCode.DARKRED + "- "  + String.valueOf(player.getDefense()) + " | " +
                ColorCode.ORANGE + "Magic" + ColorCode.DARKRED + "- " + String.valueOf(player.getMagic()) + " | " +
                ColorCode.ORANGE + "Skill Points" + ColorCode.DARKRED + "- " + String.valueOf(player.getSp()) + " | " +
                ColorCode.ORANGE + "Exp" + ColorCode.DARKRED + "- " + String.valueOf(player.getExp()) + " | " +
                ColorCode.ORANGE +  "Level" + ColorCode.DARKRED + "- " + String.valueOf(player.getLevel())
                );
    }
}
