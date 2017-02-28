using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using webbrowser;

namespace WebBrowser.Procedure
{
    class LaunchBrowser : AProcedure
    {
        string url = "http://www.yunkp.com";
        override public void Process()
        {
            web webs = new web(url);
            webs.TopMost = true;
            Application.Run(webs);
        }
    }
}
