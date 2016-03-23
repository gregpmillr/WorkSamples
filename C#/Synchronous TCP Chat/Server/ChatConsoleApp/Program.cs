using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ChatLib;
using System.Net.Sockets;

namespace ChatConsoleApp
{
    class Program
    {
        static void Main(string[] args)
        {
            Chat chat = new Chat();
            chat.start(args);
        }
    }
}