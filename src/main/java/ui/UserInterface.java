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

                if (num > maxNumber) {
                    throw new IllegalArgumentException();
                }
                numberIn.nextLine();

            } catch (InputMismatchException e) {
                System.out.println("Input number");
                numberIn.nextLine();
            }
        }

        return num;
    }

    public void showRowColRequest(String rowOrCol, int boardSize) {
        System.out.println(MessageFormat.format("Enter {0} number (between 1 and {1})", rowOrCol, boardSize));
    }

    public void showNotEmptyCellInfo(int row, int col) {
        System.out.println("Cell " + row + " " + col + " is not empty!");
        System.out.println("Try again");
    }
}
