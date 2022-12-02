package com.tushartambe.aoc.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AocUtil {
    public static List<String> getInputFile(String inputFile,Class cls) throws FileNotFoundException {
        File file = new File(Objects.requireNonNull(cls.getResource("/" + inputFile)).getFile());
        var scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            lines.add(line);
        }

        return lines;
    }

}
