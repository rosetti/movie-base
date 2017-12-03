package main;

//java imports

import java.io.*;
import java.util.Properties;

import gui.InitialiseSwing;

//import main.java.gui.InitialiseSwing;

public class ApplicationMain {

    public static String pwd;
    public static String slash;
    public static boolean mediaInfoAvailable;
    public static Properties properties;

    public static void main(String[] args) {
        if (args.length == 0) {
            pwd = System.getProperty("user.dir");
        } else {
            if (args.length > 1) {
                getOpts(args);
            } else {
                pwd = args[0];
            }
        }
        setSlash();
        initialise();
        setMediaInfoAvailable();
        getProperties();

        new ProgramLaunch();
        //new MovieTest();
    }

    /**
     * Initialise program: Create image directory, set swing constants.
     */
    private static void initialise() {
        File imagesDirectory = new File(pwd + slash + "images");

        if (!imagesDirectory.exists()) {
            imagesDirectory.mkdir();
        }
        InitialiseSwing.setSwingConstants();
    }

    /**
     * Method determines if OS uses forward or backward slashes.
     * TODO: Remove this. It's really not needed
     */
    private static void setSlash() {
        if (pwd.contains("/")) {
            slash = "/";
        } else {
            slash = "\\";
        }
    }

    /**
     * Check if MediaInfo is present.
     * TODO: Remove this, use maven for MediaInfo
     */
    private static void setMediaInfoAvailable() {
        if (new File(pwd + slash + "MediaInfo.exe").exists()) {
            mediaInfoAvailable = true;
        } else {
            mediaInfoAvailable = false;
        }
    }

    private static void getOpts(String[] args) {
        System.out.println("Getting options...");
    }

    private static void getProperties() {

        String apiPropsFilePath = ApplicationMain.pwd + ApplicationMain.slash + "api-key.properties";
        properties = new Properties();
        File apiPropsFile = new File(apiPropsFilePath);

        if (!apiPropsFile.exists()) {
            System.out.println("No API properties!");
        } else {

            try {
                properties.load(new FileInputStream(apiPropsFilePath));
            }

            catch (FileNotFoundException  e) {
                e.printStackTrace();
            }

            catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
