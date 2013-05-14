package com.whymeman.rpgbot.src.rpg;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 4/2/13
 * Time: 4:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player
{
    private String username;
    private int health,maxHP,mana,maxMana,level,attack,defense,magic,sp,exp;
    private boolean isDead;
    private int coolDown = 0;
    public Player(String username)
    {
        this.username = username;
        this.level = 1;
        this.health = 20;
        this.maxHP = 20;
        this.mana = 50;
        this.maxMana = 50;
        this.attack = 5;
        this.defense = 5;
        this.magic = 5;
        this.sp = 5;
        this.exp = 0;
        this.isDead = false;
    }
    public Player(ArrayList<String> content)
    {
        this.username = content.get(0);
        this.level = Integer.parseInt(content.get(1));
        this.health = Integer.parseInt(content.get(2));
        this.maxHP = Integer.parseInt(content.get(3));
        this.attack = Integer.parseInt(content.get(4));
        this.defense = Integer.parseInt(content.get(5));
        this.magic = Integer.parseInt(content.get(6));
        this.sp = Integer.parseInt(content.get(7));
        this.exp = Integer.parseInt(content.get(8));
        this.isDead = Boolean.parseBoolean(content.get(9));
    }
    public ArrayList<String> getContents()
    {
        ArrayList<String> stats = new ArrayList<String>();
        //name,level,health,maxhealth,attack,defense,magic,skillpoints,exp
        stats.add(username);
        stats.add(level + "");
        stats.add(health + "");
        stats.add(maxHP + "");
        stats.add(attack + "");
        stats.add(defense + "");
        stats.add(magic + "");
        stats.add(sp + "");
        stats.add(exp + "");
        stats.add(isDead + "");
        return stats;
    }
    public String getUsername() { return username; }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public int getMaxHP() { return maxHP; }
    public int getAttack()  { return attack; }
    public int getDefense() { return defense; }
    public int getMagic() { return magic; }
    public int getSp() { return sp; }
    public int getExp() { return exp; }
    public boolean isDead() { return isDead; }
    public boolean isAlive() { return !isDead; }

    public void setLevel(int level) { this.level = level; }
    public void setHealth(int health) { this.health = health; }
    public void setMaxHP(int maxHP) { this.maxHP = maxHP; }
    public void setAttack(int attack)  { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setMagic(int magic) { this.magic = magic; }
    public void setSp(int sp) { this.sp = sp; }
    public void setExp(int exp) { this.exp = exp; }
    public void setAlive(boolean isAlive) { this.isDead = isAlive; }

    public void update(float delta)
    {
        if (coolDown != 0)
        {
            coolDown -= delta;
            if (coolDown <= 0)
                coolDown = 0;
        }
    }
}
