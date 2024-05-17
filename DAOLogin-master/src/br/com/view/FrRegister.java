package br.com.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import br.com.anonymous.frontend.Button;
import br.com.anonymous.frontend.Label;
import br.com.anonymous.frontend.Panel;
import br.com.anonymous.frontend.PasswordField;
import br.com.anonymous.frontend.TextField;
import br.com.dao.UserDao;
import br.com.model.Usuario;
import br.com.statics.Cores;

public class FrRegister extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Panel tela;
	private Panel container;
	
	private Label lblLogin, lblSenha, lblConfirmaSenha, lblTitle;
	private TextField txtLogin;
	private PasswordField txtSenha, txtConfirmaSenha;
	private Button btnRegister;
	private Button btnVoltar;
	
	private Font title = new Font("Verdana", Font.PLAIN, 22);
	private Font labelFont = new Font("Verdana", Font.PLAIN, 18);
	private Font normal = new Font("Verdana", Font.PLAIN, 16);
	
	public FrRegister() {
		setTitle("Cadastro");
		setSize(1080, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("../images/register.png")).getImage());
		setResizable(false);
		
		tela = new Panel(Cores.corCinza);
		tela.setSize(this.getSize());
		setContentPane(tela);
		
		initContainer();
		initControls();
		
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
		container = new Panel(500, 370, Color.DARK_GRAY);
		container.setBorder(BorderFactory.createLineBorder(Cores.corGhostWhite));
		container.setLocation(
					getWidth()/2 - container.getWidth()/2,
					getHeight()/2 -container.getHeight()/2
				);
		
		int width = container.getWidth()/100*60;
		int height = 35;
		int x = container.getWidth()/2 - width / 2;

		lblLogin = new Label(80, height, "Login", labelFont, Cores.corGhostWhite, null);
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

		lblConfirmaSenha = new Label(170, height, "Confirme a senha", labelFont, Cores.corGhostWhite, null);
		lblConfirmaSenha.setLocation(x, 200);
		lblConfirmaSenha.setHorizontalAlignment(SwingConstants.LEFT);
		container.add(lblConfirmaSenha);
		
		txtConfirmaSenha = new PasswordField(width, height, "", normal, Color.LIGHT_GRAY, Color.BLACK);
		txtConfirmaSenha.setLocation(x, 240);
		container.add(txtConfirmaSenha);
		
		btnRegister = new Button(130, 30, Color.DARK_GRAY, Cores.corAzulRoyal, labelFont, "Salvar", null, 0, Color.DARK_GRAY, Cores.corGhostWhite);
		btnRegister.setLocation(container.getWidth() - btnRegister.getWidth() - 20, container.getHeight() - 60);
		btnRegister.addActionListener(registerAction());
		container.add(btnRegister);

		tela.add(container);
	}
	
	private void initControls() {
		lblTitle = new Label(300, 80, "Página Cadastro", title, Cores.corGhostWhite, null, SwingConstants.CENTER, SwingConstants.CENTER);
		lblTitle.setLocation(tela.getWidth()/2 - lblTitle.getWidth()/2,  20);
		tela.add(lblTitle);
		
		btnVoltar = new Button(130, 30, Color.DARK_GRAY, Cores.corSlateBlue3, labelFont, "Voltar", null, 0, Color.DARK_GRAY, Cores.corGhostWhite);
		btnVoltar.setLocation(20, container.getHeight() - 60);
		btnVoltar.addActionListener(backAction());
		container.add(btnVoltar);
	}
	
	private ActionListener backAction() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new FrLogin();
				dispose();
			}
		};
	}
	
	private ActionListener registerAction() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		};
	}
	
	private void register() {
		String user = txtLogin.getText();
		String pass = new String(txtSenha.getPassword());
		String confirm = new String(txtConfirmaSenha.getPassword());
		
		if(user.length() != 0) {
			if(pass.equals(confirm)) {
				UserDao dao = new UserDao();
				Usuario target = dao.getUsuario(user);
				if(target.getUsuario() == null) {
					Usuario novoUsuario = new Usuario();
					novoUsuario.setUsuario(user);
					novoUsuario.setSenha(pass);
					dao.insert(novoUsuario);
					JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso");
					new FrLogin();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Usuário já cadastrado");
				}
			} else {
				JOptionPane.showMessageDialog(null, "As senhas não se coincidem");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos");
		}
	}

}
