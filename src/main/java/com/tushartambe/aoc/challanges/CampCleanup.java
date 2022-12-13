package com.tushartambe.aoc.challanges;

import com.tushartambe.aoc.annotation.Day;
import com.tushartambe.aoc.util.AocUtil;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

@Day("4")
public class CampCleanup {
    public Integer partOne(List<String> file) {
        return file.stream()
                .filter(this::containsAnotherRange)
                .toList()
                .size();
    }

    private boolean containsAnotherRange(String s) {
        String[] pairs = s.split(",");
        var firstPair = Arrays.stream(pairs[0].split("-")).mapToInt(Integer::parseInt).boxed().toList();
        var secondPair = Arrays.stream(pairs[1].split("-")).mapToInt(Integer::parseInt).boxed().toList();

        return (firstPair.get(0) <= secondPair.get(0) && firstPair.get(1) >= secondPair.get(1)) ||
                (firstPair.get(0) >= secondPair.get(0) && firstPair.get(1) <= secondPair.get(1));
    }

    public Integer partTwo(List<String> file) {
        return file.stream()
                .filter(this::doesOverLap)
                .toList()
                .size();
    }

    private boolean doesOverLap(String s) {
        String[] pairs = s.split(",");
        var firstPair = Arrays.stream(pairs[0].split("-")).mapToInt(Integer::parseInt).boxed().toList();
        var secondPair = Arrays.stream(pairs[1].split("-")).mapToInt(Integer::parseInt).boxed().toList();

        return (secondPair.get(0) >= firstPair.get(0) && secondPair.get(0) <= firstPair.get(1)) ||
                (firstPair.get(0) >= secondPair.get(0) && firstPair.get(0) <= secondPair.get(1));
    }


    public static void main(String[] args) throws FileNotFoundException {
        var campCleanup = new CampCleanup();
        var file = AocUtil.getInputFile("day4.txt", campCleanup.getClass());

        System.out.println("Part One : " + campCleanup.partOne(file));
        System.out.println("Part One : " + campCleanup.partTwo(file));

    }
}
