import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

public class Main {

    //Displays the current board.
    public static void showBoard(int[][] board){ //argument is the current board
        for (int i = 0;i < 6;i++) {
            for (int j = 0; j < 7;j++){
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("A   B   C   D   E   F   G");
    }

    //Converts user input into columns of the array
    public static int clean_input(String move){ //argument is user input
        switch (move) {
            case "A":
            case "a":
            case "1":
                return 0;
            case "B":
            case "b":
            case "2":
                return 1;
            case "C":
            case "c":
            case "3":
                return 2;
            case "D":
            case "d":
            case "4":
                return 3;
            case "E":
            case "e":
            case "5":
                return 4;
            case "F":
            case "f":
            case "6":
                return 5;
            case "G":
            case "g":
            case "7":
                return 6;
            default:
                return 999; //"Invalid Move!";
        }
    }

    //Updates the playing board and handles invalid input/moves.
    public static String placeToken(int move,int player,int[][] board) { //arguments: cleaned user input, player number, current board
        int row = 5;
        int column = move;
        String message = "";

        if(column == 999){
            return "Invalid Move!";
        }
        else if (board[0][column] != 0){
                message = "Column full!";
        }
        else {
            while (board[row][column] != 0) {
                row--;
            }
            board[row][column] = player;
        }
        return message;
    }

    //Checks if player has won.
    public static boolean connect4(int[][]board, int player){//argument: current board, player number

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

    //Computer algorithm. Detects if it or the player is close to winning and attacks/defends accordingly.
    public static int PC_bot(int[][]board, int counter, int player_move){ //Arguments: current board, counter for bot to remember last move, players last move

        //attacking/defending algo------------------------------------>
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
                            return col + 3;
                        }
                    }
                    try {
                        //first hole
                        if (board[row][col] == 0 &&
                                board[row][col + 1] == atkdef &&
                                board[row][col + 2] == atkdef &&
                                board[row][col + 3] == atkdef) {
                            return col;
                        }
                        //second hole
                        else if (board[row][col] == atkdef &&
                                board[row][col + 1] == 0 &&
                                board[row][col + 2] == atkdef &&
                                board[row][col + 3] == atkdef) {
                            return col + 1;
                        }
                        //third hole
                        else if (board[row][col] == atkdef &&
                                board[row][col + 1] == atkdef &&
                                board[row][col + 2] == 0 &&
                                board[row][col + 3] == atkdef) {
                            return col + 2;
                        }
                    } catch (Exception ignored) {}
                }
            }

            //Vertical Win/def
            for (int row = 0; row < 6 - 3; row++) {
                for (int col = 0; col < 7; col++) {
                    //last hole
                    if (board[row][col] == atkdef &&
                            board[row + 1][col] == atkdef &&
                            board[row + 2][col] == atkdef) {
                        if (board[row + 3][col] == 0) {
                            return col;
                        }
                    }
                    try {
                        //first hole
                        if (board[row][col] == 0 &&
                                board[row + 1][col] == atkdef &&
                                board[row + 2][col] == atkdef &&
                                board[row + 3][col] == atkdef) {
                            return col;
                        }
                        //second hole
                        else if (board[row][col] == atkdef &&
                                board[row + 1][col] == 0 &&
                                board[row + 2][col] == atkdef &&
                                board[row + 3][col] == atkdef) {
                            return col;
                        }
                        //third hole
                        else if (board[row][col] == atkdef &&
                                board[row + 1][col] == atkdef &&
                                board[row + 2][col] == 0 &&
                                board[row + 3][col] == atkdef) {
                            return col;
                        }
                    } catch (Exception ignored) {}
                }
            }

