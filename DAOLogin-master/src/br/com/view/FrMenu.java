package br.com.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import br.com.anonymous.frontend.Label;
import br.com.anonymous.frontend.Panel;
import br.com.model.Usuario;
import br.com.statics.Cores;

public class FrMenu extends JFrame {

	private static final long serialVersionUID = 1L;

	private Panel tela;
	
	private static final Toolkit TK = Toolkit.getDefaultToolkit();
	private static final Dimension DIMENSION = TK.getScreenSize();
	
	private static FrMenu INSTACE;
	
	private Font normal = new Font("Verdana", Font.PLAIN, 16);
	
	private Label txtUser;
	private Label lblUsers;
	
	private FrMenu(Usuario user) {
		setTitle("Menu");
		setSize(DIMENSION);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("../images/user.png")).getImage());
		setResizable(false);
		setUndecorated(true);
				
		tela = new Panel(Cores.corCinzaClaro);
		tela.setLayout(null);
		tela.setSize(DIMENSION);
		setContentPane(tela);

		setJMenuBar(new Menu());

		txtUser = new Label(200, 30, "Seja bem vindo, " + user.getUsuario(), normal, Cores.corGhostWhite, null, SwingConstants.CENTER, SwingConstants.CENTER);
		txtUser.setLocation(tela.getWidth()/2 - txtUser.getWidth()/2, 20);
		tela.add(txtUser);
		
		lblUsers = new Label(500, 260,
				new ImageIcon(new ImageIcon(getClass().getResource("../images/work.png"))
						.getImage().getScaledInstance(500, 260, 100)));
		lblUsers.setLocation(tela.getWidth()/2 - lblUsers.getWidth()/2,
				tela.getHeight()/2 - lblUsers.getHeight()/2);
		tela.add(lblUsers);
		setVisible(true);
	}
	
	public final static FrMenu getInstance(Usuario user) {
		if(INSTACE == null) {
			INSTACE = new FrMenu(user);
		}
		return INSTACE;
	}
	
	public final static void destroyInstance() {
		INSTACE.dispose();
		INSTACE = null;
	}
}
