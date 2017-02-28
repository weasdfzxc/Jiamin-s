using System;

namespace Message
{
	public class SubScs : IMessage
	{
		public void Process(IMessageListener listener, byte[] msg)
		{
			Submit.Succeeded = true;
		}
	}
}