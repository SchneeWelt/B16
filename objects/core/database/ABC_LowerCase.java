package objects.core.database;

import java.util.ArrayList;

public class ABC_LowerCase extends Database
{
	@Override
	protected void fillList()
	{
		list.add('a');
		list.add('b');
		list.add('c');
		list.add('d');
		list.add('e');
		list.add('f');
		list.add('g');
		list.add('h');
		list.add('i');
		list.add('j');
		list.add('k');
		list.add('l');
		list.add('m');
		list.add('n');
		list.add('o');
		list.add('p');
		list.add('q');
		list.add('r');
		list.add('s');
		list.add('t');
		list.add('u');
		list.add('v');
		list.add('w');
		list.add('x');
		list.add('y');
		list.add('z');
//		list.add('�');
//		list.add('�');
//		list.add('�');
	}

	public final ArrayList<Character> getABC()
	{
		return getList();
	}
}
