using System;
using System.Collections.Generic;
using System.Windows.Forms;
using System.Configuration;
using System.IO;
using System.Data.SqlClient;

namespace GregMiller_DBAS3200_BugTracker
{
    public partial class Form1 : Form
    {
        //properties
        private string ApplicationName;
        private Boolean loggedIn = false;
        private GregMiller_DBAS3200_BugTrkr_Data.User CurrentUser = new GregMiller_DBAS3200_BugTrkr_Data.User();

        public Form1(){InitializeComponent();}

        /// <summary>
        /// Load Application list. Display's the Application Name
        /// </summary>
        private void LoadApplicationsList()
        {
            try{
                GregMiller_DBAS3200_BugTrkr_Data.Application Applications = new GregMiller_DBAS3200_BugTrkr_Data.Application();
                ListBoxApplications.DataSource = Applications.GetList();
                ListBoxApplications.DisplayMember = "AppName";
            }
            catch (SqlException sqlex){
                //connection error...
                DisplayErrorMessage(sqlex.Message);
            }
        }

        /// <summary>
        /// Load combo box for a list of applications. Display's the Application's name.
        /// </summary>
        private void LoadComboBoxApplicationList()
        {
            try{
                GregMiller_DBAS3200_BugTrkr_Data.Application Applications = new GregMiller_DBAS3200_BugTrkr_Data.Application();
                List<GregMiller_DBAS3200_BugTrkr_Data.Application> ApplicationsList = Applications.GetList();
                ApplicationsList.RemoveAt(0);
                BoxApplicationSelection.DataSource = ApplicationsList;
                BoxApplicationSelection.DisplayMember = "AppName";
            }
            catch (SqlException sqlex){
                //connection error...
                DisplayErrorMessage(sqlex.Message);
            }
        }

        /// <summary>
        /// Load list of bugs from Application ID. Displays the Bug's description.
        /// </summary>
        /// <param name="AppID">Application ID</param>
        private void LoadBugList(int AppID)
        {
            try{
                GregMiller_DBAS3200_BugTrkr_Data.Bug Bugs = new GregMiller_DBAS3200_BugTrkr_Data.Bug();
                ListBoxBugs.DataSource = Bugs.GetList(AppID);
                ListBoxBugs.DisplayMember = "BugDesc";
            }
            catch (SqlException sqlex){
                //connection error...
                DisplayErrorMessage(sqlex.Message);
            }
        }

        /// <summary>
        /// Load  bug list from Application ID and Status Code ID. Display's the Bug's description.
        /// </summary>
        /// <param name="AppID">Application ID</param>
        /// <param name="StatusCodeID">Status Code ID</param>
        private void LoadBugList(int AppID, int StatusCodeID)
        {
            try{
                if(StatusCodeID == 0)
                {
                    try
                    {
                        GregMiller_DBAS3200_BugTrkr_Data.Bug Bugs = new GregMiller_DBAS3200_BugTrkr_Data.Bug();
                        ListBoxBugs.DataSource = Bugs.GetList(AppID);
                        ListBoxBugs.DisplayMember = "BugDesc";
                    }
                    catch (SqlException sqlex)
                    {
                        //connection error...
                        DisplayErrorMessage(sqlex.Message);
                    }
                }
                else
                {
                    GregMiller_DBAS3200_BugTrkr_Data.Bug Bugs = new GregMiller_DBAS3200_BugTrkr_Data.Bug();
                    ListBoxBugs.DataSource = Bugs.SetBugLogIDList(AppID, StatusCodeID);
                    ListBoxBugs.DisplayMember = "BugDesc";
                }

            }
            catch (SqlException sqlex){
                //connection error...
                DisplayErrorMessage(sqlex.Message);
            }
        }

        /// <summary>
        /// Load list of users. Display's User's name.
        /// </summary>
        private void LoadUsersList()
        {
            try{
                GregMiller_DBAS3200_BugTrkr_Data.User Users = new GregMiller_DBAS3200_BugTrkr_Data.User();
                ListBoxUsers.DataSource = Users.GetList();
                ListBoxUsers.DisplayMember = "UserName";
            }
            catch (SqlException sqlex){
                //connection error...
                DisplayErrorMessage(sqlex.Message);
            }
        }


