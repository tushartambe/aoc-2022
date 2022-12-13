package com.tushartambe.aoc.challanges;

import com.tushartambe.aoc.annotation.Day;
import com.tushartambe.aoc.util.AocUtil;

import java.io.FileNotFoundException;
import java.sql.ClientInfoStatus;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Day("5")
public class SupplyStacks {

    public String partOne(List<String> file) {
        var stacks = getStacks(file);
        var emptyLineIndex = file.indexOf("");
        for (var i = emptyLineIndex + 1; i < file.size(); i++) {
            var instruction = file.get(i).split(" ");
            var itemsToMove = Integer.parseInt(instruction[1]);
            var fromStackNumber = Integer.parseInt(instruction[3]);
            var toStackNumber = Integer.parseInt(instruction[5]);

            Stack<String> fromStack = stacks.get(fromStackNumber - 1);
            Stack<String> toStack = stacks.get(toStackNumber - 1);

            for (int j = 0; j < itemsToMove; j++) {
                if (fromStack.size() != 0) {
                    var item = fromStack.get(0);
                    fromStack.removeElementAt(0);
                    toStack.add(0, item);
                }
            }
        }

        return stacks.stream().map(i -> {
            if (i.size() != 0) {
                return i.get(0);
            }
            return "";
        }).collect(Collectors.joining());
    }

    private List<Stack<String>> getStacks(List<String> file) {

        List<Stack<String>> stacks = new ArrayList<>();

        var emptyLineIndex = file.indexOf("");
        var stacksList = file.get(emptyLineIndex - 1);

        var stackListNumberSplit = stacksList.split(" ");
        var totalStacks = Integer.parseInt(stackListNumberSplit[stackListNumberSplit.length - 1]);

        for (var i = 0; i < totalStacks; i++) {
            stacks.add(new Stack<>());
        }

        for (var i = 0; i < emptyLineIndex - 1; i++) {
            var line = file.get(i);
            for (var j = 1; j < line.length(); j += 4) {
                var stackNumber = stacksList.charAt(j);
                var stack = stacks.get(Integer.parseInt(String.valueOf(stackNumber)) - 1);
                String item = String.valueOf(line.charAt(j));
                if (!Objects.equals(item.strip(), "")) {
                    stack.add(stack.size(), item);
                }
            }
        }
        return stacks;
    }

    public String partTwo(List<String> file) {
        var stacks = getStacks(file);
        var emptyLineIndex = file.indexOf("");
        for (var i = emptyLineIndex + 1; i < file.size(); i++) {
            var instruction = file.get(i).split(" ");
            var itemsToMove = Integer.parseInt(instruction[1]);
            var fromStackNumber = Integer.parseInt(instruction[3]);
            var toStackNumber = Integer.parseInt(instruction[5]);

            Stack<String> fromStack = stacks.get(fromStackNumber - 1);
            Stack<String> toStack = stacks.get(toStackNumber - 1);

            List<String> itemsTOMove = fromStack.subList(0, itemsToMove);
            toStack.addAll(0, itemsTOMove);
            for (int j = 0; j < itemsToMove; j++) {
                fromStack.removeElementAt(0);
            }
        }

        return stacks.stream().map(i -> {
            if (i.size() != 0) {
                return i.get(0);
            }
            return "";
        }).collect(Collectors.joining());
    }


    public static void main(String[] args) throws FileNotFoundException {
        var supplyStacks = new SupplyStacks();
        var file = AocUtil.getInputFile("day5.txt", supplyStacks.getClass());

        System.out.println("Part One : " + supplyStacks.partOne(file));
        System.out.println("Part two : " + supplyStacks.partTwo(file));

    }
}
