package oop23.gfight.view;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import oop23.gfight.engine.EngineView;

public class SwingView implements EngineView{

    private JFrame frame;
    private Canvas canvas;

    private int width = 500;
    private int height = 400;

    @Override
    public void initialize() {
        frame = new JFrame("Geometry Fight");
        frame.setSize(width,height);     //needs to be changed---------------
        frame.setMinimumSize(new Dimension(width,height));
        //frame.setResizable(false);

        canvas = new Canvas(width,height);
        frame.getContentPane().add(canvas);

        frame.addWindowListener(new WindowAdapter(){//needs to be changed------------
			public void windowClosing(WindowEvent ev){
				System.exit(-1);
			}
			public void windowClosed(WindowEvent ev){
				System.exit(-1);
			}
		});

		frame.pack();
		frame.setVisible(true);
    }

    @Override
    public void render() {
        this.frame.repaint();
    }

}
