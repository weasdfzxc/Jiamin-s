using System;
using System.Text;
using TCPNetwork;
class Login
{
	static bool? succeeded = null;
	public static bool? Succeeded {get{return succeeded;} set{succeeded = value;}}
	//Request to server for checking the validity of specified user
	public static void Init()
	{
		succeeded = null;
	}
	public static void Request(TCPCilent cilent, string School, string Class, string Name)
	{
		if (cilent != null && cilent.State == TCPState.Connected)
		{
			string msgs = School + ";" + Class + ";" + Name;
			byte[] temp = Encoding.Unicode.GetBytes(msgs);
			byte[] msg = new byte[temp.Length + 1];
			msg[0] = (byte)MsgLabel.Login;
			for (int i = 1;i < msg.Length;++i)
				msg[i] = temp[i - 1];
			cilent.SendMessage(msg);
		}
	}
	private static bool? IfSucceeded()
	{
		bool? r = succeeded;
		if (succeeded != null)
			succeeded = null;
		return r;
	}
}