using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace webbrowser
{
    public partial class web : Form
    {
        string home;
        AutoSizeForm asc = new AutoSizeForm();
        int[] X = new int[9];
        int[] Y = new int[9];
        int hold = 0;
        bool first = true;

        public web(string url)
        {
            InitializeComponent();
            home = url;
        }

        private void web_Load(object sender, EventArgs e)
        {
            asc.controllInitializeSize(this);
            webKitBrowser1.Navigate(home);
            //webKitBrowser1.ScriptErrorsSuppressed = true;
            webKitBrowser1.IsWebBrowserContextMenuEnabled = false;

            if (this.WindowState == FormWindowState.Maximized)
            {
                this.WindowState = FormWindowState.Normal;
            }
            else
            {
                this.FormBorderStyle = FormBorderStyle.None;
                this.WindowState = FormWindowState.Maximized;
            }
            timer3.Interval = 1000;
            timer3.Start();
        }

        private void web_SizeChanged(object sender, EventArgs e)
        {
            asc.controlAutoSize(this);
            animal();
            animalxy();
        }

        private void webBrowser_NewWindow(object sender, CancelEventArgs e)
        {
            e.Cancel = true;
        }

        //private void webBrowser_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        //{
        //    //将所有的链接的目标，指向本窗体
        //    foreach (HtmlElement archor in this.webKitBrowser1.Document)
        //    {
        //        archor.SetAttribute("target", "_self");
        //    }

        //    //将所有的FORM的提交目标，指向本窗体
        //    foreach (HtmlElement form in this.webKitBrowser1.Document.Forms)
        //    {
        //        form.SetAttribute("target", "_self");
        //    }
        //}

        private void pictureBox1_MouseEnter(object sender, EventArgs e)
        {
            pictureBox1.Image = global::WebBrowser.Properties.Resources.backh;
        }

        private void pictureBox1_MouseUp(object sender, MouseEventArgs e)
        {
            webKitBrowser1.GoBack();
            pictureBox9.SetBounds(-10, -10, 0, 0);
        }

        private void pictureBox1_MouseLeave(object sender, EventArgs e)
        {
            pictureBox1.Image = global::WebBrowser.Properties.Resources.back;
        }

        private void pictureBox1_MouseDown(object sender, MouseEventArgs e)
        {
            pictureBox1.Image = global::WebBrowser.Properties.Resources.backp;
        }

        private void pictureBox2_MouseEnter(object sender, EventArgs e)
        {
            pictureBox2.Image = global::WebBrowser.Properties.Resources.forwardh;
        }

        private void pictureBox2_MouseLeave(object sender, EventArgs e)
        {
            pictureBox2.Image = global::WebBrowser.Properties.Resources.forward;
        }

        private void pictureBox2_MouseDown(object sender, MouseEventArgs e)
        {
            pictureBox2.Image = global::WebBrowser.Properties.Resources.forwardp;
        }

        private void pictureBox2_MouseUp(object sender, MouseEventArgs e)
        {
            webKitBrowser1.GoForward();
        }

        private void pictureBox3_MouseDown(object sender, MouseEventArgs e)
        {
            pictureBox3.Image = global::WebBrowser.Properties.Resources.reloadp;
        }

        private void pictureBox3_MouseEnter(object sender, EventArgs e)
        {
            pictureBox3.Image = global::WebBrowser.Properties.Resources.reloadh;
        }

        private void pictureBox3_MouseLeave(object sender, EventArgs e)
        {
            pictureBox3.Image = global::WebBrowser.Properties.Resources.reload;
        }

        private void pictureBox3_MouseUp(object sender, MouseEventArgs e)
        {
            webKitBrowser1.Refresh();
        }

        private void pictureBox4_MouseEnter(object sender, EventArgs e)
        {
            pictureBox4.Image = global::WebBrowser.Properties.Resources.homeh;
        }

        private void pictureBox4_MouseDown(object sender, MouseEventArgs e)
        {
            pictureBox4.Image = global::WebBrowser.Properties.Resources.homep;
        }

        private void pictureBox4_MouseLeave(object sender, EventArgs e)
        {
            pictureBox4.Image = global::WebBrowser.Properties.Resources.home;
        }

        private void pictureBox4_MouseUp(object sender, MouseEventArgs e)
        {
            webKitBrowser1.Navigate(home);
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            if (pictureBox5.Location.Y <= this.Size.Height)
            {
                Y[0] += 10;
                Y[1] += 10;
                Y[2] += 10;
                Y[3] += 10;
                Y[4] += 10;
                //Y[5] += 10;
                Y[6] += 15;
                Y[7] += 10;
                Y[8] += 10;
                pictureBox1.SetBounds(X[0], pictureBox1.Location.Y, pictureBox1.Size.Width, pictureBox1.Size.Height);
                pictureBox2.SetBounds(X[1], pictureBox2.Location.Y, pictureBox2.Size.Width, pictureBox2.Size.Height);
                pictureBox3.SetBounds(X[2], pictureBox3.Location.Y, pictureBox3.Size.Width, pictureBox3.Size.Height);
                pictureBox4.SetBounds(X[3], pictureBox4.Location.Y, pictureBox4.Size.Width, pictureBox4.Size.Height);
                pictureBox5.SetBounds(X[4], pictureBox5.Location.Y, pictureBox5.Size.Width, pictureBox5.Size.Height);
                pictureBox6.SetBounds(X[5], pictureBox6.Location.Y, pictureBox6.Size.Width, pictureBox6.Size.Height);
                //pictureBox7.SetBounds(Y[6], pictureBox7.Location.Y, pictureBox7.Size.Width, pictureBox7.Size.Height); 
                pictureBox8.SetBounds(X[7], pictureBox8.Location.Y, pictureBox8.Size.Width, pictureBox8.Size.Height);
                pictureBox9.SetBounds(X[8], pictureBox9.Location.Y, pictureBox9.Size.Width, pictureBox9.Size.Height);
            }
            else
            {
                timer1.Stop();
                //animalxy();
            }
        }

        private void animal() 
        {
            X[0] = pictureBox1.Location.X;
            X[1] = pictureBox2.Location.X;
            X[2] = pictureBox3.Location.X;
            X[3] = pictureBox4.Location.X;
            X[4] = pictureBox5.Location.X;
            X[5] = pictureBox6.Location.X;
            //X[6] = pictureBox7.Location.X;
            X[7] = pictureBox8.Location.X;
            X[8] = pictureBox9.Location.X;
        }
        private void animalxy()
        {
            Y[0] = X[0];
            Y[1] = X[1];
            Y[2] = X[2];
            Y[3] = X[3];
            Y[4] = X[4];
            Y[5] = X[5];
            //Y[6] = X[6];
            Y[7] = X[7];
            Y[8] = X[8];
        }

        private void timer2_Tick(object sender, EventArgs e)
        {
            if (pictureBox5.Location.Y-10 >= X[4])
            {
                Y[0] -= 10;
                Y[1] -= 10;
                Y[2] -= 10;
                Y[3] -= 10;
                Y[4] -= 10;
                Y[5] -= 10;
                //Y[6] -= 15;
                Y[7] -= 10;
                Y[8] -= 10;
                pictureBox1.SetBounds(X[0], pictureBox1.Location.Y, pictureBox1.Size.Width, pictureBox1.Size.Height);
                pictureBox2.SetBounds(X[1], pictureBox2.Location.Y, pictureBox2.Size.Width, pictureBox2.Size.Height);
                pictureBox3.SetBounds(X[2], pictureBox3.Location.Y, pictureBox3.Size.Width, pictureBox3.Size.Height);
                pictureBox4.SetBounds(X[3], pictureBox4.Location.Y, pictureBox4.Size.Width, pictureBox4.Size.Height);
                pictureBox5.SetBounds(X[4], pictureBox5.Location.Y, pictureBox5.Size.Width, pictureBox5.Size.Height);
                pictureBox6.SetBounds(X[5], pictureBox6.Location.Y, pictureBox6.Size.Width, pictureBox6.Size.Height);
                //pictureBox7.SetBounds(Y[6], pictureBox7.Location.Y, pictureBox7.Size.Width, pictureBox7.Size.Height);
                pictureBox8.SetBounds(X[7], pictureBox8.Location.Y, pictureBox8.Size.Width, pictureBox8.Size.Height);
                pictureBox9.SetBounds(X[8], pictureBox9.Location.Y, pictureBox9.Size.Width, pictureBox9.Size.Height);
            }
            else
            {
                timer2.Stop();
                //animalxy();
                timer3.Interval = 8000;
                timer3.Start();
            }
        }

        private void pictureBox7_MouseDown(object sender, MouseEventArgs e)
        {
            timer2.Interval = 25;
            timer2.Start();
        }

        private void timer3_Tick(object sender, EventArgs e)
        {
            if (hold <= 3)
            {
                hold++;
               
            }
            else
            {
                timer3.Stop();
                timer1.Interval = 35;
                timer1.Start();
                hold = 0;
            }
        }

        private void webBrowser_Navigated(object sender, WebBrowserNavigatedEventArgs e)
        {
            if (!first)
            {
                pictureBox8.SetBounds(-10, -10, 0, 0);
            }
            if (first)
            {
                first = false;
            }
        }
    }
}
