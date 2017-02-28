using UnityEngine;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TCPNetwork
{
    public enum TCPState : short
    {
        Connecting = 10001,
        Connected = 10002,
        Timeout = 10060,
        Refused = 10061,
        Disconnected = 10054,
        Full = 10000,
		Error = 0
    }
}
