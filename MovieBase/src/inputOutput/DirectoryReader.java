package inputOutput;

import java.io.*;

import resources.ReadMediaInfo;
import movieControl.Movie;

public class DirectoryReader 
{
	private File movieDirectory;
	private File[] fileList;

	public DirectoryReader(String directoryPath)
	{
		movieDirectory = new File(directoryPath);
		
	}
	
	public void readDirectory()
	{
		fileList = movieDirectory.listFiles();
	}
	
	public void printMovieList()
	{
		int count = 0;
		
		for (File i: fileList)
		{
				count++;
				Movie rawMovie = cleanMovieName(i);
				System.out.println(count + ":");
				System.out.println("Film." + rawMovie.getTitle());
				System.out.println("type: " + rawMovie.getFileType());
				System.out.println("Watched: " + rawMovie.isWatched());
				System.out.println("-------------------------------------------------");
		}
		
		System.out.println("Operation Completed - " + count + " movies added");
	}
	
	public Movie cleanMovieName(File file)
	{
		String title;
		String type;
		String fileLocation;
		boolean watched = true;
		int runtime = -1;
		fileLocation = file.getAbsolutePath();
		Movie returnMovie = new Movie();
		
		//find the dot
		
		if (file.getName().contains("."))
		{
			int dotPosition = file.getName().lastIndexOf(".");
			int length = file.getName().length();
			title = file.getName().substring(0, dotPosition);
			type = file.getName().substring(dotPosition, length);
		}
		
		else
		{
			title = file.getName();
			type = "folder";
		}
		
		if (title.contains("(TW)"))
		{
			watched = false;
			
			int twPosition = title.indexOf(" (TW)");
			title = title.substring(0,  twPosition);
		}
		
		if (!file.isDirectory())
		{
			runtime = ReadMediaInfo.getRuntimeFromFile(fileLocation); 
		}
		
		returnMovie.setRuntime(runtime);
		returnMovie.setFileLocation(fileLocation);
		returnMovie.setTitle(title);
		returnMovie.setFileType(type);
		returnMovie.setWatched(watched);
		
		return returnMovie;
	}
	
	public File[] getFileList()
	{
		return fileList;
	}
}
