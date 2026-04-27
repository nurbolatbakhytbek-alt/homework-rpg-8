package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.Monster;

public abstract class TowerFloor {
    protected final int floorNumber;
    protected Monster monster;

    public TowerFloor(int floorNumber) {
        this.floorNumber = floorNumber;
    }


    public final FloorResult explore(Hero hero) {
        System.out.println("\n=== Floor " + floorNumber + ": " + getFloorName() + " ===");
        announce();
        setup();
        boolean success = resolveChallenge(hero);
        if (success) {
            awardLoot(hero);
            cleanup();
            return new FloorResult(floorNumber, true, hero.getHp(), getExpReward(), getGoldReward());
        } else {
            return new FloorResult(floorNumber, false, hero.getHp(), 0, 0);
        }
    }

    protected abstract String getFloorName();
    protected abstract void announce();
    protected abstract void setup();
    protected abstract boolean resolveChallenge(Hero hero);

    protected void awardLoot(Hero hero) {}
    protected void cleanup() {}

    protected int getExpReward() { return 50; }
    protected int getGoldReward() { return 100; }
}