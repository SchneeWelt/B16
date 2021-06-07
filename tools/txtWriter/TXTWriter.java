package tools.txtWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Schreibt String objekte aus einer ArrayList aus String objekten in ein Txt
 * Dokument. Der verwende Output ordner ist der data ordner, existiert dieser zu
 * programm start nicht, wird er automatisch erzeugt, sofern eine Instanz dieser
 * Klasse verwendet wird, oder Klassen mir gleichem ordner system arbeiten.
 */

public class TXTWriter
{
	private boolean done = false;

	private File file;
	private String fileName;
	private BufferedWriter writer;

	/**
	 * 
	 * @param fileName The name of the File since this is a TXTWriter the ending
	 *                 will always be .txt.
	 */

	public TXTWriter(String fileName)
	{
		this.fileName = fileName + ".txt";
	}

	public final void write(ArrayList<String> lines)
	{
		if (!done)
		{
			createFolder();
			createFile(fileName);

			done = true;
		}

		try
		{
			writer = new BufferedWriter(new FileWriter(file, true));
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		for (String s : lines)
		{
			try
			{
				writer.append(s);
				writer.append(System.lineSeparator());
				writer.flush();
			} catch (IOException e)
			{
				e.printStackTrace();
			}

		}

		try
		{
			writer.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private final void createFolder()
	{
		File folder = new File("./data/");

		if (!folder.exists())
			folder.mkdir();
	}

	private final void createFile(String fileName)
	{
		if (fileName == null)
			fileName = "out.txt";

		file = new File("data/" + fileName);

		if (!file.exists())
		{
			try
			{
				file.createNewFile();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns the name of the File this is writer is writing to.
	 * 
	 * @return
	 */

	public final String getFileName()
	{
		return fileName;
	}

	/**
	 * @return The directory in wich the writer is writing to the file
	 */

	public final String getPath()
	{
		return "/data/";
	}
}
