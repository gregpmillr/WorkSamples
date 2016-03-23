using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;

namespace GregMiller_DBAS3200_BugTrkr_Data
{
    public class Bug
    {
        //properties
        public Int32 BugID { get; set; } = 0;
        public Int32 AppID { get; set; } = 0;
        public Int32 UserID { get; set; } = 0;
        public Int32? BugSignOff { get; set; } = 0;
        public DateTime? BugDate { get; set; } = null;
        public String BugDesc { get; set; } = "Add New";
        public String BugDetails { get; set; } = "New Details";
        public String RepSteps { get; set; } = "New RepSteps";
        public DateTime? FixDate { get; set; } = null;
        public Int32 StatusCodeID { get; set; } = 0;

        public List<int> BugLogIDs = new List<int>(); 
        
        /// <summary>
        /// Load Bug from Reader
        /// </summary>
        /// <param name="Reader">Reader</param>
        public void Load(SqlDataReader Reader)
        {
            BugID = Int32.Parse(Reader["BugID"].ToString());
            AppID = Int32.Parse(Reader["AppID"].ToString());
            UserID = Int32.Parse(Reader["UserID"].ToString());
            if (!Reader.IsDBNull(Reader.GetOrdinal("BugSignOff")))
            {
                BugSignOff = Reader.GetInt32(Reader.GetOrdinal("BugSignOff"));
            }
            else
            {
                BugSignOff = null;
            }
            BugDate = DateTime.Parse(Reader["BugDate"].ToString());
            BugDesc = Reader["BugDesc"].ToString();
            BugDetails = Reader["BugDetails"].ToString();
            RepSteps = Reader["RepSteps"].ToString();
            if (!Reader.IsDBNull(Reader.GetOrdinal("FixDate")))
            {
                FixDate = Reader.GetDateTime(Reader.GetOrdinal("FixDate"));
            }
            else
            {
                FixDate = null;
            }//end if/else
        }//end load


        /// <summary>
        /// Load current status of a bug from reader.
        /// </summary>
        /// <param name="Reader">Reader</param>
        public void LoadStatusBugs(SqlDataReader Reader)
        {
            BugID = Int32.Parse(Reader["BugID"].ToString());
            AppID = Int32.Parse(Reader["AppID"].ToString());
            UserID = Int32.Parse(Reader["UserID"].ToString());
            if (!Reader.IsDBNull(Reader.GetOrdinal("BugSignOff")))
            {
                BugSignOff = Reader.GetInt32(Reader.GetOrdinal("BugSignOff"));
            }
            else
            {
                BugSignOff = null;
            }//end if/else
            BugDate = DateTime.Parse(Reader["BugDate"].ToString());
            BugDesc = Reader["BugDesc"].ToString();
            BugDetails = Reader["BugDetails"].ToString();
            RepSteps = Reader["RepSteps"].ToString();
            if (!Reader.IsDBNull(Reader.GetOrdinal("FixDate")))
            {
                FixDate = Reader.GetDateTime(Reader.GetOrdinal("FixDate"));
            }
            else
            {
                FixDate = null;
            }//end if/else
            StatusCodeID = Int32.Parse(Reader["StatusCodeID"].ToString());
        }//end LoadStatusBugs

        /// <summary>
        /// Get list of bugs from AppID
        /// </summary>
        /// <param name="AppID">App ID</param>
        /// <returns>List of Bugs</returns>
        public List<Bug> GetList(int AppID)
        {
            List<Bug> Bugs = new List<Bug>();

            using (SqlConnection connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = connection.CreateCommand())
                {
                    Command.CommandText = @"GetAllBugs";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlParameter Parameter1 = new SqlParameter("appID", System.Data.SqlDbType.Int);
                    Parameter1.Value = AppID;
                    Command.Parameters.Add(Parameter1);

                    SqlDataReader Reader = Command.ExecuteReader();

                    while (Reader.Read())
                    {
                        Bug B = new Bug();
                        B.Load(Reader);
                        Bugs.Add(B);
                    }//end while
                }
            }//end outer using
            Bug DummyBug = new Bug();
            Bugs.Insert(0, DummyBug);
            return Bugs;
        }//end GetList

