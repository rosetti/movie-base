package processManagers;

//java imports
import java.io.File;
import javax.swing.JPanel;

//local imports
import main.ApplicationMain;
import javax.swing.JOptionPane;

//class will delete XML file, and images folder - thus clearing downloaded data
public class clearMovieBaseManager 
{
	File imagesDirectory;
	File xmlFile;
	JOptionPane prompt;
	
	public clearMovieBaseManager()
	{
		imagesDirectory = new File(ApplicationMain.pwd + ApplicationMain.slash + "images"); 
		xmlFile = new File(ApplicationMain.pwd + ApplicationMain.slash + "movieData.xml");
		
		prompt = new JOptionPane();
		int x = JOptionPane.showOptionDialog(new JPanel(), "Deleting MovieBase will delete the XML data file, and all downloaded movie posters.\n"
				+ "Are you sure you wish to clear MovieBase data?\n"
				+ "Please note, MovieBase will exit upon successful delete", "Delete MovieBase data?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, JOptionPane.ICON_PROPERTY);
		
		if (x==0)
		{
			deleteImages();
			xmlFile.delete();
			JOptionPane.showMessageDialog(new JPanel(), "Successfully deleted Movie Base data. MovieBase will now exit");
		}
		
		System.exit(0);
	}
	
	public void deleteImages()
	{
		for (File i: imagesDirectory.listFiles())
		{
			i.delete();
		}
		
		if (imagesDirectory.listFiles().length == 0)
		{
			imagesDirectory.delete();
		}
		
		else
		{
			System.out.println("Unable to delete images folder at: " + imagesDirectory.getAbsolutePath());
		}
	}
}
