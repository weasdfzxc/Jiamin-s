using UnityEngine;
using System.Collections;
using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Threading;


public class WCamera : MonoBehaviour
{
    public string deviceName;
    public Material mat;
    public Renderer renderTarget;
    public string stringToEdit;
    public float lineWidth;
    public Renderer numRender;
    public int delayTime = 3000;
    public GUISkin Mygui;
    public float alpha = 9 / 10;
    private float alphaprc;
    private float linewidthprc;
    /// <summary>
    /// 摄像头设备
    /// </summary>
    /// 

    public WebCamTexture webCamera;
    private bool controller = true;
    private bool confirmController;
    private bool cancelController;
    private bool resetController;
    private bool photoController;
    private bool tempController = true;
    private bool isYellow;
    private bool isBlack;
    private bool isBlue;
    private bool isWhite;
    private bool isGray;
    private bool isOrange;
    private bool isAyano;
    private bool isGreen;
    private bool isOck;
    private string button = "blue";
    private Color col;
    public int currentTime;
    public int CUT;
    
    /// <summary>
    /// 启动方法
    /// </summary>
    /// <returns></returns>
    IEnumerator Start()
    {

        //获取授权
        yield return Application.RequestUserAuthorization(UserAuthorization.WebCam);
        //授权通过
        if (Application.HasUserAuthorization(UserAuthorization.WebCam))
        {
            //获得设备数量
            WebCamDevice[] devices = WebCamTexture.devices;
            if (devices.Length >1)
            {
                deviceName = devices[devices.Length-1].name;
                //deviceName = devices[0].name;
                Debug.Log(deviceName);
            }
            else
            {
                deviceName = devices[0].name;
                Debug.Log(deviceName);
            }
            webCamera = new WebCamTexture(deviceName, 1918, 1078, 30);
            renderTarget = GameObject.FindGameObjectWithTag("RenderTarget").renderer;
            renderTarget.material.mainTexture = webCamera;
            Play();
        }
        else
        {
        }
    }