        /// <summary>
        /// Load list of Status Codes. Display Status Code's description
        /// </summary>
        private void LoadComboBoxStatusCodesList()
        {
            try{
                GregMiller_DBAS3200_BugTrkr_Data.StatusCode StatusCodes= new GregMiller_DBAS3200_BugTrkr_Data.StatusCode();
                ComboBoxStatusCodes.DataSource = StatusCodes.GetList();
                BoxBugStatus.DataSource = StatusCodes.GetList();
                BoxBugStatus.DisplayMember = "StatusCodeDesc";
                ComboBoxStatusCodes.DisplayMember = "StatusCodeDesc";
            }
            catch (SqlException sqlex){
                //connection error...
                DisplayErrorMessage(sqlex.Message);
            }
        }

        /// <summary>
        /// Load bug combo box Status Codes. Display Status Code's description.
        /// </summary>
        private void LoadBugComboBoxStatusCodesList()
        {
            try{
                GregMiller_DBAS3200_BugTrkr_Data.StatusCode StatusCodes = new GregMiller_DBAS3200_BugTrkr_Data.StatusCode();
                BoxBugStatus.DataSource = StatusCodes.GetList();
                BoxBugStatus.DisplayMember = "StatusCodeDesc";
            }
            catch (SqlException sqlex){
                //connection error...
                DisplayErrorMessage(sqlex.Message);
            }
        }

        /// <summary>
        /// Display error message.
        /// </summary>
        /// <param name="Message">Message</param>
        private void DisplayErrorMessage(string Message)
        {
            MessageBox.Show(this,Message,"Error",MessageBoxButtons.OK,MessageBoxIcon.Error);
        }

        /// <summary>
        /// Load on Form shown.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Form1_Shown(object sender, EventArgs e)
        {
            try
            {
                //point the value of |DataDirectory| at our database in the datalayer
                string DataDirectory = ConfigurationManager.AppSettings["DataDirectory"];
                string AbsoluteDataDirectory = Path.GetFullPath(DataDirectory);
                AppDomain.CurrentDomain.SetData("DataDirectory", AbsoluteDataDirectory);

                //set application name
                ApplicationName = ConfigurationManager.AppSettings["ApplicationName"].ToString();

                //set connection settings
                GregMiller_DBAS3200_BugTrkr_Data.DB.ApplicationName = ApplicationName;
                GregMiller_DBAS3200_BugTrkr_Data.DB.ConnectionTimeout = 30;

                //load employees into listbox
                LoadApplicationsList();
                LoadUsersList();
                LoadComboBoxApplicationList();
                LoadComboBoxStatusCodesList();
            }
            catch (SqlException sqlex)
            {
                //connection error...
                DisplayErrorMessage(sqlex.Message);
            }
        }


        /// <summary>
        /// ListBoxApplications selected index changed
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void ListBoxApplications_SelectedIndexChanged(object sender, EventArgs e)
        {
            GregMiller_DBAS3200_BugTrkr_Data.Application SelectedApplication = (GregMiller_DBAS3200_BugTrkr_Data.Application)ListBoxApplications.SelectedValue;
            LblAppID.Text = SelectedApplication.AppID.ToString();
            TxtAppName.Text = SelectedApplication.AppName.ToString();
            TxtCurrentVersion.Text = SelectedApplication.AppVersion.ToString();
            TxtDescription.Text = SelectedApplication.AppDesc.ToString();
        }

        /// <summary>
        /// ComboBoxApplications selected index changed
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void ComboBoxApplications_SelectedIndexChanged(object sender, EventArgs e)
        {
            GregMiller_DBAS3200_BugTrkr_Data.Application Applications = new GregMiller_DBAS3200_BugTrkr_Data.Application();
            BoxApplicationSelection.DataSource = Applications.GetList();
            BoxApplicationSelection.DisplayMember = "AppName";
        }

        /// <summary>
        /// Update / Insert Application.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void BtnSave_Click(object sender, EventArgs e)
        {
            GregMiller_DBAS3200_BugTrkr_Data.Application Applications = new GregMiller_DBAS3200_BugTrkr_Data.Application();
            GregMiller_DBAS3200_BugTrkr_Data.Application SelectedApplication = (GregMiller_DBAS3200_BugTrkr_Data.Application)ListBoxApplications.SelectedValue;

            String AppName = TxtAppName.Text.ToString();
            String AppVersion = TxtCurrentVersion.Text.ToString();
            String AppDescription = TxtDescription.Text.ToString();

            //validation to check for lengths
            if (AppName.Length == 0 || AppName.Length > 40 || AppVersion.Length <= 1 || AppVersion.Length > 40 || AppDescription.Length > 255 || AppDescription.Length <= 1)
            {
                MessageBox.Show("Add Application failed: Empty field");
            }
            else if (AppName == "Add New")
            {
                MessageBox.Show("Add Application failed: Incorrect Application Name");
            }
            else
            {
                if (SelectedApplication.AppID == 0) { Applications.InsertApplication(AppName, AppVersion, AppDescription); }
                else { Applications.UpdateApplication(SelectedApplication.AppID, AppName, AppVersion, AppDescription); }
            }

            //reload the data to refresh the hidden fields
            LoadApplicationsList();
            LoadComboBoxApplicationList();
        }

