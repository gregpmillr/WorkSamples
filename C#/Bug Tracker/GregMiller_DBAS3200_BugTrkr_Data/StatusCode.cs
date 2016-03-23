using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;

namespace GregMiller_DBAS3200_BugTrkr_Data
{
    public class StatusCode
    {
        //properties
        public Int32 StatusCodeID { get; set; }
        public String StatusCodeDesc { get; set; } = "All";

        /// <summary>
        /// Load Status Code object from database.
        /// </summary>
        /// <param name="Reader">SqlDataReader Reader</param>
        public void Load(SqlDataReader Reader)
        {
            StatusCodeID = Int32.Parse(Reader["StatusCodeID"].ToString());
            StatusCodeDesc = Reader["StatusCodeDesc"].ToString();
        }

        /// <summary>
        /// Get list of Status Codes
        /// </summary>
        /// <returns>List of Status Codes</returns>
        public List<StatusCode> GetList()
        {
            List<StatusCode> StatusCodes = new List<StatusCode>();

            using (SqlConnection Connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = Connection.CreateCommand())
                {
                    Command.CommandText = @"GetAllStatusCodes";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlDataReader Reader = Command.ExecuteReader();

                    while (Reader.Read())
                    {
                        StatusCode S = new StatusCode();
                        S.Load(Reader);
                        StatusCodes.Add(S);
                    }
                }
            }
            StatusCode dummyCode = new StatusCode();
            StatusCodes.Insert(0, dummyCode);
            return StatusCodes;
        }//end GetList
    }
}
