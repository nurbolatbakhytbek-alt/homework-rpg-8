package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public class NormalState implements HeroState {
    @Override
    public String getStateName() {
        return "Normal";
    }

    @Override
    public int modifyDamage(int damage) {
        return damage;
    }

    @Override
    public int modifyReceivedDamage(int damage) {
        return damage;
    }

    @Override
    public HeroState transition(Hero hero) {
        if (hero.getHp() <= hero.getMaxHp() * 0.3) {
            return new RageState();
        }
        if (hero.isStunned()) {
            return new StunnedState();
        }
        if (hero.isPoisoned()) {
            return new PoisonedState();
        }
        return this;
    }
}