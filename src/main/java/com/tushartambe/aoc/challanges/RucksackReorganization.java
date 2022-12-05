package com.tushartambe.aoc.challanges;

import com.tushartambe.aoc.annotation.Day;
import com.tushartambe.aoc.util.AocUtil;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Day("3")
public class RucksackReorganization {
    public Integer partOne(List<String> file) {
        return file.stream().map(this::getCommonElement)
                .map(this::getPriority)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Integer partTwo(List<String> file) {
        var groups =
                IntStream.range(0, file.size())
                        .filter(i -> i % 3 == 0)
                        .mapToObj(i -> file.subList(i, Math.min(i + 3, file.size())))
                        .toList();


        return groups.stream().map(this::getCommonElement)
                .map(this::getPriority)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private Character getCommonElement(List<String> commonElements) {
        String first = commonElements.get(0);
        String second = commonElements.get(1);
        String third = commonElements.get(2);

        Set<String> firstSet = Arrays.stream(first.split("")).collect(Collectors.toSet());
        Set<String> secondSet = Arrays.stream(second.split("")).collect(Collectors.toSet());
        Set<String> thirdSet = Arrays.stream(third.split("")).collect(Collectors.toSet());
        firstSet.retainAll(secondSet);
        firstSet.retainAll(thirdSet);

        String[] strings = firstSet.toArray(new String[0]);
        return strings[0].charAt(0);

    }

    private Character getCommonElement(String str) {
        int length = str.length();
        String first = str.substring(0, length / 2);
        String second = str.substring(length / 2, length);

        Set<String> firstSet = Arrays.stream(first.split("")).collect(Collectors.toSet());
        Set<String> secondSet = Arrays.stream(second.split("")).collect(Collectors.toSet());
        firstSet.retainAll(secondSet);
        String[] strings = firstSet.toArray(new String[0]);
        return strings[0].charAt(0);

    }

    private int getPriority(Character ch) {
        int numericValue = Character.getNumericValue(ch) - 9;
        if (Character.isLowerCase(ch)) {
            return numericValue;
        }
        return numericValue + 26;
    }

    public static void main(String[] args) throws FileNotFoundException {
        var rucksackReorganization = new RucksackReorganization();
        var file = AocUtil.getInputFile("day3.txt", rucksackReorganization.getClass());

        System.out.println("Part One : " + rucksackReorganization.partOne(file));
        System.out.println("Part One : " + rucksackReorganization.partTwo(file));
    }
}
