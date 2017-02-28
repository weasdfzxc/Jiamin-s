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
        //�������ͷ������
        cameraMgr = gameObject.GetComponent<CameraManager>();
        screenX = Screen.width;
        screenY = Screen.height;
        startButton = "��ʼ";
        pauseButton = "��ͣ";
        isStart = false;
        isPause = false;
    }

    void OnGUI()
    {
        GUI.skin = mySkin;
        Rect area = new Rect(OffsetX, OffsetY, Width, Height);

        GUILayout.BeginArea(area);
        GUILayout.BeginVertical();

        //��ʾ������Ϣ
        if (cameraMgr.GetDebugMessage() != null)
        {
            GUILayout.Label(cameraMgr.GetDebugMessage());
        }

        //��ʼֹͣ
        if (GUILayout.Button(startButton))
        {
            isStart = !isStart;
            if (isStart)
            {
                startButton = "ֹͣ";
                cameraMgr.OpenCamera(RenderTarget);
            }
            else
            {
                startButton = "��ʼ";
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
                    pauseButton = "ȡ����ͣ";
                    cameraMgr.PasueCamera();
                }
                else
                {
                    pauseButton = "��ͣ";
                    cameraMgr.OpenCamera(RenderTarget);
                }
            }
        }

        if (GUILayout.Button("����"))
        {
            cameraMgr.SaveScreenData(0, 0, screenX, screenY);
        }

        if (GUILayout.Button("����ͼ��"))
        {
            cameraMgr.SaveCameraData();
        }

        GUILayout.EndVertical();
        GUILayout.EndArea();
    }

}
