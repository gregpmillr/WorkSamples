using System;
using System.Collections.Generic;
using System.Data.SqlClient;

namespace GregMiller_DBAS3200_BugTrkr_Data
{
    public class BugLog
    {
        //properties
        BugLog B;
        SqlDataReader Reader;

        public Int32 BugLogID { get; set; }
        public DateTime BugLogDate { get; set; }
        public String BugLogDesc { get; set; }
        public String StatusCodeDesc { get; set; }
        public String UserName { get; set; }

        /// <summary>
        /// Load BugLog data from reader.
        /// </summary>
        /// <param name="Reader">Reader</param>
        public void Load(SqlDataReader Reader)
        {
            BugLogID = Int32.Parse(Reader["BugLogID"].ToString());
            BugLogDate = DateTime.Parse(Reader["BugLogDate"].ToString());
            BugLogDesc = Reader["BugLogDesc"].ToString();
            StatusCodeDesc = Reader["StatusCodeDesc"].ToString();
            UserName = Reader["UserName"].ToString();
        }

        /// <summary>
        /// Load single Status Code ID
        /// </summary>
        /// <param name="Reader">Reader</param>
        /// <param name="StatusCodeID">Status Code ID</param>
        /// <returns></returns>
        public Int32 SingleLoad(SqlDataReader Reader,Int32 StatusCodeID)
        {
            return Int32.Parse(Reader["StatusCodeID"].ToString());
        }

        /// <summary>
        /// Get list of BugLogs from Bug ID
        /// </summary>
        /// <param name="bugID">Bug ID</param>
        /// <returns>List of Bug Logs</returns>
        public List<BugLog> GetList(int bugID)
        {
            List<BugLog> BugLogs = new List<BugLog>();

            using (SqlConnection Connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = Connection.CreateCommand())
                {
                    Command.CommandText = @"GetAllBugLogs";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlParameter Parameter1 = new SqlParameter("bugID", System.Data.SqlDbType.Int);
                    Parameter1.Value = bugID;
                    Command.Parameters.Add(Parameter1);

                    Reader = Command.ExecuteReader();

                    while (Reader.Read())
                    {
                        B = new BugLog();
                        B.Load(Reader);
                        BugLogs.Add(B);
                    }
                }
            }
            return BugLogs;
        }//end GetList


        /// <summary>
        /// Get single bug log from Bug ID
        /// </summary>
        /// <param name="BugID">Bug ID</param>
        /// <returns>Int32</returns>
        public Int32 GetSingleBugLog(int BugID)
        {
            Int32 StatusCodeID=0;
            using (SqlConnection Connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = Connection.CreateCommand())
                {
                    Command.CommandText = @"GetSpecificBugsStatus";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlParameter Parameter1 = new SqlParameter("BugID", System.Data.SqlDbType.Int);
                    Parameter1.Value = BugID;
                    Command.Parameters.Add(Parameter1);

                    Reader = Command.ExecuteReader();

                    while (Reader.Read())
                    {
                        B = new BugLog();
                        StatusCodeID = B.SingleLoad(Reader,StatusCodeID);
                    }
                }
            }
            return StatusCodeID; 
        }//end GetList


        /// <summary>
        /// Get single bug log's description
        /// </summary>
        /// <param name="BugID">Bug ID</param>
        /// <returns>String</returns>
        public String GetSingleBugLogDesc(int BugID)
        {
            String BugLogDesc = " ";
            using (SqlConnection Connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = Connection.CreateCommand())
                {
                    Command.CommandText = @"GetSingleBugLogDesc";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlParameter Parameter1 = new SqlParameter("BugID", System.Data.SqlDbType.Int);
                    Parameter1.Value = BugID;
                    Command.Parameters.Add(Parameter1);

                    Reader = Command.ExecuteReader();

                    while (Reader.Read())
                    {
                        BugLogDesc = Reader["BugLogDesc"].ToString();
                    }
                }
            }
            return BugLogDesc;
        }//end GetList

        /// <summary>
        /// Insert bug log into database
        /// </summary>
        /// <param name="BugLogDate">Date</param>
        /// <param name="StatusCodeID">Status Code ID</param>
        /// <param name="UserID">User ID</param>
        /// <param name="BugLogDesc">Bug Log Description</param>
        /// <param name="BugID">Bug ID</param>
        public void InsertBugLog(DateTime BugLogDate, int StatusCodeID, int UserID, String BugLogDesc,
                                 int BugID)
        {
            using (SqlConnection Connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = Connection.CreateCommand())
                {
                    Command.CommandText = @"InsertBugLog";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlParameter Parameter1 = new SqlParameter("BugLogDate", System.Data.SqlDbType.DateTime);
                    Parameter1.Value = BugLogDate;
                    Command.Parameters.Add(Parameter1);

                    SqlParameter Parameter2 = new SqlParameter("StatusCodeID", System.Data.SqlDbType.Int);
                    Parameter2.Value = StatusCodeID;
                    Command.Parameters.Add(Parameter2);

                    SqlParameter Parameter3 = new SqlParameter("UserID", System.Data.SqlDbType.Int);
                    Parameter3.Value = UserID;
                    Command.Parameters.Add(Parameter3);


                    SqlParameter Parameter4 = new SqlParameter("BugLogDesc", System.Data.SqlDbType.NVarChar, 100);
                    Parameter4.Value = BugLogDesc;
                    Command.Parameters.Add(Parameter4);


                    SqlParameter Parameter5 = new SqlParameter("BugID", System.Data.SqlDbType.Int);
                    Parameter5.Value = BugID;
                    Command.Parameters.Add(Parameter5);

                    Command.ExecuteNonQuery();

                }
            }
        }

    }
}
