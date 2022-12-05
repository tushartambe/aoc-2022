package com.tushartambe.aoc.challanges;

import com.tushartambe.aoc.annotation.Day;
import com.tushartambe.aoc.util.AocUtil;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Day("2")
public class RockPaperScissors {
    Map<String, Integer> scores = Map.of("X", 1, "Y", 2, "Z", 3);
    Map<String, String> wins = Map.of("X", "C", "Y", "A", "Z", "B");
    Map<String, String> draws = Map.of("X", "A", "Y", "B", "Z", "C");
    Map<String, String> losses = Map.of("X", "B", "Y", "C", "Z", "A");

    public void partOne(List<String> file) throws FileNotFoundException {
        int totalScore = 0;
        for (String turn : file) {
            var opponentMove = turn.split(" ")[0];
            var yourMove = turn.split(" ")[1];

            int resultPoints = resultPoints(yourMove, opponentMove);
            int currentScore = resultPoints + scores.get(yourMove);

            totalScore = totalScore + currentScore;
        }

        System.out.println(totalScore);
    }


    public void partTwo(List<String> file) throws FileNotFoundException {
        int totalScore = 0;
        for (String turn : file) {
            var opponentMove = turn.split(" ")[0];
            var yourMove = turn.split(" ")[1];

            int currentScore = resultPointsPartTwo(yourMove, opponentMove);
            totalScore = totalScore + currentScore;
        }

        System.out.println(totalScore);
    }

    private int resultPoints(String yourMove, String opponentMove) {
        if (Objects.equals(wins.get(yourMove), opponentMove)) {
            return 6;
        }
        if (Objects.equals(draws.get(yourMove), opponentMove)) {
            return 3;
        }
        return 0;
    }

    private int resultPointsPartTwo(String yourMove, String opponentMove) {
        if (yourMove.equals("Z")) {
            var entry = wins.entrySet()
                    .stream()
                    .filter(e -> e.getValue().equals(opponentMove))
                    .findFirst()
                    .get();
            return scores.get(entry.getKey()) + 6;
        }
        if (yourMove.equals("Y")) {
            var entry = draws.entrySet()
                    .stream()
                    .filter(e -> e.getValue().equals(opponentMove))
                    .findFirst()
                    .get();
            return scores.get(entry.getKey()) + 3;
        }

        var entry = losses.entrySet()
                .stream()
                .filter(e -> e.getValue().equals(opponentMove))
                .findFirst()
                .get();

        return scores.get(entry.getKey());
    }

    public static void main(String[] args) throws FileNotFoundException {
        RockPaperScissors rockPaperScissors = new RockPaperScissors();
        List<String> inputFile = AocUtil.getInputFile("day2.txt", rockPaperScissors.getClass());
        rockPaperScissors.partOne(inputFile);
        rockPaperScissors.partTwo(inputFile);
    }
}
