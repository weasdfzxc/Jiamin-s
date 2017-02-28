using System;
using System.Collections.Generic;
using System.Text;
using TCPNetwork;
class Quiz
{
	//Quiz Time
	static int? time;
	public static int? Time {get{return time;} set{time = value;}}
	//The number of the questions
	static int? total;
	public static int? Total {get{return total;} set{total = value;}}
	//Displaying before the begining of the quiz
	static string comment;
	public static string Comment {get{return comment;} set{comment = value;}}
	//Reprecents the order of the question
	static int number;	
	public static int Number {get{return number;} set{number = value;}}
	//Question list
	static List<Question> questions;
	public static List<Question> Questions {get{return questions;} set{questions = value;}}
	//If it's requesting question.
	static bool requesting;
	public static bool Requesting {get{return requesting;} set{requesting = value;}}
	//If the quiz has been finished
	static bool finished;
	public static bool Finished {get{return finished;} set{finished = value;}}
	public static void Init()
	{
		time = null;
		total = null;
		comment = null;
		number = 0;
		requesting = false;
		finished = false;
		questions = new List<Question>();
	}
	//Request to server for new question
	public static void Request(TCPCilent cilent)
	{
		if (cilent != null && cilent.State == TCPState.Connected && !requesting && !finished)
		{
			string msgs = number.ToString();
			byte[] temp = Encoding.Unicode.GetBytes(msgs);
			byte[] msg = new byte[temp.Length + 1];
			msg[0] = (byte)MsgLabel.GetQuiz;
			for (int i = 1;i < msg.Length;++i)
				msg[i] = temp[i - 1];
			cilent.SendMessage(msg);
			//Add to next number
			number++;
			requesting = true;
		}
	}
}