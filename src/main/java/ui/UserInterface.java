package ui;

import java.text.MessageFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    private Scanner numberIn;

    public UserInterface() {
        numberIn = new Scanner(System.in);
    }

    public int getNumberFromPlayer(int maxNumber) {
        int num = 0;
        while (num == 0) {
            try {
                num = numberIn.nextInt();

                if (num > maxNumber || num < 1) {
                    throw new IllegalArgumentException();
                }
                numberIn.nextLine();

            } catch (InputMismatchException e) {
                Printer printer = new Printer();
                printer.mismatchExceptionMessage();
                numberIn.nextLine();
            }
        }
        return num;
    }

    public void showRowColRequest(String rowOrCol, int boardSize) {
        Printer printer = new Printer();
        printer.rowColRequestDisplay(rowOrCol, boardSize);
    }

    public void showNotEmptyCellInfo(int row, int col) {
        Printer printer = new Printer();
        printer.notEmptyCellMessage(row, col);
    }

    public int getInputFromTo(int minInput, int maxInput) {
        int num = 0;
        boolean checkNumber = true;
        while (checkNumber) {
            try {
                num = numberIn.nextInt();

                if (num > maxInput || num < minInput) {
                    throw new IllegalArgumentException();
                }
                checkNumber = false;
            } catch (InputMismatchException e) {
                Printer printer = new Printer();
                printer.mismatchExceptionMessage();
                numberIn.nextLine();
            } catch (IllegalArgumentException e) {
                Printer printer = new Printer();
                printer.askForNumberBetween(minInput, maxInput);
                numberIn.nextLine();
            }
        }
        return num;
    }
}