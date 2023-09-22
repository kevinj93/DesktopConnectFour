package four;

import javax.swing.*;

public class Cell extends JButton {
    public Cell(int row, int column) {
        super("%c%d".formatted(column + 'A', row));
        setFocusPainted(false);
        setName("Button%c%d".formatted(column + 'A', row));
    }


}
