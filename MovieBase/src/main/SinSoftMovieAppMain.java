package main;

public class SinSoftMovieAppMain {

	public static String pwd;
	
	public static void main(String[] args) 
	{
		if (args.length==0)
		{
			setPwd(System.getProperty("user.dir"));
		}
		
		else
			
		{
			setPwd(args[0]);
		}
		
		new MovieTest();
	}

	public static void setPwd(String ppwd)
	{
		pwd = ppwd;
	}
}
