package br.com.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import br.com.dao.UserDao;
import br.com.anonymous.frontend.Button;
import br.com.anonymous.frontend.Label;
import br.com.anonymous.frontend.Panel;
import br.com.anonymous.frontend.PasswordField;
import br.com.anonymous.frontend.TextField;
import br.com.model.Usuario;
import br.com.statics.Cores;

public class FrLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Panel tela;
	private Panel container;
	
	private Label lblLogin, lblSenha, lblCadastrar, lblTitle;
	private TextField txtLogin;
	private JPasswordField txtSenha;
	private Button btnLogin;	
	
	private Font title = new Font("Verdana", Font.PLAIN, 22);
	private Font labelFont = new Font("Verdana", Font.PLAIN, 18);
	private Font normal = new Font("Verdana", Font.PLAIN, 16);
	private Font small = new Font("Verdana", Font.PLAIN, 14);
	
	public FrLogin() {
		setTitle("Login");
		setSize(1080, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("../images/login.png")).getImage());
		setResizable(false);
		
		tela = new Panel(Cores.corCinza);
		tela.setSize(this.getSize());
		setContentPane(tela);
		
		lblTitle = new Label(300, 80, "Página Login", title, Cores.corGhostWhite, null, SwingConstants.CENTER, SwingConstants.CENTER);
		lblTitle.setLocation(tela.getWidth()/2 - lblTitle.getWidth()/2,  20);
		tela.add(lblTitle);
		
		initContainer();
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        int res = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja fechar o programa?", "Fechar programa?", 
			            JOptionPane.YES_NO_OPTION,
			            JOptionPane.QUESTION_MESSAGE);
		        
		    	if (res == JOptionPane.YES_OPTION)
		            dispose();
		        
		    }
		});
		
		setVisible(true);
	}
	
	private void initContainer() {
		container = new Panel(500, 300, Color.DARK_GRAY);
		container.setBorder(BorderFactory.createLineBorder(Cores.corGhostWhite));
		container.setLocation(
					getWidth()/2 - container.getWidth()/2,
					getHeight()/2 -container.getHeight()/2
				);
		
		int width = container.getWidth()/100*60;
		int height = 35;
		int x = container.getWidth()/2 - width / 2;

		lblLogin = new Label(80, height, "Usuário", labelFont, Cores.corGhostWhite, null);
		lblLogin.setLocation(x, 40);
		lblLogin.setHorizontalAlignment(SwingConstants.LEFT);
		container.add(lblLogin);
		
		txtLogin = new TextField(width, height, "", normal, Color.LIGHT_GRAY, Color.BLACK);
		txtLogin.setLocation(x, 80);
		container.add(txtLogin);
		
		lblSenha = new Label(80, height, "Senha", labelFont, Cores.corGhostWhite, null);
		lblSenha.setLocation(x, 120);
		lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
		container.add(lblSenha);
		
		txtSenha = new PasswordField(width, height, "", normal, Color.LIGHT_GRAY, Color.BLACK);
		txtSenha.setLocation(x, 160);
		container.add(txtSenha);
		
		lblCadastrar = new Label(140, 20, "Não tenho cadastro", small, Cores.corAzulRoyal, null);
		lblCadastrar.setLocation(x, 200);
		lblCadastrar.setHorizontalAlignment(SwingConstants.LEFT);
		lblCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblCadastrar.addMouseListener(registerAction());
		container.add(lblCadastrar);
		
		btnLogin = new Button(80, 30, Color.DARK_GRAY, Cores.corSlateBlue3, labelFont, "Login", null, 0, Color.DARK_GRAY, Cores.corGhostWhite);
		btnLogin.setLocation(container.getWidth() - 120, container.getHeight() - 60);
		btnLogin.addActionListener(loginAction());
		container.add(btnLogin);
		
		tela.add(container);
	}

	private void login() {
		Usuario usuario = new Usuario();
		usuario.setUsuario(txtLogin.getText());
		usuario.setSenha(new String(txtSenha.getPassword()));
		
		UserDao dao = new UserDao();
		List<Usuario> users = dao.getLista();
		
		boolean exists = false;
		for(Usuario user: users) {
			if(user.getUsuario().equals(usuario.getUsuario())) {
				if(user.getSenha().equals(usuario.getSenha())) {
					FrMenu.getInstance(user);
					dispose();
					exists = true;
					break;
				}
			}
		}
		
		if(!exists)
			JOptionPane.showMessageDialog(null, "Usuario ou senha incorretos");
	}
	
	private ActionListener loginAction() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		};
	}
	
	private MouseListener registerAction() {
		return new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				new FrRegister();
				dispose();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		};
	}
	
}