package gui;

//java imports
import javax.swing.*;

import java.awt.Component;
import java.awt.Dimension;

public class FetchResults extends DialogTemplate
{
	private JLabel message;
	private JLabel searchedString;
	private JTable resultsTable;
	private JScrollPane resultsPane;
	
	public FetchResults()
	{
		super("Fetch Results", 500, 400);
		setMessage();
		makeTable();
		setButtons();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		mainPanel.add(message);
		mainPanel.add(searchedString);
		mainPanel.add(resultsPane);
		mainPanel.add(buttonPanel);
		
		window.setVisible(true);
	}
	
	private void setMessage()
	{
		message = new JLabel("The following results were returned when searching for:");
		message.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchedString = new JLabel("Iron Man");
		searchedString.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
	private void setButtons()
	{
		buttonPanel = new JPanel();
		buttonPanel.add(okBtn);
		buttonPanel.add(cancelBtn);
	}

	private void makeTable()
	{
		Dimension resultsPaneSize = new Dimension(450,300);
		
		String[] columnNames = {"Title", "Year", "IMDb", "Poster"};
		resultsTable = new JTable(getData(), columnNames);
		resultsTable.setFillsViewportHeight(true);
		
		resultsPane = new JScrollPane(resultsTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		resultsPane.setPreferredSize(resultsPaneSize);
		resultsPane.setMinimumSize(resultsPaneSize);
		resultsPane.setMaximumSize(resultsPaneSize);
		

	}
	
	private Object[][] getData()
	{
		ImageIcon image = new ImageIcon();
		Object[][] data = 
			{
					{"The Martian", "2015", "oinoin", image}
			};
		
		return data;
	}
}