            //Diagonal Win/def
            for (int row = 3; row < 6; row++) {
                for (int col = 0; col < 7 - 3; col++) {
                    //last hole
                    if (board[row][col] == atkdef &&
                            board[row - 1][col + 1] == atkdef &&
                            board[row - 2][col + 2] == atkdef) {
                        if (board[row - 3][col + 3] == 0) {
                            return col + 3;
                        }
                    }
                    try{
                        //first hole
                        if (board[row][col] == 0 &&
                                board[row - 1][col + 1] == atkdef &&
                                board[row - 2][col + 2] == atkdef &&
                                board[row - 3][col + 3] == atkdef) {
                            return col;
                        }
                        //second hole
                        else if (board[row][col] == atkdef &&
                                board[row - 1][col + 1] == 0 &&
                                board[row - 2][col + 2] == atkdef &&
                                board[row - 3][col + 3] == atkdef) {
                            return col + 1;
                        }
                        //third hole
                        else if (board[row][col] == atkdef &&
                                board[row - 1][col + 1] == atkdef &&
                                board[row - 2][col + 2] == 0 &&
                                board[row - 3][col + 3] == atkdef) {
                            return col + 2;
                        }
                    } catch (Exception ignored) {}

                }
            }
            for (int row = 0; row < 6 - 3; row++) {
                for (int col = 0; col < 7 - 3; col++) {
                    //last hole
                    if (board[row][col] == atkdef &&
                            board[row + 1][col + 1] == atkdef &&
                            board[row + 2][col + 2] == atkdef) {
                        if (board[row + 3][col + 3] == 0) {
                            return col + 3;
                        }
                    }
                    try{
                        //first hole
                        if (board[row][col] == 0 &&
                                board[row + 1][col + 1] == atkdef &&
                                board[row + 2][col + 2] == atkdef &&
                                board[row + 3][col + 3] == atkdef) {
                            return col;
                        }
                        //second hole
                        else if (board[row][col] == atkdef &&
                                board[row + 1][col + 1] == 0 &&
                                board[row + 2][col + 2] == atkdef &&
                                board[row + 3][col + 3] == atkdef) {
                            return col + 1;
                        }
                        //third hole
                        else if (board[row][col] == atkdef &&
                                board[row + 1][col + 1] == atkdef &&
                                board[row + 2][col + 2] == 0 &&
                                board[row + 3][col + 3] == atkdef) {
                            return col + 2;
                        }
                    } catch (Exception ignored) {}
                }
            }
        }
        //regular move algo-------------------------------------------->
        int column = (counter%7);

        //every three turns place token above players last move :TrollFace:
        if(counter > 2 && (counter+1)%3 == 0){
            return player_move;
        }
        return column;
    }

    public static void main(String[] args) {

        //Initial board
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

        //start game --------------------------->
        while (!(play1 || play2) || (turn == 21)) {
            String move1 = "";
            String move2 = "";

            System.out.println("Turn #" + ++turn + ":");

            System.out.println("P1's turn:");
            String input1 = scan.nextLine();
            move1 = placeToken(clean_input(input1), 1, board);

            //Player gets to retry if invalid move occurs
            while(!(move1.equals(""))){
                System.out.println(move1 + " Please try again:");
                input1 = scan.nextLine();
                move1 = placeToken(clean_input(input1), 1, board);
            }
            showBoard(board);
            play1 = connect4(board, 1);

            if (input.equals("2P")) {
                System.out.println("P2's turn:");
                String input2 = scan.nextLine();
                move2 = (placeToken(clean_input(input2), 2, board));

                //Player gets to retry if invalid move occurs
                while(!(move2.equals(""))){
                    System.out.println(move2 + " Please try again:");
                    input2 = scan.nextLine();
                    move2 = placeToken(clean_input(input2), 2, board);
                }
                showBoard(board);
                play2 = connect4(board, 2);

                //only for versing computer ------------------------------->
            }else{
                System.out.println("PC's turn:");
                int movePC = PC_bot(board,counter,clean_input(input1));
                placeToken(movePC, 2 , board);
                counter++;
                showBoard(board);
                play2 = connect4(board, 2);
            }
        }

        //game ends when player 1 or player 2 is true----------------------->
        if (play1)
        {
            System.out.println("Player 1 has won!");
        }else if (play2)
        {
            System.out.println("Player 2 has won!");
        } else
        {
            System.out.println("CAT'S GAME!");
        }
    }
}

