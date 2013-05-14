package com.whymeman.rpgbot.src.manager;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 4/1/13
 * Time: 1:45 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Manager
{
    private String name;

    public Manager(String name)
    {
        this.name = name;
    }
    public void update(float delta)
    {

    }
    public String getName()
    {
        return name;
    }
}
