using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Threading.Tasks;

namespace GregMiller_DBAS3200_BugTrkr_Data
{
    public class User
    {
        //properties
        public Int32 UserID { get; set; } = 0;
        public String UserName { get; set; } = "Add New";
        public String UserEmail { get; set; } = " ";
        public String UserTel { get; set; } = " ";
        
        /// <summary>
        /// Load this User's properties from SqlDataReader.
        /// </summary>
        /// <param name="Reader">SqlDataReader Reader</param>
        public void Load(SqlDataReader Reader)
        {
            UserID = Int32.Parse(Reader["UserID"].ToString());
            UserName = Reader["UserName"].ToString();
            UserEmail = Reader["UserEmail"].ToString();
            UserTel = Reader["UserTel"].ToString();
        }

        /// <summary>
        /// Get list of Users.
        /// </summary>
        /// <returns>List of Users</returns>
        public List<User> GetList()
        {
            List<User> Users = new List<User>();

            using (SqlConnection Connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = Connection.CreateCommand())
                {
                    Command.CommandText = @"GetAllUsers";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlDataReader Reader = Command.ExecuteReader();

                    while (Reader.Read())
                    {
                        User u = new User();
                        u.Load(Reader);
                        Users.Add(u);
                    }
                }
            }
            User DummyUser = new User();
            Users.Insert(0, DummyUser);
            return Users;
        }//end GetList

        /// <summary>
        /// Insert User into database.
        /// </summary>
        /// <param name="UserName">User Name</param>
        /// <param name="UserEmail">User Email</param>
        /// <param name="UserTel">User Phone Number</param>
        public void InsertUser(String UserName, String UserEmail, String UserTel)
        {

            using (SqlConnection Connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = Connection.CreateCommand())
                {
                    Command.CommandText = @"InsertUser";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlParameter Parameter1 = new SqlParameter("name", System.Data.SqlDbType.NVarChar);
                    Parameter1.Value = UserName;
                    Command.Parameters.Add(Parameter1);

                    SqlParameter Parameter2 = new SqlParameter("email", System.Data.SqlDbType.NVarChar, 100);
                    Parameter2.Value = UserEmail;
                    Command.Parameters.Add(Parameter2);

                    //used for concurrency check (manual)
                    SqlParameter Parameter3 = new SqlParameter("tel", System.Data.SqlDbType.NVarChar, 100);
                    Parameter3.Value = UserTel;
                    Command.Parameters.Add(Parameter3);

                    Command.ExecuteNonQuery();
                }
            }
        }


        /// <summary>
        /// Delete user from database.
        /// </summary>
        /// <param name="UserID">User ID</param>
        public void DeleteUser(int UserID)
        {
            using (SqlConnection Connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = Connection.CreateCommand())
                {
                    Command.CommandText = @"DeleteUser";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlParameter Parameter1 = new SqlParameter("UserID", System.Data.SqlDbType.Int);
                    Parameter1.Value = UserID;
                    Command.Parameters.Add(Parameter1);
                    Command.ExecuteNonQuery();

                }
            }
        }//end DeleteApplication

        /// <summary>
        /// Update User in the database.
        /// </summary>
        /// <param name="UserID">User ID</param>
        /// <param name="UserName">User Name</param>
        /// <param name="UserEmail">User Email</param>
        /// <param name="UserTel">User Phone Number</param>
        public void UpdateUser(int UserID, String UserName, String UserEmail, String UserTel)
        {
            using (SqlConnection Connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = Connection.CreateCommand())
                {
                    Command.CommandText = @"UpdateUser";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlParameter Parameter1 = new SqlParameter("UserID", System.Data.SqlDbType.Int);
                    Parameter1.Value = UserID;
                    Command.Parameters.Add(Parameter1);

                    SqlParameter Parameter2 = new SqlParameter("UserName", System.Data.SqlDbType.NVarChar,100);
                    Parameter2.Value = UserName;
                    Command.Parameters.Add(Parameter2);

                    SqlParameter Parameter3 = new SqlParameter("UserEmail", System.Data.SqlDbType.NVarChar,100);
                    Parameter3.Value = UserEmail;
                    Command.Parameters.Add(Parameter3);

                    SqlParameter Parameter4 = new SqlParameter("UserTel", System.Data.SqlDbType.NVarChar,100);
                    Parameter4.Value = UserTel;
                    Command.Parameters.Add(Parameter4);

                    Command.ExecuteNonQuery();

                }
            }
        }//end DeleteApplication

        /// <summary>
        /// Get User currently logged in.
        /// </summary>
        /// <param name="UserName">User Name</param>
        /// <returns>User</returns>
        public User GetCurrentUser(String UserName)
        {
            User currentUser = new User();

            using (SqlConnection Connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = Connection.CreateCommand())
                {
                    Command.CommandText = @"GetUserDetails";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlParameter Parameter1 = new SqlParameter("UserName", System.Data.SqlDbType.NVarChar,100);
                    Parameter1.Value = UserName;
                    Command.Parameters.Add(Parameter1);


                    SqlDataReader Reader = Command.ExecuteReader();

                    while (Reader.Read())
                    {
                        currentUser.Load(Reader);
                    }
                }
            }
            return currentUser;
        }//end GetCurrentUser

        /// <summary>
        /// Check to see if User has associated Bug Data
        /// </summary>
        /// <param name="UserID">User ID</param>
        /// <returns>Boolean</returns>
        public Boolean UserHasBugData(int UserID)
        {
            using (SqlConnection Connection = DB.GetSqlConnection())
            {
                using (SqlCommand Command = Connection.CreateCommand())
                {
                    Command.CommandText = @"GetBugsFromUserID";
                    Command.CommandType = System.Data.CommandType.StoredProcedure;

                    SqlParameter Parameter1 = new SqlParameter("UserID", System.Data.SqlDbType.Int);
                    Parameter1.Value = UserID;
                    Command.Parameters.Add(Parameter1);


                    SqlDataReader Reader = Command.ExecuteReader();

                    while (Reader.Read())
                    {
                        return true;
                    }
                }
            }
            return false;
        }//end GetList
    }
}