    void OnGUI()
    {
        GUI.skin = Mygui;   
        if (renderTarget != null&&webCamera!=null)
        {
            renderTarget.material.mainTexture = webCamera;
        }

        if (controller)
        {
            if (photoController = GUI.Button(new Rect((Screen.width - Screen.width * 3 / 45) / 2, Screen.height - Screen.width * 3 / 45-20, Screen.width * 3 / 45, Screen.width * 3 / 45), "", "photo"))
                 CUT = currentTime;
        }
        if (photoController)
        {
            /*delay();
            Debug.Log(0);
            change();*/
            controller = false;
                if ((currentTime - CUT) > 4) 
                {
                    delay();
                    Debug.Log(0);
                    change();
                }
                else if ((currentTime - CUT) == 4)
                {
                    GUI.Label(new Rect((Screen.width - Screen.height / 3) / 2, Screen.height / 3, Screen.height / 3, Screen.height / 3), "", "back4");
                }
                else if ((currentTime - CUT) == 3) 
                {
                    GUI.Label(new Rect((Screen.width-Screen.height/3)/2, Screen.height/3 ,Screen.height/3, Screen.height/3),"","back1");
                }
                else if ((currentTime - CUT) == 2)
                {
                    GUI.Label(new Rect((Screen.width - Screen.height / 3) / 2, Screen.height / 3, Screen.height / 3, Screen.height / 3), "", "back2");
                }
                else if ((currentTime - CUT) == 1)
                {
                    GUI.Label(new Rect((Screen.width - Screen.height / 3) / 2, Screen.height / 3, Screen.height / 3, Screen.height / 3), "", "back3");
                }
        }
        if (tempController == false)
        {
            GUI.skin.label.normal.textColor = Color.white ;
            GUI.Label(new Rect(0, Screen.height * 5 / 6 - 50, Screen.width, Screen.height / 6 + 50), "", "bg");
            GUI.Label(new Rect(50, Screen.height * 5 / 6 - 45, 110, 40), "线条颜色");
            GUILayout.BeginArea(new Rect(50, Screen.height*5/6-15, Screen.width-100, Screen.height/6));
            
            GUILayout.BeginHorizontal();
            GUILayout.BeginVertical();
            GUILayout.Space(18);
            GUILayout.BeginHorizontal(GUILayout.Width(Screen.width*2/3));
            GUILayout.BeginHorizontal();
            if (isWhite = GUILayout.Toggle(isWhite, "", "white", GUILayout.Width(Screen.width * 3 / 45), GUILayout.Height(Screen.width * 3 / 51)))
            {
                stringToEdit = "white";
                isYellow = false;
                isBlack = false;
                isBlue = false;
                //isWhite = false;
                isGray = false;
                isOrange = false;
                isAyano = false;
                isGreen = false;
                isOck = false;
            }
            GUILayout.EndHorizontal();
            GUILayout.BeginHorizontal();
            if (isYellow = GUILayout.Toggle(isYellow, "", "yellow", GUILayout.Width(Screen.width * 3 / 45), GUILayout.Height(Screen.width * 3 / 51)))
            {
                stringToEdit = "red";
                //isYellow = false;
                isBlack = false;
                isBlue = false;
                isWhite = false;
                isGray = false;
                isOrange = false;
                isAyano = false;
                isGreen = false;
                isOck = false;
            }
            GUILayout.EndHorizontal();
            GUILayout.BeginHorizontal();
            if (isOrange = GUILayout.Toggle(isOrange, "", "orange", GUILayout.Width(Screen.width * 3 / 45), GUILayout.Height(Screen.width * 3 / 51)))
            {
                stringToEdit = "red";
                isYellow = false;
                isBlack = false;
                isBlue = false;
                isWhite = false;
                isGray = false;
                //isOrange = false;
                isAyano = false;
                isGreen = false;
                isOck = false;
            }
            GUILayout.EndHorizontal();
            GUILayout.BeginHorizontal();
            //isYellow = GUI.Button(new Rect(Screen.width / 6 + (Screen.width / 20 + 10), Screen.height - Screen.width / 20 - 20, Screen.width / 20, Screen.width / 20), "");
            if (isAyano = GUILayout.Toggle(isAyano, "", "ayano", GUILayout.Width(Screen.width * 3 / 45), GUILayout.Height(Screen.width * 3 / 51)))
            {
                stringToEdit = "red";
                isYellow = false;
                isBlack = false;
                isBlue = false;
                isWhite = false;
                isGray = false;
                isOrange = false;
                //isAyano = false;
                isGreen = false;
                isOck = false;
            }
            GUILayout.EndHorizontal();
            GUILayout.BeginHorizontal();
            if (isGreen = GUILayout.Toggle(isGreen, "", "green", GUILayout.Width(Screen.width * 3 / 45), GUILayout.Height(Screen.width * 3 / 51)))
            {
                stringToEdit = "red";
                isYellow = false;
                isBlack = false;
                isBlue = false;
                isWhite = false;
                isGray = false;
                isOrange = false;
                isAyano = false;
                //isGreen = false;
                isOck = false;
            }
            GUILayout.EndHorizontal();
            GUILayout.BeginHorizontal();
            if (isBlue = GUILayout.Toggle(isBlue, "", "blue", GUILayout.Width(Screen.width * 3 / 45), GUILayout.Height(Screen.width * 3 / 51)))
            {
                stringToEdit = "red";
                isYellow = false;
                isBlack = false;
                //isBlue = false;
                isWhite = false;
                isGray = false;
                isOrange = false;
                isAyano = false;
                isGreen = false;
                isOck = false;
            }
            GUILayout.EndHorizontal();
            GUILayout.BeginHorizontal();
            if (isOck = GUILayout.Toggle(isOck, "", "ock", GUILayout.Width(Screen.width * 3 / 45), GUILayout.Height(Screen.width * 3 / 51)))
            {
                stringToEdit = "red";
                isYellow = false;
                isBlack = false;
                isBlue = false;
                isWhite = false;
                isGray = false;
                isOrange = false;
                isAyano = false;
                isGreen = false;
                //isOck = false;
            }
            GUILayout.EndHorizontal();
            GUILayout.BeginHorizontal();
            if(isGray = GUILayout.Toggle(isGray,"", "gray", GUILayout.Width(Screen.width * 3 / 45), GUILayout.Height(Screen.width * 3 / 51)))
            {
                stringToEdit = "red";
                isYellow = false;
                isBlack = false;
                isBlue = false;
                isWhite = false;
                //isGray = false;
                isOrange = false;
                isAyano = false;
                isGreen = false;
                isOck = false;
            }
            GUILayout.EndHorizontal();
            GUILayout.BeginHorizontal();
            if (isBlack = GUILayout.Toggle(isBlack, "", "black", GUILayout.Width(Screen.width * 3 / 45), GUILayout.Height(Screen.width * 3 / 51)))
            {
                stringToEdit = "red";
                isYellow = false;
                //isBlack = false;
                isBlue = false;
                isWhite = false;
                isGray = false;
                isOrange = false;
                isAyano = false;
                isGreen = false;
                isOck = false;
            }
            GUILayout.EndHorizontal();
            GUILayout.EndHorizontal();
            /*GUILayout.Space(15);
            GUILayout.BeginHorizontal(GUILayout.Width(Screen.width * 2/3));
            GUILayout.BeginHorizontal(GUILayout.Width(Screen.width * 1/3));
            GUILayout.Label("粗细");
            GUILayout.BeginVertical();
            GUILayout.Space(10);
            lineWidth = GUILayout.HorizontalSlider(lineWidth, 5.0F, 20.0F, GUILayout.Width(Screen.width * 9 / 31));
            GUILayout.EndVertical();
            GUILayout.EndHorizontal();
            GUILayout.Space(10);
            GUILayout.BeginHorizontal(GUILayout.Width(Screen.width * 1/3));
            GUILayout.Label("透明度");
            GUILayout.BeginVertical();
            GUILayout.Space(10);
            alpha = GUILayout.HorizontalSlider(alpha, 6 / 10, 10 / 10, GUILayout.Width(Screen.width * 9 / 32));
            GUILayout.EndVertical();
            GUILayout.EndHorizontal();
            GUILayout.EndHorizontal();*/
            GUILayout.EndVertical();
            GUILayout.Space(Screen.width / 10);
            GUILayout.BeginHorizontal();
            GUILayout.BeginHorizontal();
            GUILayout.BeginVertical(GUILayout.Height(Screen.height / 6));
            confirmController = GUILayout.Button("", "true", GUILayout.Width(Screen.height / 7), GUILayout.Height(Screen.height / 9));
            GUILayout.Space(10);
            GUILayout.BeginHorizontal();
            GUILayout.Space(43);
            GUILayout.Label("确认", GUILayout.Width(Screen.height / 9));
            GUILayout.EndHorizontal();
            GUILayout.EndVertical();
            GUILayout.EndHorizontal();
            GUILayout.BeginHorizontal();
            GUILayout.BeginVertical(GUILayout.Height(Screen.height / 18));
            cancelController = GUILayout.Button("", "back", GUILayout.Width(Screen.height / 7), GUILayout.Height(Screen.height / 9));
            GUILayout.Space(10);
            GUILayout.BeginHorizontal();
            GUILayout.Space(43);
            GUILayout.Label("取消", GUILayout.Width(Screen.height / 9));
            GUILayout.EndHorizontal();
            GUILayout.EndVertical();
            GUILayout.EndHorizontal();
            //GUILayout.BeginVertical(GUILayout.Height(Screen.height / 18));
            //if(resetController = GUILayout.Button("重置", button, GUILayout.Height(Screen.height / 20)))

            //GUILayout.EndVertical();
            GUILayout.EndHorizontal();
            GUILayout.EndHorizontal();
            GUILayout.EndArea();
            GUI.Label(new Rect(50,Screen.height-55,70,37),"粗细");
            GUI.Label(new Rect(130, Screen.height - 46, (Screen.width * 9 / 31), 25), "", "bar2");
            GUI.Label(new Rect(130, Screen.height - 46, (Screen.width * 9 / 31) * linewidthprc, 25), "", "bar");
            lineWidth = GUI.HorizontalSlider(new Rect(130, Screen.height - 46, Screen.width * 9 / 31, 30), lineWidth, 5.0F, 20.0F);
            if (lineWidth >= 0.1f)
                linewidthprc = (lineWidth - 5.0f) / 15.0f;
            else
                linewidthprc = 0.005f;            
            GUI.Label(new Rect(Screen.width * 9 / 31 + 145, Screen.height - 55, 105, 37), "透明度");
            GUI.Label(new Rect(Screen.width * 9 / 31 + 250, Screen.height - 46, (Screen.width * 9 / 31), 25), "", "bar2");
            GUI.Label(new Rect(Screen.width * 9 / 31 + 250, Screen.height - 46, (Screen.width * 9 / 32) * alphaprc, 25), "", "bar");
            alpha = GUI.HorizontalSlider(new Rect(Screen.width * 9 / 31 + 250, Screen.height - 46, Screen.width * 9 / 32, 30), alpha, 0.5F, 1.0F);
            if (alpha >= 0.0001f)
                alphaprc = (alpha - 0.5f) / 0.5f;
            else
                alphaprc = 0.005f;
            
        }
        if (confirmController)
        {

            tempController = true;
            StartCoroutine(getTexture2d());
            //这个方法就是保存图片 
            confirmController = false;
            webCamera.Play();
            
        }
        if (cancelController)
        {
            webCamera.Play();
            cancelController = false;
            tempController = true;
            controller = true;
        }
        setColor();//设置画笔颜色
        /* 
               //重启开始
               if (GUI.Button(new Rect(0, 300, 100, 30), "Restart"))
               {
                   webCamera.Play();
               }
             if (GUI.Button(new Rect(100, 0, 100, 30), "摄像"))
               {
                   //开始摄像，摄像就是一系列的图片集合
                   StartCoroutine(getTexture2dshexiang());
               }
       */
    }