        /// <summary>
        /// Delete Application from ID.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void BtnDelete_Click(object sender, EventArgs e)
        {
            GregMiller_DBAS3200_BugTrkr_Data.Application SelectedApplication = (GregMiller_DBAS3200_BugTrkr_Data.Application)ListBoxApplications.SelectedValue;
            GregMiller_DBAS3200_BugTrkr_Data.Bug Bugs = new GregMiller_DBAS3200_BugTrkr_Data.Bug();

            List<GregMiller_DBAS3200_BugTrkr_Data.Bug> AppIDBugs = Bugs.GetList(SelectedApplication.AppID);
            AppIDBugs.RemoveAt(0);
            //make sure user hasn't chosen the first appid
            if (AppIDBugs.Count == 0)
            {
                if(SelectedApplication.AppID != 0)
                {
                    int AppID = SelectedApplication.AppID;
                    SelectedApplication.DeleteApplication(AppID);
                    LoadApplicationsList();
                    LoadComboBoxApplicationList();
                }
                else
                {
                    MessageBox.Show("Cannot delete this object.");
                }
            }
            else
            {
                MessageBox.Show("Cannot delete application with existing bugs.");
            }
        }

        /// <summary>
        /// Update / Insert user into the Database.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void BtnUsersSave_Click(object sender, EventArgs e)
        {
            GregMiller_DBAS3200_BugTrkr_Data.User Users = new GregMiller_DBAS3200_BugTrkr_Data.User();
            GregMiller_DBAS3200_BugTrkr_Data.User SelectedUser = (GregMiller_DBAS3200_BugTrkr_Data.User)ListBoxUsers.SelectedValue;

            String UserName = TxtAddUserName.Text.ToString();
            String UserEmail = TxtAddUserEmail.Text.ToString();
            String UserTel = TxtAddUserTel.Text.ToString();

            //validation to check lengths
            if(UserName.Length <= 1 || UserName.Length > 80 || UserEmail.Length <= 1 || UserEmail.Length > 80 || UserTel.Length <= 1 || UserTel.Length > 40 || UserName == "Add New")
            {
                MessageBox.Show("Invalid field(s)");
            }
            else
            {
                if (SelectedUser.UserID == 0) { Users.InsertUser(UserName, UserEmail, UserTel); }
                else { Users.UpdateUser(SelectedUser.UserID, UserName, UserEmail, UserTel); }
            }
            LoadUsersList();
        }

        /// <summary>
        /// ListBoxUser's index selection changed.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void ListBoxUsers_SelectedIndexChanged(object sender, EventArgs e)
        {
            GregMiller_DBAS3200_BugTrkr_Data.User SelectedUser = (GregMiller_DBAS3200_BugTrkr_Data.User)ListBoxUsers.SelectedValue;
            LblUserID.Text = SelectedUser.UserID.ToString();
            TxtAddUserName.Text = SelectedUser.UserName.ToString();
            TxtAddUserEmail.Text = SelectedUser.UserEmail.ToString();
            TxtAddUserTel.Text = SelectedUser.UserTel.ToString();
        }

        /// <summary>
        /// Delete specific User.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void BtnUsersDelete_Click(object sender, EventArgs e)
        {
            GregMiller_DBAS3200_BugTrkr_Data.User SelectedUser = (GregMiller_DBAS3200_BugTrkr_Data.User)ListBoxUsers.SelectedValue;
            int UserID = SelectedUser.UserID;
            if (UserID == 0)
            {
                MessageBox.Show("You cannot delete this object.");
            }
            else if(UserID == 4)
            {
                MessageBox.Show("Access Denied: Unable to delete Administrator.");
            }
            else
            {
                if (SelectedUser.UserHasBugData(SelectedUser.UserID))
                {
                    MessageBox.Show("Unable to delete user with associated bug data.");
                }
                else
                {
                    SelectedUser.DeleteUser(UserID);
                    LoadUsersList();
                }

            }
        }

