package com.whymeman.rpgbot.src.manager;

import com.whymeman.rpgbot.src.io.GameIOManager;
import com.whymeman.rpgbot.src.rpg.Player;
import com.whymeman.rpgbot.src.threads.MainThread;
import org.jibble.pircbot.User;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 4/1/13
 * Time: 1:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class RPGManager extends Manager
{
    public ArrayList<Player> loadedPlayers;

    public RPGManager()
    {
        super("RPGManager");
        loadedPlayers = new ArrayList<Player>();
    }
    public void start()
    {
        System.out.println("Starting RPG");
        reloadPlayers();
    }
    public void reloadPlayers()
    {
        System.out.println("Reloading all players");
        loadedPlayers.clear();
        loadedPlayers = GameIOManager.getSavedPlayersList();
        System.out.println("Players reloaded. Players found: " + loadedPlayers.size());
    }
    public void saveAllLoadedPlayerData()
    {
        System.out.println("Saving data for: " + loadedPlayers.size() + " loaded players");
        for (Player p : loadedPlayers)
        {
            GameIOManager.writePlayerContents(p);
        }
        System.out.println("All player data has been saved");
    }
    public void savePlayerData(Player p)
    {
        System.out.println("Saving player data for: " + p.getUsername());
        GameIOManager.writePlayerContents(p);
        System.out.println("Player data saved(" + p.getUsername() + ")");
    }
    public Player getPlayerByName(String username)
    {
        for (Player p : loadedPlayers)
            if (p.getUsername().equalsIgnoreCase(username))
                return p;
        return null;
    }
    @Override
    public void update(float delta)
    {

    }
}