    /// <summary>
    /// 
    /// </summary>
    /// <returns></returns>
    /*    IEnumerator getTexture2dshexiang()
        {
            while (true)
            {
                yield return new WaitForEndOfFrame();
                Texture2D t = new Texture2D(200, 180, TextureFormat.RGB24, true);
                t.ReadPixels(new Rect(200, 320, 200, 180), 0, 0, false);
                t.Apply();

                print(t);
                byte[] byt = t.EncodeToPNG();
                File.WriteAllBytes(Application.dataPath + "/shexiang/" + Time.time.ToString().Split('.')[0] + "_" + Time.time.ToString().Split('.')[1] + ".png", byt);
                Thread.Sleep(200);
            }

        }
        */
    /// <summary>
    /// 获取摄像头截取的图片，这里也是一个协程
    /// </summary>
    /// <returns></returns>
    ///
    private void setColor()
    {
        if (isYellow)
        {
            stringToEdit = "red";
            
        }
        if (isBlack)
        {
            stringToEdit = "black";
            
        }
        if (isBlue)
        {
            stringToEdit = "blue";
           
        }
        if (isWhite)
        {
            stringToEdit = "white";
            
        }
        if (isGray)
        {
            stringToEdit = "gray";
           
        }
        if (isOrange)
        {
            stringToEdit = "orange";
            
        }
        if (isAyano)
        {
            stringToEdit = "ayano";
           
        }
        if (isGreen)
        {
            stringToEdit = "green";
            
        }
        if (isOck)
        {
            stringToEdit = "ock";
           
        }
    }

