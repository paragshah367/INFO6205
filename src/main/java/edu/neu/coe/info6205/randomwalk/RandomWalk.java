/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import java.util.Random;

public class RandomWalk {

    private int x = 0;  // current x position
    private int y = 0;  // current y position

    private static final Random random = new Random();

    /**
     * Private method to move the current position, that's to say the drunkard moves
     *
     * @param dx the distance he moves in the x direction
     * @param dy the distance he moves in the y direction
     */

    private void move(int dx, int dy) {
        x = x + dx;
        y = y + dy;
//        System.out.println("x = " + x + " " + "y = " + y);
    }

    /**
     * Private method to generate a random move according to the rules of the situation.
     * That's to say, moves can be (+-1, 0) or (0, +-1).
     */
    private void randomMove() {
        boolean ns = random.nextBoolean();  // true means step in x direction, false = y
        int step = random.nextBoolean() ? 1 : -1; // step means +ve or -ve direction
        move(ns ? step : 0, ns ? 0 : step);
    }

    /**
     * Perform a random walk of m steps
     *
     * @param m the number of steps the drunkard takes
     */
    private void randomWalk(int m) {
        for (int i = 0; i < m; i++) {
            randomMove();
        }
    }

    /**
     * Method to compute the distance from the origin (the lamp-post where the drunkard starts) to his current position.
     *
     * @return the (Euclidean) distance from the origin to the current position.
     */
    public double distance() {
        double distance = 0.0;
        distance = Math.pow(x, 2) + Math.pow(y, 2); // since the starting position is 0
//        System.out.println("Current x = " + x + " Current y = " + y + ", Euclidean distance = " + Math.sqrt(distance));
        return Math.sqrt(distance);
    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     *
     * @param m the number of steps for each experiment
     * @param n the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int m, int n) {
        double totalDistance = 0;
        for (int i = 0; i < n; i++) {
            RandomWalk walk = new RandomWalk();

            walk.randomWalk(m);
            totalDistance = totalDistance + walk.distance();
        }
        return totalDistance / n;   // Mean distance
    }

    public static void main(String[] args) {

        int m;     // no. of steps

        // At least 100 different number of steps, i.e m
        for (m = 1; m < 1000; m+= 10) {
//            int m = random.nextInt(51);
            int n = 50;      // number of experiments, optional parameter for input
            double meanDistance = randomWalkMulti(m, n);
//            System.out.println(m + " steps: " + meanDistance + " over " + n + " experiments");
            System.out.println(m +" "+ meanDistance);
        }
    }

}
