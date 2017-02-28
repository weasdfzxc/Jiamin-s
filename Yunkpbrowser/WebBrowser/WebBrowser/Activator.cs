using System;
using Microsoft.Win32;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Xml;
using System.IO;

namespace WebBrowser
{
    public partial class Activator : Form
    {
        public delegate void Finished();
        public event Finished Confirmed;
        public Activator()
        {
            InitializeComponent();
        }
        //取消
        private void Cancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }
        //确定
        private void Confirm_Click(object sender, EventArgs e)
        {
            if (radio1.Checked)
            {
                StringBuilder ls = new StringBuilder();
                ls.AppendLine("您的序列号已复制到剪贴板：");
                string format = Helper.GetFormatID();
                try
                {
                    Clipboard.SetText(format, TextDataFormat.Text);
                }
                catch(Exception ec)
                {
                    MessageBox.Show("对不起，无法访问剪贴板，请以管理员身份运行。", "错误", MessageBoxButtons.OK,
                     MessageBoxIcon.Error);
                }
                ls.AppendLine("        " + format);
                ls.AppendLine("该序列号将作为您注册时所必要的凭证，请保管好并在注册时准确无误的提供给我们。");
                MessageBox.Show(ls.ToString(),"注册提示");
            }
            else if (radio2.Checked)
                if (openfile.ShowDialog() == DialogResult.OK)
                {
                    if (Activate())
                    {
                        this.Close();
                        Confirmed();
                    }
                }
        }
        //我想知道怎样注册
        private void radio1_CheckedChanged(object sender, EventArgs e)
        {
            confirm.Enabled = true;
        }
        //我已经有了激活码
        private void radio2_CheckedChanged(object sender, EventArgs e)
        {
            confirm.Enabled = true;
        }
        //激活
        private bool Activate()
        {
            var barcode = new Register.BitCodeCreator();
            string code = "", label = "", time = "";
            try
            {
                string content = barcode.Decode(openfile.FileName);
                XmlDocument doc = new XmlDocument();
                doc.LoadXml(content);
                code = doc.DocumentElement.SelectSingleNode("c").InnerText;
                label = doc.DocumentElement.SelectSingleNode("l").InnerText;
                time = doc.DocumentElement.SelectSingleNode("t").InnerText;
            }
            catch (Exception e)
            {
                MessageBox.Show("对不起，您的激活码文件存在问题，无法激活。", "错误", MessageBoxButtons.OK,
                     MessageBoxIcon.Error);
                return false;
            }
            try
            {
                string[] codesp = code.Split('-');
                code = "";
                for (int i = 0; i < codesp.Length; ++i)
                    code += codesp[i];
                if (code != Helper.GetID()) throw new Exception();
                if (label != "KJZX") throw new Exception();
            }
            catch (Exception e)
            {
                MessageBox.Show("对不起，您的激活码不正确，因此无法激活。", "错误", MessageBoxButtons.OK,
                     MessageBoxIcon.Error);
                return false;
            }
            try
            {
                RegistryKey local = Registry.LocalMachine;
                RegistryKey kjzx = local.CreateSubKey("SOFTWARE\\KJZX");
                RegistryKey webbrowser = kjzx.CreateSubKey("WebBrowser");
                webbrowser.SetValue("KEY", code);
                webbrowser.SetValue("TIME", time);
                MessageBox.Show("激活成功！您可继续使用本产品。", "提示");
                return true;
            }
            catch (Exception e)
            {
                MessageBox.Show("注册表访问失败，激活信息无法写入，请以管理员身份运行。", "错误", MessageBoxButtons.OK,
                     MessageBoxIcon.Error);
            }
            return false;
        }

  
    }
}
