package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.Monster;

public class CombatFloor extends TowerFloor {
    public CombatFloor(int floorNumber) {
        super(floorNumber);
    }

    @Override
    protected String getFloorName() {
        return "Combat Arena";
    }

    @Override
    protected void announce() {
        System.out.println("A fearsome monster blocks your path!");
        System.out.println("Monster: " + monster.getName() + " (HP: " + monster.getHp() + ", Attack: " + monster.getAttackPower() + ")");
    }

    @Override
    protected void setup() {

        int monsterHp = 100 + floorNumber * 20;
        int monsterAttack = 20 + floorNumber * 5;
        monster = new Monster("Floor Guardian", monsterHp, monsterAttack, 15);
    }

    @Override
    protected boolean resolveChallenge(Hero hero) {
        System.out.println("\n[COMBAT START]");
        int round = 0;

        while (hero.isAlive() && monster.isAlive() && round < 20) {
            round++;
            System.out.println("\n--- Round " + round + " ---");

            int heroDamage = hero.getCurrentState().modifyDamage(hero.getAttackPower());
            monster.takeDamage(heroDamage);
            System.out.println(hero.getName() + " deals " + heroDamage + " damage to " + monster.getName());

            if (!monster.isAlive()) {
                System.out.println(monster.getName() + " is defeated!");
                break;
            }

            int monsterDamage = monster.getAttackPower();
            int finalDamage = hero.getCurrentState().modifyReceivedDamage(monsterDamage);
            hero.takeDamage(finalDamage);
            System.out.println(monster.getName() + " deals " + finalDamage + " damage to " + hero.getName());

            if (!hero.isAlive()) {
                System.out.println(hero.getName() + " has been defeated!");
                return false;
            }

            hero.updateState();
        }

        return hero.isAlive() && !monster.isAlive();
    }

    @Override
    protected void awardLoot(Hero hero) {
        hero.addExp(getExpReward());
        hero.addGold(getGoldReward());
        System.out.println("Rewards: " + getExpReward() + " EXP, " + getGoldReward() + " Gold");
    }

    @Override
    protected int getExpReward() {
        return 50 + floorNumber * 5;
    }

    @Override
    protected int getGoldReward() {
        return 100 + floorNumber * 10;
    }
}