using System.Collections;
using System;
using System.Collections.Generic;
using System.Text;

namespace TCPNetwork
{
    class HT
    {
        public static byte[] EnCry(byte[] data)
        {
            byte[] bytes = new byte[4];
            System.Security.Cryptography.RNGCryptoServiceProvider rng =
                new System.Security.Cryptography.RNGCryptoServiceProvider();
            rng.GetBytes(bytes);
            Random a = new Random(BitConverter.ToInt32(bytes, 0));
            byte b, c;
            b = (byte)a.Next(256);
            c = (byte)a.Next(256);
            byte[] d = new byte[data.Length + 2];
            d[0] = (byte)(~b);
            for (int i = 0; i < data.Length; i++) d[i + 1] = data[i];
            d[data.Length + 1] = (byte)(~c);
            byte e = (byte)((b) ^ (c));
            for (int i = 1; i < data.Length + 1; i++) d[i] = (byte)(e ^ d[i]);
            for (int i = 0; i < d.Length; i++)
            {
                if (i + 1 == d.Length) break;
                d[i + 1] = (byte)(d[i] ^ d[i + 1]);
            }
            return d;
        }
        public static byte[] DeCry(byte[] data)
        {
            byte temp = 0, temp2 = 0;
            for (int i = 0; i < data.Length; i++)
            {
                if (i + 1 == data.Length) break;
                if (i == 0)
                {
                    temp = data[i + 1];
                    data[i + 1] = (byte)(data[i] ^ data[i + 1]);
                }
                else if (i % 2 != 0)
                {
                    temp2 = data[i + 1];
                    data[i + 1] = (byte)(temp ^ data[i + 1]);
                }
                else if (i % 2 == 0)
                {
                    temp = data[i + 1];
                    data[i + 1] = (byte)(temp2 ^ data[i + 1]);
                }
            }
            byte a, b, c;
            a = (byte)(~data[0]);
            b = (byte)(~data[data.Length - 1]);
            c = (byte)((a) ^ (b));
            byte[] d = new byte[data.Length - 2];
            for (int i = 1; i < data.Length - 1; i++) d[i - 1] = (byte)(c ^ data[i]);
            return d;
        }
    }
}
