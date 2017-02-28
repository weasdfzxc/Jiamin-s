using UnityEngine;
using System.Collections;

public class ButtonCtrl : MonoBehaviour
{

    public GUISkin mySkin;

    public Renderer RenderTarget;

    public int OffsetX = 100;
    public int OffsetY = 100;

    public int Width = 100;
    public int Height = 300;
    public int ButtonSpace = 30;

    private CameraManager cameraMgr;
    private int screenX;
    private int screenY;

    private string startButton;
    private string pauseButton;
    private bool isStart;
    private bool isPause;

    void Start()
    {
        //获得摄像头管理类
        cameraMgr = gameObject.GetComponent<CameraManager>();
        screenX = Screen.width;
        screenY = Screen.height;
        startButton = "开始";
        pauseButton = "暂停";
        isStart = false;
        isPause = false;
    }

    void OnGUI()
    {
        GUI.skin = mySkin;
        Rect area = new Rect(OffsetX, OffsetY, Width, Height);

        GUILayout.BeginArea(area);
        GUILayout.BeginVertical();

        //显示调试信息
        if (cameraMgr.GetDebugMessage() != null)
        {
            GUILayout.Label(cameraMgr.GetDebugMessage());
        }

        //开始停止
        if (GUILayout.Button(startButton))
        {
            isStart = !isStart;
            if (isStart)
            {
                startButton = "停止";
                cameraMgr.OpenCamera(RenderTarget);
            }
            else
            {
                startButton = "开始";
                cameraMgr.StopCamera();
            }
        }

        if (GUILayout.Button(pauseButton))
        {
            if (isStart)
            {
                isPause = !isPause;
                if (isPause)
                {
                    pauseButton = "取消暂停";
                    cameraMgr.PasueCamera();
                }
                else
                {
                    pauseButton = "暂停";
                    cameraMgr.OpenCamera(RenderTarget);
                }
            }
        }

        if (GUILayout.Button("截屏"))
        {
            cameraMgr.SaveScreenData(0, 0, screenX, screenY);
        }

        if (GUILayout.Button("保存图像"))
        {
            cameraMgr.SaveCameraData();
        }

        GUILayout.EndVertical();
        GUILayout.EndArea();
    }

}
