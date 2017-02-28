using UnityEngine;
using System.IO;
using System.Collections;
using System.Threading;


/// <summary>
/// 摄像头管理类，负责管理摄像头设备
/// </summary>
public class CameraManager : MonoBehaviour
{
    #region 属性
    public WebCamTexture LocalCamera;

    //有关摄像头的设置
    public int ResulotionWidth = 1024;
    public int ResulotionHeight = 768;
    public int Fps = 30;

    //摄像头的驱动名称
    private string deviceName;
    //错误信息
    private string DebugMessage;
    #endregion

    #region 公开方法

    /// <summary>
    /// 开启摄像头
    /// </summary>
    /// <param name="render"></param>
    public void OpenCamera(Renderer render)
    {
        StartCoroutine(OpenLocalCamera(render));
    }

    /// <summary>
    /// 保存屏幕数据到一张PNG图片
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
    /// 保存摄像头数据到一张PNG图片
    /// </summary>
    public void SaveCameraData()
    {
        StartCoroutine(SaveCurentCameraFrame());
    }

    /// <summary>
    /// 暂停摄像
    /// </summary>
    public void PasueCamera()
    {
        if (LocalCamera != null)
        {
            LocalCamera.Pause();
        }
    }

    /// <summary>
    /// 停止摄像
    /// </summary>
    public void StopCamera()
    {
        if (LocalCamera != null)
        {
            LocalCamera.Stop();
        }
    }

    /// <summary>
    /// 获得调试信息，当摄像头无法打开的时候显示
    /// </summary>
    /// <returns></returns>
    public string GetDebugMessage()
    {
        return this.DebugMessage;
    }
    #endregion

    #region 私有方法

    /// <summary>
    /// 打开摄像头
    /// </summary>
    /// <returns></returns>
    private IEnumerator OpenLocalCamera(Renderer render)
    {
        //授权，表示程序使用
        yield return Application.RequestUserAuthorization(UserAuthorization.WebCam);
        if (Application.HasUserAuthorization(UserAuthorization.WebCam))
        {
            //获得设备数量
            WebCamDevice[] devices = WebCamTexture.devices;
            print(devices.Length);
            deviceName = devices[0].name;
            LocalCamera = new WebCamTexture(deviceName, ResulotionWidth, ResulotionHeight, Fps);
            render.material.mainTexture = this.LocalCamera;
            LocalCamera.Play();
        }
        else
        {
            DebugMessage = "授权未通过，不能启动摄像头，请注意是否";
            Debug.Log(DebugMessage);
        }
    }

    /// <summary>
    /// 将指定的屏幕范围保存为PNG图片到本地介质
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
            //程序挂起200毫秒
            Thread.Sleep(200);
        }
    }

    /// <summary>
    /// 保存当前Camera的图像
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
            //程序挂起200毫秒
            Thread.Sleep(200);
        }
    }

    /// <summary>
    /// 保存一段视频
    /// </summary>
    /// <returns></returns>
    private IEnumerator SaveCameraVideo()
    {
        yield return new WaitForEndOfFrame();
    }

    /// <summary>
    /// 将byte数据以Png图片格式保存到本地介质
    /// </summary>
    /// <param name="piexls"></param>
    private void SavePicture(byte[] piexls)
    {
        string path = Application.dataPath + "/SaveData";
        //创建目录信息
        DirectoryInfo dir = new DirectoryInfo(path);
        //如果不存在目录则创建目录
        if (!dir.Exists)
        {
            Debug.Log("Creat Directory ：" + path);
            dir.Create();
        }
        //建立文件信息
        string file = path + "/" + Time.time.ToString().Split('.')[0] + "_" + Time.time.ToString().Split('.')[1] + ".png";
        File.WriteAllBytes(file, piexls);
    }

    /// <summary>
    /// 当程序退出的时候自动关闭摄像头
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
