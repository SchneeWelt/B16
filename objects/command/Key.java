package objects.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import globals.Globals;
import objects.core.SpinGenerator;
import objects.core.keyWordGenerator.Behavior;
import objects.core.keyWordGenerator.KeyWordGenerator;
import objects.error.MissingArgumentError;
import tools.txtWriter.TXTWriter;

public class Key extends ParameterBasedCommand
{
	private String loadStatement = "/load";
	private String storeStatement = "/store";
	private String printStatement = "/print";
	private String randomStatement = "/random";
	private TXTWriter writer = new TXTWriter("keyOut");
	private SpinGenerator spinGenerator = new SpinGenerator();
	private KeyWordGenerator keyWordGenerator = new KeyWordGenerator(Behavior.USE_TYPE_512);

	public Key()
	{
		super("key");
	}

	@Override
	public void executeCommand(ArrayList<String> params)
	{
		super.executeCommand(params);

		if (params.get(0).equals(printStatement))
		{
			printKey();
		} else if (params.get(0).equals(loadStatement))
		{
			if (params.size() >= 2)
			{
				loadKey(params);
			} else
			{
				new MissingArgumentError();
				return;
			}
		} else if (params.get(0).equals(randomStatement))
		{
			String input = "32";

			if (params.size() >= 2)
				input = params.get(1);

			newRandomKey(input);
			printNewKey();
		} else if (params.get(0).equals(storeStatement))
		{
			writeKeyToFile();
		} else
		{
			createNewKeyWord(params);
			createNewSpin(params);

			printNewKey();
		}
	}

	private final void loadKey(ArrayList<String> params)
	{
		/*
		 * Sy: key </load> <PATH> PATH: data/key.txt
		 * 
		 * Incase that there are more than one key in this file then the first key will
		 * be used
		 */

		Scanner scanner = null;
		String path = params.get(1);
		ArrayList<String> lines = new ArrayList<String>();

		try
		{
			scanner = new Scanner(new File(path));
			
			while (scanner.hasNextLine())
			{
				lines.add(scanner.nextLine());
			}
			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			
			lines.add("-an error accoured!");
			lines.add("-an error accoured!");
		}

		loadSpinFromLine(lines.get(0));
		loadKeyWordFromLine(lines.get(1));
		
		printNewKey();
	}
	
	private final void loadKeyWordFromLine(String line)
	{
		StringBuilder stringBuilder = new StringBuilder(line);
		stringBuilder.delete(0, 9);
		
		setKeyWord(stringBuilder.toString());
	}

	private final void loadSpinFromLine(String line)
	{
		int spin = 0;

		if (line.contains("1") | line.contains("+"))
			spin = 1;

		if (line.contains("-"))
			spin = -1;

		setSpin(spin);
	}

	private final void writeKeyToFile()
	{
		ArrayList<String> lines = new ArrayList<String>();

		lines.add("Spin: " + spinGenerator.getSpin());
		lines.add("KeyWord: " + keyWordGenerator.getKeyWord());

		writer.write(lines);

		String debug = "[" + Globals.SOFTWARE_NAME + "] wrote key in: " + writer.getPath() + writer.getFileName();
		System.out.println(debug);
	}

	private final void newRandomKey(String type)
	{
		spinGenerator.makeNew();

		if (type.equals("16"))
		{
			keyWordGenerator.makeNew(Behavior.USE_TYPE_16);
		} else if (type.equals("32"))
		{
			keyWordGenerator.makeNew(Behavior.USE_TYPE_32);
		} else if (type.equals("64"))
		{
			keyWordGenerator.makeNew(Behavior.USE_TYPE_64);
		} else if (type.equals("256"))
		{
			keyWordGenerator.makeNew(Behavior.USE_TYPE_256);
		} else if (type.equals("512"))
		{
			keyWordGenerator.makeNew(Behavior.USE_TYPE_512);
		} else if (type.equals("1024"))
		{
			keyWordGenerator.makeNew(Behavior.USE_TYPE_1024);
		} else
		{
			keyWordGenerator.makeNew(Behavior.USE_TYPE_32);
		}
	}

	private final void createNewKeyWord(ArrayList<String> params)
	{
		String newKeyWord = params.get(0);

		if (newKeyWord.indexOf('+') == 0 | newKeyWord.indexOf('-') == 0)
		{
			StringBuilder stringBuilder = new StringBuilder(newKeyWord);
			stringBuilder.deleteCharAt(0);
			newKeyWord = stringBuilder.toString();
		}

		keyWordGenerator.setKeyWord(newKeyWord);
	}

	private final void printNewKey()
	{
		System.out.println("New keyWord: " + keyWordGenerator.getKeyWord());
		System.out.println("New spin: " + spinGenerator.getSpin());
	}

	private final void printKey()
	{
		System.out.println("current keyWord: " + keyWordGenerator.getKeyWord());
		System.out.println("Current spin: " + spinGenerator.getSpin());
	}

	private final void createNewSpin(ArrayList<String> params)
	{
		int newSpin = 0;
		String param = params.get(0);

		if (param.indexOf("+") == 0)
			newSpin = 1;
		else if (param.indexOf("-") == 0)
			newSpin = -1;
		else
			newSpin = 1;

		spinGenerator.setSpin(newSpin);
	}

	@Override
	public String getSpecificCommandInfo()
	{
		String output = "\n";
		output += "First sub command:\n";
		output += "Sy: key <+-NEW_KEY> \n";
		output += "Example: keyWord <-This is a Key>\n";
		output += "Second example: keyWord <+This is another Key>\n";
		output += "Third example: keyWord <This is also another key>\n";
		output += "The key String can be as long as wished\n";
		output += "'-' before the keyWord means a negative spin\n";
		output += "leaving out the plus or adding plus to the start\n";
		output += "will create a positive spin\n";
		output += "\n";
		output += "Second sub command:\n";
		output += "Sy: key <" + printStatement + ">\n";
		output += "will print the current key word and spin\n";
		output += "\n";
		output += "Third sub command:\n";
		output += "Sy: key <" + randomStatement + "> <TYPE>\n";
		output += "will generate a new random key of length TYPE\n";
		output += "TYPE can either be 16, 32, 64, 256, 512 or 1024\n";
		output += "If no TYPE parameter is parsed the TYPE will be set to 32\n";
		output += "\n";
		output += "Fourth sub command:\n";
		output += "Sy: key <" + storeStatement + ">\n";
		output += "will write the current key information\n";
		output += "in the file keyOut.txt wich is located in the data folder\n";
		output += "\n";
		output += "Fifth sub command:\n";
		output += "Sy: key <" + loadStatement + "> <PATH>\n";
		output += "This sub command will load a key into the system\n";
		output += "PATH is the path where the keyOut.txt file is located\n";
		output += "The program will always read the first key when there are more\n"
				+ "than one key stored in the keyOut.txt file\n";
		output += "Example: key <" + loadStatement + "> <data/keyOut.txt>\n";
		output += "Every path is allowed so C://Users/... is also a valid path";

		return output;
	}

	public final void setSpin(int spin)
	{
		spinGenerator.setSpin(spin);
	}

	public final int getSpin()
	{
		return spinGenerator.getSpin();
	}

	public final void setKeyWord(String keyWord)
	{
		keyWordGenerator.setKeyWord(keyWord);
	}

	public final String getKeyWord()
	{
		return keyWordGenerator.getKeyWord();
	}
}
