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

    public static void placeToken(String move,int player,int[][] board) {

        int row = 5;
        int column = 0;

        switch (move) {
            case "A":
                column = 0;
                break;
            case "B":
                column = 1;
                break;
            case "C":
                column = 2;
                break;
            case "D":
                column = 3;
                break;
            case "E":
                column = 4;
                break;
            case "F":
                column = 5;
                break;
            case "G":
                column = 6;
                break;
        }

            while (board[row][column] != 0) {
                row--;
            }
            board[row][column] = player;

    }

    public static boolean connect4(int[][]board, int player){

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

        while(!(play1 || play2)) {

            System.out.println("P1's turn:");
            String input1 = scan.nextLine();
            placeToken(input1,1,board);
            showBoard(board);
            play1 = connect4(board,1);

            System.out.println("P2's turn:");
            String input2 = scan.nextLine();
            placeToken(input2,2,board);
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
