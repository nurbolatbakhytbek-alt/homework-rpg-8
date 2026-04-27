package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public class RageState implements HeroState {
    private int turnsActive = 0;
    private static final int MAX_TURNS = 3;

    @Override
    public String getStateName() {
        return "Rage";
    }

    @Override
    public int modifyDamage(int damage) {
        return damage * 2;
    }

    @Override
    public int modifyReceivedDamage(int damage) {
        return damage;
    }

    @Override
    public HeroState transition(Hero hero) {
        turnsActive++;
        if (turnsActive >= MAX_TURNS || hero.getHp() > hero.getMaxHp() * 0.3) {
            return new NormalState();
        }
        return this;
    }
}