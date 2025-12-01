package net.hibernate;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.hibernate.dao.VideogameDAO;
import net.hibernate.entity.Videogame;

public class Frontend 
{
	private JFrame frame;
	private JTable jtable;
	
	public Frontend() {
		frame = new JFrame("Videogame Database");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String[] columnNames = {"ID","Title","Platform","Rating","Collection","Romhack","★"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames,0);
		jtable = new JTable(tableModel);
		for(Videogame videogame : VideogameDAO.selectVideogame()) {
			tableModel.addRow(videogame.toTable());
		}
		
		JPanel panel = new JPanel();
		
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(jtable);
		
		jtable.getColumnModel().getColumn(0).setPreferredWidth(10);
		jtable.getColumnModel().getColumn(1).setPreferredWidth(500);
		jtable.getColumnModel().getColumn(2).setPreferredWidth(100);
		jtable.getColumnModel().getColumn(3).setPreferredWidth(40);
		jtable.getColumnModel().getColumn(4).setPreferredWidth(20);
		jtable.getColumnModel().getColumn(5).setPreferredWidth(20);
		jtable.getColumnModel().getColumn(6).setPreferredWidth(10);
	    DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
	    cellRenderer.setHorizontalAlignment(JLabel.CENTER);
	    jtable.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
	    jtable.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
	    jtable.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
	    jtable.getColumnModel().getColumn(6).setCellRenderer(cellRenderer);
	    
	    panel.add(scroll);
	    frame.add(panel);
		frame.setSize(1280, 600);
		frame.setResizable(false);
	}
	
	public void show(){
		frame.setVisible(true);	
	}
}
