package timeTracking.view;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class HauptFenster extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HauptFenster() {
		setLayout(new BorderLayout(0, 0));

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		JMenuItem mntmDatenbank = new JMenuItem("Datenbank");
		mntmDatenbank.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				JDialog datenbanksettings = new DatenbankSettings();
				datenbanksettings.setVisible(true);
			}
		});
		mnMenu.add(mntmDatenbank);

		setSize(800, 400);
		setLocationRelativeTo(null);
		setTitle("Zeiterfassung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(new ProcessView(), BorderLayout.CENTER);
	}
}
