using UnityEngine;
using System.Collections;

public class FullScreen : MonoBehaviour
{

    void Start()
    {
        //得到主Camera
        Camera cam = Camera.mainCamera;
        //设置为正交
        cam.isOrthoGraphic = true;
        //设置大小为屏幕宽度的一半。乘以10是为了以后使用物体缩放时直接使用像素
        cam.orthographicSize = Screen.height / 2 * 10;
        //设置背景Plane的大小
        gameObject.transform.localScale = new Vector3(cam.pixelWidth, 1, cam.pixelHeight);
    }

    //以后Plane上的物体都使用此缩放
    static public Vector3 GetScale()
    {
        Camera cam = Camera.mainCamera;
        return new Vector3(cam.pixelHeight, 1, cam.pixelHeight);
    }
}