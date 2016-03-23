using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;



namespace ChatLib
{
    public class Client : Messenger
    {
        TcpClient client;
        NetworkStream stream;
        Byte[] data;

        /// <summary>
        /// Connect the client to the server with provided port: 13000
        /// </summary>
        /// <param name="server">String</param>
        public bool connect(String server)
        {
            try
            {
                // Note, for this client to work you need to have a TcpServer 
                client = new TcpClient(server, port);
                return true;
            }
            catch (ArgumentNullException e)
            {
                Console.WriteLine("ArgumentNullException: {0}", e);
            }
            catch (SocketException e)
            {
                Console.WriteLine("SocketException: {0}", e);
            }//end try/catch
            return false;
        }//end Connect method
        

        /// <summary>
        /// Send a message from the client to the server, this contains NetworkStream
        /// </summary>
        /// <param name="Message">String</param>
        public override void sendMessage(String Message)
        {
            // Translate the passed message into ASCII and store it as a Byte array.
            data = System.Text.Encoding.ASCII.GetBytes(Message);
            stream = client.GetStream();
            stream.Write(data, 0, data.Length);
        }//end sendMessage


        /// <summary>
        /// Receive message from the server into the client
        /// </summary>
        public override String receiveMessage()
        {
            String responseData = null;
            if (stream == null && client != null)
            {
                stream = client.GetStream();
            }
            if (stream != null && stream.DataAvailable)
            {
                // Buffer to store the response bytes.
                data = new Byte[256];
                // ASCII representation.
                responseData = String.Empty;
                // Read TcpServer response bytes.
                Int32 bytes = stream.Read(data, 0, data.Length);
                responseData = System.Text.Encoding.ASCII.GetString(data, 0, bytes);
                return responseData;
            }//end if
            return responseData;
        }//end receive message
        
    }
}

