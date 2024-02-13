package gfight.view.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import gfight.engine.api.Engine;
import gfight.engine.api.Engine.EngineStatus;

public class TestPanel extends JPanel {
    TestPanel(final Engine engine, final String text) {
        
        JButton button = new JButton();
        button.setText(text);
        this.add(button);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                engine.changeStatus(EngineStatus.GAME);
            }
            
        });
    }
}