        /// <summary>
        /// Save bug for specific application.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void BtnSaveBug_Click(object sender, EventArgs e)
        {
            DateTime? FixDate = null;
            int? BugSignOff = null;
            String Status = BoxBugStatus.Text.ToString();

            //get selected data
            GregMiller_DBAS3200_BugTrkr_Data.Application App = (GregMiller_DBAS3200_BugTrkr_Data.Application)BoxApplicationSelection.SelectedItem;
            GregMiller_DBAS3200_BugTrkr_Data.Bug ChosenBug = (GregMiller_DBAS3200_BugTrkr_Data.Bug)ListBoxBugs.SelectedItem;
            GregMiller_DBAS3200_BugTrkr_Data.Bug NewBug = new GregMiller_DBAS3200_BugTrkr_Data.Bug();
            GregMiller_DBAS3200_BugTrkr_Data.BugLog NewBugLog = new GregMiller_DBAS3200_BugTrkr_Data.BugLog();
            GregMiller_DBAS3200_BugTrkr_Data.StatusCode ChosenStatusCode = (GregMiller_DBAS3200_BugTrkr_Data.StatusCode)BoxBugStatus.SelectedItem;

            //validation to check lengths
            if(TxtBugDescription.TextLength <= 1|| TxtBugDescription.TextLength > 40 || TxtBugDetails.TextLength <= 1|| TxtBugDetails.TextLength > 80|| TxtBugRepSteps.TextLength <= 1 || TxtBugRepSteps.TextLength > 80)
            {
                MessageBox.Show("Textbox length(s) are out of bounds.");
            }
            else
            {
                //if it's closed then set the fixed date to now
                if (Status == "Closed")
                {
                    FixDate = DateTime.Now;
                    BugSignOff = CurrentUser.UserID;
                }
                //check to see if user wants to insert or update
                if (ChosenBug.BugID == 0)
                {
                    NewBug.InsertBug(DateTime.Now, TxtBugDescription.Text.ToString(), TxtBugDetails.Text.ToString(),
                    TxtBugRepSteps.Text.ToString(), FixDate, App.AppID, CurrentUser.UserID, BugSignOff);
                    int LastBugID = NewBug.GetLastBugID();
                    NewBugLog.InsertBugLog(DateTime.Now, 4, CurrentUser.UserID, "Created bug", LastBugID);
                }
                else
                {
                    NewBug.UpdateBug(ChosenBug.BugID, TxtBugDescription.Text.ToString(), TxtBugDetails.Text.ToString(),
                                    TxtBugRepSteps.Text.ToString(), FixDate, BugSignOff);
                    NewBugLog.InsertBugLog(DateTime.Now, ChosenStatusCode.StatusCodeID, CurrentUser.UserID, TxtUpdateComments.Text.ToString(), ChosenBug.BugID);
                }
            }
            LoadBugList(App.AppID);
        }

        /// <summary>
        /// Event triggered on when Application selected index has been changed.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void BoxApplicationSelection_SelectedIndexChanged(object sender, EventArgs e)
        {
            GregMiller_DBAS3200_BugTrkr_Data.Application SelectedApplication = (GregMiller_DBAS3200_BugTrkr_Data.Application)BoxApplicationSelection.SelectedValue;
            LoadBugList(SelectedApplication.AppID);
        }

