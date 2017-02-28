using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;

namespace WebBrowser
{
    static class Program
    {
        /// <summary>
        /// 应用程序的主入口点。
        /// </summary>
        [STAThread]
        static void Main()
        {
            Helper.RegiditWriter();
            //设定处理流程
            var verifyregister = new Procedure.VerifyRegister();
            var checkupdate = new Procedure.CheckUpdate();
            var launchbrowser = new Procedure.LaunchBrowser();
            verifyregister.Next = checkupdate;
            checkupdate.Next = launchbrowser;
            //运行处理流程
            verifyregister.Process();
        }
    }
}
