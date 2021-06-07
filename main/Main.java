package main;

import java.awt.Graphics2D;

import globals.Globals;
import in.Draw;
import objects.commandTerminal.CommandTerminal;

public class Main implements Draw
{
	private CommandTerminal commandTerminal = new CommandTerminal();
	
	public Main()
	{
		setup();

		/* game loop */
		while (true)
		{
			draw(null);
			sleep();
		}
	}
	
	/*
	 * Die Draw methode wird mit jeden neuen Tick neu aufgerufen. Nur wenn auch die
	 * repaintCanvas() methode aufgerufen wird, wird ebenfalls eine Aktualisierte
	 * Version des graphics objektes mit übergeben.
	 */

	@Override 
	public final void draw(Graphics2D graphics)
	{
		commandTerminal.update();
	}
	
	private final void sleep()
	{
		/*
		 * Spiel Engines sollten nicht mehr über Thred.sleep() arbeiten. Da das hier
		 * aber eine resourcen günstiege Engine für alles mögliche sein soll ist das ok.
		 */

		try
		{
			Thread.sleep((int) Globals.getTickrate());
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	private final void setup()
	{
	}

	public static void main(String[] args)
	{
		new Main();
	}
}
