import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

public class Main {

    public static void showBoard(int[][] board){

        for (int i = 0;i < 6;i++) {
            for (int j = 0; j < 7;j++){
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("A   B   C   D   E   F   G");
    }

    public static String placeToken(String move,int player,int[][] board) {

        int row = 5;
        int column = 0;
        String message = "";

        switch (move) {
            case "A":
            case "a":
            case "1":
                column = 0;
                break;
            case "B":
            case "b":
            case "2":
                column = 1;
                break;
            case "C":
            case "c":
            case "3":
                column = 2;
                break;
            case "D":
            case "d":
            case "4":
                column = 3;
                break;
            case "E":
            case "e":
            case "5":
                column = 4;
                break;
            case "F":
            case "f":
            case "6":
                column = 5;
                break;
            case "G":
            case "g":
            case "7":
                column = 6;
                break;
            default:
                return"Invalid Move!";
        }

        if (board[0][column] != 0){
            message = "Column full!";
            //skip players turn
        }else {
            while (board[row][column] != 0) {
                row--;
            }
            board[row][column] = player;
        }
        return message;
    }

    public static boolean connect4(int[][]board, int player){

        //Horizontal Win
        for(int row = 0; row<6; row++){
            for (int col = 0;col < 7 - 3;col++){
                if (board[row][col] == player   &&
                        board[row][col+1] == player &&
                        board[row][col+2] == player &&
                        board[row][col+3] == player){
                    return true;
                }
            }
        }

        //Vertical Win
        for(int row = 0; row < 6 - 3; row++){
            for(int col = 0; col < 7; col++){
                if (board[row][col] == player   &&
                        board[row+1][col] == player &&
                        board[row+2][col] == player &&
                        board[row+3][col] == player){
                    return true;
                }
            }
        }

        //Diagonal Win
        for(int row = 3; row < 6; row++){
            for(int col = 0; col < 7 - 3; col++){
                if (board[row][col] == player   &&
                        board[row-1][col+1] == player &&
                        board[row-2][col+2] == player &&
                        board[row-3][col+3] == player){
                    return true;
                }
            }
        }
        for(int row = 0; row < 6 - 3; row++){
            for(int col = 0; col < 7 - 3; col++){
                if (board[row][col] == player   &&
                        board[row+1][col+1] == player &&
                        board[row+2][col+2] == player &&
                        board[row+3][col+3] == player){
                    return true;
                }
            }
        }
        return false;
    }

    public static void PC_bot(int[][]board, int counter){

        //attacking/defending----------------------------------------->

        for (int atkdef = 1; atkdef < 3; atkdef++) {
            //1 for defending, 2 for attacking

            //Horizontal Win/def
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 7 - 3; col++) {
                    //last hole
                    if (board[row][col] == atkdef &&
                            board[row][col + 1] == atkdef &&
                            board[row][col + 2] == atkdef) {
                        if (board[row][col + 3] == 0) {
                            board[row][col + 3] = 2;
                            return;
                        }
                    }
                    try {
                        //first hole
                        if (board[row][col] == 0 &&
                                board[row][col + 1] == atkdef &&
                                board[row][col + 2] == atkdef &&
                                board[row][col + 3] == atkdef) {
                            board[row][col] = 2;
                            return;
                        }
                        //second hole
                        else if (board[row][col] == atkdef &&
                                board[row][col + 1] == 0 &&
                                board[row][col + 2] == atkdef &&
                                board[row][col + 3] == atkdef) {
                            board[row][col + 1] = 2;
                            return;
                        }
                        //third hole
                        else if (board[row][col] == atkdef &&
                                board[row][col + 1] == atkdef &&
                                board[row][col + 2] == 0 &&
                                board[row][col + 3] == atkdef) {
                            board[row][col + 2] = 2;
                            return;
                        }
                    } catch (Exception ignored) {}
                }
            }

            //Vertical Win/def
            for (int row = 0; row < 6 - 3; row++) {
                for (int col = 0; col < 7; col++) {
                    if (board[row][col] == atkdef &&
                            board[row + 1][col] == atkdef &&
                            board[row + 2][col] == atkdef) {
                        if (board[row + 3][col] == 0) {
                            board[row + 3][col] = 2;
                            return;
                        }
                    }
                }
            }

            //Diagonal Win/def
            for (int row = 3; row < 6; row++) {
                for (int col = 0; col < 7 - 3; col++) {
                    if (board[row][col] == atkdef &&
                            board[row - 1][col + 1] == atkdef &&
                            board[row - 2][col + 2] == atkdef) {
                        if (board[row - 3][col + 3] == 0) {
                            board[row - 3][col + 3] = 2;
                            return;
                        }
                    }
                }
            }
            for (int row = 0; row < 6 - 3; row++) {
                for (int col = 0; col < 7 - 3; col++) {
                    if (board[row][col] == atkdef &&
                            board[row + 1][col + 1] == atkdef &&
                            board[row + 2][col + 2] == atkdef) {
                        if (board[row + 3][col + 3] == 0) {
                            board[row + 3][col + 3] = 2;
                            return;
                        }
                    }
                }
            }
        }

        //regular----------------------------------------------->
        String PC_move = String.valueOf((counter%7)+1);
        placeToken(PC_move, 2 , board);
        return;
    }

    public static void main(String[] args) {

        int[][] board = {{0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},};

        Scanner scan = new Scanner(System.in);

        System.out.println("Two-player or verse computer? (2P/PC): ");
        String input = scan.nextLine();

        showBoard(board);

        boolean play1 = false;
        boolean play2 = false;
        int turn = 0;
        int counter = 0;

        while (!(play1 || play2)) {

            System.out.println("Turn #" + ++turn + ":");

            System.out.println("P1's turn:");
            String input1 = scan.nextLine();
            System.out.println(placeToken(input1, 1, board));
            showBoard(board);
            play1 = connect4(board, 1);

            if (input.equals("2P")) {
                System.out.println("P2's turn:");
                String input2 = scan.nextLine();
                System.out.println(placeToken(input2, 2, board));
                showBoard(board);
                play2 = connect4(board, 2);
            }else{
                System.out.println("PC's turn:");
                PC_bot(board,counter);
                counter++;
                showBoard(board);
                play2 = connect4(board, 2);
            }
        }

        if (play1) {
            System.out.println("Player 1 has won!");
        } else {
            System.out.println("Player 2 has won!");
            }
        }
    }

