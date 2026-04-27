package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public class StunnedState implements HeroState {
    private int turnsActive = 0;
    private static final int MAX_TURNS = 2;

    @Override
    public String getStateName() {
        return "Stunned";
    }

    @Override
    public int modifyDamage(int damage) {
        return 0;
    }

    @Override
    public int modifyReceivedDamage(int damage) {
        return damage;
    }

    @Override
    public HeroState transition(Hero hero) {
        turnsActive++;
        if (turnsActive >= MAX_TURNS) {
            hero.setStunned(false);
            return new NormalState();
        }
        return this;
    }
}