        /// <summary>
        /// Set BugLogID list from app id and statusCodeID
        /// </summary>
        /// <param name="AppID">AppID</param>
        /// <param name="StatusCodeID">Status Code ID</param>
        /// <returns>List of bugs</returns>
        public List<Bug> SetBugLogIDList(int AppID,int StatusCodeID)
        {
            using (SqlConnection Connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = Connection.CreateCommand())
                {
                    Command.CommandText = @"GetBugsFilteredByAppID";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlParameter Parameter1 = new SqlParameter("@ApplicationID", System.Data.SqlDbType.Int);
                    Parameter1.Value = AppID;
                    Command.Parameters.Add(Parameter1);

                    SqlDataReader Reader = Command.ExecuteReader();

                    while (Reader.Read())
                    {
                       BugLogIDs.Add(Int32.Parse(Reader["BugLogID"].ToString()));
                    }//end while
                }
            }
            List<Bug> Bugs = GetBugsFromBugLogID(StatusCodeID);
            Bug DummyBug = new Bug();
            Bugs.Insert(0, DummyBug);
            return Bugs;
        }//end SetBugLogIDList

        /// <summary>
        /// Get bugs from a bug log ID
        /// </summary>
        /// <param name="StatusCodeID">Status Code ID</param>
        /// <returns>List of Bugs</returns>
        public List<Bug> GetBugsFromBugLogID(int StatusCodeID)
        {
            List<Bug> Bugs = new List<Bug>();

            //for every BugLogID in the list, get it's bug
            foreach (int BugLogID in BugLogIDs)
            {
                using (SqlConnection connection = DB.GetSqlConnection())
                {
                    using (SqlCommand Command = connection.CreateCommand())
                    {
                        Command.CommandText = @"GetBugFromBugLogID";
                        Command.CommandType = System.Data.CommandType.StoredProcedure;

                        SqlParameter Parameter1 = new SqlParameter("@BugLogID", System.Data.SqlDbType.Int);
                        Parameter1.Value = BugLogID;
                        Command.Parameters.Add(Parameter1);

                        SqlDataReader Reader = Command.ExecuteReader();

                        while (Reader.Read())
                        {
                            if (Int32.Parse(Reader["StatusCodeID"].ToString()) == StatusCodeID)
                            {
                                Bug B = new Bug();
                                B.LoadStatusBugs(Reader);
                                Bugs.Add(B);
                            }
                        }//end while
                    }
                }//end outer using
            }//end foreach
            return Bugs;
        }//end GetBugsFromBugLogID


        /// <summary>
        /// Get last bug ID 
        /// </summary>
        /// <returns>int</returns>
        public int GetLastBugID()
        {
            int BugID = 0;
            using (SqlConnection connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = connection.CreateCommand())
                {
                    Command.CommandText = @"GetLastBugID";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlDataReader Reader = Command.ExecuteReader();

                    while (Reader.Read())
                    {
                        BugID = Int32.Parse(Reader["last_bug"].ToString());
                    }
            }
        }//end outer using
            return BugID;
        }//end GetLastBugID

        /// <summary>
        /// Delete bug from given Bug ID
        /// </summary>
        /// <param name="BugID">Bug ID</param>
        public void DeleteBug(int BugID)
        {
            using (SqlConnection connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = connection.CreateCommand())
                {
                    Command.CommandText = @"DeleteBug";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlParameter Parameter1 = new SqlParameter("BugID", System.Data.SqlDbType.Int);
                    Parameter1.Value = BugID;
                    Command.Parameters.Add(Parameter1);
                    Command.ExecuteNonQuery();
                }
            }//end outer using
        }//end DeleteApplication

        /// <summary>
        /// Insert bug given information
        /// </summary>
        /// <param name="date">Date</param>
        /// <param name="Description">Description</param>
        /// <param name="Details">Details</param>
        /// <param name="RepSteps">RepSteps</param>
        /// <param name="FixDate">FixDate</param>
        /// <param name="AppID">AppID</param>
        /// <param name="UserID">UserID</param>
        /// <param name="BugSignOff">BugSignOff</param>
        public void InsertBug(DateTime date, String Description, String Details, String RepSteps,
                            DateTime? FixDate, int AppID, int UserID, int? BugSignOff)
        {
            using (SqlConnection connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = connection.CreateCommand())
                {
                    Command.CommandText = @"InsertBug";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlParameter Parameter1 = new SqlParameter("Date", System.Data.SqlDbType.DateTime);
                    Parameter1.Value = date;
                    Command.Parameters.Add(Parameter1);

                    SqlParameter Parameter2 = new SqlParameter("Description", System.Data.SqlDbType.NVarChar, 100);
                    Parameter2.Value = Description;
                    Command.Parameters.Add(Parameter2);

                    SqlParameter Parameter3 = new SqlParameter("Details", System.Data.SqlDbType.NVarChar, 100);
                    Parameter3.Value = Details;
                    Command.Parameters.Add(Parameter3);

                    
                    SqlParameter Parameter4 = new SqlParameter("RepSteps", System.Data.SqlDbType.NVarChar, 100);
                    Parameter4.Value = RepSteps;
                    Command.Parameters.Add(Parameter4);

                   
                    SqlParameter Parameter5 = new SqlParameter("FixDate", System.Data.SqlDbType.DateTime, 100);
                    if (FixDate == null)
                    {
                        Parameter5.Value = DBNull.Value;
                    }
                    else
                    {
                        Parameter5.Value = FixDate;
                    }//end if/else
                    
                    Command.Parameters.Add(Parameter5);

                    SqlParameter Parameter6 = new SqlParameter("AppID", System.Data.SqlDbType.Int, 100);
                    Parameter6.Value = AppID;
                    Command.Parameters.Add(Parameter6);

                    //used for concurrency check (manual)
                    SqlParameter Parameter7 = new SqlParameter("UserID", System.Data.SqlDbType.Int, 100);
                    Parameter7.Value = UserID;
                    Command.Parameters.Add(Parameter7);

                    //used for concurrency check (manual)
                    SqlParameter Parameter8 = new SqlParameter("BugSignOff", System.Data.SqlDbType.NVarChar, 100);
                    if (BugSignOff == 0)
                    {
                        Parameter8.Value = DBNull.Value;
                    }
                    else
                    {
                        Parameter8.Value = BugSignOff;
                    }//end if/else
                    Command.Parameters.Add(Parameter8);

                    Command.ExecuteNonQuery();

                }
            }//end outer using
        }//end insertBug()

        /// <summary>
        /// Update Bug from given information
        /// </summary>
        /// <param name="BugID">BugID</param>
        /// <param name="Description">Description</param>
        /// <param name="Details">Details</param>
        /// <param name="RepSteps">RepSteps</param>
        /// <param name="FixDate">FixDate</param>
        /// <param name="BugSignOff">BugSignOff</param>
        public void UpdateBug(int BugID, String Description, String Details, String RepSteps,
                            DateTime? FixDate, int? BugSignOff)
        {
            using (SqlConnection connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = connection.CreateCommand())
                {
                    Command.CommandText = @"UpdateBug";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlParameter Parameter1 = new SqlParameter("BugID", System.Data.SqlDbType.Int);
                    Parameter1.Value = BugID;
                    Command.Parameters.Add(Parameter1);

                    SqlParameter Parameter2 = new SqlParameter("Description", System.Data.SqlDbType.NVarChar, 100);
                    Parameter2.Value = Description;
                    Command.Parameters.Add(Parameter2);

                    SqlParameter Parameter3 = new SqlParameter("Details", System.Data.SqlDbType.NVarChar, 100);
                    Parameter3.Value = Details;
                    Command.Parameters.Add(Parameter3);


                    SqlParameter Parameter4 = new SqlParameter("RepSteps", System.Data.SqlDbType.NVarChar, 100);
                    Parameter4.Value = RepSteps;
                    Command.Parameters.Add(Parameter4);


                    SqlParameter Parameter5 = new SqlParameter("FixDate", System.Data.SqlDbType.DateTime, 100);
                    if (FixDate == null)
                    {
                        Parameter5.Value = DBNull.Value;
                    }
                    else
                    {
                        Parameter5.Value = FixDate;
                    }

                    Command.Parameters.Add(Parameter5);

                    //used for concurrency check (manual)
                    SqlParameter Parameter6 = new SqlParameter("BugSignOff", System.Data.SqlDbType.NVarChar, 100);
                    if (BugSignOff == 0)
                    {
                        Parameter6.Value = DBNull.Value;
                    }
                    else
                    {
                        Parameter6.Value = BugSignOff;
                    }
                    Command.Parameters.Add(Parameter6);

                    Command.ExecuteNonQuery();
                }
            }//end outer using
        }//end UpdateBug
    }//end class Bug
}//end namespace
