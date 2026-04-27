package com.narxoz.rpg.tower;

import com.narxoz.rpg.floor.FloorResult;
import java.util.List;

public class TowerRunResult {
    private final boolean completed;
    private final int floorsCleared;
    private final List<FloorResult> floorResults;
    private final int finalHp;
    private final int totalExp;
    private final int totalGold;

    public TowerRunResult(boolean completed, int floorsCleared, List<FloorResult> floorResults,
                          int finalHp, int totalExp, int totalGold) {
        this.completed = completed;
        this.floorsCleared = floorsCleared;
        this.floorResults = floorResults;
        this.finalHp = finalHp;
        this.totalExp = totalExp;
        this.totalGold = totalGold;
    }

    public boolean isCompleted() { return completed; }
    public int getFloorsCleared() { return floorsCleared; }
    public List<FloorResult> getFloorResults() { return floorResults; }
    public int getFinalHp() { return finalHp; }
    public int getTotalExp() { return totalExp; }
    public int getTotalGold() { return totalGold; }

    public void display() {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║       TOWER RUN RESULTS        ║");
        System.out.println("╚════════════════════════════════╝");
        System.out.println("Floors Cleared: " + floorsCleared + " / 10");
        System.out.println("Completion: " + (completed ? "✓ FULL CLEAR!" : "✗ Failed"));
        System.out.println("Final HP: " + finalHp);
        System.out.println("Total EXP: " + totalExp);
        System.out.println("Total Gold: " + totalGold);

        System.out.println("\n--- Floor Log ---");
        for (FloorResult result : floorResults) {
            String status = result.isSuccess() ? "✓" : "✗";
            System.out.printf("Floor %2d: %s | HP: %3d | +%3d EXP | +%4d Gold%n",
                    result.getFloorNumber(), status, result.getHeroHpRemaining(),
                    result.getExpGained(), result.getGoldGained());
        }
    }
}