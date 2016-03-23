using System;
using System.Windows.Forms;
using ChatLib;
using LoggerLib;
using System.Threading;

namespace ChatApp
{
    /// <summary>
    /// Main form UI class which takes an IClient constructor parameter (dependency injection)
    /// </summary>
    public partial class ClientChatForm : Form
    {
        //properties
        string message;
        IClient client;
        private Thread chatThread;

        /// <summary>
        /// Configures XML and initialized Form
        /// </summary>
        public ClientChatForm()
        {
            log4net.Config.XmlConfigurator.Configure();
            InitializeComponent();
        }

        /// <summary>
        /// Creates instance of IClient which creates instance of ILoggingService
        /// </summary>
        /// <param name="tempClient"></param>
        public ClientChatForm(IClient tempClient) : this()
        {
            this.client = tempClient;
        }

        private void btnSend_Click(object sender, EventArgs e)
        {
            message = textBoxMessage.Text;
            if (client != null)
            {
                client.sendMessage(message);
                textBoxConvo.Text += ">> " + message + Environment.NewLine;
            };
        }

        /// <summary>
        /// Connect to server
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void connectToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (client.connect("127.0.0.1"))
            {
                textBoxConvo.Text += "Connected to server" + Environment.NewLine;
            };

            client.initDataHandler(new ListenForMessageHandler(UpdateConvo)); //new delegate of type UpdateConvo
                                                                              //giving client the UpdateConvo method

            chatThread = new Thread(client.listeningLoop);
            chatThread.Name = "ChatThread";
            chatThread.Start();
            
        }

        /// <summary>
        /// Handler for Delegate
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void UpdateConvo(object sender, ListenForMessageEventArgs e)
        {
            if (textBoxConvo.InvokeRequired)
            {
                MethodInvoker myMethod = new MethodInvoker(
                    delegate() {
                        textBoxConvo.Text += e.Message + Environment.NewLine;
                    }
                );
                textBoxConvo.BeginInvoke(myMethod);
            }
            else
            {
                textBoxConvo.Text += e.Message + Environment.NewLine;  
            }
        }
       

        /// <summary>
        /// Disconnect from server
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void disconnectToolStripMenuItem_Click(object sender, EventArgs e)
        {
            closeForm();
            client.disconnect();
            textBoxConvo.Text += "Disconnected" + Environment.NewLine;
        }

        /// <summary>
        /// exit the program
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            closeForm();
            Application.Exit();
        }

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            closeForm();
        }

        private void closeForm()
        {
            if (chatThread != null && chatThread.IsAlive) {
                client.setIfListening(true);
                chatThread.Join();
            }
        }
    }
}
