package com.whymeman.rpgbot.src.threads;

import com.whymeman.rpgbot.src.RPGBot;
import com.whymeman.rpgbot.src.manager.*;
import spambot.SpamBot;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 4/1/13
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainThread extends Thread
{
    private static RPGBot rpgBot;
    private long lastTimeNanos;
    private static ArrayList<Manager> managers;

    public MainThread()
    {

        rpgBot = new RPGBot();
        lastTimeNanos = System.nanoTime();
        managers = new ArrayList<Manager>();
        setupManagers();
    }
    private void setupManagers()
    {
        managers.add(new RPGManager());
        managers.add(new CommandManager());
    }
    public void run()
    {
        while (true)
        {
            float timePassed = (System.nanoTime() - lastTimeNanos)/10000000f;
            lastTimeNanos = System.nanoTime();

            if (!rpgBot.isRunning)
                return;
            for (Manager m : managers)
            {
                m.update(timePassed);
            }

            try {
                Thread.sleep(50);
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
    public static Manager getManagerByName(String name)
    {
        for (Manager m : managers)
        {
            if (m.getName().equals(name))
                return m;
        }
        return null;
    }
    public static RPGBot getRPGBot() { return rpgBot; }
}
