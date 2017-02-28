using UnityEngine;
using System;
using System.Collections;
using System.Text;
using System.Net;
using TCPNetwork;
using System.Timers;

public class LMX : MonoBehaviour, IMessageListener{

    private string schoolname;
    private string classname;
    private string studentname;
    private string answertext;
    //private string Ip = "172.32.128.25";
    private string Ip = "192.168.1.205";
    private string errorinfo = string.Empty;
    private Rect win = new Rect(0, 0, Screen.width-20, Screen.height-20);
    private bool ok01 = false;
    private bool ok02 = false;
    private bool ok03 = false;
    private int count;
    private int i = 1;
    private int c = 0;
    private int n = 0;
    private int num = 0;
    private int h = 0;
    private int j = 0;
    private int score;
    private string question;
    private string type;
    private string ans;
    private string[] qsl = new string[500];
    private string[] tsl = new string[500];
    private string[] asl = new string[500];
    private string[] showans = new string[500];
    private string[] showresult = new string[500];
    private string an1;
    private string an2;
    private string an3;
    private string an4;
    private string answer1 = "";
    private string answer2 = "";
    private string answer3 = "";
    private string answer4 = "";
    private string[] anx;
    private bool[] result = new bool[500];
    bool connected;
    TCPCilent cilent;
    MessageFactory msprocess;
    //GetText gt;
    private string show;
    private Vector2 scroll;
    private Rect win2 = new Rect(0, 0, Screen.width, Screen.height);
    private Rect win3 = new Rect(Screen.width / 11, Screen.height / 11 + 35, 9 * (Screen.width / 11), Screen.height);
    private Rect win7 = new Rect(Screen.width / 11, Screen.height / 11 + 35, 9 * (Screen.width / 11), 9 * (Screen.height / 11));
    private Rect win4 = new Rect(Screen.width / 11, Screen.height / 11 - 45 , 9 * (Screen.width / 11), 70);
    private Rect win5 = new Rect(Screen.width / 11, Screen.height / 11, 9 * (Screen.width / 11), 9 * (Screen.height / 11));
    private Rect win6 = new Rect(Screen.width / 4, Screen.height / 3, Screen.width / 2, Screen.height / 3);
    private Rect win8 = new Rect(Screen.width / 11, Screen.height / 11-20, 9 * (Screen.width / 11), 9 * (Screen.height / 11)+20);
    private Rect win9 = new Rect(Screen.width / 11, Screen.height / 11 - 45, 9 * (Screen.width / 11), 70);
    private Rect win10 = new Rect(Screen.width / 11, Screen.height / 11 + 35, 9 * (Screen.width / 11), Screen.height);
    private string body;
    bool A = false;
    bool B = false;
    bool C = false;
    bool D = false;
    bool ok1 = false;
    bool ok2 = false;
    bool ok3 = false;
    bool ok4 = false;
    bool ok05 = false;
    bool ok04 = false;
    bool ok06 = false;
    bool ok07 = false;

    public int currentTime;
    public int currentTime2;
    private int x;
    private int y;
    private int z;
    private int t = 0;
    string[] bgimage = { "bg0","bg1", "bg2", "bg3", "bg4" };

    public GUISkin q4;
    public GUISkin q1;
    public GUISkin q2;
    public GUISkin q3;
    public GUISkin q5;
    public GUISkin q6;
    public GUISkin q0;

	void Start () {
        schoolname = "";
        classname = "";
        studentname = "";
        errorinfo = "登陆失败, 请确认填写信息后重新登陆.";
        scroll[0] = 1;
        scroll[1] = 1;
        show = "2.如对本次答题进行“检查”、“重做”或“修改”可点击界面右上方“列表”按钮进行切换 \n 3.答题确认无误后点击“确认”按钮交卷，否则本次答题无效，交卷后成绩将会提交无法再进行修改";
        count = 0;
        
        for (int m = 0; m < 499; m++)
            result[m] = false;
	}
	
