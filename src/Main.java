import java.util.Scanner;

public class Main {
    final static Scanner scanner = new Scanner(System.in);
    static String[][] newMove = new String[][]{{"_", "_", "_"}, {"_", "_", "_"}, {"_", "_", "_"}}; //empty field
    static String[] cases = new String[8]; //array to store possible cases of the field

    // draw board
    public static void draw () {
        System.out.println("---------");
        System.out.println("| " + newMove[0][0] + " " + newMove[0][1] + " " + newMove[0][2] + " |") ;
        System.out.println("| " + newMove[1][0] + " " + newMove[1][1] + " " + newMove[1][2] + " |") ;
        System.out.println("| " + newMove[2][0] + " " + newMove[2][1] + " " + newMove[2][2] + " |") ;
        System.out.println("---------");
    }
    // check if numbers
    public static String[] ifNumers (){
        int xCoord;
        int yCoord;
        String[] coords = {"0", "0"};
        boolean enteredNumbers = false;
        while(!enteredNumbers) {
            try {
                String input = scanner.nextLine();
                coords = input.split(" ");
                xCoord = Integer.parseInt(coords[0]);
                yCoord = Integer.parseInt(coords[1]);
                enteredNumbers = true;
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        }
        return coords;
    }
    // check if valid coordinates
    public static String[] validCoords(String[]coords) {
        int xCoord = Integer.parseInt(coords[0]);
        int yCoord = Integer.parseInt(coords[1]);
        while (xCoord < 1 || xCoord > 3 || yCoord < 1 || yCoord > 3){
            System.out.println("Coordinates should be from 1 to 3!");
            System.out.println("Enter the coordinates:");
            coords = ifNumers();
            xCoord = Integer.parseInt(coords[0]);
            yCoord = Integer.parseInt(coords[1]);
        }
        return coords;
    }
    // user enters coordinates
    public static int[] nextMove () {
        String[] coords = ifNumers();
        coords = validCoords(coords);
        int xCoord = Integer.parseInt(coords[0]);
        int yCoord = Integer.parseInt(coords[1]);
        // now that we made sure that user had valid coordinates, check if cell is empty
        int temp = xCoord;
        xCoord = 3- yCoord;
        yCoord = temp - 1; // first convert coordinates to array indexes
        while (!(newMove[xCoord][yCoord]).equals("_")){
            System.out.println("This cell is occupied! Choose another one!");
            System.out.println("Enter the coordinates:");
            coords = ifNumers(); //make sure users inputs numbers
            coords = validCoords(coords); //make sure coords are valid
            // get new coordinates from user inputs and convert to array indexes
            xCoord = Integer.parseInt(coords[0]);
            yCoord = Integer.parseInt(coords[1]);
            temp = xCoord;
            xCoord = 3- yCoord;
            yCoord = temp - 1;
        }
        int[] xy = {xCoord, yCoord};
        return xy;
    }

    // game starts, ask for user inputs
    public static void gameStarts () {
        boolean hasWinner = false;
        int numOfMoves = 1;
        while (!hasWinner){ //no winner, keep going
            System.out.println("Enter the coordinates:");
            int[] xy = nextMove(); //ask for input
            int xCoord = xy[0];
            int yCoord = xy[1]; // get x and y coordinates from user input
            if (numOfMoves % 2 == 0) {  // an even number of moves means O moves
                newMove[xCoord][yCoord] = "O"; // O makes a move
            } else { // an odd number of moves means X moves
                newMove[xCoord][yCoord] = "X"; // X makes a move
            }
            draw(); //draw the field after X/O makes a move
            hasWinner = gameResults(); //do we have a winner?
            if (hasWinner) {
                System.exit(1); //if yes, exit the game
            } else numOfMoves ++; //if no, keep going
        }
    }

    // check if we have a winner
    public static boolean gameResults () {
        String case0 = (newMove[0][0].concat(newMove[0][1])).concat(newMove[0][2]);
        String case1 = (newMove[1][0].concat(newMove[1][1])).concat(newMove[1][2]);
        String case2 = (newMove[2][0].concat(newMove[2][1])).concat(newMove[2][2]);
        String case3 = (newMove[0][0].concat(newMove[1][0])).concat(newMove[2][0]);
        String case4 = (newMove[0][1].concat(newMove[1][1])).concat(newMove[2][1]);
        String case5 = (newMove[0][2].concat(newMove[1][2])).concat(newMove[2][2]);
        String case6 = (newMove[0][0].concat(newMove[1][1])).concat(newMove[2][2]);
        String case7 = (newMove[0][2].concat(newMove[1][1])).concat(newMove[2][0]);
        cases = new String[]{case0, case1, case2, case3, case4, case5, case6, case7}; //possible cases

        int countX = 0; // # of occurrences of having 3 X's in a row
        int countO = 0; // # of occurrences of having 3 O's in a row
        for (int i = 0; i < 8; i++) {
            if (cases[i].equals("XXX")) {
                countX += 1;
            } else if (cases[i].equals("OOO")) {
                countO += 1;
            }
        }
        // count the # of empty cells left
        int count_ = 0;
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 3; j++) {
                if (newMove[k][j] == "_"){
                    count_++;
                }
            }
        }

        if (countX == 0 && countO == 0 && count_ == 0) {
            System.out.println("Draw"); //when no side has a 3 in a row and field has no empty cells
            return true; // true means game is finished
        }  else if (countX != 0) {
            System.out.println("X wins"); //3 X's in a row
            return true;
        } else if (countO != 0) {
            System.out.println("O wins"); //3 O's in a row
            return true;
        } else return false; // false means game continues
    }

    public static void main(String[] args) {
        draw();
        gameStarts();
    }
}
