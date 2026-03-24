package visual;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setTitle("Sistema");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		Dimension dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height-48);
		setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new java.awt.Color(40, 40, 40));
		menuBar.setForeground(java.awt.Color.WHITE);
		menuBar.setPreferredSize(new Dimension(menuBar.getWidth(), 50));
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Registrar\r\n");
		mnNewMenu.setForeground(SystemColor.window);
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cliente");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/iconcliente.png"));
		java.awt.Image img = icon.getImage().getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
		ImageIcon iconoPequeno = new ImageIcon(img);
		mntmNewMenuItem.setIcon(iconoPequeno);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Regcliente cliente=new Regcliente();
				cliente.setModal(true);
				cliente.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Personal\r\n");
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		ImageIcon icon2 = new ImageIcon(getClass().getResource("/imagenes/iconpersonal.png"));
		java.awt.Image img2 = icon2.getImage().getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
		ImageIcon iconoPequeno2 = new ImageIcon(img2);
		mntmNewMenuItem_1.setIcon(iconoPequeno2);
		mnNewMenu.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

	}

}
