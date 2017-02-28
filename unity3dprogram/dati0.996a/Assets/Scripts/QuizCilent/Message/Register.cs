using System;
using System.Text;
namespace Message
{
	public class RegisterScs : IMessage
	{
		public void Process(IMessageListener listener, byte[] msg)
		{
			Register.Succeeded = true;
		}
	}
	public class RegisterFld : IMessage
	{
		public void Process(IMessageListener listener, byte[] msg)
		{
			Register.Succeeded = false;
			Register.Reason = Encoding.Unicode.GetString(msg);
		}
	}
}