package com.game.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TicTacToeGamePlay {
    // Create Tic Tac Toe Game Board
    static char[][] gameBoard = {
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '},
    };
    // Count to stop printing statement
    private static int i;

    // Create Global variable for user's positions
    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> botPositions = new ArrayList<>();

    // Printing game board
    public static void printGameBoard(char[][] gameBoard){
        for(char[] row : gameBoard){
            for(char character : row){
                System.out.print(character);
            }
            System.out.println();
        }
        if(i == 0){
            System.out.println("Board created! The game will start with player X");
        }
        i++;
    }

    // Game logic
    public static void gameOperations() throws InterruptedException {
        // Loop to continue until we find a winner/draw
        while((playerPositions.size() + botPositions.size()) <= 9){
            System.out.print("Your Turn! Enter your placement (1-9):");
            Scanner scan = new Scanner(System.in);
            int playerPosition;
            int botPosition = 0;
            boolean botFalseMove = true;
            // To restrict other invalid values
            if(scan.hasNextInt()){
                playerPosition = scan.nextInt();
                if(playerPositions.contains(playerPosition) || (botPositions.contains(playerPosition))
                        || (playerPosition > 9) || (playerPosition < 1)){
                    continue;
                }
            }
            else
                continue;

            piecePlacement(gameBoard, playerPosition, true);
            printGameBoard(gameBoard);
            String result = checkWinner(playerPositions, true);
            if(!result.equals(" ")) {
                System.out.println(result);
                System.exit(0);
            }
            System.out.println("Bot's Turn");
            while(botFalseMove)
            {
                botPosition = new Random().nextInt(9) +1;
                if(!botPositions.contains(botPosition) && !playerPositions.contains(botPosition)){
                    botFalseMove = false;
                }
            }
            piecePlacement(gameBoard, botPosition, false);
            TimeUnit.SECONDS.sleep(2);
            printGameBoard(gameBoard);
            result = checkWinner(botPositions, false);
            if(!result.equals(" ")) {
                System.out.println(result);
                System.exit(0);
            }
        }
    }

    private static String checkWinner(ArrayList<Integer> playerPositions, boolean player) {
        String p;
        String printStatement = " ";
        if(player){
            p = "Player X";
        }
        else{
            p = "Player O";
        }
        // Horizontal line winning conditions
        if(playerPositions.containsAll(Arrays.asList(1,2,3)) || (playerPositions.containsAll(Arrays.asList(4,5,6))) ||
        (playerPositions.containsAll(Arrays.asList(7,8,9)))){
            printStatement = p + " WON with Horizontal line";
            // Vertical line winning conditions
        } else if(playerPositions.containsAll(Arrays.asList(1,4,7)) || (playerPositions.containsAll(Arrays.asList(2,5,8))) ||
                (playerPositions.containsAll(Arrays.asList(3,6,9)))){
            printStatement = p + " WON with Vertical line";
            // Diagonal line winning conditions
        } else if(playerPositions.containsAll(Arrays.asList(1,5,9)) || (playerPositions.containsAll(Arrays.asList(3,5,7)))){
            printStatement = p + " WON with Diagonal line";
            // Draw condition
        }else if((playerPositions.size() + botPositions.size()) == 9){
            printStatement = "Game ends with a draw";
        }
        return printStatement;
    }

    // Adding the piece in 2D array
    private static void piecePlacement(char[][] gameBoard, int pos, boolean isPlayer) {
        char symbol;
        if(isPlayer){
            symbol = 'X';
            playerPositions.add(pos);
        }
        else{
            symbol = 'O';
            botPositions.add(pos);
        }

        switch (pos){
            //case -> is more efficient but it's not supported in lower versions
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        printGameBoard(gameBoard);
        gameOperations();
    }
}
