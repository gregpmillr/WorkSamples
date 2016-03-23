using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using ChatLib;

namespace ChatConsoleApp
{
    public class Chat
    {

        //properties
        private String response;
        Client client;
        String ipAddress = "127.0.0.1";

        /// <summary>
        /// Start and connect server to client
        /// </summary>
        /// <param name="args">Takes in string args to determine if server or client</param>
        public void start(string[] args)
        {
            if (args.Length == 0)
            {
                client = new Client();
                if (client.connect(ipAddress))
                {
                    Console.WriteLine("Connected to the server!");
                }
                listeningLoop(client);
            }//end if
            else if (args[0] == "-server")
            {
                Server server = new Server();
                Console.WriteLine(Environment.NewLine + "Server started on port " + server.getPort() + Environment.NewLine);
                server.startServer();
                Console.Write("Waiting for a client connection... " + Environment.NewLine);
                if (server.startListening())
                {
                    Console.WriteLine("Client connected!");
                }//end if
                listeningLoop(server);
            }//end if
        }//end start method


        /// <summary>
        /// Do listening loop waiting for response or keystrokes
        /// </summary>
        /// <param name="mode">Takes in server of client to listen for</param>
        public void listeningLoop(Messenger mode)
        {
            while (true)
            {
                this.response = mode.receiveMessage();
                if (response != null)
                {
                    Console.WriteLine(response);
                }
                if (Console.KeyAvailable)
                {
                    ConsoleKeyInfo keyInfo = Console.ReadKey(true);
                    if (keyInfo.Key == ConsoleKey.I)
                    {
                        Console.Write(">>");
                        String message = Console.ReadLine();
                        if (message == "quit")
                        {
                            break;
                        }
                        mode.sendMessage(message);
                    }
                }
            }//end while
        }
    }//end class
}//end namespace
