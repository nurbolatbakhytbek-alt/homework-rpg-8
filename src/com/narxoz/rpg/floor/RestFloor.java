package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;

public class RestFloor extends TowerFloor {
    private static final int HEAL_AMOUNT = 80;

    public RestFloor(int floorNumber) {
        super(floorNumber);
        this.monster = null;
    }

    @Override
    protected String getFloorName() {
        return "Sanctuary";
    }

    @Override
    protected void announce() {
        System.out.println("You discover a peaceful sanctuary...");
        System.out.println("A warm light envelops you.");
    }

    @Override
    protected void setup() {

    }

    @Override
    protected boolean resolveChallenge(Hero hero) {
        System.out.println("\n[REST] You take a moment to recover.");

        int oldHp = hero.getHp();
        hero.heal(HEAL_AMOUNT);
        int actualHeal = hero.getHp() - oldHp;

        System.out.println("You restore " + actualHeal + " HP!");
        System.out.println("Current HP: " + hero.getHp() + "/" + hero.getMaxHp());

        if (hero.isPoisoned()) {
            hero.setPoisoned(false);
            System.out.println("The poison has been cleansed!");
        }
        if (hero.isStunned()) {
            hero.setStunned(false);
            System.out.println("The stun effect wears off!");
        }

        hero.forceState(new com.narxoz.rpg.state.NormalState());

        return true;
    }

    @Override
    protected void awardLoot(Hero hero) {
        hero.addGold(getGoldReward());
        System.out.println("A mysterious blessing grants you " + getGoldReward() + " Gold!");
    }

    @Override
    protected int getGoldReward() {
        return 200 + floorNumber * 20;
    }

    @Override
    protected int getExpReward() {
        return 0;
    }
}