using System;
using System.Text;

namespace Message
{
	public class Comment : IMessage
	{
		public void Process(IMessageListener listener, byte[] msg)
		{
			string[] sp = Encoding.Unicode.GetString(msg).Split(';');
			Quiz.Time = Int32.Parse(sp[0]);
			Quiz.Total = Int32.Parse(sp[1]);
			Quiz.Comment = sp[2];
		}
	}
	public class RtnQuiz : IMessage
	{
		public void Process(IMessageListener listener, byte[] msg)
		{
			string[] sp = Encoding.Unicode.GetString(msg).Split(':');
			Question q = new Question();
			q.question = sp[0];
			q.type = sp[1];
			q.answer = sp[2];
			Quiz.Questions.Add(q);
			if (sp.Length > 3) Quiz.Finished = true;
			Quiz.Requesting = false;
		}
	}
}