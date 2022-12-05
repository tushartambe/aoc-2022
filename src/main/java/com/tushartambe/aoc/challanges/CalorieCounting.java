package com.tushartambe.aoc.challanges;

import com.tushartambe.aoc.annotation.Day;
import com.tushartambe.aoc.util.AocUtil;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

@Day("1")
public class CalorieCounting {

    public void partOne() throws FileNotFoundException {
        var file = AocUtil.getInputFile("day1.txt", this.getClass());
        var treeSet = getCaloriesInSortedOrder(file);
        System.out.println("Max Calories : " + treeSet.first());
    }

    public void partTwo() throws FileNotFoundException {
        var file = AocUtil.getInputFile("day1.txt", this.getClass());

        var treeSet = getCaloriesInSortedOrder(file);

        var sumOfTopThree = 0;
        for (var i = 0; i < 3; i++) {
            sumOfTopThree += treeSet.pollFirst();
        }

        System.out.println("Sum of 3 max Calories : " + sumOfTopThree);
    }

    private TreeSet<Integer> getCaloriesInSortedOrder(List<String> file) {
        var treeSet = new TreeSet<Integer>(Comparator.reverseOrder());


        var totalCaloriesForCurrentElf = 0;

        for (var s : file) {
            if (s.isEmpty()) {
                treeSet.add(totalCaloriesForCurrentElf);
                totalCaloriesForCurrentElf = 0;
            } else {
                totalCaloriesForCurrentElf += Integer.parseInt(s);
            }
        }
        return treeSet;
    }

    public static void main(String[] args) throws FileNotFoundException {
        var elfCarryingMostCalories = new CalorieCounting();
        elfCarryingMostCalories.partTwo();
    }
}
