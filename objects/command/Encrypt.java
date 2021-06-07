package objects.command;

import java.util.ArrayList;

import globals.Globals;
import objects.core.Encryptor;
import tools.txtWriter.TXTWriter;
import tools.txtWriter.ToLines;

public class Encrypt extends ParameterBasedCommand
{
	private Key key;
	private String last = "";
	private Encryptor encryptor;
	private String storeStatement = "/store";
	private String storeLastStatement = "/storeLast";
	private TXTWriter writer = new TXTWriter("encOut");

	public Encrypt(Key key)
	{
		super("encrypt");
		this.key = key;
		encryptor = new Encryptor(key.getSpin(), key.getKeyWord());
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
	 * Die methode gibt den Text zur�ck, den sie entschl�sselt hat.
	 * 
	 * @param params
	 * @return
	 */

	private final String doDefaultCommand(ArrayList<String> params)
	{
		encryptor.updateKey(key.getSpin(), key.getKeyWord());
		String message = encryptor.encrypt(params.get(0));
		last = message;
		System.out.println(message);
		
		return message;
	}

	@Override
	public String getSpecificCommandInfo()
	{
		String result = "\n";
		result += "First sub Command\n";
		result += "Sy: encrypt <MESSAGE>\n";
		result += "MESSAGE means the text that has to be encrypted\n";
		result += "\n";
		result += "Second sub command\n";
		result += "Sy: encrypt <MESSAGE> <" + storeStatement + ">\n";
		result += "This sub command will do the same as the first\n";
		result += "However, the output will also be printed in a file\n";
		result += "that is called encOut.txt\n";
		result += "\n";
		result += "Third sub command\n";
		result += "Sy: encrypt <" + storeLastStatement + ">\n";
		result += "This sub command will store the last encrypted text \n";
		result += "into the encOut.txt file";

		return result;
	}
}
