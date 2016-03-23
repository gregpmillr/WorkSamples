using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using LoggerLib;

namespace ChatLib
{
    /// <summary>
    /// Interface for Client class
    /// </summary>
    public interface IClient
    {
        /// <summary>
        /// Connect to the server
        /// </summary>
        /// <param name="server"></param>
        /// <returns></returns>
        bool connect(String server);
        /// <summary>
        /// Send string message to networkstream
        /// </summary>
        /// <param name="message"></param>
        void sendMessage(String message);
        /// <summary>
        /// Receive message inside listening loop
        /// </summary>
        /// <returns></returns>
        String receiveMessage();
        /// <summary>
        /// Start listening for messages sent over networkstream
        /// </summary>
        void listeningLoop();
        /// <summary>
        /// Disconnect from server.
        /// </summary>
        void disconnect();
        /// <summary>
        /// Check if client is listening for incoming connection requests.
        /// </summary>
        /// <returns></returns>
        bool getIfListening();
        /// <summary>
        /// Set client listening variable
        /// </summary>
        /// <param name="listening"></param>
        void setIfListening(bool listening);
        /// <summary>
        /// Set data handler for Client class
        /// </summary>
        /// <param name="Handler"></param>
        void initDataHandler(ListenForMessageHandler Handler);

    }
}
