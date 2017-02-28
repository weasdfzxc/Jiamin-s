using System;

public interface IMessage
{
    void Process(IMessageListener listener, byte[] msg);
}
public interface IMessageListener
{
	void Connecting();
    void Connected();
    void Disconnected();
}