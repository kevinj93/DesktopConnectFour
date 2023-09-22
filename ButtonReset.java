package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonReset extends JButton {
    public ButtonReset(){
        super("Reset");
        setName("ButtonReset");
        setBackground(Color.decode("#ffd54f"));
        setEnabled(true);

    }

    public static void reset(){

        for (JButton[] button : Button.buttons) {
            for (JButton btn : button) {
                btn.setText(" ");
            }
        }
        Button.currentPlayer = 0;
        Button.setButtonColors();
        Button.removeActionListeners();
        Button.init();


    }

    @Override
    public void addActionListener(ActionListener l) {
        super.addActionListener(l);
    }
}
