package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.AlunoDAO;
import dao.CursoDAO;
import model.Aluno;
import model.Curso;

public class MostrarCurso extends JFrame implements ActionListener {

	JScrollPane scroll = new JScrollPane();
	JTable table;
	JPanel panel;
	JButton back, update, delete;

	public MostrarCurso() {

		setSize(800, 437);
		setTitle("Curso");
		setResizable(false);
		setLocation(280, 250);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setLayout(null);
		setVisible(true);

		mostrar();
		panel = new JPanel();
		panel.setSize(800, 300);
		panel.setLocation(0, 100);
		panel.setBackground(Color.WHITE);

		back = new JButton("Voltar");
		back.setSize(120, 30);
		back.setLocation(50, 50);
		back.setFont(new Font("Arial", Font.BOLD, 20));
		back.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR) {
		});
		back.addActionListener(this);

		update = new JButton("Atualizar");
		update.setSize(120, 30);
		update.setLocation(450, 50);
		update.setFont(new Font("Arial", Font.BOLD, 20));
		update.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR) {
		});
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				updateActionPerformed(evt);
			}
		});

		delete = new JButton("Deletar");
		delete.setSize(120, 30);
		delete.setLocation(600, 50);
		delete.setFont(new Font("Arial", Font.BOLD, 20));
		delete.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR) {
		});
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				deleteActionPerformed(evt);
			}
		});

		getContentPane().add(scroll);
		getContentPane().add(back);
		getContentPane().add(update);
		getContentPane().add(delete);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		new Mostrar();
	}

	public void mostrar() {

		CursoDAO dao = new CursoDAO();

		DefaultTableModel dft = new DefaultTableModel();
		dft.setNumRows(0);
		dft.addColumn("Id");
		dft.addColumn("Codigo do curso");
		dft.addColumn("Nome do curso");
		dft.addColumn("Tipo do curso");
		dft.addColumn("Carga horaria");
		dft.addColumn("Codigo do instituto");
		table = new JTable(dft);
		table.getColumnModel().getColumn(0).setMinWidth(30);
		table.getColumnModel().getColumn(0).setMaxWidth(30);
		table.getColumnModel().getColumn(1).setMinWidth(75);
		table.getColumnModel().getColumn(2).setMinWidth(200);
		table.getColumnModel().getColumn(3).setMinWidth(75);
		table.getColumnModel().getColumn(4).setMinWidth(75);
		table.getColumnModel().getColumn(5).setMinWidth(75);
		scroll.setViewportView(table);
		scroll.setBounds(0, 100, 790, 300);
		int i = 0;
		for (Curso d : dao.mostrarCursos()) {

			dft.insertRow(i, new Object[] { d.getId(), d.getCodCurso(), d.getNomeCurso(), d.getTipoCurso(),
					d.getCargaHoraria(), d.getCodInstituto() });
			i++;
		}
	}

	public void updateActionPerformed(ActionEvent evt) {

		if (table.getSelectedRow() != -1) {

			Curso a = new Curso();
			CursoDAO dao = new CursoDAO();

			a.setCodCurso(table.getValueAt(table.getSelectedRow(), 1).toString());
			a.setNomeCurso(table.getValueAt(table.getSelectedRow(), 2).toString());
			a.setTipoCurso(table.getValueAt(table.getSelectedRow(), 3).toString());
			a.setCargaHoraria(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 4).toString()));
			a.setCodInstituto(table.getValueAt(table.getSelectedRow(), 5).toString());
			a.setId(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));

			dao.atualizar(a);

			mostrar();
		}

	}

	public void deleteActionPerformed(ActionEvent evt) {

		if (table.getSelectedRow() != -1) {

			Curso a = new Curso();
			CursoDAO dao = new CursoDAO();

			a.setId(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));

			dao.delete(a);

			mostrar();
		}

	}

}
