package main.java.kyu5;

/**
 * 5 kyu - Snakes and Ladders
 *
 * https://www.codewars.com/kata/587136ba2eefcb92a9000027
 *
 * Details:
 *
 *  Snakes and Ladders is an ancient Indian board game regarded today as a worldwide classic. It is
 *  played between  two or more players on a gameboard having numbered, gridded squares. A number
 *  of "ladders" and "snakes" are  pictured on the board, each connecting two specific board
 *  squares. (Source Wikipedia)
 *
 *  Your task is to make a simple class called SnakesLadders. The test cases will call the method
 *  play(die1, die2) independently of the state of the game or the player turn. The variables die1
 *  and die2 are the die thrown in a turn and are both integers between 1 and 6. The player will
 *  move the sum of die1 and die2.
 *
 *  Rules:
 *     1.  There are two players and both start off the board on square 0.
 *     2.  Player 1 starts and alternates with player 2.
 *     3.  You follow the numbers up the board in order 1=>100
 *     4.  If the value of both die are the same then that player will have another go.
 *     5.  Climb up ladders. The ladders on the game board allow you to move upwards and get ahead
 *     faster. If you land exactly on a square that shows an image of the bottom of a ladder, then
 *     you may move the player all the way up to the square at the top of the ladder. (even if you
 *     roll a double).
 *     6.  Slide down snakes. Snakes move you back on the board because you have to slide down them.
 *     If you land exactly at the top of a snake, slide move the player all the way to the square
 *     at the bottom of the snake or chute. (even if you roll a double).
 *     7.  Land exactly on the last square to win. The first person to reach the highest square on
 *     the board wins. But there's a twist! If you roll too high, your player "bounces" off the
 *     last square and moves back. You can only win by rolling the exact number needed to land on
 *     the last square. For example, if you are on square 98 and roll a five, move your game piece
 *     to 100 (two moves), then "bounce" back to 99, 98, 97 (three, four then five moves.)
 *     8.  If the Player rolled a double and lands on the finish square “100” without any remaining
 *     moves then the Player wins the game and does not have to roll again.
 */

import java.util.ArrayList;

public class SnakesLadders {
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Ladder> modifiers = new ArrayList<>();
    private boolean isGameStopped;

    public SnakesLadders() {
        players.add(new Player(1, true));
        players.add(new Player(2));

        modifiers.add(new Ladder(2,38));
        modifiers.add(new Ladder(7,14));
        modifiers.add(new Ladder(8,31));
        modifiers.add(new Ladder(7,14));
        modifiers.add(new Ladder(8,31));
        modifiers.add(new Ladder(15,26));
        modifiers.add(new Ladder(21,42));
        modifiers.add(new Ladder(28,84));
        modifiers.add(new Ladder(36,44));
        modifiers.add(new Ladder(51,67));
        modifiers.add(new Ladder(71,91));
        modifiers.add(new Ladder(78,98));
        modifiers.add(new Ladder(87,94));

        modifiers.add(new Slope(16,6));
        modifiers.add(new Slope(46,25));
        modifiers.add(new Slope(49,11));
        modifiers.add(new Slope(62,19));
        modifiers.add(new Slope(64,60));
        modifiers.add(new Slope(74,53));
        modifiers.add(new Slope(89,68));
        modifiers.add(new Slope(92,88));
        modifiers.add(new Slope(95,75));
        modifiers.add(new Slope(99,80));

        isGameStopped = false;
    }

    class Ladder {
        private int startX, endX;

        public Ladder(int startX, int endX) {
            this.startX = startX;
            this.endX = endX;
        }
    }

    class Slope extends Ladder {
        public Slope(int startX, int endX) {
            super(startX, endX);
        }
    }

    class Player {
        private int id, x;
        private boolean isWinner, isCurrentTurn;

        public Player(int id) {
            this.id = id;
        }

        public Player(int id, boolean isCurrentTurn) {
            this.id = id;
            this.isCurrentTurn = isCurrentTurn;
        }
    }

    private String currentState(Player p) {
        if (p.x == 100 && !isGameStopped) {
            p.isWinner = true;
            isGameStopped = true;
            return String.format("Player %d Wins!", p.id);
        } else {
            if (isGameStopped) {
                return ("Game over!");
            }
            return String.format("Player %d is on square %d", p.id, p.x);
        }
    }

    private void checkPosition(Player p) {
        modifiers.forEach(m -> {
            if (m.startX == p.x) {
                p.x = m.endX;
            }
        });
    }

    public String play(int die1, int die2) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isCurrentTurn) {
                players.get(i).x += die1 + die2;

                if (players.get(i).x > 100) {
                    players.get(i).x = 100 - (players.get(i).x - 100);
                }

                checkPosition(players.get(i));

                if (die1 != die2) {
                    players.get(i).isCurrentTurn = false;

                    if (i != players.size() - 1) {
                        players.get(i + 1).isCurrentTurn = true;
                    } else {
                        players.get(0).isCurrentTurn = true;
                    }
                }
                return currentState(players.get(i));
            }
        }
        return null;
    }
}
