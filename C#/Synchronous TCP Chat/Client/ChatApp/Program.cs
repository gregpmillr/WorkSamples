using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using ChatLib;
using Microsoft.Practices.Unity;
//using LoggerLib;
using Castle.MicroKernel.Registration;
using Castle.Windsor;
using Castle.Core.Logging;  
using LoggerLib;

[assembly: log4net.Config.XmlConfigurator(Watch=true)]

namespace ChatApp
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        public static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            /*---------------------- Windsor --------------------- */
            //var container = new WindsorContainer();
            //container.Register(Component.For<Form1>());
            //container.Register(Component.For<ILoggingService>().ImplementedBy<NewLogger>());
            //container.Register(Component.For<IClient>().ImplementedBy<Client>());
            //Application.Run(container.Resolve<Form1>());

            /*---------------------- Unity --------------------- */
            UnityContainer container = new UnityContainer();
            container.RegisterType<ClientChatForm>();
            container.RegisterType<ILoggingService, EnterpriseLogger>();
            container.RegisterType<IClient, Client>();
            Application.Run(container.Resolve<ClientChatForm>());
        }
    }
}
