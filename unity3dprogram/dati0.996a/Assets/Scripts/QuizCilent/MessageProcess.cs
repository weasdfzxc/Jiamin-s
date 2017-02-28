using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using TCPNetwork;
    /// <summary>
    /// 消息处理工厂
    /// </summary>
    class MessageFactory : ITCPCilentListener
    {
        IMessageListener listener;
        Hashtable messages; //Use message label as key, IMessage as value
        public MessageFactory(IMessageListener listener)
        {
            //Subscribe message event
            this.listener = listener;
            messages = new Hashtable();
            //Bind the specifies message
            messages[MsgLabel.LoginScs] = new Message.LoginScs();
            messages[MsgLabel.LoginFld] = new Message.LoginFld();
            messages[MsgLabel.Comment] = new Message.Comment();
            messages[MsgLabel.RtnQuiz] = new Message.RtnQuiz();
            messages[MsgLabel.SubScs] = new Message.SubScs();
			messages[MsgLabel.RegisterScs] = new Message.RegisterScs();
			messages[MsgLabel.RegisterFld] = new Message.RegisterFld();
			messages[MsgLabel.RtnRank] = new Message.RtnRank();
        }
		public void OnConnecting(Object sender, Object args)
		{
			listener.Connecting();
		}
        public void OnConnected(Object sender, Object args)
        {
			listener.Connected();
        }
        public void OnDisconnected(Object sender, Object args)
        {
			listener.Disconnected();
        }
        public void OnReceived(Object sender, Object args)
        {
            TCPMessage msg = args as TCPMessage;
            if (msg.type == MsgType.Message)
            {
                byte[] data = new byte[msg.data.Length - 1];
                for (int i = 0; i < data.Length; ++i)
                    data[i] = msg.data[i + 1];
                ((IMessage)messages[(MsgLabel)msg.data[0]]).Process(listener, data);
            }
        }
    }