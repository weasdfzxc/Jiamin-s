using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TCPNetwork
{
    public enum TCPType : byte
    {
        Empty = 0,
        Delay = 209,
        Full = 210,
		Test = 211,
		DisConnect = 212,
		Message = 213
    }
	public enum MsgType : byte
	{
		Delay = 209,
		Message = 213
	}
}
