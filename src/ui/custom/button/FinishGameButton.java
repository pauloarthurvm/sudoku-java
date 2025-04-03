package ui.custom.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class FinishGameButton  extends JButton {

    public FinishGameButton(final ActionListener actionListener) {
        this.setText("Finish game");
        this.addActionListener(actionListener);
    }
}
