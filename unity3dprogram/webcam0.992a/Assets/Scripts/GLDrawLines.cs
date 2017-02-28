using UnityEngine;
using System.Collections;
using System.IO;
public class joint
{
    public Vector3 org;
    public Vector3 end;
}
public class GLDrawLines : MonoBehaviour
{
    public float lineWidth;
    public GameObject Panel;
    Event e;
    private string deviceName;
    private WebCamTexture webCamera;
    private Vector3 orgPos;
    private Vector3 endPos;
    private bool canDrawLines = false;
    private WCamera wcamera;
    ArrayList posAL;
    ArrayList temppos;
    public Material lineMaterial;
    private bool ok = true;
    DrawLine drawline;
    void Start()
    {
        wcamera = Panel.GetComponent<WCamera>();
        temppos = new ArrayList();
        posAL = new ArrayList();
        webCamera = wcamera.webCamera;
        
    }

    void Update()
    {
        
    }

    void GLDrawLine(Vector3 beg, Vector3 end)
    {
        //float x1 = lineWidth / Screen.width;
        //float y1 = lineWidth / Screen.height;
        if (!canDrawLines)
            return;
        //drawline = new DrawLine();
        //drawline.Start();
        //drawline.Update();
    }


    void OnGUI()
    {
        if (webCamera != null)
        {
            if (webCamera.isPlaying != true)
            {
                ok = true;
                if (Input.GetMouseButton(0))
                {
                    canDrawLines = true;
                }
                if (e != null && canDrawLines)
                {
                    if (e.type == EventType.MouseDown)
                    {
                        orgPos = Input.mousePosition;
                        endPos = Input.mousePosition;
                        //Debug.Log("Mouse down");
                    }
                    if (e.type == EventType.MouseDrag)
                    {
                        //Debug.Log("Mouse drag");
                        
                        endPos = Input.mousePosition;
                        temppos.Add(Input.mousePosition);
                        //if (temppos[0] != null)
                        //{
                        //    //Vector3 x1 = (((Vector3)temppos[0]) - ((Vector3)temppos[0]));
                        //}
                        GLDrawLine(orgPos, endPos);
                        orgPos = Input.mousePosition;
                    }
                    if (e.type == EventType.MouseUp)
                    {
                        //Debug.Log("Mouse up");
                        // orgPos=Input.mousePosition;  
                        endPos = Input.mousePosition;
                    }
                    // Debug.Log("Begin Draw Line :" + orgPos);
                    //Debug.Log("End Draw Line :" + endPos);
                }
            }
            else
            {
                if (ok)
                {
                    //Debug.Log("clear lines");
                    StartCoroutine(ClearLines());
                    ok = false;
                }
            }

        }

        else
        {
            webCamera = wcamera.webCamera;
        }

        e = Event.current;
        OnPostRender();
    }

    IEnumerator ClearLines()
    {
        yield return new WaitForEndOfFrame();
        posAL.Clear();
    }

    void OnPostRender()
    {
        GLDrawLine(orgPos, endPos);
    }

    void OnRenderObject()
    {
        GLDrawLine(orgPos, endPos);
    }
}