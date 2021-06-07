package objects.core.database;

import java.util.ArrayList;

public class Digits extends Database
{
	@Override
	protected
	final void fillList()
	{
		list.add('0');
		list.add('1');
		list.add('2');
		list.add('3');
		list.add('4');
		list.add('5');
		list.add('6');
		list.add('7');
		list.add('8');
		list.add('9');
	}
	
	public final ArrayList<Character> getDigits()
	{
		return getList();
	}
}
