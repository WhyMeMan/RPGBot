package com.whymeman.rpgbot.src;
/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 4/1/13
 * Time: 12:46 PM
 * To change this template use File | Settings | File Templates.
 */
import com.whymeman.rpgbot.src.exceptions.CommandFailureException;
import com.whymeman.rpgbot.src.io.GameIOManager;
import com.whymeman.rpgbot.src.manager.CommandManager;
import com.whymeman.rpgbot.src.manager.RPGManager;
import com.whymeman.rpgbot.src.threads.MainThread;
import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;

public class RPGBot extends PircBot
{
    public boolean isRunning;
    public String activeChannel;

    public RPGBot()
    {
        isRunning = false;
        this.setName("RPGBot");
        try {
            this.connect("irc.roguecoder.net");
            //this.connect("snuggles.epicirc.com");
        }   catch(Exception e) { e.printStackTrace(); }
    }

    @Override
    protected void onConnect()
    {
        this.joinChannel("#lounge");
    }

    @Override
    protected void onJoin(String channel, String sender, String login, String hostname)
    {
        if (!sender.equals(this.getNick()))
            return;
        this.sendMessage("nickserv","identify bingo");
        this.sendMessage(channel,"RPGBot v0.1 joined " + channel);
        this.sendMessage(channel,"Awaiting instructions...");
        activeChannel = channel;

        if (this.isRunning)
        {
            RPGManager rpgManager = (RPGManager)MainThread.getManagerByName("RPGManager");
            if (GameIOManager.doesUserExist(sender))
            {
                rpgManager.saveAllLoadedPlayerData();
                rpgManager.reloadPlayers();
            }
        }
    }

    @Override
    protected void onPart(String channel, String sender, String login, String hostname)
    {
        RPGManager rpgManager = (RPGManager)MainThread.getManagerByName("RPGManager");
        if (GameIOManager.doesUserExist(sender))
        {
            rpgManager.saveAllLoadedPlayerData();
            rpgManager.reloadPlayers();
        }
    }

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message)
    {
        if (!message.startsWith("!"))
            return;
        User user = getUserByName(channel,sender);

        CommandManager commandManager = (CommandManager)MainThread.getManagerByName("CommandManager");

        try {
            commandManager.processCommand(user, message.replaceFirst("!", ""), this);
        } catch (CommandFailureException e) {
            if (!e.hasPermission)
            {
                this.sendNotice(sender,"You do not have the correct permissions to execute this command.");
                return;
            }
            else if (e.commandNotFound)
                return;
            else if (!e.isGameRunning)
            {
                this.sendNotice(sender,"Please wait until the game has been started before attempting commands.");
                return;
            }
            else if (e.isGameRunning)
            {
                this.sendNotice(sender,"Game must be stopped before this command can be executed.");
                return;
            }
            e.printStackTrace();


        }
    }
}
