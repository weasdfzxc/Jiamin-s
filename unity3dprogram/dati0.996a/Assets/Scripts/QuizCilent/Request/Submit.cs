using System;
using System.Text;
using TCPNetwork;
class Submit
{
	static bool? succeeded = null;
	public static bool? Succeeded {get{return succeeded;} set{succeeded = value;}}
	//Request to server for new question(number reprecents the order of the question)
	public static void Init()
	{
		succeeded = null;
	}
	public static void Request(TCPCilent cilent, string school, string name ,string grade, string time)
	{
		if (cilent != null && cilent.State == TCPState.Connected)
		{
			string msgs = school + ";" + name + ";" + grade + ";" + time;
			byte[] temp = Encoding.Unicode.GetBytes(msgs);
			byte[] msg = new byte[temp.Length + 1];
			msg[0] = (byte)MsgLabel.SubGrade;
			for (int i = 1;i < msg.Length;++i)
				msg[i] = temp[i - 1];
			cilent.SendMessage(msg);
			succeeded = false;
		}
	}
}