        /// <summary>
        /// Event triggered on when ListBoxBugs selected index has been changed.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void ListBoxBugs_SelectedIndexChanged(object sender, EventArgs e)
        {
            GregMiller_DBAS3200_BugTrkr_Data.Bug SelectedBug = (GregMiller_DBAS3200_BugTrkr_Data.Bug)ListBoxBugs.SelectedValue;
            LblBugID.Text = SelectedBug.BugID.ToString();
            LblBugSubmitDate.Text = SelectedBug.BugDate.ToString();
            TxtBugDescription.Text = SelectedBug.BugDesc.ToString();
            LblFixDate.Text = SelectedBug.FixDate.ToString();
            TxtBugRepSteps.Text = SelectedBug.RepSteps.ToString();
            TxtBugDetails.Text = SelectedBug.BugDetails.ToString();
 
            if (SelectedBug.BugID != 0)
            {
                GregMiller_DBAS3200_BugTrkr_Data.BugLog BugLogs = new GregMiller_DBAS3200_BugTrkr_Data.BugLog();
                //set datasource for the data grid
                DataGridBugLog.DataSource = BugLogs.GetList(SelectedBug.BugID);
                BoxBugStatus.SelectedIndex = BugLogs.GetSingleBugLog(SelectedBug.BugID);
                BoxBugStatus.Enabled = true;
                TxtUpdateComments.Enabled = true;
                GregMiller_DBAS3200_BugTrkr_Data.BugLog BugLog = new GregMiller_DBAS3200_BugTrkr_Data.BugLog();
                String Buglogdesc = BugLog.GetSingleBugLogDesc(SelectedBug.BugID);
                TxtUpdateComments.Text = Buglogdesc;
            }
            else { BoxBugStatus.Enabled = false; TxtUpdateComments.Enabled = false; DataGridBugLog.DataSource = null; }
            LoadApplicationsList();
        }


        /// <summary>
        /// Delete bug specified by Application.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void BtnBugDelete_Click(object sender, EventArgs e)
        {
            DialogResult result3 = MessageBox.Show("Are you sure you want to delete this bug?","Delete Bug",
            MessageBoxButtons.YesNoCancel,
            MessageBoxIcon.Question,
            MessageBoxDefaultButton.Button1);

            if (result3 == DialogResult.Yes)
            {
                GregMiller_DBAS3200_BugTrkr_Data.Bug SelectedBug = (GregMiller_DBAS3200_BugTrkr_Data.Bug)ListBoxBugs.SelectedValue;
                if(SelectedBug.BugID == 0)
                {
                    MessageBox.Show("You cannot delete this object.");
                }
                else
                {
                    GregMiller_DBAS3200_BugTrkr_Data.Application SelectedApplication = (GregMiller_DBAS3200_BugTrkr_Data.Application)BoxApplicationSelection.SelectedValue;
                    SelectedBug.DeleteBug(SelectedBug.BugID);
                    LoadBugList(SelectedApplication.AppID);
                }
            }
        }

        /// <summary>
        /// Check for User's login credentials.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void BtnGo_Click(object sender, EventArgs e)
        {
            String UserName = TxtUserName.Text.ToString();
            CurrentUser = CurrentUser.GetCurrentUser(UserName);
            if (loggedIn == false)
            {
                if (CurrentUser.UserID == 4)
                {
                    TabControl1.TabPages.Add(TabApplications);
                    TabControl1.TabPages.Add(TabBugs);
                    TabControl1.TabPages.Add(TabUsers);
                    this.Text += " - " + UserName;
                    loggedIn = true;
                }
                else if (CurrentUser.UserID > 4)
                {
                    TabControl1.TabPages.Add(TabApplications);
                    TabControl1.TabPages.Add(TabBugs);
                    this.Text += " - " + UserName;
                    loggedIn = true;
                }
                else
                {
                    MessageBox.Show("The user " + UserName + " was not found!", "User Not Found!");
                    RemoveTabs();
                }
            }
            else
            {
                MessageBox.Show("You're already logged in.");
            }
        }

        /// <summary>
        /// Manage tabs on Form1 load.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Form1_Load(object sender, EventArgs e)
        {
            RemoveTabs();
            BoxBugStatus.Enabled = false;
            TxtUpdateComments.Enabled = false;
        }

        /// <summary>
        /// Remove all tabs.
        /// </summary>
        private void RemoveTabs()
        {
            TabControl1.TabPages.Remove(TabApplications);
            TabControl1.TabPages.Remove(TabBugs);
            TabControl1.TabPages.Remove(TabUsers);
        }

        /// <summary>
        /// Update BugList from selected Status Code.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void ComboBoxStatusCodes_SelectedIndexChanged(object sender, EventArgs e)
        {
            GregMiller_DBAS3200_BugTrkr_Data.StatusCode SelectedStatusCode = (GregMiller_DBAS3200_BugTrkr_Data.StatusCode)ComboBoxStatusCodes.SelectedValue;
            GregMiller_DBAS3200_BugTrkr_Data.Application SelectedApplication = (GregMiller_DBAS3200_BugTrkr_Data.Application)BoxApplicationSelection.SelectedValue;
            LoadBugList(SelectedApplication.AppID,SelectedStatusCode.StatusCodeID);
        }
    }
}
