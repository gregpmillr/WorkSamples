using Microsoft.Practices.EnterpriseLibrary.Logging;
using Microsoft.Practices.EnterpriseLibrary.Logging.Formatters;
using Microsoft.Practices.EnterpriseLibrary.Logging.TraceListeners;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LoggerLib
{
        /// <summary>
        /// EnterpriseLogger implements ILoggingService, so is of type ILoggingService
        /// </summary>
        public class EnterpriseLogger : ILoggingService
        {
            //create formatter for document
            static TextFormatter briefFormatter = new TextFormatter();//Sets format for text files
            //trace listener to check where file is located
            FlatFileTraceListener flatFileTraceListener = new FlatFileTraceListener(
              @"C:\Temp\FlatFile.log",
              "----------------------------------------",
              "----------------------------------------",
              briefFormatter);

            /// <summary>
            /// Append/create to log files
            /// </summary>
            /// <param name="message"></param>
            public void Log(string message)
            {
                var config = new LoggingConfiguration();//Build config object

                config.AddLogSource("DiskFiles", System.Diagnostics.SourceLevels.All, true)
                  .AddTraceListener(flatFileTraceListener);

                LogWriter defaultWriter = new LogWriter(config);

                if (defaultWriter.IsLoggingEnabled())//Check to see if config allows logging
                {
                    defaultWriter.Write(message);//Write 
                }
                else
                {
                    Console.WriteLine("Logging is disabled in the configuration.");//Default message
                }//End if

        }


        }
    }
