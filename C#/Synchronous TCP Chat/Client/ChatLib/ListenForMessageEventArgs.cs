using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ChatLib;

namespace ChatLib
{
    /// <summary>
    /// Add event args listener
    /// </summary>
    public class ListenForMessageEventArgs : EventArgs
    {
        /// <summary>
        /// Constructor for class. Creates object with new message.
        /// </summary>
        /// <param name="message"></param>
        public ListenForMessageEventArgs(string message)
        {
            Message = message;
        }

        /// <summary>
        /// Attach message to object
        /// </summary>
        public string Message { get; set; }




    }
}
