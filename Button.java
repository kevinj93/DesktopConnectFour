package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Button {
    static final int ROW = 6, COLUMN = 7;
    static JButton[][] buttons = new JButton[ROW][COLUMN];
    static int currentPlayer = 0;
    public static String lastClicked;

    public static void placeButtons(JPanel panel) {

        for (int row = ROW; row > 0; row--) {
            for (int column = 0; column < COLUMN; column++) {
                Cell button = new Cell(row, column);
                buttons[row - 1][column] = button;

                button.setText(" ");
                button.setFont(new Font("Arial", Font.PLAIN, 40));

                panel.add(button);
            }
        }
        setButtonColors();
        init();
    }

    public static void changePlayer() {
        currentPlayer = (currentPlayer == 0) ? 1 : 0;
    }

    public static String getLetter() {
        return currentPlayer == 0 ? "X" : "O";
    }

    public static void setButtonColors() {

        Color a, b;

        for (int i = 0; i < buttons.length; i++) {
            if (i % 2 != 0) {
//                a = Color.decode("#9CCC66");
                b = Color.decode("#AED582");

                a = Color.decode("#AED582");

            } else {
                a = Color.decode("#AED582");
//                b = Color.decode("#9CCC66");

                b = Color.decode("#AED582");
            }
            for (int j = 0; j < buttons[i].length; j++) {
                if (j % 2 == 0) buttons[i][j].setBackground(a);
                else {
                    buttons[i][j].setBackground(b);
                }
            }
        }

    }

    public static void setActive(JButton button) {

        button.addActionListener(l -> {
            lastClicked = button.getName();
            updateField();
            checkWinner();

        });
    }

    public static void init() {
        for (JButton[] button : buttons) {
            for (JButton btn : button) setActive(btn);
        }
    }

    public static int getLastColumn() {
        return lastClicked.charAt(6) - 'A';
    }

    public static int getLastFullField() {
        int l = 0;
        for (int i = 0; i < ROW; i++) if (Objects.equals(buttons[i][getLastColumn()].getText(), " ")) l++;
        return l;
    }

    public static void updateField() {

        int row = ROW - getLastFullField();
        if (getLastFullField() > 0) {
            buttons[row][getLastColumn()].setText(getLetter());
            changePlayer();
        }

    }

    public static void removeActionListeners() {
        for (JButton[] button : buttons)
            for (JButton btn : button) {
                for (ActionListener al : btn.getActionListeners()) {
//                    btn.setEnabled(false);
                    btn.removeActionListener(al);
                }
            }
    }

    public static void checkDirections() {
        int[][] directions = {{1,0}, {1,-1}, {1,1}, {0,1}};
        for (int[] d : directions) {
            int dx = d[0];
            int dy = d[1];
            for (int x = 0; x < ROW; x++) {
                for (int y = 0; y < COLUMN; y++) {
                    int lastx = x + 3*dx;
                    int lasty = y + 3*dy;
                    if (0 <= lastx && lastx < ROW && 0 <= lasty && lasty < COLUMN) {
                        String w = buttons[x][y].getText();
                        if (!Objects.equals(w, " ") && Objects.equals(w, buttons[x + dx][y + dy].getText())
                                && Objects.equals(w, buttons[x + 2 * dx][y + 2 * dy].getText())
                                && Objects.equals(w, buttons[lastx][lasty].getText())) {
                            buttons[x][y].setBackground(Color.GREEN);
                            buttons[x + dx][y + dy].setBackground(Color.GREEN);
                            buttons[x + 2 * dx][y + 2 * dy].setBackground(Color.GREEN);
                            buttons[lastx][lasty].setBackground(Color.GREEN);
                            removeActionListeners();
                        }
                    }
                }
            }
        }
    }

    public static void checkWinner() {
        checkDirections();
    }

}
