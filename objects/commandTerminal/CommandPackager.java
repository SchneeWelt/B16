package objects.commandTerminal;

import java.util.ArrayList;

import objects.command.Command;
import objects.command.Decrypt;
import objects.command.Encrypt;
import objects.command.Exit;
import objects.command.Help;
import objects.command.Key;
import objects.command.Stop;

/**
 * Verantwortlich für die erzeugung aller verwendbaren Commands, die während der
 * Laufzeit aufrufbar sind. Die Commands werden bei instanzierung einer Instanz
 * dieser Klasse erzeugt und können über den Befehl getCommans() als ArrayListe
 * aus Command objekten erhalten werden.
 */

public class CommandPackager
{
	private ArrayList<Command> commands = new ArrayList<Command>();

	public CommandPackager()
	{
		Stop stop = new Stop();
		Exit exit = new Exit();
		Key keyWord = new Key();
		Decrypt decrypt = new Decrypt(keyWord);
		Encrypt encrypt = new Encrypt(keyWord);
		
		commands.add(exit);
		commands.add(stop);
		commands.add(keyWord);
		commands.add(decrypt);
		commands.add(encrypt);
		
		commands.add(new Help(commands));
	}

	public final ArrayList<Command> getCommands()
	{
		return commands;
	}
}
