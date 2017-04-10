using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;
using SpaceInvaders_Models;
using System.Data.Entity;

namespace UFOInvasion
{
    public partial class LoadGameForm : Form
    {
        public delegate void LoadGameHandler(SavedState StatePassed);
        UFOInvasionForm form;

        public LoadGameForm(CultureInfo culture, UFOInvasionForm form)
        {
            InitializeComponent();
            Thread.CurrentThread.CurrentCulture = culture;
            Thread.CurrentThread.CurrentUICulture = culture;
            this.form = form;
            DBContext DB = new DBContext();
            DB.SavedState.Load();
            lstGames.DataSource = DB.SavedState.Local.ToBindingList();
            lstGames.DisplayMember = "StateName";
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void btnLoad_Click(object sender, EventArgs e)
        {
            SavedState SelectedState = (SavedState)lstGames.SelectedItem;
            LoadGameHandler load = new LoadGameHandler(form.loadGame);
            load(SelectedState);
            this.Close();
        }
    }
}
