using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using LoggerLib;
using Castle.Core.Logging;
using log4net;
using log4net.Config;
//using Logger;

namespace ChatLib
{
    /// <summary>
    /// Create Client object which implemnts IClient. Must inject logger. 
    /// </summary>
    public class Client : IClient
    {

        //properties
        private ILoggingService Logger;

        TcpClient client;
        NetworkStream stream;
        Byte[] data;
        Int32 port = 1234;
        string response;
        /// <summary>
        /// Listen for message delegate implementation.
        /// </summary>
        public ListenForMessageHandler ListenForMessage;
        string logMessage;
        /// <summary>
        /// Property to check whether client is currently listening.
        /// </summary>
        public bool stopListening = false;

        /// <summary>
        /// Constructor to create instance of logger
        /// </summary>
        /// <param name="logger"></param>
        public Client(ILoggingService logger)
        {
            this.Logger = logger;
        }

        /// <summary>
        /// Connect the client to the server with provided port
        /// </summary>
        /// <param name="server">String</param>
        public bool connect(String server)
        {
            try
            {
                client = new TcpClient(server, port);
                Logger.Log(DateTime.Now + " Connected to server");
                return true;
            }   
            catch (ArgumentNullException e)
            {
                logMessage = e.Message;
                Logger.Log(logMessage);
            }
            catch (SocketException e)
            {}//end try/catch

            return false;

        }//end Connect method

       

        /// <summary>
        /// Send a message from the client to the server, this contains NetworkStream
        /// </summary>
        /// <param name="message">String</param>
        public void sendMessage(String message)
        {
            data = System.Text.Encoding.ASCII.GetBytes(message);
            stream = client.GetStream();
            stream.Write(data, 0, data.Length);
            Logger.Log(DateTime.Now + " Client: " + message);
        }//end sendMessage


        /// <summary>
        /// Receive message from the server into the client
        /// </summary>
        public String receiveMessage()
        {
            String responseData = null;
            if (stream == null && client != null)
            {
                stream = client.GetStream();
            }
            if (stream != null && stream.DataAvailable)
            {
                // Receive the TcpServer.response.
                // Buffer to store the response bytes.
                data = new Byte[256];
                
                responseData = String.Empty;
                Int32 bytes = stream.Read(data, 0, data.Length);
                responseData = System.Text.Encoding.ASCII.GetString(data, 0, bytes);
            }//end if
            if (responseData != null)
            {
                Logger.Log(DateTime.Now + " Server: " + responseData);
            }
            return responseData;
        }


        /// <summary>
        /// Start listening loop for incoming messages.
        /// </summary>
        public void listeningLoop()
        {
            while (!stopListening)
            {
                this.response = receiveMessage();
                if (response != null)
                {
                    //ListenForMessage is actually the UpdateConvo method from Form1, just as a delegate.
                    ListenForMessage(this, new ListenForMessageEventArgs(response));
                }
            }//end while
        }


        /// <summary>
        /// Disconnect NetworkStream and Client
        /// </summary>
        public void disconnect()
        {
            stream.Close();
            client.Close();
            Logger.Log(DateTime.Now + " Disconnected");
        }

        /// <summary>
        /// Return bool to check if client is listening
        /// </summary>
        /// <returns></returns>
        public bool getIfListening()
        {
            return this.stopListening;
        }

        /// <summary>
        /// Set client listening for messages
        /// </summary>
        /// <param name="listening"></param>
        public void setIfListening(bool listening)
        {
            this.stopListening = listening;
        }

        /// <summary>
        /// Uses the UpdateConvo method from Form1 (UpdateConvo passed here)
        /// </summary>
        /// <param name="Handler"></param>
        public void initDataHandler(ListenForMessageHandler Handler)
        {
            this.ListenForMessage += Handler;
        }//End initDataHandler
    }
}


