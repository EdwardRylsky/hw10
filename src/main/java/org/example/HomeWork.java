package org.example;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * <h1>Задание 1.</h1>
 * Решить задачу https://codeforces.com/contest/356/problem/A
 */
public class HomeWork {
    private final static int MIN_NUMBER_OF_KNIGHTS = 2;
    private final static int MAX_NUMBER_OF_KNIGHTS = 30000;
    private final static int MIN_NUMBER_OF_FIGHTS = 1;
    private final static int MAX_NUMBER_OF_FIGHTS = 30000;

    @SneakyThrows
    public void championship(InputStream in, OutputStream out) {
        Objects.requireNonNull(in, "Parameter 'in' can't be null");
        Objects.requireNonNull(out, "Parameter 'out' can't be null");


        try (Scanner scanner = new Scanner(in)) {
            int numberOfKnights;
            int numberOfFights;
            try (Scanner firstLineScanner = new Scanner(scanner.nextLine())) {
                numberOfKnights = firstLineScanner.nextInt();
                checkNumberOfKnights(numberOfKnights);
                numberOfFights = firstLineScanner.nextInt();
                checkNumberOfFights(numberOfFights);
            }

            int[] defeatedBy = new int[numberOfKnights];

            for (int i = 0; i < numberOfFights; i++) {
                try (Scanner fightDescr = new Scanner(scanner.nextLine())) {
                    int numberBegging = fightDescr.nextInt();
                    int numberEnding = fightDescr.nextInt();
                    int winnersNumber = fightDescr.nextInt();

                    checkFightDescription(numberBegging, numberEnding, winnersNumber);

                    for (int j = numberBegging; j <= numberEnding; j++) {
                        if (j != winnersNumber && defeatedBy[j-1] == 0) {
                            defeatedBy[j-1] = winnersNumber;
                        }
                    }
                }
            }

            StringJoiner stringJoiner = new StringJoiner(" ");

            for (int i : defeatedBy) {
                stringJoiner.add(Integer.toString(i));
            }
            out.write((stringJoiner.toString()).getBytes());
            out.flush();
        }
    }


    private void checkNumberOfKnights(int numberOfKnights) {
        if (numberOfKnights < MIN_NUMBER_OF_KNIGHTS) {
            throw new IllegalArgumentException("Number of knights have to be greater than 0");
        }
        if (numberOfKnights > MAX_NUMBER_OF_KNIGHTS) {
            throw new IllegalArgumentException("Number of knights have to be less or equal than " + MAX_NUMBER_OF_KNIGHTS);
        }
    }

    private void checkNumberOfFights(int numberOfFights) {
        if (numberOfFights < MIN_NUMBER_OF_FIGHTS) {
            throw new IllegalArgumentException("Number of fights have to be greater than 0");
        }
        if (numberOfFights > MAX_NUMBER_OF_FIGHTS) {
            throw new IllegalArgumentException("Number of fights have to be less or equal than " + MAX_NUMBER_OF_KNIGHTS);
        }
    }

    private void checkFightDescription(int numberBegging, int numberEnding, int winnersNumber) {
        if (numberBegging < 1 || numberBegging > MAX_NUMBER_OF_KNIGHTS) {
            throw new IllegalArgumentException("Number of first knights have to be greater than 1 and less than " + MAX_NUMBER_OF_KNIGHTS);
        }
        if (numberEnding < numberBegging || numberEnding > MAX_NUMBER_OF_KNIGHTS) {
            throw new IllegalArgumentException("Number of last knights have to be greater than first knights and less than " + MAX_NUMBER_OF_KNIGHTS);
        }
        if (winnersNumber < numberBegging || winnersNumber > numberEnding) {
            throw new IllegalArgumentException("Number of winner knights have to be greater than first knights and less than last knights");
        }
    }
}