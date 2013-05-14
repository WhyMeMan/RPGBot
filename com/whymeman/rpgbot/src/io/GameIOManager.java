package com.whymeman.rpgbot.src.io;


import com.whymeman.rpgbot.src.manager.RPGManager;
import com.whymeman.rpgbot.src.rpg.Player;
import com.whymeman.rpgbot.src.threads.MainThread;

import java.io.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 4/2/13
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameIOManager
{
    public static ArrayList<String> getUserList()
    {
        ArrayList<String> userList = new ArrayList<String>();
        ArrayList<File> fileList = IOManager.getAllFilesInFolder(new File("users"));
        for (File f : fileList)
        {
            userList.add(f.getName().replace(".dat",""));
        }
        return userList;
    }
    public static ArrayList<Player> getSavedPlayersList()
    {
        ArrayList<Player> players = new ArrayList<Player>();
        ArrayList<File> files = IOManager.getAllFilesInFolder(new File("users"));
        ArrayList<String> playerContent = new ArrayList<String>();
        for (File fileEntry : files)
        {
            ArrayList<String> fileContent = IOManager.getFileContents(fileEntry.getAbsolutePath());
            players.add(new Player(fileContent));
        }
        return players;
    }
    public static boolean doesUserExist(String username)
    {
        ArrayList<String> users = getUserList();
        for (String s : users)
        {
            if (s.equalsIgnoreCase(username))
                return true;
        }
        return false;
    }
    public static void writePlayerContents(Player player)
    {
        IOManager.writeToFile("users/" + player.getUsername() + ".dat",player.getContents());
    }
    public static void createNewUser(String username)
    {
        System.out.println("Attempting to create new user: " + username);
        RPGManager rpgManager = (RPGManager)MainThread.getManagerByName("RPGManager");
        rpgManager.saveAllLoadedPlayerData();
        Player newPlayer = new Player(username);
        System.out.println("New user created. Attempting to write to file.");
        writePlayerContents(newPlayer);
        System.out.println("Writing out to file successful!");
        rpgManager.reloadPlayers();
    }
}
