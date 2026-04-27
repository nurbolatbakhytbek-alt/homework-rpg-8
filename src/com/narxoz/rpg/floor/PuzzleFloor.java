package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import java.util.Random;
import java.util.Scanner;

public class PuzzleFloor extends TowerFloor {
    private String puzzleQuestion;
    private String correctAnswer;
    private Random random;

    public PuzzleFloor(int floorNumber) {
        super(floorNumber);
        this.random = new Random();
    }

    @Override
    protected String getFloorName() {
        return "Puzzle Chamber";
    }

    @Override
    protected void announce() {
        System.out.println("A mysterious riddle echoes through the chamber...");
        System.out.println("Solve it to proceed!");
    }

    @Override
    protected void setup() {

        String[] questions = {
                "What is 15 + 27?",
                "What is the capital of France?",
                "Which number is prime: 4, 7, 9, or 12?",
                "What is 8 * 7?",
                "Who wrote 'Romeo and Juliet'?"
        };

        String[] answers = {
                "42",
                "paris",
                "7",
                "56",
                "shakespeare"
        };

        int index = random.nextInt(questions.length);
        puzzleQuestion = questions[index];
        correctAnswer = answers[index];
    }

    @Override
    protected boolean resolveChallenge(Hero hero) {
        System.out.println("\n[PUZZLE] " + puzzleQuestion);
        System.out.print("Your answer: ");

        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine().trim().toLowerCase();

        boolean correct = answer.equals(correctAnswer);

        if (correct) {
            System.out.println("Correct! The door opens.");

            int healAmount = 50;
            hero.heal(healAmount);
            System.out.println("You feel refreshed! +" + healAmount + " HP");
            return true;
        } else {
            System.out.println("Wrong answer! The correct answer was: " + correctAnswer);
            System.out.println("The chamber traps you!");

            int trapDamage = 30;
            hero.takeDamage(trapDamage);
            System.out.println(hero.getName() + " takes " + trapDamage + " damage!");
            return hero.isAlive();
        }
    }

    @Override
    protected void awardLoot(Hero hero) {
        hero.addExp(getExpReward());
        hero.addGold(getGoldReward());
        System.out.println("Rewards: " + getExpReward() + " EXP, " + getGoldReward() + " Gold");
    }

    @Override
    protected void cleanup() {
        System.out.println("The puzzle chamber dissolves behind you...");
    }

    @Override
    protected int getExpReward() {
        return 30 + floorNumber * 3;
    }

    @Override
    protected int getGoldReward() {
        return 150 + floorNumber * 15;
    }
}