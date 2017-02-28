using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Net;
using System.Net.Sockets;

namespace TCPNetwork
{
    public class TCPCilent
    {
        public delegate void ClientEventHandler(Object sender, Object args);
        public event ClientEventHandler OnConnecting;
        public event ClientEventHandler Connected;
        public event ClientEventHandler Disconnected;
        public event ClientEventHandler Received;
        TCPState state = TCPState.Disconnected;
        public TCPState State { get { return state; } }
        Thread connecter;
        Thread waiter;
        IPEndPoint ipe;
        Socket socket;
        public Socket Socket { get { return socket; } }
        List<byte[]> datapool;
        public TCPCilent(ITCPCilentListener lsn, string ip, int port)
        {
            OnConnecting += lsn.OnConnecting;
            Connected += lsn.OnConnected;
            Disconnected += lsn.OnDisconnected;
            Received += lsn.OnReceived;
            ipe = new IPEndPoint(IPAddress.Parse(ip), port);
            socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
        }
        void Connecting()
        {
            try
            {
                state = TCPState.Connecting;
                if (OnConnecting != null) OnConnecting(this, 0);
                socket.Connect(ipe);
                //After connected : 
                state = TCPState.Connected;
                if (Connected != null) Connected(this, 0);
                waiter = new Thread(Receive);
                waiter.Start();
            }
            catch (SocketException e)
            {
                if (Disconnected != null) Disconnected(this, e.ErrorCode);
                if (e.ErrorCode == (int)TCPState.Refused) state = TCPState.Refused;
                else if (e.ErrorCode == (int)TCPState.Timeout) state = TCPState.Timeout;
            }
        }
        public void Connect()
        {
            connecter = new Thread(Connecting);
            connecter.Start();
        }
        public void Close()
        {
            if (connecter != null)
                connecter.Abort();
            if (waiter != null)
                waiter.Abort();
            state = TCPState.Disconnected;
			byte[] dis = { (byte)TCPType.DisConnect };
            Send(dis);
            socket.Close();
        }
        void Send(byte[] data)
        {
            //EnCry will increase 2 element to store random number so the count need minus 2.
            byte[] ndata = new byte[1024 - 2];
            for (int i = 0; i < data.Length; i++) ndata[i] = data[i];
            ndata = HT.EnCry(ndata);
            socket.Send(ndata);
        }
        public void SendMessage(byte[] data)
        {
            byte[] temp = new byte[data.Length + 3];
            temp[0] = (byte)MsgType.Message;
            temp[1] = (byte)(data.Length / 256);
            temp[2] = (byte)(data.Length % 256);
            for (int i = 0; i < data.Length; i++)
                temp[i + 3] = data[i];
            Send(temp);
        }
        void Receive()
        {
            while (true)
            {
                var d = new byte[1024];
                try
                {
                    socket.Receive(d);
                    if (datapool == null) datapool = new List<byte[]>();
                    var nd = HT.DeCry(d);
                    TCPMessage msg = new TCPMessage();
                    //Determine weather a message or data 
                    switch ((TCPType)nd[0])
                    {
                        case TCPType.Full:
                            state = TCPState.Full;
                            if (Disconnected != null) Disconnected(this, (int)TCPState.Full);
                            state = TCPState.Disconnected;
                            break;
                        case TCPType.Delay:
                            byte[] del = { (byte)TCPType.Delay };
                            Send(del);
                            break;
                        case TCPType.Message:
                            msg.data = new byte[(int)nd[1] * 256 + (int)nd[2]];
                            for (int i = 0; i < msg.data.Length; ++i)
                                msg.data[i] = nd[i + 3];
                            msg.type = (MsgType)nd[0];
                            if (Received != null) Received(this, msg);
                            break;
                    }
                }
                catch (SocketException e)
                {
                    if (Disconnected != null) Disconnected(this, e.ErrorCode);
                    if (e.ErrorCode == (int)TCPState.Disconnected) state = TCPState.Disconnected;
                    break;
                }
            }
        }
    }
}
