using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Threading;
using System.Windows.Forms;
using SpaceInvaders_Models;
using System.Data.Entity;

namespace UFOInvasion
{
    public partial class HighScoreForm : Form
    {
        public HighScoreForm(CultureInfo culture)
        {
            InitializeComponent();
            Thread.CurrentThread.CurrentCulture = culture;
            Thread.CurrentThread.CurrentUICulture = culture;
            DBContext DB = new DBContext();
            DB.SavedState.Load();
            List<SavedState> Scores = new List<SavedState>();
            Scores = DB.SavedState.ToList();
            Scores = Scores.OrderByDescending(o => o.HighScore).ToList();
            string Output = Properties.Resources.leaderTitle;
            lstScores.Items.Add(Output);

            for (int x = 0; x < 5; x++)
            {
                Output = String.Format(Properties.Resources.highScore, (x + 1).ToString(), Scores[x].StateName.ToString(), Scores[x].HighScore.ToString());

                lstScores.Items.Add(Output);
            }//End for
        }//End constructor

     
        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }//End button click

        private void HighScoreForm_Load(object sender, EventArgs e)
        {

        }
    }//End form
}//EOF
