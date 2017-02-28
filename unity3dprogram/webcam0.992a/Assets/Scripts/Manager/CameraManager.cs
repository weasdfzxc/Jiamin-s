using UnityEngine;
using System.IO;
using System.Collections;
using System.Threading;


/// <summary>
/// ����ͷ�����࣬�����������ͷ�豸
/// </summary>
public class CameraManager : MonoBehaviour
{
    #region ����
    public WebCamTexture LocalCamera;

    //�й�����ͷ������
    public int ResulotionWidth = 1024;
    public int ResulotionHeight = 768;
    public int Fps = 30;

    //����ͷ����������
    private string deviceName;
    //������Ϣ
    private string DebugMessage;
    #endregion

    #region ��������

    /// <summary>
    /// ��������ͷ
    /// </summary>
    /// <param name="render"></param>
    public void OpenCamera(Renderer render)
    {
        StartCoroutine(OpenLocalCamera(render));
    }

    /// <summary>
    /// ������Ļ���ݵ�һ��PNGͼƬ
    /// </summary>
    /// <param name="Offset_X"></param>
    /// <param name="Offset_Y"></param>
    /// <param name="width"></param>
    /// <param name="height"></param>
    public void SaveScreenData(int Offset_X, int Offset_Y, int width, int height)
    {
        StartCoroutine(this.SaveCurentFrame(Offset_X, Offset_Y, width, height));
    }

    /// <summary>
    /// ��������ͷ���ݵ�һ��PNGͼƬ
    /// </summary>
    public void SaveCameraData()
    {
        StartCoroutine(SaveCurentCameraFrame());
    }

    /// <summary>
    /// ��ͣ����
    /// </summary>
    public void PasueCamera()
    {
        if (LocalCamera != null)
        {
            LocalCamera.Pause();
        }
    }

    /// <summary>
    /// ֹͣ����
    /// </summary>
    public void StopCamera()
    {
        if (LocalCamera != null)
        {
            LocalCamera.Stop();
        }
    }

    /// <summary>
    /// ��õ�����Ϣ��������ͷ�޷��򿪵�ʱ����ʾ
    /// </summary>
    /// <returns></returns>
    public string GetDebugMessage()
    {
        return this.DebugMessage;
    }
    #endregion

    #region ˽�з���

    /// <summary>
    /// ������ͷ
    /// </summary>
    /// <returns></returns>
    private IEnumerator OpenLocalCamera(Renderer render)
    {
        //��Ȩ����ʾ����ʹ��
        yield return Application.RequestUserAuthorization(UserAuthorization.WebCam);
        if (Application.HasUserAuthorization(UserAuthorization.WebCam))
        {
            //����豸����
            WebCamDevice[] devices = WebCamTexture.devices;
            print(devices.Length);
            deviceName = devices[0].name;
            LocalCamera = new WebCamTexture(deviceName, ResulotionWidth, ResulotionHeight, Fps);
            render.material.mainTexture = this.LocalCamera;
            LocalCamera.Play();
        }
        else
        {
            DebugMessage = "��Ȩδͨ����������������ͷ����ע���Ƿ�";
            Debug.Log(DebugMessage);
        }
    }

    /// <summary>
    /// ��ָ������Ļ��Χ����ΪPNGͼƬ�����ؽ���
    /// </summary>
    /// <returns></returns>
    private IEnumerator SaveCurentFrame(int Offset_X, int Offset_Y, int width, int height)
    {
        yield return new WaitForEndOfFrame();
        if (LocalCamera != null)
        {
            Texture2D texture = new Texture2D(width, height, TextureFormat.RGB24, true);
            texture.ReadPixels(new Rect(Offset_X, Offset_Y, width, height), 0, 0, false);
            texture.Apply();

            Debug.Log("Save Picture From:" + texture);
            byte[] piexls = texture.EncodeToPNG();
            this.SavePicture(piexls);
            //�������200����
            Thread.Sleep(200);
        }
    }

    /// <summary>
    /// ���浱ǰCamera��ͼ��
    /// </summary>
    /// <returns></returns>
    private IEnumerator SaveCurentCameraFrame()
    {
        yield return new WaitForEndOfFrame();
        if (LocalCamera != null)
        {
            Color32[] data = new Color32[LocalCamera.width * LocalCamera.height];
            data = LocalCamera.GetPixels32();

            Texture2D texture = new Texture2D(LocalCamera.width, LocalCamera.height, TextureFormat.RGBA32, true);
            texture.SetPixels32(data);
            texture.Apply(true);
            byte[] piexls = texture.EncodeToPNG();
            this.SavePicture(piexls);
            //�������200����
            Thread.Sleep(200);
        }
    }

    /// <summary>
    /// ����һ����Ƶ
    /// </summary>
    /// <returns></returns>
    private IEnumerator SaveCameraVideo()
    {
        yield return new WaitForEndOfFrame();
    }

    /// <summary>
    /// ��byte������PngͼƬ��ʽ���浽���ؽ���
    /// </summary>
    /// <param name="piexls"></param>
    private void SavePicture(byte[] piexls)
    {
        string path = Application.dataPath + "/SaveData";
        //����Ŀ¼��Ϣ
        DirectoryInfo dir = new DirectoryInfo(path);
        //���������Ŀ¼�򴴽�Ŀ¼
        if (!dir.Exists)
        {
            Debug.Log("Creat Directory ��" + path);
            dir.Create();
        }
        //�����ļ���Ϣ
        string file = path + "/" + Time.time.ToString().Split('.')[0] + "_" + Time.time.ToString().Split('.')[1] + ".png";
        File.WriteAllBytes(file, piexls);
    }

    /// <summary>
    /// �������˳���ʱ���Զ��ر�����ͷ
    /// </summary>
    void OnApplicationQuit()
    {
        if (this.LocalCamera != null)
        {
            this.LocalCamera.Stop();
        }
        StopAllCoroutines();
    }

    #endregion

}
