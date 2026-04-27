package com.narxoz.rpg.combatant;

public class Monster {
    private final String name;
    private int hp;
    private final int maxHp;
    private final int attackPower;
    private final int defense;

    public Monster(String name, int hp, int attackPower, int defense) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attackPower = attackPower;
        this.defense = defense;
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAttackPower() { return attackPower; }
    public int getDefense() { return defense; }
    public boolean isAlive() { return hp > 0; }

    public void takeDamage(int amount) {
        int actualDamage = Math.max(1, amount - defense);
        hp = Math.max(0, hp - actualDamage);
    }
}