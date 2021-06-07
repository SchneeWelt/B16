package objects.core.database;

import java.util.ArrayList;

public abstract class Database
{
	protected ArrayList<Character> list = new ArrayList<Character>();

	public Database()
	{
		fillList();
	}
	
	protected abstract void fillList();

	protected final ArrayList<Character> getList()
	{
		return list;
	}
}
