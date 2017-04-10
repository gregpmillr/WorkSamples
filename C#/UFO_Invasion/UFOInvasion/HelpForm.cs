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

namespace UFOInvasion
{
    public partial class HelpForm : Form
    {
        public delegate void CloseWindowHandler();
        UFOInvasionForm form;

        public HelpForm(CultureInfo culture)
        {
            InitializeComponent();
            Thread.CurrentThread.CurrentCulture = culture;
            Thread.CurrentThread.CurrentUICulture = culture;
        }
        public HelpForm(CultureInfo culture, UFOInvasionForm form)
        {
            InitializeComponent();
            this.form = form;
            Thread.CurrentThread.CurrentCulture = culture;
            Thread.CurrentThread.CurrentUICulture = culture;
        }

        private void HelpForm_KeyDown(object sender, KeyEventArgs e)
        {
          
            if (e.KeyCode == Keys.H)
            {
                CloseWindowHandler close = new CloseWindowHandler(form.startTimer);
                close();
                this.Close();
            }
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }


    }
}
