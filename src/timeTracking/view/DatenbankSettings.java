package timeTracking.view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

import timeTracking.main.Controller;
import timeTracking.model.Database;

public class DatenbankSettings extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public DatenbankSettings() {
		setTitle("Datenbank Einstellungen");
		setModal(true);
		getContentPane().setLayout(null);
		
		setSize(300,274);
		setLocationRelativeTo(this.getParent());

		JLabel lblNewLabel = new JLabel("Datenbank");
		lblNewLabel.setBounds(12, 75, 91, 15);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Server");
		lblNewLabel_1.setBounds(12, 30, 70, 15);
		getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(110, 73, 174, 19);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(110, 28, 174, 19);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("Speichern");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnNewButton.setBounds(12, 199, 272, 25);
		getContentPane().add(btnNewButton);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(12, 117, 70, 15);
		getContentPane().add(lblPort);
		
		textField_2 = new JTextField();
		textField_2.setBounds(110, 115, 174, 19);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JRadioButton rdbtnMssql = new JRadioButton("MS-SQl");
		rdbtnMssql.setBounds(12, 152, 116, 23);
		getContentPane().add(rdbtnMssql);
		
		JRadioButton rdbtnMysql = new JRadioButton("MYSQL");
		rdbtnMysql.setBounds(156, 152, 128, 23);
		getContentPane().add(rdbtnMysql);
	}
}
