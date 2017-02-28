using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Management;
using System.Runtime.InteropServices;
using System.IO;
using System.Diagnostics;

namespace WebBrowser
{
    class Helper
    {
        public static void RegiditWriter()
        {
            String version;
            using (Microsoft.Win32.RegistryKey versionKey = Microsoft.Win32.Registry.LocalMachine.OpenSubKey(@"Software\Microsoft\Internet Explorer"))
            {
                version = versionKey.GetValue("Version").ToString();
            }
            Console.WriteLine(version.Substring(0, 2));
            if (version.Substring(0, 2).Equals("11"))
            {
                String regPath = "ie11.reg";
                if (File.Exists(regPath))
                {
                    regPath = @"""" + regPath + @"""";
                    Process.Start("regedit", string.Format(" /s {0}", regPath));
                    Console.WriteLine("ie11");
                }

            }
            if (version.Substring(0, 2).Equals("10"))
            {
                String regPath = "ie10.reg";
                if (File.Exists(regPath))
                {
                    regPath = @"""" + regPath + @"""";
                    Process.Start("regedit", string.Format(" /s {0}", regPath));
                }
                Console.WriteLine("ie10");
            }
            if (version.Substring(0, 2).Equals("9.")) 
            {
                String regPath = "ie9.reg";
                if (File.Exists(regPath))
                {
                    regPath = @"""" + regPath + @"""";
                    Process.Start("regedit", string.Format(" /s {0}", regPath));
                }
                Console.WriteLine("ie9");
            }
            if (version.Substring(0, 2).Equals("8."))
            {
                String regPath = "ie8.reg";
                if (File.Exists(regPath))
                {
                    regPath = @"""" + regPath + @"""";
                    Process.Start("regedit", string.Format(" /s {0}", regPath));
                }
                Console.WriteLine("ie8");
            }
        }
        public static String GetFormatID()
        {
            string id = GetID();
            for (int i = 1; i <= 3; ++i)
                id = id.Insert(i * 5 + (i - 1), "-");
            return id;
        }
        public static String GetID()
        {
            string cpuid = GetCPUID();
            string[] mac = GetMAC().Split(':');
            string nid = "";
            for (int i = 0; i < cpuid.Length; ++i)
            {
                if (i >= 0 && i <= 1) continue;
                if (i >= 8 && i <= 11) continue;
                nid += cpuid[i];
            }
            string nmac = "";
            for (int i = 0; i < mac.Length; ++i)
            {
                byte[] e = ASCIIEncoding.ASCII.GetBytes(mac[i]);
                for (int k = 0; k < e.Length; ++k)
                    if (e[k] >= 65)
                        e[k] += 5;
                    else if (e[k] > 49)
                        e[k]--;
                nmac += ASCIIEncoding.ASCII.GetString(e);
            }
            byte[] n = ASCIIEncoding.ASCII.GetBytes(nid);
            for (int i = 0; i < n.Length; ++i)
                if (n[i] >= 65)
                    n[i] += 2;
                else if (n[i] < 57)
                    n[i]++;
            nid = ASCIIEncoding.ASCII.GetString(n);
            string nnid = "";
            for (int i = 0; i < nid.Length; ++i)
            {
                nnid += nmac[i];
                nnid += nid[i];
            }

            return nnid;
        }
        static String GetCPUID()
        {
            try
            {

                ManagementClass mc = new ManagementClass("Win32_Processor");
                ManagementObjectCollection moc = mc.GetInstances();
                String strCpuID = null;
                foreach (ManagementObject mo in moc)
                {
                    strCpuID = mo.Properties["ProcessorId"].Value.ToString();
                    break;
                }
                return strCpuID;
            }
            catch
            {
                return "";
            }
        }
        static String GetMAC()
        {
            try
            {
                string MoAddress = "";
                ManagementClass mc = new ManagementClass("Win32_NetworkAdapterConfiguration");
                ManagementObjectCollection moc = mc.GetInstances();
                foreach (ManagementObject mo in moc)
                {
                    if ((bool)mo["IPEnabled"] == true)
                        MoAddress = mo["MacAddress"].ToString();
                    mo.Dispose();
                }
                return MoAddress;
            }
            catch
            {
                return "";
            }
        }
    }
}
