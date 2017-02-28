using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace WebBrowser
{
    class AProcedure
    {
        public AProcedure Next;
        public virtual void Process() { }
        public void Continue()
        {
            if (Next != null)
                Next.Process();
        }
    }
}
