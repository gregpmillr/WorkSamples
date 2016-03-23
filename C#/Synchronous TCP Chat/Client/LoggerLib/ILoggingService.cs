using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LoggerLib
{
    /// <summary>
    /// Interface to be implemented by Logger classes.
    /// </summary>
    public interface ILoggingService
    {
        /// <summary>
        /// Log to a text file or other logging mechanism.
        /// </summary>
        /// <param name="message"></param>
        void Log(string message);
    }
}
