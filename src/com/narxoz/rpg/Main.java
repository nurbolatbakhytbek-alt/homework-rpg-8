package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.tower.TowerRunResult;
import com.narxoz.rpg.tower.TowerRunner;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║   THE HAUNTED TOWER - Homework 8     ║");
        System.out.println("║   State Pattern + Template Method    ║");
        System.out.println("╚═══════════════════════════════════════╝");

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter your hero's name: ");
        String name = scanner.nextLine();

        System.out.println("\nChoose your class:");
        System.out.println("1. Warrior (High HP, Medium Attack)");
        System.out.println("2. Rogue (Medium HP, High Attack)");
        System.out.println("3. Knight (Very High HP, Low Attack)");
        System.out.print("Choice: ");

        int choice = scanner.nextInt();

        Hero hero;
        switch (choice) {
            case 1:
                hero = new Hero(name, 300, 40);
                System.out.println("\n⚔️ Warrior chosen! High endurance, balanced damage.");
                break;
            case 2:
                hero = new Hero(name, 200, 55);
                System.out.println("\n🗡️ Rogue chosen! Fragile but deadly.");
                break;
            case 3:
                hero = new Hero(name, 400, 30);
                System.out.println("\n🛡️ Knight chosen! Unbreakable defense, lower damage.");
                break;
            default:
                hero = new Hero(name, 250, 45);
                System.out.println("\n⚔️ Default hero created.");
        }

        System.out.println("\nPress ENTER to begin your ascent...");
        scanner.nextLine();
        scanner.nextLine();

        TowerRunner runner = new TowerRunner(hero);
        TowerRunResult result = runner.runTower();

        result.display();

        System.out.println("\n=== PATTERN DEMONSTRATION ===");
        System.out.println("✓ State Pattern: Hero states (Normal, Rage, Stunned, Poisoned)");
        System.out.println("  - States modify damage output");
        System.out.println("  - Automatic transitions based on conditions");
        System.out.println("  - Rage activates below 30% HP, doubles damage for 3 turns");
        System.out.println("  - Poison reduces damage by 30% and deals DOT");
        System.out.println("  - Stun prevents all actions for 2 turns");
        System.out.println("\n✓ Template Method Pattern: TowerFloor");
        System.out.println("  - Fixed algorithm: announce → setup → resolveChallenge → awardLoot → cleanup");
        System.out.println("  - Subclasses: CombatFloor, PuzzleFloor, RestFloor");
        System.out.println("  - Hooks: awardLoot(), cleanup()");
        System.out.println("  - Each floor implements its own challenge resolution");

        scanner.close();
    }
}