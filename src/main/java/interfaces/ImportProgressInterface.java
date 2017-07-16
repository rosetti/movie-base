package interfaces;

/**
 * Interface describes a gui window used to display the progress of imports.
 * Key aspects of the interface are a text window to display the results, a progressbar, and a label to update.
 * @author vklx
 *
 */
public interface ImportProgressInterface
{
	/**
	 * Set the size of the progress bar
	 * @param length
	 */
	public void setProgressBarLength(int length);
	
	/**
	 * Set the value of the progress bar
	 * @param value
	 */
	public void setProgressBarValue(int value);
	
	/**
	 * Set the progress label 
	 * e.g. "1 of 10"
	 * @param count
	 */
	public void setProgressLabel(int count, int total);
}
