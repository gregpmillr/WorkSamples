namespace GregMiller_DBAS3200_BugTracker
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.TabControl1 = new System.Windows.Forms.TabControl();
            this.TabIdentify = new System.Windows.Forms.TabPage();
            this.BtnGo = new System.Windows.Forms.Button();
            this.TxtUserName = new System.Windows.Forms.TextBox();
            this.Label2 = new System.Windows.Forms.Label();
            this.Label1 = new System.Windows.Forms.Label();
            this.TabApplications = new System.Windows.Forms.TabPage();
            this.ListBoxApplications = new System.Windows.Forms.ListBox();
            this.BtnDelete = new System.Windows.Forms.Button();
            this.BtnSave = new System.Windows.Forms.Button();
            this.TxtDescription = new System.Windows.Forms.TextBox();
            this.TxtCurrentVersion = new System.Windows.Forms.TextBox();
            this.LblAppID = new System.Windows.Forms.Label();
            this.TxtAppName = new System.Windows.Forms.TextBox();
            this.Label6 = new System.Windows.Forms.Label();
            this.Label5 = new System.Windows.Forms.Label();
            this.Label4 = new System.Windows.Forms.Label();
            this.Label3 = new System.Windows.Forms.Label();
            this.LblApplicationManager = new System.Windows.Forms.Label();
            this.TabBugs = new System.Windows.Forms.TabPage();
            this.Label25 = new System.Windows.Forms.Label();
            this.DataGridBugLog = new System.Windows.Forms.DataGridView();
            this.ListBoxBugs = new System.Windows.Forms.ListBox();
            this.BtnSaveBug = new System.Windows.Forms.Button();
            this.Label24 = new System.Windows.Forms.Label();
            this.Label11 = new System.Windows.Forms.Label();
            this.LblBugID = new System.Windows.Forms.Label();
            this.BtnBugDelete = new System.Windows.Forms.Button();
            this.Label17 = new System.Windows.Forms.Label();
            this.Label16 = new System.Windows.Forms.Label();
            this.LblBugSubmitDate = new System.Windows.Forms.Label();
            this.BoxBugStatus = new System.Windows.Forms.ComboBox();
            this.LblFixDate = new System.Windows.Forms.Label();
            this.TxtBugDescription = new System.Windows.Forms.TextBox();
            this.Label15 = new System.Windows.Forms.Label();
            this.Label14 = new System.Windows.Forms.Label();
            this.Label13 = new System.Windows.Forms.Label();
            this.Label12 = new System.Windows.Forms.Label();
            this.Label10 = new System.Windows.Forms.Label();
            this.Label9 = new System.Windows.Forms.Label();
            this.ComboBoxStatusCodes = new System.Windows.Forms.ComboBox();
            this.Label8 = new System.Windows.Forms.Label();
            this.BoxApplicationSelection = new System.Windows.Forms.ComboBox();
            this.Label7 = new System.Windows.Forms.Label();
            this.TxtUpdateComments = new System.Windows.Forms.TextBox();
            this.TxtBugRepSteps = new System.Windows.Forms.TextBox();
            this.TxtBugDetails = new System.Windows.Forms.TextBox();
            this.Label26 = new System.Windows.Forms.Label();
            this.TabUsers = new System.Windows.Forms.TabPage();
            this.Label27 = new System.Windows.Forms.Label();
            this.ListBoxUsers = new System.Windows.Forms.ListBox();
            this.Label23 = new System.Windows.Forms.Label();
            this.BtnUsersDelete = new System.Windows.Forms.Button();
            this.BtnUsersSave = new System.Windows.Forms.Button();
            this.LblUserID = new System.Windows.Forms.Label();
            this.TxtAddUserTel = new System.Windows.Forms.TextBox();
            this.TxtAddUserEmail = new System.Windows.Forms.TextBox();
            this.TxtAddUserName = new System.Windows.Forms.TextBox();
            this.Label22 = new System.Windows.Forms.Label();
            this.label21 = new System.Windows.Forms.Label();
            this.Label20 = new System.Windows.Forms.Label();
            this.Label19 = new System.Windows.Forms.Label();
            this.Label18 = new System.Windows.Forms.Label();
            this.TabControl1.SuspendLayout();
            this.TabIdentify.SuspendLayout();
            this.TabApplications.SuspendLayout();
            this.TabBugs.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridBugLog)).BeginInit();
            this.TabUsers.SuspendLayout();
            this.SuspendLayout();
            // 
            // TabControl1
            // 
            this.TabControl1.Controls.Add(this.TabIdentify);
            this.TabControl1.Controls.Add(this.TabApplications);
            this.TabControl1.Controls.Add(this.TabBugs);
            this.TabControl1.Controls.Add(this.TabUsers);
            this.TabControl1.Location = new System.Drawing.Point(1, -3);
            this.TabControl1.Name = "TabControl1";
            this.TabControl1.SelectedIndex = 0;
            this.TabControl1.Size = new System.Drawing.Size(840, 758);
            this.TabControl1.TabIndex = 0;
            // 
            // TabIdentify
            // 
            this.TabIdentify.Controls.Add(this.BtnGo);
            this.TabIdentify.Controls.Add(this.TxtUserName);
            this.TabIdentify.Controls.Add(this.Label2);
            this.TabIdentify.Controls.Add(this.Label1);
            this.TabIdentify.Font = new System.Drawing.Font("Corbel", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.TabIdentify.Location = new System.Drawing.Point(4, 22);
            this.TabIdentify.Name = "TabIdentify";
            this.TabIdentify.Padding = new System.Windows.Forms.Padding(3);
            this.TabIdentify.Size = new System.Drawing.Size(832, 732);
            this.TabIdentify.TabIndex = 0;
            this.TabIdentify.Text = "Identify";
            this.TabIdentify.UseVisualStyleBackColor = true;
            // 
            // BtnGo
            // 
            this.BtnGo.Location = new System.Drawing.Point(320, 324);
            this.BtnGo.Name = "BtnGo";
            this.BtnGo.Size = new System.Drawing.Size(222, 47);
            this.BtnGo.TabIndex = 5;
            this.BtnGo.Text = "Go";
            this.BtnGo.UseVisualStyleBackColor = true;
            this.BtnGo.Click += new System.EventHandler(this.BtnGo_Click);
            // 
            // TxtUserName
            // 
            this.TxtUserName.Location = new System.Drawing.Point(320, 241);
            this.TxtUserName.Name = "TxtUserName";
            this.TxtUserName.Size = new System.Drawing.Size(222, 21);
            this.TxtUserName.TabIndex = 4;
            // 
            // Label2
            // 
            this.Label2.AutoSize = true;
            this.Label2.Font = new System.Drawing.Font("Corbel", 12F);
            this.Label2.Location = new System.Drawing.Point(316, 219);
            this.Label2.Name = "Label2";
            this.Label2.Size = new System.Drawing.Size(86, 19);
            this.Label2.TabIndex = 3;
            this.Label2.Text = "User Name:";
            // 
            // Label1
            // 
            this.Label1.AutoSize = true;
            this.Label1.Font = new System.Drawing.Font("Corbel", 20F);
            this.Label1.Location = new System.Drawing.Point(29, 3);
            this.Label1.Name = "Label1";
            this.Label1.Size = new System.Drawing.Size(268, 33);
            this.Label1.TabIndex = 2;
            this.Label1.Text = "Please Identify Yourself";
            // 
            // TabApplications
            // 
            this.TabApplications.Controls.Add(this.ListBoxApplications);
            this.TabApplications.Controls.Add(this.BtnDelete);
            this.TabApplications.Controls.Add(this.BtnSave);
            this.TabApplications.Controls.Add(this.TxtDescription);
            this.TabApplications.Controls.Add(this.TxtCurrentVersion);
            this.TabApplications.Controls.Add(this.LblAppID);
            this.TabApplications.Controls.Add(this.TxtAppName);
            this.TabApplications.Controls.Add(this.Label6);
            this.TabApplications.Controls.Add(this.Label5);
            this.TabApplications.Controls.Add(this.Label4);
            this.TabApplications.Controls.Add(this.Label3);
            this.TabApplications.Controls.Add(this.LblApplicationManager);
            this.TabApplications.Location = new System.Drawing.Point(4, 22);
            this.TabApplications.Name = "TabApplications";
            this.TabApplications.Padding = new System.Windows.Forms.Padding(3);
            this.TabApplications.Size = new System.Drawing.Size(832, 732);
            this.TabApplications.TabIndex = 1;
            this.TabApplications.Text = "Applications";
            this.TabApplications.UseVisualStyleBackColor = true;
            // 
            // ListBoxApplications
            // 
            this.ListBoxApplications.FormattingEnabled = true;
            this.ListBoxApplications.Items.AddRange(new object[] {
            "<Add New>"});
            this.ListBoxApplications.Location = new System.Drawing.Point(137, 247);
            this.ListBoxApplications.Name = "ListBoxApplications";
            this.ListBoxApplications.Size = new System.Drawing.Size(502, 225);
            this.ListBoxApplications.TabIndex = 12;
            this.ListBoxApplications.SelectedIndexChanged += new System.EventHandler(this.ListBoxApplications_SelectedIndexChanged);
            // 
            // BtnDelete
            // 
            this.BtnDelete.Location = new System.Drawing.Point(436, 513);
            this.BtnDelete.Name = "BtnDelete";
            this.BtnDelete.Size = new System.Drawing.Size(101, 39);
            this.BtnDelete.TabIndex = 11;
            this.BtnDelete.Text = "Delete";
            this.BtnDelete.UseVisualStyleBackColor = true;
            this.BtnDelete.Click += new System.EventHandler(this.BtnDelete_Click);
            // 
            // BtnSave
            // 
            this.BtnSave.Location = new System.Drawing.Point(263, 513);
            this.BtnSave.Name = "BtnSave";
            this.BtnSave.Size = new System.Drawing.Size(101, 39);
            this.BtnSave.TabIndex = 10;
            this.BtnSave.Text = "Save";
            this.BtnSave.UseVisualStyleBackColor = true;
            this.BtnSave.Click += new System.EventHandler(this.BtnSave_Click);
            // 
            // TxtDescription
            // 
            this.TxtDescription.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.TxtDescription.Location = new System.Drawing.Point(436, 63);
            this.TxtDescription.Multiline = true;
            this.TxtDescription.Name = "TxtDescription";
            this.TxtDescription.Size = new System.Drawing.Size(203, 161);
            this.TxtDescription.TabIndex = 8;
            // 
            // TxtCurrentVersion
            // 
            this.TxtCurrentVersion.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.TxtCurrentVersion.Location = new System.Drawing.Point(195, 169);
            this.TxtCurrentVersion.Name = "TxtCurrentVersion";
            this.TxtCurrentVersion.Size = new System.Drawing.Size(203, 21);
            this.TxtCurrentVersion.TabIndex = 7;
            // 
            // LblAppID
            // 
            this.LblAppID.AutoSize = true;
            this.LblAppID.Location = new System.Drawing.Point(192, 78);
            this.LblAppID.Name = "LblAppID";
            this.LblAppID.Size = new System.Drawing.Size(0, 13);
            this.LblAppID.TabIndex = 6;
            // 
            // TxtAppName
            // 
            this.TxtAppName.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.TxtAppName.Location = new System.Drawing.Point(195, 118);
            this.TxtAppName.Name = "TxtAppName";
            this.TxtAppName.Size = new System.Drawing.Size(203, 21);
            this.TxtAppName.TabIndex = 5;
            // 
            // Label6
            // 
            this.Label6.AutoSize = true;
            this.Label6.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.Label6.Location = new System.Drawing.Point(433, 47);
            this.Label6.Name = "Label6";
            this.Label6.Size = new System.Drawing.Size(60, 13);
            this.Label6.TabIndex = 4;
            this.Label6.Text = "Description";
            // 
            // Label5
            // 
            this.Label5.AutoSize = true;
            this.Label5.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.Label5.Location = new System.Drawing.Point(99, 172);
            this.Label5.Name = "Label5";
            this.Label5.Size = new System.Drawing.Size(78, 13);
            this.Label5.TabIndex = 3;
            this.Label5.Text = "Current Version";
            // 
            // Label4
            // 
            this.Label4.AutoSize = true;
            this.Label4.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.Label4.Location = new System.Drawing.Point(99, 121);
            this.Label4.Name = "Label4";
            this.Label4.Size = new System.Drawing.Size(90, 13);
            this.Label4.TabIndex = 2;
            this.Label4.Text = "Application Name";
            // 
            // Label3
            // 
            this.Label3.AutoSize = true;
            this.Label3.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.Label3.Location = new System.Drawing.Point(99, 78);
            this.Label3.Name = "Label3";
            this.Label3.Size = new System.Drawing.Size(73, 13);
            this.Label3.TabIndex = 1;
            this.Label3.Text = "Application ID";
            // 
            // LblApplicationManager
            // 
            this.LblApplicationManager.Font = new System.Drawing.Font("Corbel", 20F);
            this.LblApplicationManager.Location = new System.Drawing.Point(29, 3);
            this.LblApplicationManager.Name = "LblApplicationManager";
            this.LblApplicationManager.Size = new System.Drawing.Size(251, 46);
            this.LblApplicationManager.TabIndex = 0;
            this.LblApplicationManager.Text = "Application Manager";
            // 
            // TabBugs
            // 
            this.TabBugs.BackColor = System.Drawing.Color.Transparent;
            this.TabBugs.Controls.Add(this.Label25);
            this.TabBugs.Controls.Add(this.DataGridBugLog);
            this.TabBugs.Controls.Add(this.ListBoxBugs);
            this.TabBugs.Controls.Add(this.BtnSaveBug);
            this.TabBugs.Controls.Add(this.Label24);
            this.TabBugs.Controls.Add(this.Label11);
            this.TabBugs.Controls.Add(this.LblBugID);
            this.TabBugs.Controls.Add(this.BtnBugDelete);
            this.TabBugs.Controls.Add(this.Label17);
            this.TabBugs.Controls.Add(this.Label16);
            this.TabBugs.Controls.Add(this.LblBugSubmitDate);
            this.TabBugs.Controls.Add(this.BoxBugStatus);
            this.TabBugs.Controls.Add(this.LblFixDate);
            this.TabBugs.Controls.Add(this.TxtBugDescription);
            this.TabBugs.Controls.Add(this.Label15);
            this.TabBugs.Controls.Add(this.Label14);
            this.TabBugs.Controls.Add(this.Label13);
            this.TabBugs.Controls.Add(this.Label12);
            this.TabBugs.Controls.Add(this.Label10);
            this.TabBugs.Controls.Add(this.Label9);
            this.TabBugs.Controls.Add(this.ComboBoxStatusCodes);
            this.TabBugs.Controls.Add(this.Label8);
            this.TabBugs.Controls.Add(this.BoxApplicationSelection);
            this.TabBugs.Controls.Add(this.Label7);
            this.TabBugs.Controls.Add(this.TxtUpdateComments);
            this.TabBugs.Controls.Add(this.TxtBugRepSteps);
            this.TabBugs.Controls.Add(this.TxtBugDetails);
            this.TabBugs.Controls.Add(this.Label26);
            this.TabBugs.Location = new System.Drawing.Point(4, 22);
            this.TabBugs.Name = "TabBugs";
            this.TabBugs.Size = new System.Drawing.Size(832, 732);
            this.TabBugs.TabIndex = 2;
            this.TabBugs.Text = "Bugs";
            // 
            // Label25
            // 
            this.Label25.AutoSize = true;
            this.Label25.Location = new System.Drawing.Point(122, 59);
            this.Label25.Name = "Label25";
            this.Label25.Size = new System.Drawing.Size(31, 13);
            this.Label25.TabIndex = 39;
            this.Label25.Text = "Bugs";
            // 
            // DataGridBugLog
            // 
            this.DataGridBugLog.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DataGridBugLog.GridColor = System.Drawing.SystemColors.ControlLight;
            this.DataGridBugLog.Location = new System.Drawing.Point(115, 184);
            this.DataGridBugLog.Name = "DataGridBugLog";
            this.DataGridBugLog.Size = new System.Drawing.Size(544, 150);
            this.DataGridBugLog.TabIndex = 38;
            // 
            // ListBoxBugs
            // 
            this.ListBoxBugs.FormattingEnabled = true;
            this.ListBoxBugs.Location = new System.Drawing.Point(125, 75);
            this.ListBoxBugs.Name = "ListBoxBugs";
            this.ListBoxBugs.Size = new System.Drawing.Size(523, 82);
            this.ListBoxBugs.TabIndex = 37;
            this.ListBoxBugs.SelectedIndexChanged += new System.EventHandler(this.ListBoxBugs_SelectedIndexChanged);
            // 
            // BtnSaveBug
            // 
            this.BtnSaveBug.Location = new System.Drawing.Point(279, 571);
            this.BtnSaveBug.Name = "BtnSaveBug";
            this.BtnSaveBug.Size = new System.Drawing.Size(85, 32);
            this.BtnSaveBug.TabIndex = 36;
            this.BtnSaveBug.Text = "Save";
            this.BtnSaveBug.UseVisualStyleBackColor = true;
            this.BtnSaveBug.Click += new System.EventHandler(this.BtnSaveBug_Click);
            // 
            // Label24
            // 
            this.Label24.AutoSize = true;
            this.Label24.Location = new System.Drawing.Point(22, 352);
            this.Label24.Name = "Label24";
            this.Label24.Size = new System.Drawing.Size(81, 13);
            this.Label24.TabIndex = 34;
            this.Label24.Text = "Bug Information";
            // 
            // Label11
            // 
            this.Label11.AutoSize = true;
            this.Label11.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.Label11.Location = new System.Drawing.Point(312, 380);
            this.Label11.Name = "Label11";
            this.Label11.Size = new System.Drawing.Size(52, 13);
            this.Label11.TabIndex = 32;
            this.Label11.Text = "Rep Steps";
            // 
            // LblBugID
            // 
            this.LblBugID.AutoSize = true;
            this.LblBugID.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.LblBugID.Location = new System.Drawing.Point(113, 380);
            this.LblBugID.Name = "LblBugID";
            this.LblBugID.Size = new System.Drawing.Size(0, 13);
            this.LblBugID.TabIndex = 31;
            // 
            // BtnBugDelete
            // 
            this.BtnBugDelete.Location = new System.Drawing.Point(433, 571);
            this.BtnBugDelete.Name = "BtnBugDelete";
            this.BtnBugDelete.Size = new System.Drawing.Size(85, 32);
            this.BtnBugDelete.TabIndex = 27;
            this.BtnBugDelete.Text = "Delete Bug";
            this.BtnBugDelete.UseVisualStyleBackColor = true;
            this.BtnBugDelete.Click += new System.EventHandler(this.BtnBugDelete_Click);
            // 
            // Label17
            // 
            this.Label17.AutoSize = true;
            this.Label17.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.Label17.Location = new System.Drawing.Point(534, 378);
            this.Label17.Name = "Label17";
            this.Label17.Size = new System.Drawing.Size(94, 13);
            this.Label17.TabIndex = 26;
            this.Label17.Text = "Update Comments:";
            // 
            // Label16
            // 
            this.Label16.AutoSize = true;
            this.Label16.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.Label16.Location = new System.Drawing.Point(112, 168);
            this.Label16.Name = "Label16";
            this.Label16.Size = new System.Drawing.Size(87, 13);
            this.Label16.TabIndex = 25;
            this.Label16.Text = "Bug Activity Log:";
            // 
            // LblBugSubmitDate
            // 
            this.LblBugSubmitDate.AutoSize = true;
            this.LblBugSubmitDate.Location = new System.Drawing.Point(113, 411);
            this.LblBugSubmitDate.Name = "LblBugSubmitDate";
            this.LblBugSubmitDate.Size = new System.Drawing.Size(0, 13);
            this.LblBugSubmitDate.TabIndex = 24;
            // 
            // BoxBugStatus
            // 
            this.BoxBugStatus.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.BoxBugStatus.FormattingEnabled = true;
            this.BoxBugStatus.Items.AddRange(new object[] {
            "Open",
            "Closed"});
            this.BoxBugStatus.Location = new System.Drawing.Point(109, 474);
            this.BoxBugStatus.Name = "BoxBugStatus";
            this.BoxBugStatus.Size = new System.Drawing.Size(121, 21);
            this.BoxBugStatus.TabIndex = 23;
            // 
            // LblFixDate
            // 
            this.LblFixDate.AutoSize = true;
            this.LblFixDate.Font = new System.Drawing.Font("Corbel", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.LblFixDate.Location = new System.Drawing.Point(111, 511);
            this.LblFixDate.Name = "LblFixDate";
            this.LblFixDate.Size = new System.Drawing.Size(0, 13);
            this.LblFixDate.TabIndex = 22;
            // 
            // TxtBugDescription
            // 
            this.TxtBugDescription.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.TxtBugDescription.Location = new System.Drawing.Point(109, 441);
            this.TxtBugDescription.Name = "TxtBugDescription";
            this.TxtBugDescription.Size = new System.Drawing.Size(156, 21);
            this.TxtBugDescription.TabIndex = 21;
            // 
            // Label15
            // 
            this.Label15.AutoSize = true;
            this.Label15.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.Label15.Location = new System.Drawing.Point(40, 444);
            this.Label15.Name = "Label15";
            this.Label15.Size = new System.Drawing.Size(63, 13);
            this.Label15.TabIndex = 20;
            this.Label15.Text = "Description:";
            // 
            // Label14
            // 
            this.Label14.AutoSize = true;
            this.Label14.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.Label14.Location = new System.Drawing.Point(40, 378);
            this.Label14.Name = "Label14";
            this.Label14.Size = new System.Drawing.Size(41, 13);
            this.Label14.TabIndex = 19;
            this.Label14.Text = "Bug ID:";
            // 
            // Label13
            // 
            this.Label13.AutoSize = true;
            this.Label13.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.Label13.Location = new System.Drawing.Point(40, 411);
            this.Label13.Name = "Label13";
            this.Label13.Size = new System.Drawing.Size(67, 13);
            this.Label13.TabIndex = 18;
            this.Label13.Text = "Submit Date:";
            // 
            // Label12
            // 
            this.Label12.AutoSize = true;
            this.Label12.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.Label12.Location = new System.Drawing.Point(312, 464);
            this.Label12.Name = "Label12";
            this.Label12.Size = new System.Drawing.Size(38, 13);
            this.Label12.TabIndex = 17;
            this.Label12.Text = "Details";
            // 
            // Label10
            // 
            this.Label10.AutoSize = true;
            this.Label10.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.Label10.Location = new System.Drawing.Point(41, 477);
            this.Label10.Name = "Label10";
            this.Label10.Size = new System.Drawing.Size(39, 13);
            this.Label10.TabIndex = 15;
            this.Label10.Text = "Status:";
            // 
            // Label9
            // 
            this.Label9.AutoSize = true;
            this.Label9.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.Label9.Location = new System.Drawing.Point(42, 510);
            this.Label9.Name = "Label9";
            this.Label9.Size = new System.Drawing.Size(47, 13);
            this.Label9.TabIndex = 14;
            this.Label9.Text = "Fix Date:";
            // 
            // ComboBoxStatusCodes
            // 
            this.ComboBoxStatusCodes.DisplayMember = "Hi";
            this.ComboBoxStatusCodes.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.ComboBoxStatusCodes.FormattingEnabled = true;
            this.ComboBoxStatusCodes.Location = new System.Drawing.Point(315, 39);
            this.ComboBoxStatusCodes.Name = "ComboBoxStatusCodes";
            this.ComboBoxStatusCodes.Size = new System.Drawing.Size(121, 21);
            this.ComboBoxStatusCodes.TabIndex = 13;
            this.ComboBoxStatusCodes.SelectedIndexChanged += new System.EventHandler(this.ComboBoxStatusCodes_SelectedIndexChanged);
            // 
            // Label8
            // 
            this.Label8.AutoSize = true;
            this.Label8.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.Label8.Location = new System.Drawing.Point(246, 42);
            this.Label8.Name = "Label8";
            this.Label8.Size = new System.Drawing.Size(66, 13);
            this.Label8.TabIndex = 12;
            this.Label8.Text = "Status Filter:";
            // 
            // BoxApplicationSelection
            // 
            this.BoxApplicationSelection.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.BoxApplicationSelection.FormattingEnabled = true;
            this.BoxApplicationSelection.Location = new System.Drawing.Point(315, 12);
            this.BoxApplicationSelection.Name = "BoxApplicationSelection";
            this.BoxApplicationSelection.Size = new System.Drawing.Size(121, 21);
            this.BoxApplicationSelection.TabIndex = 11;
            this.BoxApplicationSelection.SelectedIndexChanged += new System.EventHandler(this.BoxApplicationSelection_SelectedIndexChanged);
            // 
            // Label7
            // 
            this.Label7.AutoSize = true;
            this.Label7.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.Label7.Location = new System.Drawing.Point(246, 12);
            this.Label7.Name = "Label7";
            this.Label7.Size = new System.Drawing.Size(64, 13);
            this.Label7.TabIndex = 10;
            this.Label7.Text = "Application:";
            // 
            // TxtUpdateComments
            // 
            this.TxtUpdateComments.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.TxtUpdateComments.Location = new System.Drawing.Point(536, 393);
            this.TxtUpdateComments.Multiline = true;
            this.TxtUpdateComments.Name = "TxtUpdateComments";
            this.TxtUpdateComments.Size = new System.Drawing.Size(223, 144);
            this.TxtUpdateComments.TabIndex = 9;
            // 
            // TxtBugRepSteps
            // 
            this.TxtBugRepSteps.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.TxtBugRepSteps.Location = new System.Drawing.Point(315, 393);
            this.TxtBugRepSteps.Multiline = true;
            this.TxtBugRepSteps.Name = "TxtBugRepSteps";
            this.TxtBugRepSteps.Size = new System.Drawing.Size(156, 60);
            this.TxtBugRepSteps.TabIndex = 8;
            // 
            // TxtBugDetails
            // 
            this.TxtBugDetails.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.TxtBugDetails.Location = new System.Drawing.Point(315, 477);
            this.TxtBugDetails.Multiline = true;
            this.TxtBugDetails.Name = "TxtBugDetails";
            this.TxtBugDetails.Size = new System.Drawing.Size(156, 60);
            this.TxtBugDetails.TabIndex = 7;
            // 
            // Label26
            // 
            this.Label26.AutoSize = true;
            this.Label26.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.Label26.Location = new System.Drawing.Point(26, 358);
            this.Label26.MinimumSize = new System.Drawing.Size(750, 200);
            this.Label26.Name = "Label26";
            this.Label26.Size = new System.Drawing.Size(750, 200);
            this.Label26.TabIndex = 35;
            // 
            // TabUsers
            // 
            this.TabUsers.Controls.Add(this.Label27);
            this.TabUsers.Controls.Add(this.ListBoxUsers);
            this.TabUsers.Controls.Add(this.Label23);
            this.TabUsers.Controls.Add(this.BtnUsersDelete);
            this.TabUsers.Controls.Add(this.BtnUsersSave);
            this.TabUsers.Controls.Add(this.LblUserID);
            this.TabUsers.Controls.Add(this.TxtAddUserTel);
            this.TabUsers.Controls.Add(this.TxtAddUserEmail);
            this.TabUsers.Controls.Add(this.TxtAddUserName);
            this.TabUsers.Controls.Add(this.Label22);
            this.TabUsers.Controls.Add(this.label21);
            this.TabUsers.Controls.Add(this.Label20);
            this.TabUsers.Controls.Add(this.Label19);
            this.TabUsers.Controls.Add(this.Label18);
            this.TabUsers.Location = new System.Drawing.Point(4, 22);
            this.TabUsers.Name = "TabUsers";
            this.TabUsers.Size = new System.Drawing.Size(832, 732);
            this.TabUsers.TabIndex = 3;
            this.TabUsers.Text = "Users";
            this.TabUsers.UseVisualStyleBackColor = true;
            // 
            // Label27
            // 
            this.Label27.AutoSize = true;
            this.Label27.Location = new System.Drawing.Point(501, 120);
            this.Label27.Name = "Label27";
            this.Label27.Size = new System.Drawing.Size(34, 13);
            this.Label27.TabIndex = 15;
            this.Label27.Text = "Users";
            // 
            // ListBoxUsers
            // 
            this.ListBoxUsers.FormattingEnabled = true;
            this.ListBoxUsers.Location = new System.Drawing.Point(504, 136);
            this.ListBoxUsers.Name = "ListBoxUsers";
            this.ListBoxUsers.Size = new System.Drawing.Size(198, 264);
            this.ListBoxUsers.TabIndex = 14;
            this.ListBoxUsers.SelectedIndexChanged += new System.EventHandler(this.ListBoxUsers_SelectedIndexChanged);
            // 
            // Label23
            // 
            this.Label23.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.Label23.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.Label23.Location = new System.Drawing.Point(466, 136);
            this.Label23.Name = "Label23";
            this.Label23.Size = new System.Drawing.Size(2, 268);
            this.Label23.TabIndex = 13;
            // 
            // BtnUsersDelete
            // 
            this.BtnUsersDelete.Location = new System.Drawing.Point(610, 406);
            this.BtnUsersDelete.Name = "BtnUsersDelete";
            this.BtnUsersDelete.Size = new System.Drawing.Size(92, 39);
            this.BtnUsersDelete.TabIndex = 12;
            this.BtnUsersDelete.Text = "Delete";
            this.BtnUsersDelete.UseVisualStyleBackColor = true;
            this.BtnUsersDelete.Click += new System.EventHandler(this.BtnUsersDelete_Click);
            // 
            // BtnUsersSave
            // 
            this.BtnUsersSave.Location = new System.Drawing.Point(504, 406);
            this.BtnUsersSave.Name = "BtnUsersSave";
            this.BtnUsersSave.Size = new System.Drawing.Size(100, 39);
            this.BtnUsersSave.TabIndex = 11;
            this.BtnUsersSave.Text = "Save";
            this.BtnUsersSave.UseVisualStyleBackColor = true;
            this.BtnUsersSave.Click += new System.EventHandler(this.BtnUsersSave_Click);
            // 
            // LblUserID
            // 
            this.LblUserID.AutoSize = true;
            this.LblUserID.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.LblUserID.Location = new System.Drawing.Point(200, 199);
            this.LblUserID.Name = "LblUserID";
            this.LblUserID.Size = new System.Drawing.Size(0, 13);
            this.LblUserID.TabIndex = 9;
            // 
            // TxtAddUserTel
            // 
            this.TxtAddUserTel.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.TxtAddUserTel.Location = new System.Drawing.Point(200, 301);
            this.TxtAddUserTel.Name = "TxtAddUserTel";
            this.TxtAddUserTel.Size = new System.Drawing.Size(199, 21);
            this.TxtAddUserTel.TabIndex = 8;
            // 
            // TxtAddUserEmail
            // 
            this.TxtAddUserEmail.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.TxtAddUserEmail.Location = new System.Drawing.Point(200, 267);
            this.TxtAddUserEmail.Name = "TxtAddUserEmail";
            this.TxtAddUserEmail.Size = new System.Drawing.Size(199, 21);
            this.TxtAddUserEmail.TabIndex = 7;
            // 
            // TxtAddUserName
            // 
            this.TxtAddUserName.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.TxtAddUserName.Location = new System.Drawing.Point(200, 232);
            this.TxtAddUserName.Name = "TxtAddUserName";
            this.TxtAddUserName.Size = new System.Drawing.Size(199, 21);
            this.TxtAddUserName.TabIndex = 6;
            // 
            // Label22
            // 
            this.Label22.AutoSize = true;
            this.Label22.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.Label22.Location = new System.Drawing.Point(113, 305);
            this.Label22.Name = "Label22";
            this.Label22.Size = new System.Drawing.Size(79, 13);
            this.Label22.TabIndex = 5;
            this.Label22.Text = "Phone Number:";
            // 
            // label21
            // 
            this.label21.AutoSize = true;
            this.label21.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.label21.Location = new System.Drawing.Point(113, 270);
            this.label21.Name = "label21";
            this.label21.Size = new System.Drawing.Size(36, 13);
            this.label21.TabIndex = 4;
            this.label21.Text = "Email:";
            // 
            // Label20
            // 
            this.Label20.AutoSize = true;
            this.Label20.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.Label20.Location = new System.Drawing.Point(113, 232);
            this.Label20.Name = "Label20";
            this.Label20.Size = new System.Drawing.Size(57, 13);
            this.Label20.TabIndex = 3;
            this.Label20.Text = "UserName:";
            // 
            // Label19
            // 
            this.Label19.AutoSize = true;
            this.Label19.Font = new System.Drawing.Font("Corbel", 8.25F);
            this.Label19.Location = new System.Drawing.Point(113, 199);
            this.Label19.Name = "Label19";
            this.Label19.Size = new System.Drawing.Size(42, 13);
            this.Label19.TabIndex = 2;
            this.Label19.Text = "User ID:";
            // 
            // Label18
            // 
            this.Label18.Font = new System.Drawing.Font("Corbel", 20F);
            this.Label18.Location = new System.Drawing.Point(6, 0);
            this.Label18.Name = "Label18";
            this.Label18.Size = new System.Drawing.Size(251, 46);
            this.Label18.TabIndex = 1;
            this.Label18.Text = "User Manager";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(821, 634);
            this.Controls.Add(this.TabControl1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.Shown += new System.EventHandler(this.Form1_Shown);
            this.TabControl1.ResumeLayout(false);
            this.TabIdentify.ResumeLayout(false);
            this.TabIdentify.PerformLayout();
            this.TabApplications.ResumeLayout(false);
            this.TabApplications.PerformLayout();
            this.TabBugs.ResumeLayout(false);
            this.TabBugs.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DataGridBugLog)).EndInit();
            this.TabUsers.ResumeLayout(false);
            this.TabUsers.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl TabControl1;
        private System.Windows.Forms.TabPage TabIdentify;
        private System.Windows.Forms.Label Label1;
        private System.Windows.Forms.TabPage TabApplications;
        private System.Windows.Forms.TabPage TabBugs;
        private System.Windows.Forms.TabPage TabUsers;
        private System.Windows.Forms.Button BtnGo;
        private System.Windows.Forms.TextBox TxtUserName;
        private System.Windows.Forms.Label Label2;
        private System.Windows.Forms.Button BtnDelete;
        private System.Windows.Forms.Button BtnSave;
        private System.Windows.Forms.TextBox TxtDescription;
        private System.Windows.Forms.TextBox TxtCurrentVersion;
        private System.Windows.Forms.Label LblAppID;
        private System.Windows.Forms.TextBox TxtAppName;
        private System.Windows.Forms.Label Label6;
        private System.Windows.Forms.Label Label5;
        private System.Windows.Forms.Label Label4;
        private System.Windows.Forms.Label Label3;
        private System.Windows.Forms.Label LblApplicationManager;
        private System.Windows.Forms.TextBox TxtBugDetails;
        private System.Windows.Forms.TextBox TxtUpdateComments;
        private System.Windows.Forms.TextBox TxtBugRepSteps;
        private System.Windows.Forms.Label LblFixDate;
        private System.Windows.Forms.TextBox TxtBugDescription;
        private System.Windows.Forms.Label Label15;
        private System.Windows.Forms.Label Label14;
        private System.Windows.Forms.Label Label13;
        private System.Windows.Forms.Label Label12;
        private System.Windows.Forms.Label Label10;
        private System.Windows.Forms.Label Label9;
        private System.Windows.Forms.ComboBox ComboBoxStatusCodes;
        private System.Windows.Forms.Label Label8;
        private System.Windows.Forms.ComboBox BoxApplicationSelection;
        private System.Windows.Forms.Label Label7;
        private System.Windows.Forms.Label LblBugSubmitDate;
        private System.Windows.Forms.Label Label16;
        private System.Windows.Forms.Button BtnBugDelete;
        private System.Windows.Forms.Label Label17;
        private System.Windows.Forms.Label Label23;
        private System.Windows.Forms.Button BtnUsersDelete;
        private System.Windows.Forms.Button BtnUsersSave;
        private System.Windows.Forms.Label LblUserID;
        private System.Windows.Forms.TextBox TxtAddUserTel;
        private System.Windows.Forms.TextBox TxtAddUserEmail;
        private System.Windows.Forms.TextBox TxtAddUserName;
        private System.Windows.Forms.Label Label22;
        private System.Windows.Forms.Label label21;
        private System.Windows.Forms.Label Label20;
        private System.Windows.Forms.Label Label19;
        private System.Windows.Forms.Label Label18;
        private System.Windows.Forms.Label LblBugID;
        private System.Windows.Forms.Label Label24;
        private System.Windows.Forms.Label Label11;
        private System.Windows.Forms.Label Label26;
        private System.Windows.Forms.Button BtnSaveBug;
        private System.Windows.Forms.ListBox ListBoxApplications;
        private System.Windows.Forms.ListBox ListBoxUsers;
        private System.Windows.Forms.ListBox ListBoxBugs;
        private System.Windows.Forms.ComboBox BoxBugStatus;
        private System.Windows.Forms.DataGridView DataGridBugLog;
        private System.Windows.Forms.Label Label25;
        private System.Windows.Forms.Label Label27;
    }
}

