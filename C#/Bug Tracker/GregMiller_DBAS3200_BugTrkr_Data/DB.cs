using System;
using System.Collections;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GregMiller_DBAS3200_BugTrkr_Data
{

    public class DB
    {

        public static string ConnectionString
        {
            get {
                string ConnectionString 
                    = ConfigurationManager.ConnectionStrings["BugConnection"].ConnectionString;

                SqlConnectionStringBuilder StringBuilder = new SqlConnectionStringBuilder(ConnectionString);
                StringBuilder.ApplicationName = ApplicationName ?? StringBuilder.ApplicationName;
                StringBuilder.ConnectTimeout = (ConnectionTimeout > 0) ? ConnectionTimeout : StringBuilder.ConnectTimeout;

                return StringBuilder.ToString();
            }
        }

        public static SqlConnection GetSqlConnection()
        {
            SqlConnection Connection = new SqlConnection(ConnectionString);
            Connection.Open();

            return Connection;
        }

        /// <summary>
        /// Overrides the Connection timeout
        /// </summary>
        public static int ConnectionTimeout { get; set; }

        /// <summary>
        /// Property used to override the name of the application
        /// </summary>
        public static string ApplicationName { get; set; }

    }
}
