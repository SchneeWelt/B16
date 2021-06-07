package objects.core.database;

import java.util.ArrayList;

public class ABC_UpperCase extends Database
{
	@Override
	protected void fillList()
	{
		list.add('A');
		list.add('B');
		list.add('C');
		list.add('D');
		list.add('E');
		list.add('F');
		list.add('G');
		list.add('H');
		list.add('I');
		list.add('J');
		list.add('K');
		list.add('L');
		list.add('M');
		list.add('N');
		list.add('O');
		list.add('P');
		list.add('Q');
		list.add('R');
		list.add('S');
		list.add('T');
		list.add('U');
		list.add('V');
		list.add('W');
		list.add('X');
		list.add('Y');
		list.add('Z');
//		list.add('Ä');
//		list.add('Ö');
//		list.add('Ü');
	}

	public final ArrayList<Character> getABC()
	{
		return getList();
	}
}