	void OnGUI() {
        
        GUI.skin = q0;
        //GUI.backgroundColor = Color.white;
        GUI.Label(new Rect(0, 0, Screen.width, Screen.height), "", bgimage[t]);
        if (GUI.Button(new Rect(- 2, Screen.height - 89, 110, 91), "", "bgt"))
        {

            if (t >= 4)
                t = 0;
            else
                t++;

        }
        if (connected == false)
        {

            GUI.Window(0, win, winlogin, "欢迎使用钢铁冶炼知识答题系统");
            GUI.Label(new Rect(Screen.width - 300, Screen.height - 60, 300, 60), "Designed by 东北大学秦皇岛分校科学教育研究中心");
            
        }
        else if (connected == null)
        {
            GUI.Label(new Rect(Screen.width / 6, Screen.height / 9, Screen.width * 4 / 6, Screen.height), "正在尝试接触服务器，请一定耐心等候哦！","login");
        }

        else {
            if (Login.Succeeded == false) {
                GUI.Window(0, win, winlogin, "欢迎使用钢铁冶炼知识答题系统");
                GUI.Label(new Rect(200, 100, 250, 60), errorinfo);
                GUI.Label(new Rect(Screen.width - 300, Screen.height - 60, 300, 60), "Designed by 东北大学秦皇岛分校科学教育研究中心");

            }
            if (Login.Succeeded == true && !ok01 && !ok02 && !ok03)
            {
                show = Quiz.Comment;
                GUI.Window(1, win2, winlogin2, "答题事项以及相关说明");
            }

            if (Login.Succeeded == true && ok01 && !ok02 && !ok03 && !ok05)
            {
                GUI.Window(3, win4, winlogin4, "");
                if (!ok04)
                {
                    GUI.Window(2, win3, winlogin3, "");
                    
                    if (GUI.Button(new Rect(Screen.width - Screen.width / 8-10, (Screen.width / 9), Screen.width / 7+15, 50),"", "zl"))
                        ok04 = true;
                }
                else if(ok04) {
                    GUI.Window(6, win7, winlogin7, "");
                    GUI.Label(new Rect(Screen.width - 300, Screen.height - 60, 300, 60), "Designed by 东北大学秦皇岛分校科学教育研究中心");
                    if (GUI.Button(new Rect(Screen.width - Screen.width / 8-10, (Screen.width / 9), Screen.width / 7+15, 50),"", "lz"))
                        ok04 = false;
                }
            }
            if (Login.Succeeded == true && ok01 && !ok02 && !ok03 && ok05)
                GUI.Window(5, win6, winlogin6, "");
            if (Login.Succeeded == true && ok01 && ok02 ) {
                if (!ok06)
                {
                    GUI.Window(4, win5, winlogin5, "");
                    GUI.Label(new Rect(Screen.width - 300, Screen.height - 60, 300, 60), "Designed by 东北大学秦皇岛分校科学教育研究中心");
                    if (GUI.Button(new Rect(Screen.width - Screen.width / 8-10, (Screen.width / 9), Screen.width / 7+15, 50),"", "fg"))
                        ok06 = true;
                }
                else if(ok06)
                    if (!ok07)
                    {
                        
                        GUI.Window(7, win8, winlogin8, "");
                        GUI.Label(new Rect(Screen.width - 300, Screen.height - 60, 300, 60), "Designed by 东北大学秦皇岛分校科学教育研究中心");
                        if (GUI.Button(new Rect(Screen.width - Screen.width / 8-10, (Screen.width / 9), Screen.width / 7+15, 50),"", "gf"))
                            ok06 = false;
                    }
                    else if (ok07)
                    {
                        GUI.Window(9, win10, winlogin10, "");
                        GUI.Window(8, win9, winlogin9, "");
                    }
            }
        }
            
	}
    void Update()
    {
        
       // if(ok01 && !ok02)
            currentTime = Mathf.CeilToInt(Time.fixedTime);
        //currentTime = (int)Time.time;
        
        
    }
    private void winlogin(int windowid) {
        GUI.skin = q1;
        GUI.Label(new Rect(50, (Screen.height)*2/12+30, 150, ((Screen.height)/15)), "学校：");
        GUI.Label(new Rect(50, (Screen.height) * 5 / 12 + 45, 150, ((Screen.height) / 15)), "班级：");
        GUI.Label(new Rect(50, (Screen.height) * 8 / 12 + 60, 150, ((Screen.height) / 15)), "姓名：");
        schoolname = GUI.TextField(new Rect(180, (Screen.height)*2 / 12 + 30, (Screen.width - 250), ((Screen.height ) / 15)), schoolname, 20);
        classname = GUI.TextField(new Rect(180, (Screen.height) * 5 / 12 + 45, (Screen.width - 250), ((Screen.height) / 15)), classname, 20);
        studentname = GUI.TextField(new Rect(180, (Screen.height) * 8 / 12 + 60, (Screen.width - 250), ((Screen.height) / 15)), studentname, 20);

        if (GUI.Button(new Rect((Screen.width) / 3, (Screen.height) * 10 / 12 + 20, (Screen.width) / 3, (Screen.height)/10), "让我们开始吧"))
        {

            
            Connect();
            connected = true;
            count++;
        }
    }
    private void winlogin2(int windowid)
    {
        GUI.skin = q2;
        //scroll = GUI.BeginScrollView(new Rect(10, 30, (7 * (Screen.width / 9) - 20), (7 * (Screen.height / 9) - 160)), scroll, new Rect(31, 31, (Screen.width * (7 / 9) - 62), Screen.height-200), false, false);

        GUI.Label(new Rect(50, 100, (Screen.width - 100), Screen.height - 210), "1.本次答题时间为" + Quiz.Time + "分钟" + "\n" + "2.如对本次答题进行“检查”、“重做”或“修改”可蒂娜及界面右上方“列表”按钮进行切换 \n 3.答题确认无误后点击“确认”按钮交卷，否则本次答题无效，交卷后成绩将会提交无法再进行修改");
        //GUI.EndScrollView();

        if (GUI.Button(new Rect(Screen.width / 3, Screen.height - 110, Screen.width / 3, 60), "开始答题"))
        {
            ok01 = true;
            Quiz.Request(cilent);
            num++;
            
            currentTime2 = (currentTime + (int)Quiz.Time * 60);
            //Timer time = new Timer();           
        }

    }
    private void winlogin3(int windowid)
    {
        //i = 1;
        GUI.skin = q3;
        if (Quiz.Requesting == false)
        {
            qsl[num] = Quiz.Questions[Quiz.Questions.Count - 1].question;
            tsl[num] = Quiz.Questions[Quiz.Questions.Count - 1].type;
            asl[num] = Quiz.Questions[Quiz.Questions.Count - 1].answer;
        }
            question = qsl[i];
            type = tsl[i];
            ans = asl[i];
            //Debug.Log(num + "sn"); 
        //Debug.Log(ans);        
        
        if (ans.IndexOf("[A]") != -1) 
            A = true;
        if (ans.IndexOf("[B]") != -1)
            B = true;
        if (ans.IndexOf("[C]") != -1)
            C = true;
        if (ans.IndexOf("[D]") != -1)
            D = true;
        
        ans = ans.Replace("[","");
        ans = ans.Replace("]","");

        anx = ans.Split(';');
        an1 = anx[0];
        if (anx.Length >= 2)
            an2 = anx[1];
        if (anx.Length >= 3)
            an3 = anx[2];
        if (anx.Length >= 4)
            an4 = anx[3];

        GUI.Label(new Rect(0, 0, (9 * (Screen.width / 11) - 30), (9 * (Screen.height / 11)) * 3 / 5-50), question + "（" + showans[i] + "）");
        if (type.IndexOf("选择题") != -1)
        {
            ok1 = GUI.Toggle(new Rect(0, (9 * (Screen.height / 11)) * 3 / 5 - 50, (9 * (Screen.width / 11) - 30), (9 * (Screen.height / 11)) / 10), ok1, an1);
            if (ok1)
            {
                answer1 = "A";
            }
            else
                answer1 = "";
            if (anx.Length >= 2)
            {
                ok2 = GUI.Toggle(new Rect(0, (9 * (Screen.height / 11)) * 7 / 10 - 50, (9 * (Screen.width / 11) - 30), (9 * (Screen.height / 11)) / 10), ok2, an2);
                if (ok2)
                {
                    answer2 = "B";
                }
                else
                    answer2 = "";
            }

            if (anx.Length >= 3)
            {
                ok3 = GUI.Toggle(new Rect(0, (9 * (Screen.height / 11)) * 8 / 10 - 50, (9 * (Screen.width / 11) - 30), (9 * (Screen.height / 11)) / 10), ok3, an3);
                if (ok3)                    
                    answer3 = "C";
                else
                    answer3 = "";
            }
            if (anx.Length >= 4)
            {
                ok4 = GUI.Toggle(new Rect(0, (9 * (Screen.height / 11)) * 9 / 10 - 50, (9 * (Screen.width / 11) - 30), (9 * (Screen.height / 11)) / 10), ok4, an4);
                if (ok4)
                    answer4 = "D";
                else
                    answer4 = "";
            }
            showans[i] = answer1 + answer2 + answer3 + answer4;
            //if (GUI.Toggle(new Rect(0, (9 * (Screen.height / 11)) * 3 / 5 -50, (9 * (Screen.width / 11) - 30), (9 * (Screen.height / 11)) / 10), ok1, an1))
            //{
            //    answer1 = "A";
            //}
            //if (anx.Length >= 2)
            //    if (GUI.Toggle(new Rect(0, (9 * (Screen.height / 11)) * 7 / 10 - 50, (9 * (Screen.width / 11) - 30), (9 * (Screen.height / 11)) / 10), ok2, an2))
            //    {
            //        answer2 = "B";
            //    }
            //if (anx.Length >= 3)
            //    if (GUI.Toggle(new Rect(0, (9 * (Screen.height / 11)) * 8 / 10 - 50, (9 * (Screen.width / 11) - 30), (9 * (Screen.height / 11)) / 10), ok3, an3))
            //    {
            //        answer3 = "C";
            //    }
            //if (anx.Length >= 4)
            //    if (GUI.Toggle(new Rect(0, (9 * (Screen.height / 11)) * 9 / 10 - 50, (9 * (Screen.width / 11) - 30), (9 * (Screen.height / 11)) / 10), ok4, an4))
            //    {
                    
            //        answer4 = "D";
            //    }
            
        }

        else if(type.IndexOf("填空题") != -1){
            GUI.Label(new Rect(Screen.width*14/27, (7 * (Screen.height / 9)) * 9 / 10, 70, 50), "填写答案：");
            answertext = GUI.TextField(new Rect(100, (7 * (Screen.height / 9) - 150), (7 * (Screen.width / 9) - 60), 50), answertext, 20);
        }
        if (i<Quiz.Total)
        {
            if (i >= 2) {
                if (GUI.Button(new Rect(0, (9 * (Screen.height / 11)) - 45, (9 * (Screen.width / 11)) * 4 / 11, 45), "上一题"))
                {
                    showans[i] = answer1 + answer2 + answer3 + answer4;
                    i--;
                    A = false;
                    B = false;
                    C = false;
                    D = false;
                    ok1 = false;
                    ok2 = false;
                    ok3 = false;
                    ok4 = false;
                    answer1 = "";
                    answer2 = "";
                    answer3 = "";
                    answer4 = "";
                }
            }

            if (GUI.Button(new Rect((9 * (Screen.width / 11)) * 7 / 11, (9 * (Screen.height / 11)) - 45, (9 * (Screen.width / 11)) * 4 / 11, 45), "下一题"))
            {
                if (type.IndexOf("选择题") != -1)
                {
                    if ((A == ok1) && (B == ok2) && (C == ok3) && (D == ok4))
                        result[i] = true;
                    showans[i] = answer1 + answer2 + answer3 + answer4;
                    answer1 = "";
                    answer2 = "";
                    answer3 = "";
                    answer4 = "";
                    A = false;
                    B = false;
                    C = false;
                    D = false;
                    ok1 = false;
                    ok2 = false;
                    ok3 = false;
                    ok4 = false;
                }
                if (type.IndexOf("填空题") != -1)
                {
                    if (ans == answertext)
                        result[i] = true;
                }
                i++;
                if (j <= i)
                    j = i;
                //Debug.Log(num+"st");
                if (num < Quiz.Total) {
                    Quiz.Request(cilent);
                    num++;
                }
                //Debug.Log(num);
            }
        }
        else if (i == Quiz.Total)
        {
            if (i >= 2)
            {
                if (GUI.Button(new Rect(0, (9 * (Screen.height / 11)) - 45, (9 * (Screen.width / 11)) * 4 / 11, 45), "上一题"))
                { 
                    i--;
                    A = false;
                    B = false;
                    C = false;
                    D = false;
                    ok1 = false;
                    ok2 = false;
                    ok3 = false;
                    ok4 = false;
                }

            }

            if (GUI.Button(new Rect((9 * (Screen.width / 11)) * 7 / 11, (9 * (Screen.height / 11)) - 45, (9 * (Screen.width / 11)) * 4 / 11, 45), "交卷"))
            {
                if (type.IndexOf("选择题") != -1)
                {
                    if ((A == ok1) && (B == ok2) && (C == ok3) && (D == ok4))
                        result[i] = true;
                }
                showans[i] = answer1 + answer2 + answer3 + answer4;
                answer1 = "";
                answer2 = "";
                answer3 = "";
                answer4 = "";
                if (type.IndexOf("填空题") != -1)
                {
                    if (ans == answertext)
                        result[i] = true;
                }
                

                for (int m = 1; m <= i; m++) {
                    if (result[m])
                        n++;
                }
                score = n * (100 / (int)Quiz.Total);
                   
                    ok05 = true;
            }
        }
    }
    private void winlogin4(int windowid)
    {
        GUI.skin = q4;
        
        y = (currentTime2 - currentTime) / 60;
        z = (currentTime2 - currentTime) % 60;
        
        if ((currentTime2 - currentTime)<=0)
        {
            for (int m = 1; m <= i; m++)
            {
                if (result[m])
                    n++;
            }
            score = n * (100 / (int)Quiz.Total);

            Submit.Request(cilent, schoolname, studentname, score.ToString(), ((int)Quiz.Time * 60 - (currentTime2 - currentTime)) / 60 + ":" + ((int)Quiz.Time * 60 - (currentTime2 - currentTime)) % 60);
            Rank.Request(cilent);
            ok03 = true;
            ok02 = true;
        }
        if ((currentTime2 - currentTime) <= 60)
            GUI.Label(new Rect(0, 7, 9 * (Screen.width / 11), 70), "第" + i + "题" + "\t" + "\t" + "\t" + "\t" + "\t" + "\t" + "剩余时间：" + y + "：" + z,"notime"); 
        else
        GUI.Label(new Rect(0, 7, 9 * (Screen.width / 11), 70), "第" + i + "题" + "\t" + "\t" + "\t" + "\t" + "\t" + "\t" + "剩余时间：" + y + "：" + z);        
        //GUI.Label(new Rect(30, 7, 7 * (Screen.width / 9) - 60, 70), "剩余时间：" + y + "：" + z);
    }
    //"学校：" + schoolname + "\t" + "\t" + "\t" + "班级：" + classname + "\t" + "\t" + "\t" + "姓名：" + studentname + "\t" + "\t" + "\t" + 
    private void winlogin5(int windowid)
    {
        GUI.skin = q5;
        if (Submit.Succeeded == null)
        {
            Submit.Request(cilent, schoolname, studentname, score.ToString(), ((int)Quiz.Time * 60 - (currentTime2 - currentTime)) / 60 + ":" + ((int)Quiz.Time * 60 - (currentTime2 - currentTime)) % 60);

        }

        if (Submit.Succeeded != null)
        {
            

            if (Rank.Ranklists == null || Rank.Ranklists.Count == 0)
                return;

            
            GUILayout.BeginArea(new Rect(0, 0, 9 * (Screen.width / 11), 9 * (Screen.height / 11)));
            GUILayout.BeginVertical("");
            

            GUILayout.Label("第1名："+Rank.Ranklists[0].date + "\t" + "\t" + Rank.Ranklists[0].school + "\t" + "\t" + Rank.Ranklists[0].name + "\t" + "\t" + Rank.Ranklists[0].grade+"分","firstscore");
            if (c >= 2)
            {
                GUILayout.Label("第2名：" + Rank.Ranklists[1].date + "\t" + "\t" + Rank.Ranklists[1].school + "\t" + "\t" + Rank.Ranklists[1].name + "\t" + "\t" + Rank.Ranklists[1].grade + "分");
            }
            if (c >= 3)
            {
                GUILayout.Label("第3名：" + Rank.Ranklists[2].date + "\t" + "\t" + Rank.Ranklists[2].school + "\t" + "\t" + Rank.Ranklists[2].name + "\t" + "\t" + Rank.Ranklists[2].grade + "分");
            }
            if (c >= 4)
            {
                GUILayout.Label("第4名：" + Rank.Ranklists[3].date + "\t" + "\t" + Rank.Ranklists[3].school + "\t" + "\t" + Rank.Ranklists[3].name + "\t" + "\t" + Rank.Ranklists[3].grade + "分");
            }
            if (c >= 5)
            {
                GUILayout.Label("第5名：" + Rank.Ranklists[4].date + "\t" + "\t" + Rank.Ranklists[4].school + "\t" + "\t" + Rank.Ranklists[4].name + "\t" + "\t" + Rank.Ranklists[4].grade + "分");
            }
            if (c >= 6)
            {
                GUILayout.Label("第6名：" + Rank.Ranklists[4].date + "\t" + "\t" + Rank.Ranklists[4].school + "\t" + "\t" + Rank.Ranklists[4].name + "\t" + "\t" + Rank.Ranklists[5].grade + "分");
            }
            for (int v = 0; v < Rank.Ranklists.Count; v++) {
                if (studentname.Equals(Rank.Ranklists[v].name) && schoolname.Equals(Rank.Ranklists[v].school))
                {
                    c = v + 1;
                    GUILayout.Label("你的位置在第" + c + "名：" + Rank.Ranklists[v].date + "\t" + "\t" + Rank.Ranklists[v].school + "\t" + "\t" + Rank.Ranklists[v].name + "\t" + "\t" + Rank.Ranklists[v].grade + "分", "myscore");
                    
                }
                }
            
            }
            
            GUILayout.EndVertical();
            GUILayout.EndArea();
                //GUI.Label(new Rect(30, (Screen.height * 2 / 3 - 40) * 2 / 8, Screen.width * 2 / 3 - 70, ((Screen.height * 2 / 3 - 40) / 8)), "学校：" + schoolname);
                //GUI.Label(new Rect(30, (Screen.height * 2 / 3 - 40) * 3 / 8, Screen.width * 2 / 3 - 70, ((Screen.height * 2 / 3 - 40) / 8)), "班级：" + classname);
                //GUI.Label(new Rect(30, (Screen.height * 2 / 3 - 40) * 4 / 8, Screen.width * 2 / 3 - 70, ((Screen.height * 2 / 3 - 40) / 8)), "姓名：" + studentname);
                //GUI.Label(new Rect(30, (Screen.height * 2 / 3 - 40) * 5 / 8, Screen.width * 2 / 3 - 70, ((Screen.height * 2 / 3 - 40) / 8)), "分数：" + score + "分");
                //GUI.Label(new Rect(30, (Screen.height * 2 / 3 - 40) * 6 / 8, Screen.width * 2 / 3 - 70, ((Screen.height * 2 / 3 - 40) / 8)), "时间：" + ((int)Quiz.Time*60 - (currentTime2 - currentTime)) / 60 + ":" + ((int)Quiz.Time*60 - (currentTime2 - currentTime)) % 60);
            if (GUI.Button(new Rect((Screen.width) * 9 / 11 / 3, (Screen.height) * 9 / 11 * 8/9, (Screen.width) * 9 / 11 / 3, 50), "下次再见"))
                {
                    DisConnect();

                }
        }
    
