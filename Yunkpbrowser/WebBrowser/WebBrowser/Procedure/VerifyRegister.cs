using System;
using Microsoft.Win32;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace WebBrowser.Procedure
{
    class VerifyRegister : AProcedure
    {
        bool activated;
        override public void Process()
        {
            if (CheckIfActivate())
                Continue();
            else if (activated)
                Continue();
        }
        bool CheckIfActivate()
        {
            RegistryKey local = Registry.LocalMachine;
            RegistryKey software = local.OpenSubKey("SOFTWARE");
            RegistryKey kjzx = software.OpenSubKey("KJZX");
            if (kjzx == null)
            {
                OpenActivator();
                return false;
            }
            RegistryKey webbrowser = kjzx.OpenSubKey("WebBrowser");
            if (webbrowser == null)
            {
                OpenActivator();
                return false;
            }
            string key = webbrowser.GetValue("KEY").ToString();
            if (key != null && key == Helper.GetID())
                return true;
            else
            {
                OpenActivator();
                return false;
            }
        }
        void Activated()
        {
            activated = true;
        }
        void OpenActivator()
        {
            var ac = new Activator();
            ac.Confirmed += Activated;
            Application.Run(ac);
        }
    }
}
