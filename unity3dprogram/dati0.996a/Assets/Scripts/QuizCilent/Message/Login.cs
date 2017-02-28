using System;

namespace Message
{
	public class LoginScs : IMessage
	{
		public void Process(IMessageListener listener, byte[] msg)
		{
			Login.Succeeded = true;
		}
	}
	public class LoginFld : IMessage
	{
		public void Process(IMessageListener listener, byte[] msg)
		{
			Login.Succeeded = false;
		}
	}
}