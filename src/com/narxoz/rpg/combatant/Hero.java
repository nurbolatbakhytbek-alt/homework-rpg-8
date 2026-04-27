package com.narxoz.rpg.combatant;

import com.narxoz.rpg.state.HeroState;
import com.narxoz.rpg.state.NormalState;

public class Hero {
    private final String name;
    private int hp;
    private final int maxHp;
    private int exp;
    private int gold;
    private int attackPower;
    private final int baseAttackPower;
    private HeroState currentState;
    private boolean stunned;
    private boolean poisoned;

    public Hero(String name, int hp, int attackPower) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attackPower = attackPower;
        this.baseAttackPower = attackPower;
        this.currentState = new NormalState();
        this.stunned = false;
        this.poisoned = false;
        this.exp = 0;
        this.gold = 0;
    }

    // Getters
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getExp() { return exp; }
    public int getGold() { return gold; }
    public int getAttackPower() { return attackPower; }
    public HeroState getCurrentState() { return currentState; }
    public boolean isAlive() { return hp > 0; }
    public boolean isStunned() { return stunned; }
    public boolean isPoisoned() { return poisoned; }

    public void setStunned(boolean stunned) { this.stunned = stunned; }
    public void setPoisoned(boolean poisoned) { this.poisoned = poisoned; }

    public void takeDamage(int amount) {
        hp = Math.max(0, hp - amount);
        System.out.println(name + " takes " + amount + " damage! HP: " + hp + "/" + maxHp);
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }

    public void addExp(int amount) {
        exp += amount;
        checkLevelUp();
    }

    public void addGold(int amount) {
        gold += amount;
    }

    private void checkLevelUp() {
        int newAttackPower = baseAttackPower + (exp / 100);
        if (newAttackPower > attackPower) {
            attackPower = newAttackPower;
            System.out.println(name + " leveled up! Attack Power increased to " + attackPower);
        }
    }

    public void updateState() {
        HeroState newState = currentState.transition(this);
        if (newState != currentState) {
            System.out.println(name + " state changed: " + currentState.getStateName() + " -> " + newState.getStateName());
            currentState = newState;
        }
    }

    public void forceState(HeroState newState) {
        this.currentState = newState;
        System.out.println(name + " forced to state: " + newState.getStateName());
    }

    public void applyPoison() {
        this.poisoned = true;
        updateState();
    }

    public void applyStun() {
        this.stunned = true;
        updateState();
    }

    public void displayStatus() {
        System.out.println("\n=== " + name + " ===");
        System.out.println("HP: " + hp + "/" + maxHp);
        System.out.println("Attack: " + attackPower);
        System.out.println("State: " + currentState.getStateName());
        System.out.println("EXP: " + exp + " | Gold: " + gold);
    }
}