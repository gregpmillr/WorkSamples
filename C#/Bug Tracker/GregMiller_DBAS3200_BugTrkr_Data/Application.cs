using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Threading.Tasks;

namespace GregMiller_DBAS3200_BugTrkr_Data
{
    public class Application
    {
        //properties
        public int AppID { get; set; } = 0;
        public String AppName { get; set; } = "Add New";
        public String AppVersion { get; set; } = " ";
        public String AppDesc { get; set; } = " ";

        /// <summary>
        /// Load Application Object from database reader.
        /// </summary>
        /// <param name="Reader">Reader</param>
        public void Load(SqlDataReader Reader)
        {
            AppID = Int32.Parse(Reader["AppID"].ToString());
            AppName = Reader["AppName"].ToString();
            AppVersion = Reader["AppVersion"].ToString();
            AppDesc= Reader["AppDesc"].ToString();
        }//end load

        /// <summary>
        /// Get list of application
        /// </summary>
        /// <returns>List of applications</returns>
        public List<Application> GetList()
        {

            List<Application> Applications= new List<Application>();

            using (SqlConnection Connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = Connection.CreateCommand())
                {
                    Command.CommandText = @"GetAllApplications";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlDataReader Reader = Command.ExecuteReader();
                    while (Reader.Read())
                    {
                        Application A = new Application();
                        A.Load(Reader);
                        Applications.Add(A);
                    }
                }
            }
            Application dummyApp = new Application();
            Applications.Insert(0, dummyApp);

            return Applications;
        }//end GetList

        /// <summary>
        /// Insert application given information
        /// </summary>
        /// <param name="ApplicationName">AppName</param>
        /// <param name="ApplicationVersion">AppVersion</param>
        /// <param name="ApplicationDescription">AppDescription</param>
        public void InsertApplication(String ApplicationName, String ApplicationVersion, String ApplicationDescription)
        {
            using (SqlConnection Connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = Connection.CreateCommand())
                {
                    Command.CommandText = @"InsertApplication";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlParameter Parameter1 = new SqlParameter("AppName", System.Data.SqlDbType.NVarChar);
                    Parameter1.Value = ApplicationName;
                    Command.Parameters.Add(Parameter1);

                    SqlParameter Parameter2 = new SqlParameter("AppVersion", System.Data.SqlDbType.NVarChar, 100);
                    Parameter2.Value = ApplicationVersion;
                    Command.Parameters.Add(Parameter2);

                    //used for concurrency check (manual)
                    SqlParameter Parameter3 = new SqlParameter("AppDesc", System.Data.SqlDbType.NVarChar, 100);
                    Parameter3.Value = ApplicationDescription;
                    Command.Parameters.Add(Parameter3);

                    Command.ExecuteNonQuery();
                }
            }//end outer using
        }//end InsertApplication

        /// <summary>
        /// Delete application given Application ID
        /// </summary>
        /// <param name="AppID">AppID</param>
        public void DeleteApplication(int AppID)
        {
            using (SqlConnection Connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = Connection.CreateCommand())
                {
                    Command.CommandText = @"DeleteApplication";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlParameter Parameter1 = new SqlParameter("AppID", System.Data.SqlDbType.Int);
                    Parameter1.Value = AppID;
                    Command.Parameters.Add(Parameter1);
                    Command.ExecuteNonQuery();

                }
            }//end outer using
        }//end DeleteApplication

        /// <summary>
        /// Update application given information
        /// </summary>
        /// <param name="AppID">AppID</param>
        /// <param name="AppName">App Name</param>
        /// <param name="AppVersion">App Version</param>
        /// <param name="AppDescription">App Description</param>
        public void UpdateApplication(int AppID, String AppName, String AppVersion, String AppDescription)
        {
            using (SqlConnection Connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = Connection.CreateCommand())
                {
                    Command.CommandText = @"UpdateApplication";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlParameter Parameter1 = new SqlParameter("AppID", System.Data.SqlDbType.Int);
                    Parameter1.Value = AppID;
                    Command.Parameters.Add(Parameter1);

                    SqlParameter Parameter2 = new SqlParameter("AppName", System.Data.SqlDbType.NVarChar, 100);
                    Parameter2.Value = AppName;
                    Command.Parameters.Add(Parameter2);

                    SqlParameter Parameter3 = new SqlParameter("AppVersion", System.Data.SqlDbType.NVarChar, 100);
                    Parameter3.Value = AppVersion;
                    Command.Parameters.Add(Parameter3);

                    SqlParameter Parameter4 = new SqlParameter("AppDesc", System.Data.SqlDbType.NVarChar, 100);
                    Parameter4.Value = AppDescription;
                    Command.Parameters.Add(Parameter4);
                    Command.ExecuteNonQuery();
                }
            }//end outer using
        }//end UpdateApplication
    }//end class Application
}//end namespace
