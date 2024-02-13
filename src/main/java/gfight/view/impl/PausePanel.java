package gfight.view.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import gfight.engine.api.Engine;
import gfight.engine.api.Engine.EngineStatus;

public class PausePanel extends JPanel {

    PausePanel(final Engine engine) {
        final JButton continueBTN = new JButton();
        continueBTN.setText("continue");
        continueBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                engine.changeStatus(EngineStatus.GAME);
            }
        });
        final JButton gotomenuBTN = new JButton();
        gotomenuBTN.setText("go to the menu");
        gotomenuBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                engine.changeStatus(EngineStatus.MENU);
            }
        });

        this.add(continueBTN);
        this.add(gotomenuBTN);
    }
}
