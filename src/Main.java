import java.util.Scanner;
public class Main {
    final static Scanner scanner = new Scanner(System.in);
    String[][] xMoves = new String[3][3]; //array to store X's and O's
    String[] cases = new String[8]; //array to store possible cases of the field


    // draw board
    public void draw () {
        System.out.println("---------");
        System.out.println("| " + xMoves[0][0] + " " + xMoves[0][1] + " " + xMoves[0][2] + " |") ;
        System.out.println("| " + xMoves[1][0] + " " + xMoves[1][1] + " " + xMoves[1][2] + " |") ;
        System.out.println("| " + xMoves[2][0] + " " + xMoves[2][1] + " " + xMoves[2][2] + " |") ;
        System.out.println("---------");
    }
    // check if numbers
    public String[] ifNumers (){
        int xCoord = 0;
        int yCoord = 0;
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
                continue;
            }
        }
        return coords;
    }
    // check if valid coordinates
    public String[] validCoords(String[]coords) {
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
    public void nextMove () {
        String[] coords = ifNumers();
        coords = validCoords(coords);
        int xCoord = Integer.parseInt(coords[0]);
        int yCoord = Integer.parseInt(coords[1]);
        // now that we made sure that user had valid coordinates, check if cell is empty
        int temp = xCoord;
        xCoord = 3- yCoord;
        yCoord = temp - 1; // first convert coordinates to array indexes
        while (!(xMoves[xCoord][yCoord]).equals("_")){
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
        xMoves[xCoord][yCoord] = "X"; // X makes a move
    }

    // check if field has a lot more X's than O's or vice versa. the difference should be 1 or 0
    public boolean moreOnOneSide (String gameSymbols) {
        int numOfX = 0;
        int numOfO = 0;
        for (int i = 0; i < gameSymbols.length(); i++) {
            if (gameSymbols.charAt(i) == 'X') {
                numOfX += 1;
            } else if (gameSymbols.charAt(i) == 'O')
                numOfO += 1;
        }
        if ((numOfO - numOfX) >1 || (numOfX - numOfO) >1) {
            return false;
        } else return true;
    }
    // print game results
    public void gameResults (String gameSymbols) {
        int countX= 0; // # of occurrences of having 3 X's in a row
        int countO = 0; // # of occurrences of having 3 O's in a row
        for (int i = 0; i < 8; i++) {
            if (cases[i].equalsIgnoreCase("XXX")) {
                countX += 1;
            } else if (cases[i].equalsIgnoreCase("OOO")) {
                countO += 1;
            }
        }
        if (!moreOnOneSide(gameSymbols)){
            System.out.println("Impossible");
            System. exit(1);
        }
        if (countX == 0 && countO == 0 && !gameSymbols.contains("_")) {
            System.out.println("Draw"); //when no side has a 3 in a row and field has no empty cells
        } else if (countO !=0 && countX != 0) {
            System.out.println("Impossible"); //when the field has both 3 X's and 3 O's in a row
        } else if (countX == 0 && countO == 0 && gameSymbols.contains("_")){
            System.out.println("Game not finished"); //when no side has a 3 in a row but field has empty cells
        } else if (countX != 0) {
            System.out.println("X wins"); //3 X's in a row
        } else if (countO != 0) {
            System.out.println("O wins"); //3 O's in a row
        }
    }

    // establish board
    public Main(String gameSymbols) {
        String case0 = gameSymbols.substring(0,3);
        String case1 = gameSymbols.substring(3,6);
        String case2 = gameSymbols.substring(6,9);
        String case3 = gameSymbols.valueOf(gameSymbols.charAt(0)) + gameSymbols.valueOf(gameSymbols.charAt(3)) +
                gameSymbols.valueOf(gameSymbols.charAt(6));
        String case4 = gameSymbols.valueOf(gameSymbols.charAt(1)) + gameSymbols.valueOf(gameSymbols.charAt(4)) +
                gameSymbols.valueOf(gameSymbols.charAt(7));
        String case5 = gameSymbols.valueOf(gameSymbols.charAt(2)) + gameSymbols.valueOf(gameSymbols.charAt(5)) +
                gameSymbols.valueOf(gameSymbols.charAt(8));
        String case6 = gameSymbols.valueOf(gameSymbols.charAt(0)) + gameSymbols.valueOf(gameSymbols.charAt(4)) +
                gameSymbols.valueOf(gameSymbols.charAt(8));
        String case7 = gameSymbols.valueOf(gameSymbols.charAt(2)) + gameSymbols.valueOf(gameSymbols.charAt(4)) +
                gameSymbols.valueOf(gameSymbols.charAt(6));
        cases = new String[]{case0, case1, case2, case3, case4, case5, case6, case7}; //possible cases

        int initialIndex = 0; //use this to go through user's string input
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                xMoves[i][j] = gameSymbols.valueOf(gameSymbols.charAt(initialIndex));
                initialIndex ++;
            }

        }
    }

    public static void main(String[] args) {
        System.out.println("Enter cells:");
        String gameBoard = scanner.nextLine();
        Main game1 = new Main(gameBoard);
        game1.draw();
        System.out.println("Enter the coordinates:");
        game1.nextMove();
        game1.draw();
        //game1.gameResults(gameBoard);
    }
}
