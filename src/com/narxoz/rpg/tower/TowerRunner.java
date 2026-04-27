package com.narxoz.rpg.tower;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TowerRunner {
    private static final int TOTAL_FLOORS = 10;
    private final Hero hero;
    private final Random random;
    private final List<FloorResult> results;

    public TowerRunner(Hero hero) {
        this.hero = hero;
        this.random = new Random();
        this.results = new ArrayList<>();
    }

    public TowerRunResult runTower() {
        System.out.println("\n🏰 THE HAUNTED TOWER 🏰");
        System.out.println("Ascending " + TOTAL_FLOORS + " floors...\n");

        hero.displayStatus();

        for (int floorNum = 1; floorNum <= TOTAL_FLOORS; floorNum++) {
            if (!hero.isAlive()) {
                System.out.println("\n💀 " + hero.getName() + " has fallen! Tower ascent failed! 💀");
                return new TowerRunResult(false, floorNum - 1, results,
                        hero.getHp(), hero.getExp(), hero.getGold());
            }

            TowerFloor floor = createFloor(floorNum);
            FloorResult result = floor.explore(hero);
            results.add(result);

            if (!result.isSuccess()) {
                System.out.println("\n💀 Failed on floor " + floorNum + "! 💀");
                return new TowerRunResult(false, floorNum, results,
                        hero.getHp(), hero.getExp(), hero.getGold());
            }

            System.out.println("\n✓ Floor " + floorNum + " cleared!");
            hero.displayStatus();
        }

        System.out.println("\n✨ CONGRATULATIONS! You have conquered all " + TOTAL_FLOORS + " floors! ✨");
        return new TowerRunResult(true, TOTAL_FLOORS, results,
                hero.getHp(), hero.getExp(), hero.getGold());
    }

    private TowerFloor createFloor(int floorNumber) {

        if (floorNumber == 5) {
            return new RestFloor(floorNumber);
        }
        if (floorNumber == 10) {
            return new PuzzleFloor(floorNumber);
        }

        double chance = random.nextDouble();

        if (floorNumber >= 7) {
            return random.nextBoolean() ? new CombatFloor(floorNumber) : new PuzzleFloor(floorNumber);
        }

        if (chance < 0.7) {
            return new CombatFloor(floorNumber);
        } else {
            return new PuzzleFloor(floorNumber);
        }
    }
}