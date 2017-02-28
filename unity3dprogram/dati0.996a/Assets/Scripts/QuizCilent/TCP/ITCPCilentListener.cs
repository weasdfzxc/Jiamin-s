using UnityEngine;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TCPNetwork
{
    public interface ITCPCilentListener
    {
        /// <summary>
        /// 
        /// </summary>
        /// <param name="sender">TCPClient</param>
        /// <param name="args">0</param>
        void OnConnecting(object sender, object args);
        /// <summary>
        /// 
        /// </summary>
        /// <param name="sender">TCPClient</param>
        /// <param name="args">0</param>
        void OnConnected(object sender, object args);
        /// <summary>
        /// 
        /// </summary>
        /// <param name="sender">TCPClient</param>
        /// <param name="args">ErrorCode(int)</param>
        void OnDisconnected(object sender, object args);
        /// <summary>
        /// 
        /// </summary>
        /// <param name="sender">TCPClient</param>
        /// <param name="args">TCPMessage</param>
        void OnReceived(object sender, object args);
    }
}
