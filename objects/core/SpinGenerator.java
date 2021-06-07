package objects.core;

import java.util.Random;

/**
 * Erzeugt einen Zufälliegen Spin, der zuletzt erzeugte Spin kann über den
 * getSpin() getter ausgelesen werden. Der Spin kann über den setter setSpin()
 * gesetzt werden. Der Spin kann über makeNew() neu berechnet werden.
 */

public class SpinGenerator
{
	private int spin = 0;

	public SpinGenerator()
	{
		makeNew();
	}
	
	public final void makeNew()
	{
		Random random = new Random();
		if (random.nextBoolean())
		{
			spin = 1;
		} else
		{
			spin = -1;
		}
	}

	public final void setSpin(int spin)
	{
		this.spin = spin;
	}
	
	public final int getSpin()
	{
		return spin;
	}
}
