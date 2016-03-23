using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.Net.Sockets;

using System.Threading.Tasks;

namespace ChatLib
{
    public class Server : Messenger
    {

        IPAddress localAddr;
        TcpListener server = null;
        Byte[] bytes;
        TcpClient client;
        NetworkStream stream;
        String address = "127.0.0.1";

        /// <summary>
        /// Start the server.
        /// </summary>
        public void startServer()
        {
            try
            {
                localAddr = IPAddress.Parse(address);

                // TcpListener server = new TcpListener(port);
                server = new TcpListener(localAddr, port);

                // Start listening for client requests.
                server.Start();

                // Buffer for reading data
                bytes = new Byte[256];
            }
            catch (SocketException e)
            {
                Console.WriteLine("SocketException: {0}", e);
            }//end try/catch
        }//end begin


        /// <summary>
        /// send message from server to client
        /// </summary>
        /// <param name="data">string</param>
        public override void sendMessage(string message)
        {
            byte[] msg = System.Text.Encoding.ASCII.GetBytes(message);

            // Send back a response.
            stream.Write(msg, 0, msg.Length);
        }//end sendMessage


        /// <summary>
        /// Receive a message from the server
        /// </summary>
        public override String receiveMessage()
        {
            String message = null;
            // Get a stream object for reading and writing
            if (stream == null) { stream = client.GetStream(); }

            if (client != null && stream.DataAvailable)
            {
                int i;
                //Loop to receive all the data sent by the client.
                if ((i = stream.Read(bytes, 0, bytes.Length)) != 0)
                {
                    //Translate data bytes to a ASCII string.
                    message = System.Text.Encoding.ASCII.GetString(bytes, 0, i);
                    return message;
                }
            }
            return message;
        }//end receiveMessage

        
        /// <summary>
        /// Listen for a connection from the client
        /// </summary>
        /// <returns>Boolean</returns>
        public bool startListening()
        {
            // Enter the listening loop.
            while (true)
            {
                client = server.AcceptTcpClient();
                return true;
            }//end while
        }//end startListening method

    }
}