    public void settext(Color color)
    {
        col = color;
    }

    IEnumerator getTexture2d()
    {
        yield return new WaitForEndOfFrame();
        if (webCamera != null)
        {
            Debug.Log(Screen.width);
            Texture2D texture = new Texture2D(Screen.width-2 , Screen.height-2 );
            texture.ReadPixels(new Rect(0, 0, Screen.width-2, Screen.height-2), 0, 0, false);
            texture.Apply();
            byte[] piexls = texture.EncodeToPNG();
            //然后保存为图片
            File.WriteAllBytes(Application.dataPath + "/images/" + System.DateTime.Now.Year + System.DateTime.Now.Month + System.DateTime.Now.Day + System.DateTime.Now.Hour + System.DateTime.Now.Minute + System.DateTime.Now.Second + ".png", piexls);
            controller = true;
        }
    }


    /// <summary>
    /// 开启摄像头
    /// </summary>
    /// <returns></returns>
    IEnumerator OpenCamera()
    {
        yield return Application.RequestUserAuthorization(UserAuthorization.WebCam);//授权
        {
            WebCamDevice[] devices = WebCamTexture.devices;
            deviceName = devices[1].name;
            //设置摄像机摄像的区域
            webCamera = new WebCamTexture(deviceName, 1920, 1080, 30);
            renderTarget.material.mainTexture = webCamera;
            webCamera.Play();//开始摄像
        }
    }

    void Update()
    {
        if (Input.GetKeyUp(KeyCode.Escape))
        {
            webCamera.Stop();
            webCamera = null;
            renderTarget.enabled =false;
            Destroy(renderTarget);

            controller = false;
            confirmController = false;
            tempController = true;
            Application.Quit();
        }

        currentTime = Mathf.CeilToInt(Time.fixedTime);
    
    }
    private void change()
    {
        Thread.Sleep(delayTime);
        webCamera.Pause();
        controller = false;
        tempController = false;
        photoController = false;
    }
    private void delay()
    {
        int i = 3;
        string s = i--.ToString();
        GUI.Label(new Rect(500,500,200,200),s);
        Thread.Sleep(delayTime / 3);
    }
    private void Play()
    {
        webCamera.Play();
    }
}