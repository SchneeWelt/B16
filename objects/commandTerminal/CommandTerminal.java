package objects.commandTerminal;

import java.util.ArrayList;

import in.Update;
import objects.ConsoleReader;
import objects.StrangeCharacterReplacer;
import objects.command.Command;

/**
 * Verarbeitet Commands indem entsprechende aktionen auf bestimmte aktionen
 * eingeleitet werden.
 * 
 * 
 * Notiz: Es gillt immer erst der command. Dieser besteht aus genau einem wort.
 * Anschließend folgen die tags für diesen command. Dabei gilt, das tags durch
 * genau ein space character voneinader getrennt werden. Mehrere Wörter, die als
 * ein Tag aufgefasst werden sollen, müssen in anführungszeichen gesetzt werden.
 * 
 * Beispiel: encrypt "Hallo du Hund" Ausgabe: <verschlüsselte Nachricht von
 * "Hallo du Hund">
 * 
 * Beispiel: encrypt Hallo du Hund Ausgabe: <verschlüsselte Nachricht von
 * "Hallo", da die Syntax diese Commands immer nur den 0 ten tag verschlüsselt,
 * da die anführungszeichen vergessen worden sind, bestand dieser Befehl aber
 * nicht aus einem Command und einem Tag - wie gewollt - sondern aus einem
 * Command und 3 tags, was bei diesem Befehlt nicht funktionieren würde.
 */

public class CommandTerminal implements Update
{
	private ParamReader paramReader = new ParamReader();
	private CommandReader commandReader = new CommandReader();
	private ConsoleReader consoleReader = new ConsoleReader();
	private CommandPackager commandPackager = new CommandPackager();

	@Override
	public void update()
	{
		processCommands();
	}

	private final void processCommands()
	{
		boolean foundCommand = false;
		
		String input = consoleReader.readInput();
		input = StrangeCharacterReplacer.removeStrangeCharacters(input);
		String command = commandReader.readCommand(input);
		ArrayList<String> emptyList = new ArrayList<String>();
		ArrayList<String> params = paramReader.readParams(input, emptyList);

		for (Command c : commandPackager.getCommands())
		{
			if (compareCommands(command, c))
			{
				c.executeCommand(params);
				foundCommand = true;
			}
		}
		
		if (!foundCommand)
		{
			System.out.println("[Error] could not read command...");
		}
	}

	/**
	 * Vergleicht zwei Befehle, gibt true zurück, wenn der Befehl der gleiche ist
	 * 
	 * @param command
	 * @param secondCommand
	 * 
	 * @return
	 */

	private final boolean compareCommands(String command, Command secondCommand)
	{
		return (command.equals(secondCommand.getCommand()));
	}
}
