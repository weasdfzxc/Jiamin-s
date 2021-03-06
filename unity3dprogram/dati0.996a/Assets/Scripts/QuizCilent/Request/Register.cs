using System;
using System.Text;
using TCPNetwork;
class Register
{
	static bool? succeeded = null;
	public static bool? Succeeded {get{return succeeded;} set{succeeded = value;}}
	static string reason = "";
	public static string Reason {get{return reason;} set{reason = value;}}
	//Request to server for register new account
	public static void Init()
	{
		succeeded = null;
		reason = "";
	}
	public static void Request(TCPCilent cilent, string School, string Class, string Name)
	{
		if (cilent != null && cilent.State == TCPState.Connected)
		{
			string msgs = School + ";" + Class + ";" + Name;
			byte[] temp = Encoding.Unicode.GetBytes(msgs);
			byte[] msg = new byte[temp.Length + 1];
			msg[0] = (byte)MsgLabel.Register;
			for (int i = 1;i < msg.Length;++i)
				msg[i] = temp[i - 1];
			cilent.SendMessage(msg);
		}
	}
}