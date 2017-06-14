package ui;

import java.util.Scanner;

/**
 * Created by joanna on 14.06.17.
 */
public class UserInterface {


    public int getNumberFromPlayer(int boardSize) {

        Scanner rowAndColIn = new Scanner(System.in);
        int num = rowAndColIn.nextInt();
        if (num > boardSize) {
            throw new IllegalArgumentException();
        }
        return num;
    }
}
