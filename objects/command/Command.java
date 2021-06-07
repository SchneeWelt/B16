package objects.command;

import java.util.ArrayList;

/**
 * Beschreibt abstrakt jeden Befehl, der im CommandTerminal get�tigt werden
 * kann. Der String command enth�llt den Befehl, der f�r eine sub class gillt.
 */

public abstract class Command
{
	protected String command = "";

	public Command(String command)
	{
		this.command = command;
	}

	/**
	 * F�hrt einen Befehlt aus. Mit methoden aufruf wird eine Liste aus Strings
	 * �bergeben, die als tags dienen. Element 0 aus der liste ist der erste tag.
	 * Theoretisch sind unendlich viele tags m�glich. Tags definieren einen Befehl
	 * genauser
	 * 
	 * @param params
	 */

	public void executeCommand(ArrayList<String> params)
	{
	}

	/**
	 * Wird von der Help klasse aufgerufen. Diese Methode sollte einen String
	 * zur�ckgeben, dessen Inhalt genau die Syntax eines Commands erkl�rt.
	 * 
	 * @return
	 */

	public String getSpecificCommandInfo()
	{
		return "";
	}

	public final String getCommand()
	{
		return command;
	}
}
