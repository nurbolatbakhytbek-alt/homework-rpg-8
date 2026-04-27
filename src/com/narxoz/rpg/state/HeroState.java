package com.narxoz.rpg.state;

import com.narxoz.rpg.combatant.Hero;

public interface HeroState {
    String getStateName();
    int modifyDamage(int damage);
    int modifyReceivedDamage(int damage);
    HeroState transition(Hero hero);
}