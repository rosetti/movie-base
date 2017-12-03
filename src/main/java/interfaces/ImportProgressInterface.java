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
	 * Set the progress label 
	 * e.g. "1 of 10"
	 * @param count
	 */
	public void setProgressValues(int count, int total);

	/**
	 *
	 * @param text
	 */
	public void setProgressText(String text);
}
