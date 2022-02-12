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
                column = 0;
                break;
            case "B":
            case "b":
                column = 1;
                break;
            case "C":
            case "c":
                column = 2;
                break;
            case "D":
            case "d":
                column = 3;
                break;
            case "E":
            case "e":
                column = 4;
                break;
            case "F":
            case "f":
                column = 5;
                break;
            case "G":
            case "g":
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

    public static void main(String[] args) {

        int[][] board = { {0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0},};

        showBoard(board);

        Scanner scan = new Scanner(System.in);

        boolean play1 = false;
        boolean play2 = false;
        int turn = 0;

        while(!(play1 || play2)) {

            System.out.println("Turn #" + ++turn + ":");

            System.out.println("P1's turn:");
            String input1 = scan.nextLine();
            System.out.println(placeToken(input1,1,board));
            showBoard(board);
            play1 = connect4(board,1);

            System.out.println("P2's turn:");
            String input2 = scan.nextLine();
            System.out.println(placeToken(input2,2,board));
            showBoard(board);
            play2 = connect4(board,2);
        }

        if(play1){
            System.out.println("Player 1 has won!");
        }else{
            System.out.println("Player 2 has won!");
        }
    }
}
