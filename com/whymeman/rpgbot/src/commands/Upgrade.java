package com.whymeman.rpgbot.src.commands;

import com.whymeman.rpgbot.src.RPGBot;
import com.whymeman.rpgbot.src.manager.RPGManager;
import com.whymeman.rpgbot.src.rpg.Player;
import com.whymeman.rpgbot.src.threads.MainThread;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 4/3/13
 * Time: 3:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Upgrade extends Command
{
    public Upgrade()
    {
        super("upgrade");
    }

    @Override
    public void execute(RPGBot rpgBot, String sender, String[] args)
    {
        if (args.length > 1)
        {
            String stat = args[1];
            RPGManager rpgManager = (RPGManager) MainThread.getManagerByName("RPGManager");
            Player player = rpgManager.getPlayerByName(sender);
            if (player.getSp() == 0 )
            {
                rpgBot.sendNotice(sender,"You do not have any skill points to spend!");
                return;
            }
            if (stat.equalsIgnoreCase("health"))
            {
                player.setMaxHP(Math.round((player.getMaxHP()*1.12f)));
            }
            else if (stat.equalsIgnoreCase("attack"))
            {
                player.setAttack(Math.round(player.getAttack()*1.12f));
            }
            else if (stat.equalsIgnoreCase("defense"))
            {
                player.setDefense(Math.round((player.getDefense()*1.12f)));
            }
            else if (stat.equalsIgnoreCase("magic"))
            {
                player.setMagic(Math.round((player.getMagic()*1.12f)));
            }
            else
            {
                rpgBot.sendNotice(sender,"Please choose a correct stat modifier");
                return;
            }
            player.setSp(player.getSp() - 1);
            rpgBot.sendNotice(sender,"Your stat has been upgraded. !stats to view your new stats.");
            rpgManager.saveAllLoadedPlayerData();
            rpgManager.reloadPlayers();
        }
    }
}
