package com.whymeman.rpgbot.src.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 4/1/13
 * Time: 2:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommandFailureException extends Exception
{
    public boolean hasPermission = true;
    public boolean isGameRunning = true;
    public boolean commandNotFound = false;
}
