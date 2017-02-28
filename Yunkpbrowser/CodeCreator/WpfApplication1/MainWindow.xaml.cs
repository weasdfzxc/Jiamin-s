using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

using Register;

namespace WpfApplication1
{
    /// <summary>
    /// MainWindow.xaml 的交互逻辑
    /// </summary>
    public partial class MainWindow : Window
    {
        BitCodeCreator c;
        public MainWindow()
        {
            InitializeComponent();
            c = new BitCodeCreator();
            
        }


        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            string pathString = path.Text;
            string contentString = "<x><c>" + content.Text + "</c><l>KJZX</l><t>" + DateTime.Now.ToLongDateString() + "</t></x>";
            try
            {
                c.Encode(contentString, pathString);
                info.Content = "成功生成";
                System.Threading.Thread.Sleep(3000);
                //   info.Content = null;
            }
            catch (Exception ex)
            {
                info.Content = "生成失败，错误为" + ex.ToString();
                System.Threading.Thread.Sleep(3000);
                //  info.Content = null;
            }
        }
    }
}
