package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public class PoisonedState implements HeroState {
    private int turnsActive = 0;
    private static final int MAX_TURNS = 4;
    private static final int POISON_DAMAGE = 15;

    @Override
    public String getStateName() {
        return "Poisoned";
    }

    @Override
    public int modifyDamage(int damage) {
        return (int) (damage * 0.7);
    }

    @Override
    public int modifyReceivedDamage(int damage) {
        return damage;
    }

    @Override
    public HeroState transition(Hero hero) {
        turnsActive++;


        hero.takeDamage(POISON_DAMAGE);
        System.out.println("[Poison] " + hero.getName() + " takes " + POISON_DAMAGE + " poison damage!");

        if (turnsActive >= MAX_TURNS) {
            hero.setPoisoned(false);
            return new NormalState();
        }
        return this;
    }
}