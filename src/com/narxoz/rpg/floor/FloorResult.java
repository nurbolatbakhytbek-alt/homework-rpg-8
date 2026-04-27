package com.narxoz.rpg.floor;

public class FloorResult {
    private final int floorNumber;
    private final boolean success;
    private final int heroHpRemaining;
    private final int expGained;
    private final int goldGained;

    public FloorResult(int floorNumber, boolean success, int heroHpRemaining, int expGained, int goldGained) {
        this.floorNumber = floorNumber;
        this.success = success;
        this.heroHpRemaining = heroHpRemaining;
        this.expGained = expGained;
        this.goldGained = goldGained;
    }

    public int getFloorNumber() { return floorNumber; }
    public boolean isSuccess() { return success; }
    public int getHeroHpRemaining() { return heroHpRemaining; }
    public int getExpGained() { return expGained; }
    public int getGoldGained() { return goldGained; }
}