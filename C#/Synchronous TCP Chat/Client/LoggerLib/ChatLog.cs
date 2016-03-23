using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LoggerLib
{
    /// <summary>
    /// ChatLog implements ILoggingService for it's type.
    /// </summary>
    public class ChatLog : ILoggingService
    {
        private string fileName = "log_" + DateTime.Now.ToString("yyyyMMdd") + ".log";

        /// <summary>
        /// Append a message to log file.
        /// </summary>
        /// <param name="message"></param>
        public void Log(string message)
        {
            if (!File.Exists(fileName))
            {
                using (var writer = File.CreateText(fileName))
                {
                    writer.WriteLine(message);
                }
            }
            else
            {
                using (var writer = File.AppendText(fileName))
                {
                    writer.WriteLine(message);
                }
            }

        }


    }
}
