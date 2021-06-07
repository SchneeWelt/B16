package objects.core.database;

import java.util.ArrayList;

public class SpecialCharacters extends Database
{
	@Override
	protected final void fillList()
	{
		list.add('}');
		list.add('!');
		list.add('"');
		list.add('#');
		list.add('$');
		list.add('%');
		list.add('&');
		list.add('\'');
		list.add('(');
		list.add(')');
		list.add('*');
		list.add('+');
		list.add('-');
		list.add(',');
		list.add('.');
		list.add('\\');
		list.add(':');
		list.add('=');
		list.add('?');
		list.add('@');
		list.add(' ');
		list.add('[');
		list.add('/');
		list.add(']');
		list.add('_');
		list.add('{');
		list.add('|');
		list.add('~');
	}

	public final ArrayList<Character> getSpecialCharacters()
	{
		return getList();
	}
}
