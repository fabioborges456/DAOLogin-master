package br.com.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.com.statics.Cores;

@SuppressWarnings("serial")
public class Menu extends JMenuBar {

	private JMenu menu;
	
	private JMenuItem sair;
	
	public Menu() {
		setBackground(new Color(28,28,28));
		setBorder(null);

		menu = new JMenu("ARQUIVO");
		menu.setForeground(Cores.corSlateBlue3);
		menu.setFont(new Font("Verdana", Font.PLAIN, 18));
		menu.setContentAreaFilled(false);	
		menu.setArmed(false);
		add(menu);
		
		sair = new JMenuItem("Sair");
		sair.setSize(menu.getSize());
		sair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new FrLogin();
				FrMenu.destroyInstance();
			}
		});
		
		menu.add(sair);
		add(menu);
	}
	
	
}