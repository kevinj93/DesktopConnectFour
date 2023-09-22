package four;

import javax.swing.*;
import java.awt.*;

import static four.ButtonReset.reset;

public class ConnectFour extends JFrame {

    public ConnectFour() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 425);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,7,2,2));
        Button.placeButtons(panel);

        setTitle("Connect Four");

        ButtonReset btn = new ButtonReset();
        btn.addActionListener(l -> reset());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btn);


        add(panel);
        add(buttonPanel);


//        setLayout(new FlowLayout(FlowLayout.CENTER, 2,2));
        getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        setVisible(true);

    }

}

