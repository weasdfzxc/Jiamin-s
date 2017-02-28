using System;
using System.Text;
using System.Collections.Generic;
using TCPNetwork;
class Rank
{
	static List<RankList> ranklists = new List<RankList>();
	public static List<RankList> Ranklists {get{return ranklists;} set{ranklists = value;}}
	//Request to server for getting rank lists
	public static void Request(TCPCilent cilent)
	{
		if (cilent != null && cilent.State == TCPState.Connected)
		{
			byte[] msg = {(byte)MsgLabel.GetRank};
			cilent.SendMessage(msg);
			ranklists = new List<RankList>();
		}
	}
}