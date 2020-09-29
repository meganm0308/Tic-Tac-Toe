import java.util.Scanner;

public class Main {
    String case0;
    String case1;
    String case2;
    String case3;
    String case4;
    String case5;
    String case6;
    String case7;
    String[] cases;

    // draw board
    public void draw (String gameSymbols) {
        System.out.println("---------");
        System.out.println("| " + gameSymbols.charAt(0) + " " + gameSymbols.charAt(1) + " " + gameSymbols.charAt(2) + " |") ;
        System.out.println("| " + gameSymbols.charAt(3) + " " + gameSymbols.charAt(4) + " " + gameSymbols.charAt(5) + " |") ;
        System.out.println("| " + gameSymbols.charAt(6) + " " + gameSymbols.charAt(7) + " " + gameSymbols.charAt(8) + " |") ;
        System.out.println("---------");
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
        return (numOfO - numOfX) <= 1 && (numOfX - numOfO) <= 1;
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
        case0 = gameSymbols.substring(0,3);
        case1 = gameSymbols.substring(3,6);
        case2 = gameSymbols.substring(6,9);
        case3 = gameSymbols.charAt(0) + String.valueOf(gameSymbols.charAt(3)) +
                gameSymbols.charAt(6);
        case4 = gameSymbols.charAt(1) + String.valueOf(gameSymbols.charAt(4)) +
                gameSymbols.charAt(7);
        case5 = gameSymbols.charAt(2) + String.valueOf(gameSymbols.charAt(5)) +
                gameSymbols.charAt(8);
        case6 = gameSymbols.charAt(0) + String.valueOf(gameSymbols.charAt(4)) +
                gameSymbols.charAt(8);
        case7 = gameSymbols.charAt(2) + String.valueOf(gameSymbols.charAt(4)) +
                gameSymbols.charAt(6);
        cases = new String[]{case0, case1, case2, case3, case4, case5, case6, case7};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String gameBoard = scanner.nextLine();
        Main game1 = new Main(gameBoard);
        game1.draw(gameBoard);
        game1.gameResults(gameBoard);
    }
}