package gui;

//java imports
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.awt.Dimension;

//local imports
import resources.ImageSaver;
import resources.ResizeImage;

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
		Dimension resultsPaneSize = new Dimension(600,300);
		
		String[] columnNames = {"Title", "Year", "IMDb", "Poster"};
		
		DefaultTableModel model = new DefaultTableModel(getData(), columnNames);
		resultsTable = new JTable(model)
		{
			public Class getColumnClass(int column)
			{
				return getValueAt(0, column).getClass();
			}
		};
		
		
		
		resultsTable.setFillsViewportHeight(true);
		resultsTable.setRowHeight(150);

		resultsTable.getColumnModel().getColumn(3).setWidth(100);
		resultsTable.getColumnModel().getColumn(3).setMinWidth(100);
		
		resultsPane = new JScrollPane(resultsTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		resultsPane.setPreferredSize(resultsPaneSize);
		resultsPane.setMinimumSize(resultsPaneSize);
		resultsPane.setMaximumSize(resultsPaneSize);
		

	}
	
	private Object[][] getData()
	{
		ImageIcon imageOne = new ImageIcon("/home/vin/development/images/Brave tt1217209.jpg");
		ImageIcon imageTwo = ImageSaver.getImageIcon("http://www.sonypictures.com/movies/thisistheend/assets/images/onesheet.jpg");
		
		imageTwo = ResizeImage.resizeImage(imageTwo, Theme.posterSizeSmall);
		imageOne = ResizeImage.resizeImage(imageOne, Theme.posterSizeSmall);
		Object[][] data = 
			{
					{"Brave", "2015", "oin765oin", imageOne},
					{"The Martian", "2015", "oinoin", imageTwo}
			};
		
		return data;
	}
}
