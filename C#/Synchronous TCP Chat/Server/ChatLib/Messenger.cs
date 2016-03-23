using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ChatLib
{

    public abstract class Messenger
    {
        public Int32 port = 1234;
        public abstract void sendMessage(String message);
        public abstract String receiveMessage();
        public Int32 getPort()
        {
            return port;
        }
    }
}
