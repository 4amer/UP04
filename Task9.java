import java.util.*;

public class MathematikoGame {

    static final int GRID_SIZE = 5;
    static final int DECK_SIZE = 52;
    static final int[] CARD_NUMBERS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    static int[][] grid = new int[GRID_SIZE][GRID_SIZE];
    static List<Integer> deck = new ArrayList<>();
    static Random random = new Random();

    public static void main(String[] args) {
        initializeDeck();
        playGame();
        calculateScore();
    }

    public static void initializeDeck() {
        for (int i = 0; i < 4; i++) {
            for (int num : CARD_NUMBERS) {
                deck.add(num);
            }
        }
        Collections.shuffle(deck);
    }

    public static void playGame() {
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            int card = drawCard();
            System.out.println("Card drawn: " + card);

            placeNumberRandomly(card);
        }
    }

    public static int drawCard() {
        return deck.remove(random.nextInt(deck.size()));
    }

    public static void placeNumberRandomly(int number) {
        while (true) {
            int row = random.nextInt(GRID_SIZE);
            int col = random.nextInt(GRID_SIZE);
            if (grid[row][col] == 0) {
                grid[row][col] = number;
                System.out.println("Placed number " + number + " at (" + row + ", " + col + ")");
                break;
            }
        }
    }

    public static void calculateScore() {
        int totalScore = 0;

        for (int i = 0; i < GRID_SIZE; i++) {
            totalScore += evaluateLine(grid[i]);
        }

        for (int j = 0; j < GRID_SIZE; j++) {
            int[] column = new int[GRID_SIZE];
            for (int i = 0; i < GRID_SIZE; i++) {
                column[i] = grid[i][j];
            }
            totalScore += evaluateLine(column);
        }

        int[] diagonal1 = new int[GRID_SIZE];
        int[] diagonal2 = new int[GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            diagonal1[i] = grid[i][i];
            diagonal2[i] = grid[i][GRID_SIZE - i - 1];
        }
        totalScore += evaluateLine(diagonal1);
        totalScore += evaluateLine(diagonal2);

        System.out.println("Total Score: " + totalScore);
    }

    public static int evaluateLine(int[] line) {

        int score = 0;

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : line) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        if (countMap.containsValue(4)) {
            score = 160;
        } else if (countMap.containsValue(3)) {
            score = 40;
        }
        return score;
    }
}