    private void winlogin6(int windowid)
    {
        GUI.skin = q2;

        GUI.Label(new Rect(30, Screen.height / 11, Screen.width / 2 - 60, Screen.height / 6), "一旦提交将无法再进行修改");

            if (GUI.Button(new Rect(Screen.width / 8-30, Screen.height / 3 - Screen.height / 20 - 30, Screen.width / 8, Screen.height / 20), "确认"))
            {
                ok02 = true;
                ok05 = false;

                if (Quiz.Finished && Submit.Succeeded == null)
                {
                    Submit.Request(cilent, schoolname, studentname, score.ToString(), ((int)Quiz.Time * 60 - (currentTime2 - currentTime)) / 60 + ":" + ((int)Quiz.Time * 60 - (currentTime2 - currentTime)) % 60);
                    
                    
                }
                Rank.Request(cilent);
            }
            if (GUI.Button(new Rect(Screen.width / 4+30, Screen.height / 3 - Screen.height / 20 - 30, Screen.width / 8, Screen.height / 20), "取消"))
            {
                ok05 = false;
            }
        
    }
    private void winlogin7(int windowid)
    {
        GUI.skin = q3;
        GUILayout.BeginArea(new Rect(0, 0, 9 * (Screen.width / 11), 9 * (Screen.height / 11)));
        GUILayout.BeginVertical("");
        
        for (h = 1; h <= Quiz.Total; h++)
        {
            if (h <= j)
            {
                if (h == 1)
                {

                    GUILayout.BeginHorizontal("", GUILayout.Width(9 * (Screen.width / 11) - 50));
                    GUILayout.BeginVertical("");
                    GUILayout.Label(h.ToString(), "up", GUILayout.Width((9 * (Screen.width / 11)-100) / 4), GUILayout.Height((9 * (Screen.height / 11)-60) / 12));
                    if (GUILayout.Button(showans[h], "down", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 5)))
                    {

                            i = h;
                            ok04 = false;
                        }
                    GUILayout.EndVertical();  
                    
                }
                if (h > 1 && h < Quiz.Total)
                {
                    if (h % 4 == 0)
                    {
                        GUILayout.BeginVertical("");
                        GUILayout.Label(h.ToString(), "up", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 12));
                        if (GUILayout.Button(showans[h], "down", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 5)))
                        {
                            i = h;
                            ok04 = false;
                        }
                        GUILayout.EndVertical();
                        GUILayout.EndHorizontal();
                        GUILayout.BeginHorizontal("", GUILayout.Width(9 * (Screen.width / 11) - 50));
                    }
                    else
                    {
                        GUILayout.BeginVertical("");
                        GUILayout.Label(h.ToString(), "up", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 12));
                        if (GUILayout.Button(showans[h], "down", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 5)))
                        {
                            i = h;
                            ok04 = false;
                        }
                        GUILayout.EndVertical();
                    }
                }
                if (h==Quiz.Total)
                {
                    GUILayout.BeginVertical("");
                    GUILayout.Label(h.ToString(), "up", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 12));
                    if (GUILayout.Button(showans[h], "down", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 5)))
                    {
                            i = h;
                            ok04 = false;
                    }
                    GUILayout.EndVertical();
                    GUILayout.EndHorizontal();
                    
                }
            }
            else {
                if (h == 1)
                {

                    GUILayout.BeginHorizontal("", GUILayout.Width(9 * (Screen.width / 11) - 50));
                    GUILayout.BeginVertical("");
                    GUILayout.Label(h.ToString(), "up", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 12));
                    if (GUILayout.Button(showans[h], "down", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 5)))
                    {

                    }
                    GUILayout.EndVertical();

                }
                if (h > 1 && h < Quiz.Total)
                {
                    if (h % 4 == 0)
                    {
                        GUILayout.BeginVertical("");
                        GUILayout.Label(h.ToString(), "up", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 12));
                        if (GUILayout.Button(showans[h], "down", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 5)))
                        {
                           
                        }
                        GUILayout.EndVertical();
                        GUILayout.EndHorizontal();
                        GUILayout.BeginHorizontal("", GUILayout.Width(9 * (Screen.width / 11) - 50));
                    }
                    else
                    {
                        GUILayout.BeginVertical("");
                        GUILayout.Label(h.ToString(), "up", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 12));
                        if (GUILayout.Button(showans[h], "down", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 5)))
                        {
                           
                        }
                        GUILayout.EndVertical();
                    }
                }
                if (h == Quiz.Total)
                {
                    GUILayout.BeginVertical("");
                    GUILayout.Label(h.ToString(), "up", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 12));
                    if (GUILayout.Button(showans[h], "down", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 5)))
                    {
                        
                    }
                    GUILayout.EndVertical();
                    GUILayout.EndHorizontal();

                }
            }
        }
        GUILayout.EndVertical();
        GUILayout.EndArea();


    }
    private void winlogin8(int windowid)
    {
        GUI.skin = q3;
        GUILayout.BeginArea(new Rect(0, 0, 9 * (Screen.width / 11), 9 * (Screen.height / 11)));
        GUILayout.BeginVertical("");
        
        for (int m = 0; m < 499; m++)
        {
            if (!result[m])
                showresult[m] = "×";
            else if(result[m])
                showresult[m] = "√";
        }
        for (h = 1; h <= Quiz.Total; h++)
        {
            if (h <= j)
            {
                if (h == 1)
                {

                    GUILayout.BeginHorizontal("", GUILayout.Width(9 * (Screen.width / 11) - 50));
                    GUILayout.BeginVertical("");
                    GUILayout.Label(h.ToString(), "up", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 12));
                    if (GUILayout.Button(showresult[h], "down", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 5)))
                    {

                        i = h;
                        ok07 = true;
                    }
                    GUILayout.EndVertical();

                }
                if (h > 1 && h < Quiz.Total)
                {
                    if (h % 4 == 0)
                    {
                        GUILayout.BeginVertical("");
                        GUILayout.Label(h.ToString(), "up", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 12));
                        if (GUILayout.Button(showresult[h], "down", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 5)))
                        {
                            i = h;
                            ok07 = true;
                        }
                        GUILayout.EndVertical();
                        GUILayout.EndHorizontal();
                        GUILayout.BeginHorizontal("", GUILayout.Width(9 * (Screen.width / 11) - 50));
                    }
                    else
                    {
                        GUILayout.BeginVertical("");
                        GUILayout.Label(h.ToString(), "up", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 12));
                        if (GUILayout.Button(showresult[h], "down", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 5)))
                        {
                            i = h;
                            ok07 = true;
                        }
                        GUILayout.EndVertical();
                    }
                }
                if (h == Quiz.Total)
                {
                    GUILayout.BeginVertical("");
                    GUILayout.Label(h.ToString(), "up", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 12));
                    if (GUILayout.Button(showresult[h], "down", GUILayout.Width((9 * (Screen.width / 11) - 100) / 4), GUILayout.Height((9 * (Screen.height / 11) - 60) / 5)))
                    {
                        i = h;
                        ok07 = true;
                    }
                    GUILayout.EndVertical();
                    GUILayout.EndHorizontal();

                }
            }
        }
        GUILayout.EndVertical();
        GUILayout.EndArea();
    }
    private void winlogin9(int windowid)
    {
        GUI.skin = q4;

        
        GUI.Label(new Rect(30, 7, 7 * (Screen.width / 9) - 60, 70), "第" + i + "题" + "\t" + "\t" + "\t" + "\t" + "此题：" + showresult[i]);
        //GUI.Label(new Rect(30, 7, 7 * (Screen.width / 9) - 60, 70), "剩余时间：" + y + "：" + z);
    }
    private void winlogin10(int windowid)
    {
        GUI.skin = q3;
       
        question = qsl[i];
        type = tsl[i];
        ans = asl[i];
        //Debug.Log(num + "sn"); 
        //Debug.Log(ans);        

        if (ans.IndexOf("[A]") != -1)
            A = true;
        if (ans.IndexOf("[B]") != -1)
            B = true;
        if (ans.IndexOf("[C]") != -1)
            C = true;
        if (ans.IndexOf("[D]") != -1)
            D = true;

        ans = ans.Replace("[", "");
        ans = ans.Replace("]", "");

        anx = ans.Split(';');
        an1 = anx[0];
        if (anx.Length >= 2)
            an2 = anx[1];
        if (anx.Length >= 3)
            an3 = anx[2];
        if (anx.Length >= 4)
            an4 = anx[3];

        GUI.Label(new Rect(0, 0, (9 * (Screen.width / 11) - 30), (9 * (Screen.height / 11)) * 3 / 5 + 60), question + "（" + showans[i] + "）");
        if (type.IndexOf("选择题") != -1)
        {

            ok1 = GUI.Toggle(new Rect(0, (9 * (Screen.height / 11)) * 3 / 5 - 50, (9 * (Screen.width / 11) - 30), (9 * (Screen.height / 11)) / 10), ok1, an1);
            if (ok1)
            {
                answer1 = "A";
            }
            else
                answer1 = "";
            if (anx.Length >= 2)
            {
                ok2 = GUI.Toggle(new Rect(0, (9 * (Screen.height / 11)) * 7 / 10 - 50, (9 * (Screen.width / 11) - 30), (9 * (Screen.height / 11)) / 10), ok2, an2);
                if (ok2)
                {
                    answer2 = "B";
                }
                else
                    answer2 = "";
            }

            if (anx.Length >= 3)
            {
                ok3 = GUI.Toggle(new Rect(0, (9 * (Screen.height / 11)) * 8 / 10 - 50, (9 * (Screen.width / 11) - 30), (9 * (Screen.height / 11)) / 10), ok3, an3);
                if (ok3)
                    answer3 = "C";
                else
                    answer3 = "";
            }
            if (anx.Length >= 4)
            {
                ok4 = GUI.Toggle(new Rect(0, (9 * (Screen.height / 11)) * 9 / 10 - 50, (9 * (Screen.width / 11) - 30), (9 * (Screen.height / 11)) / 10), ok4, an4);
                if (ok4)
                    answer4 = "D";
                else
                    answer4 = "";
            }
            showans[i] = answer1 + answer2 + answer3 + answer4;


        }
        else if (type.IndexOf("填空题") != -1)
        {
            GUI.Label(new Rect(Screen.width * 14 / 27, (7 * (Screen.height / 9)) * 9 / 10, 70, 50), "填写答案：");
            answertext = GUI.TextField(new Rect(100, (7 * (Screen.height / 9) - 150), (7 * (Screen.width / 9) - 60), 50), answertext, 20);
        }
        if (i <= Quiz.Total)
        {
            if ((A == ok1) && (B == ok2) && (C == ok3) && (D == ok4))
                result[i] = true;
            else
                result[i] = false;
            if (!result[i])
                showresult[i] = "错误";
            else if (result[i])
                showresult[i] = "正确";
            if (GUI.Button(new Rect((9 * (Screen.width / 11)) * 7 / 11, (9 * (Screen.height / 11)) - 45, (9 * (Screen.width / 11)) * 4 / 11, 45), "返回"))
            {
                if (type.IndexOf("选择题") != -1)
                {                    
                    A = false;
                    B = false;
                    C = false;
                    D = false;
                    ok1 = false;
                    ok2 = false;
                    ok3 = false;
                    ok4 = false;
                    ok07 = false;
                }
                if (type.IndexOf("填空题") != -1)
                {
                    if (ans == answertext)
                        result[i] = true;
                }
                
            }
        }
        
    }
    void Connectify()
    {
        
        connected = true;
        //Debug.Log(connected.ToString());
        msprocess = new MessageFactory(this);
        cilent = new TCPCilent(msprocess, Ip, 10520);
        cilent.Connect();
        Login.Init();
        Register.Init();
        Quiz.Init();
        Submit.Init();
        
    }
    void Connect()
    {

        connected = true;
        //Debug.Log(connected.ToString());
        msprocess = new MessageFactory(this);
        cilent = new TCPCilent(msprocess, Ip, 10520);
        cilent.Connect();
        Login.Init();
        Register.Init();
        Quiz.Init();
        Submit.Init();

    }

    void DisConnect()
    {
        connected = false;
        cilent.Close();
        string level = Application.loadedLevelName;
        if(level == "0")
        Application.LoadLevelAsync(1);
        else
            Application.LoadLevelAsync(0);
        count = 0;
        ok01 = false;
        ok02 = false;
        ok03 = false;
        c = 0;
        i = 1;
        for (int m = 0; m < 499; m++)
            result[m] = false;
        for (int m = 0; m < 499; m++)
            showans[m] = "";
    }

    //Interface implements
    public void Connecting()
    {
        errorinfo = string.Empty;
    }
    public void Connected()
    {
        
        Register.Request(cilent, schoolname, classname, studentname);
        Login.Request(cilent, schoolname, classname, studentname);
        
    }
    public void Connectified() {
        Register.Request(cilent, schoolname, classname, studentname);
        Rank.Request(cilent);
    }
    public void Disconnected()
    {
        errorinfo = "登陆失败, 请确认填写信息后重新登陆.";
        DisConnect();
    }
}
