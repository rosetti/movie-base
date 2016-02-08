package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//java imports
import javax.swing.*;

//local imports

public class ImportWindow 
{
	private JFrame importWindow;
	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel bottomPanel;
	private JFileChooser fileBrowser = new JFileChooser();
	private JCheckBox scanAllCheck;
	private JCheckBox postReviewCheck;
	private JButton okBtn;
	private JButton cancelBtn;
	
	public ImportWindow()
	{
		makeFrame();
		
		makeTopPanel();
		makeMiddlePanel();
		makeBottomPanel();
		
		importWindow.add(topPanel);
		importWindow.add(middlePanel);
		importWindow.add(bottomPanel);
		
		importWindow.setVisible(true);
		importWindow.revalidate();
	}
	
	private void makeFrame()
	{
		Dimension size = new Dimension(500,200);
		
		importWindow = new JFrame("Import Movies");
		Container contentPane = importWindow.getContentPane();
		
		importWindow.setMinimumSize(size);
		importWindow.setMaximumSize(size);
		importWindow.setPreferredSize(size);
		importWindow.setResizable(false);
		importWindow.setDefaultCloseOperation(3);
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
	}
	
	private void makeTopPanel()
	{
		topPanel = new JPanel();
		Dimension tpSize = new Dimension(500, 80);
		topPanel.setPreferredSize(tpSize);
		topPanel.setMinimumSize(tpSize);
		topPanel.setMaximumSize(tpSize);
		
		JLabel title = new JLabel("Path to scan");
		topPanel.add(title);
		
		JPanel pathPanel = new JPanel();
		pathPanel.setOpaque(false);
		pathPanel.setLayout(new FlowLayout());
		pathPanel.setMaximumSize(new Dimension(480, 50));
		pathPanel.setMinimumSize(new Dimension(480, 50));
		pathPanel.setPreferredSize(new Dimension(480, 50));
		
		final JTextArea pathBox = new JTextArea();
		pathBox.setPreferredSize(new Dimension(400, 30));
		pathBox.setMinimumSize(new Dimension(400, 30));
		pathBox.setMaximumSize(new Dimension(400, 30));
		pathBox.setBorder(Theme.singleBlackBorder);
		pathBox.setEditable(true);
		
		JButton fileChooserBtn = new JButton("...");
		fileChooserBtn.setMaximumSize(new Dimension(30, 30));
		fileChooserBtn.setMinimumSize(new Dimension(30,30));
		fileChooserBtn.setPreferredSize(new Dimension(30,30));
		fileChooserBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				fileBrowser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileBrowser.showOpenDialog(topPanel);
				String uri = fileBrowser.getSelectedFile().getPath();
				System.out.println(uri);
				pathBox.setText(uri);
			}
		});
		

		
		pathPanel.add(fileChooserBtn);
		pathPanel.add(pathBox);
		topPanel.add(pathPanel);
		//pathPanel.setAlignmentX(SwingConstants.BOTTOM);
	}
	
	private void makeMiddlePanel()
	{
		Dimension mpSize = new Dimension(500, 50);
		middlePanel = new JPanel();
		middlePanel.setPreferredSize(mpSize);
		middlePanel.setMinimumSize(mpSize);
		middlePanel.setMaximumSize(mpSize);
		middlePanel.setLayout(new GridLayout(2,1));
		
		scanAllCheck = new JCheckBox("Scan all movies?");
		scanAllCheck.setOpaque(false);
		postReviewCheck = new JCheckBox("Check each movie after import?");
		postReviewCheck.setOpaque(false);
		
		middlePanel.add(scanAllCheck);
		middlePanel.add(postReviewCheck);
	}
	
	private void makeBottomPanel()
	{
		bottomPanel = new JPanel();
		
		okBtn = new JButton("Ok");
		cancelBtn = new JButton("Cancel");
		
		Dimension buttonSize = new Dimension(100, 30);
		cancelBtn.setMaximumSize(buttonSize);
		cancelBtn.setMinimumSize(buttonSize);
		cancelBtn.setPreferredSize(buttonSize);
		
		okBtn.setMaximumSize(buttonSize);
		okBtn.setMinimumSize(buttonSize);
		okBtn.setPreferredSize(buttonSize);
		
		bottomPanel.add(okBtn);
		bottomPanel.add(cancelBtn);
	}
}
