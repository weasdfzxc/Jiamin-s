using System;
using System.Text;

namespace Message
{
	public class RtnRank : IMessage
	{
		public void Process(IMessageListener listener, byte[] msg)
		{
			string[] sp = Encoding.Unicode.GetString(msg).Split(';');
			for (int i = 0; i < sp.Length; i+=5) {
				RankList r = new RankList();
				r.date = sp[i];
				r.name = sp[i+1];
				r.school = sp[i+2];
				r.grade = sp[i+3];
				r.time = sp[i+4];
				Rank.Ranklists.Add(r);
			}
		}
	}
}