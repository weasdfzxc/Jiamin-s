using UnityEngine;
using System.Collections;

public class FullScreen : MonoBehaviour
{

    void Start()
    {
        //�õ���Camera
        Camera cam = Camera.mainCamera;
        //����Ϊ����
        cam.isOrthoGraphic = true;
        //���ô�СΪ��Ļ��ȵ�һ�롣����10��Ϊ���Ժ�ʹ����������ʱֱ��ʹ������
        cam.orthographicSize = Screen.height / 2 * 10;
        //���ñ���Plane�Ĵ�С
        gameObject.transform.localScale = new Vector3(cam.pixelWidth, 1, cam.pixelHeight);
    }

    //�Ժ�Plane�ϵ����嶼ʹ�ô�����
    static public Vector3 GetScale()
    {
        Camera cam = Camera.mainCamera;
        return new Vector3(cam.pixelHeight, 1, cam.pixelHeight);
    }
}