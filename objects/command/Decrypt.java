package objects.command;

import java.util.ArrayList;

import globals.Globals;
import objects.core.Decryptor;
import tools.txtWriter.TXTWriter;
import tools.txtWriter.ToLines;

public class Decrypt extends ParameterBasedCommand
{
	private Key key;
	private String last = "";
	private Decryptor decryptor;
	private String storeStatement = "/store";
	private String storeLastStatement = "/storeLast";
	private TXTWriter writer = new TXTWriter("decOut");

	public Decrypt(Key key)
	{
		super("decrypt");
		this.key = key;
		decryptor = new Decryptor(key.getSpin(), key.getKeyWord());
	}

	@Override
	public void executeCommand(ArrayList<String> params)
	{
		super.executeCommand(params);

		if (params.size() == 1)
		{
			if (params.get(0).equals(storeLastStatement))
			{
				writer.write(ToLines.toLines(70, last));
				sendWriterFeedback();
			} else 
			{
				doDefaultCommand(params);
			}
		} else if (params.get(1).equals(storeStatement))
		{
			String message = doDefaultCommand(params);
			writer.write(ToLines.toLines(70, message));

			sendWriterFeedback();
		}
	}

	private final void sendWriterFeedback()
	{
		/* send feedback */
		String debug = "[" + Globals.SOFTWARE_NAME + "] wrote text in: ." + writer.getPath() + writer.getFileName();
		System.out.println(debug);
	}
	
	/**
	 * Die methode gibt den Text zurück, den sie entschlüsselt hat.
	 * 
	 * @param params
	 * @return
	 */

	private final String doDefaultCommand(ArrayList<String> params)
	{
		decryptor.updateKey(key.getSpin(), key.getKeyWord());
		String message = decryptor.decrypt(params.get(0));
		last = message;
		System.out.println(message);

		return message;
	}
	
	@Override
	public String getSpecificCommandInfo()
	{
		String result = "\n";
		result += "First sub Command\n";
		result += "Sy: decrypt <MESSAGE>\n";
		result += "MESSAGE means the text that has to be decrypted\n";
		result += "\n";
		result += "Second sub command\n";
		result += "Sy: decrypt <MESSAGE> <" + storeStatement + ">\n";
		result += "This sub command will do the same as the first\n";
		result += "However, the output will also be printed in a file\n";
		result += "that is called decOut.txt\n";
		result += "\n";
		result += "Third sub command\n";
		result += "Sy: decrypt <" + storeLastStatement + ">\n";
		result += "This sub command will store the last decrypted text \n";
		result += "into the decOut.txt file";
		return result;
	}
}
