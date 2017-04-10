using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Globalization;
using System.Linq;
using System.Reflection;
using System.Resources;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;
using SpaceInvaders_Models;
using System.Data.Entity;

namespace UFOInvasion
{
    public partial class UFOInvasionForm : Form
    {

        CultureInfo culture = new CultureInfo("en-CA");
        ComponentResourceManager resources = new ComponentResourceManager(typeof(UFOInvasionForm));
        // ResourceManager rm = new ResourceManager("", typeof(Form1).Assembly);
        ResourceManager rm = new ResourceManager("UFO_Invasion.Properties.Resources", Assembly.GetExecutingAssembly());
        HelpForm helpForm;

        Jet jet;
        SavedState StateGlobal;
        
        HashSet<UFO> ufos = new HashSet<UFO>();
        HashSet<Missile> missiles = new HashSet<Missile>();
        HashSet<Laser> lasers = new HashSet<Laser>();
        HashSet<Cloud> clouds = new HashSet<Cloud>();
        List<SpaceInvaders_Models.UFO> LoadedUfos = new List<SpaceInvaders_Models.UFO>();
        SpaceInvaders_Models.Jet LoadedJet = new SpaceInvaders_Models.Jet();

        string[] ufoImages = { @"res/ufo1.png" };
        string[] cloudImages = { @"res/cloud1.png", @"res/cloud2.png", "res/cloud3.png" };

        Random random = new Random();

        Font smallFont = new Font("Consolas", 16);
        Font bigFont = new Font("Consolas", 50);
        SolidBrush brush = new SolidBrush(Color.Navy);

        int lives = 10;
        int score = 10;
        int level = 1;

        bool gameOver = false;
        bool win = false;
        bool pause = true;
        bool start = false;
        int timerCounter = 0;
        string instruction;
        public UFOInvasionForm()
        {
            InitializeComponent();
            Thread.CurrentThread.CurrentCulture = culture;
            Thread.CurrentThread.CurrentUICulture = culture;
        }

        private void UFOInvasionForm_Load(object sender, EventArgs e)
        {
            //adjust form to fill screen
            WindowState = FormWindowState.Maximized;
        }

        private void StartNew(int level)
        {
            //reinitialize hashsets
            ufos = new HashSet<UFO>();
            missiles = new HashSet<Missile>();
            lasers = new HashSet<Laser>();
            clouds = new HashSet<Cloud>();

            //reinitialize jet object
            jet = new Jet(this.DisplayRectangle,100,100);

            //loop to add fresh set of enemies to hashset
            for (int y = 0; y < 3; y++)
            {
                for (int x = 0; x < 6; x++)
                {
                    //new ufo - takes in game area, x+y coordinates, the image path for the current enemy type,
                    // and the current difficulty level
                    ufos.Add(new UFO(this.DisplayRectangle, y, x, level, 100, 100, ufoImages[0], false));
                }
            } 

            //invalidate screen to repaint
            Invalidate();
        }

        private void UFOInvasionForm_Paint(object sender, PaintEventArgs e)
        {
            //draw all game objects
            DrawGameObjects(e);

            //draw all strings to be displayed
            DrawStrings(e);

            //check for win
            DrawWinLose(e);
        }

        private void DrawGameObjects(PaintEventArgs e)
        {
            //refill cloud list to replace any that went off screen
            while (clouds.Count < 10)
            {
                //cloud requires game area, image path chosen at random, and a random x value for screen placement
                clouds.Add(new Cloud(this.DisplayRectangle, random.Next(1, 5), random.Next(0, this.DisplayRectangle.Right),500,800,cloudImages[random.Next(0, 2)]));
            }
            //draw each cloud
            foreach (Cloud cloud in clouds)
            {
                cloud.Draw(e.Graphics);
            }
            //draw each missile
            foreach (Missile missile in missiles)
            {
                missile.Draw(e.Graphics);
            }
            //draw each laser
            foreach (Laser laser in lasers)
            {
                laser.Draw(e.Graphics);
            }
            //draw each ufo
            foreach (UFO ufo in ufos)
            {
                ufo.Draw(e.Graphics);
            }
            //draw jet (if initialized)
            if (jet != null)
            {
                jet.Draw(e.Graphics);
            }
        }

        private void DrawStrings(PaintEventArgs e)
        {
            //display life and score count on screen if game is in progress
            if (timerCounter != 0)
            {
                string scoreDisplay = Properties.Resources.livesScore;
                Point p = new Point(20, 50);
                e.Graphics.DrawString(String.Format(scoreDisplay, lives.ToString(), score.ToString()), smallFont, brush, p);
            }
            //if start of new game or level, display enter to begin dialog, and current level
            if (timerCounter < 1)
            {
                 instruction = Properties.Resources.enter;
                Point p = new Point(((this.DisplayRectangle.Width / 2) - 400), this.DisplayRectangle.Height / 3);
                e.Graphics.DrawString(instruction, bigFont, brush, p);

                string lvl = Properties.Resources.level;
                p = new Point(((this.DisplayRectangle.Width / 2) - 200), this.DisplayRectangle.Height / 2);
                e.Graphics.DrawString(String.Format(lvl, level.ToString()), bigFont, brush, p);

                if (pause)
                {
                    timer1.Stop();
                }
            }
        }

        private void DrawWinLose(PaintEventArgs e)
        {
            //string var to hold win/loss messages
            string message = "";

            //if loss
            if (gameOver)
            {
                //display game over message
                message = Properties.Resources.gameOver;
                Point p = new Point(((this.DisplayRectangle.Width / 2) - 200), this.DisplayRectangle.Height / 3);
                e.Graphics.DrawString(message, bigFont, brush, p);

                //display game over message
                message = Properties.Resources.score;
                p = new Point(((this.DisplayRectangle.Width / 2) - 200), this.DisplayRectangle.Height / 2);
                e.Graphics.DrawString(String.Format(message, score.ToString()), bigFont, brush, p);

                timer1.Stop();
            }

            //if won
            if (win)
            {
                // and level is greater than 3 (meaning 5 levels total)
                if (level > 3)
                {
                    //display game over message
                    message = Properties.Resources.win;
                    Point p = new Point(((this.DisplayRectangle.Width / 2) - 200), this.DisplayRectangle.Height / 3);
                    e.Graphics.DrawString(message, bigFont, brush, p);

                    //display game over message
                    message = Properties.Resources.score;
                    p = new Point((this.DisplayRectangle.Width / 2 - 200), this.DisplayRectangle.Height / 2);
                    e.Graphics.DrawString(String.Format(message, score.ToString()), bigFont, brush, p);
                }
                else
                {
                    timerCounter = 0;
                    DrawStrings(e);
                    NextLevel();
                }
            }
        }

        private void NextLevel()
        {
            ////stop the timer
            //timer1.Stop();
            //increment the level
            level++;
            //reset win
            win = false;
            //pause = true;

            //reload screen with new enemies
            StartNew(level);
        }

        private void UFOInvasionForm_KeyDown(object sender, KeyEventArgs e)
        {
            //perform action depending on key pressed
            switch (e.KeyCode)
            {
                //right arrow
                case Keys.Right:
                    {
                        //move right
                        jet.Move(Jet.Direction.Right);
                        break;
                    }
                //left arrow
                case Keys.Left:
                    {
                        //move left
                        jet.Move(Jet.Direction.Left);
                        break;
                    }
                //space bar
                case Keys.Space:
                    {
                        //add missile to hashset
                        missiles.Add(new Missile(this.DisplayRectangle, jet.XPosition, jet.YPosition,30,10, @"res/missile.png"));
                        break;
                    }
                //escape key
                case Keys.Escape:
                    {
                        //menu
                        break;
                    }

                case Keys.P:
                    {
                        if (timer1.Enabled == false && pause && start)
                        {
                            timer1.Enabled = true;
                            pause = false;
                        }
                        else
                        {
                            timer1.Enabled = false;
                            pause = true;
                        }

                        break;
                    }
                //enter key
                case Keys.Enter://TODO: fix this so it doesn't move stuff when game is not on
                    {
                        if (!gameOver && !start )
                        {
                            pause = false;
                                timer1.Start();
                            start = true;
                        }
               
                        break;
                    }
                case Keys.H:
                    {
                        helpForm = new HelpForm(culture, this);
                        
                        if (!timer1.Enabled && helpForm.Visible == false)//on pause & form visible
                        {
                            helpForm.Show();
                        }
                        else if (timer1.Enabled && helpForm.Visible == false)//going & Form not visible
                        {
                            timer1.Stop();
                            helpForm.Show();
                        }

                       
                    }

                    break;
            }
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            //increment tick count
            timerCounter++;

            //move all game objects
            MoveAllObjects();

            //check for collisions, remove destroyed and out of bounds objects
            HandleCollisions();

            //check if win
            if (ufos.Count == 0 && LoadedUfos.Count == 0)
            {
                win = true;
            }

            //repaint
            Invalidate();
        }

        private void MoveAllObjects()
        {
            //move all missiles
            foreach (Missile missile in missiles)
            {
                missile.Move();
            }
            //move all lasers
            foreach (Laser laser in lasers)
            {
                laser.Move();
            }

            bool reverse = false;
            //move all ufos
            foreach (UFO ufo in ufos)
            {
                ufo.Move();

                //every 100 ticks, drop down and reverse direction
                //if (timerCounter % 100 == 0)
                //{
                //    ufo.DropDown();
                //    ufo.XVelocity *= -1;
                //}

                if (ufo.Right >= (this.DisplayRectangle.Right - (this.DisplayRectangle.Width / 16))
                    || ufo.Left <= (this.DisplayRectangle.Left + (this.DisplayRectangle.Width / 16)))
                {
                    reverse = true;
                }

                //randomly chance to fire laser
                if (random.Next(0, 100) == 1)
                {
                    lasers.Add(new Laser(this.DisplayRectangle, 20, ufo.XPosition, ufo.YPosition,30,10, @"res/laser1.png"));
                }
            }
            //reverse ufos if necessary
            if (reverse)
            {
                foreach (UFO ufo in ufos)
                {
                    ufo.DropDown();
                    ufo.XVelocity *= -1;
                }
            }
            //move all clouds
            foreach (Cloud cloud in clouds)
            {
                cloud.Move();
            }
        }

        private void HandleCollisions()
        {
            //check for objects in need of removal
            lasers.RemoveWhere(LaserOutOfBounds);
            missiles.RemoveWhere(MissileOutOfBounds);
            clouds.RemoveWhere(CloudOutOfBounds);
            ufos.RemoveWhere(UFOShotDown);

            //check to see if one of your missiles hits a ufo
            foreach (Missile missile in missiles)
            {
                foreach (UFO ufo in ufos)
                {
                    if (missile.Rectangle.IntersectsWith(ufo.Rectangle))
                    {
                        //teleport ufo out of bounds for cleanup
                        ufo.YPosition = 5000;
                        //teleport missile out of bounds for cleanup
                        missile.YPosition = -5000;
                        //increase score by amount multiplied by difficulty
                        score += 10 * level;
                    }
                }
            }
            //check to see if any lasers hit your jet
            foreach (Laser laser in lasers)
            {
                if (laser.Rectangle.IntersectsWith(jet.Rectangle))
                {
                    //teleport laser out of bounds for cleanup
                    laser.YPosition = 5000;
                    //remove one life
                    LoseALife();
                }
            }
            //check to see if any ufos hit your jet or reached the bottom of the screen
            foreach (UFO ufo in ufos)
            {
                 if (ufo.Rectangle.IntersectsWith(jet.Rectangle))
                {
                    gameOver = true;
                }
                if (ufo.Bottom >= this.DisplayRectangle.Bottom && ufo.Bottom < 5000)
                {
                    gameOver = true;
                }
            }
        }
        private bool LaserOutOfBounds(Laser laser)
        {
            return laser.Top > this.DisplayRectangle.Bottom;
        }

        private bool MissileOutOfBounds(Missile missile)
        {
            return missile.Bottom < this.DisplayRectangle.Top;
        }
        private bool CloudOutOfBounds(Cloud cloud)
        {
            return cloud.Top > this.DisplayRectangle.Bottom;
        }
        private bool UFOShotDown(UFO ufo)
        {
            return ufo.Top >= 5000;
        }

        private void LoseALife()
        {
            //subtract life
            lives--;

            //if lives are less than or = 0, jet is destroyed, and gameover = true
            if (lives <= 0)
            {

                jet.Explode();
                gameOver = true;
            }
        }

        private void UFOInvasionForm_FormClosed(object sender, FormClosedEventArgs e)
        {
            Application.Exit();
        }

        private void newGameToolStripMenuItem_Click(object sender, EventArgs e)
        {
            //stop timer if running
            if (timer1.Enabled)
            {
                timer1.Stop();
            }
            //reset level to 1
            level = 1;
            //reset timer counter
            timerCounter = 0;
            //reset score
            score = 0;
            //reset lives
            lives = 10;
            //reset gameover
            gameOver = false;
            //reset win
            win = false;
            start = false;
            //start new game
            StartNew(level);
        }

        private void englishToolStripMenuItem_Click(object sender, EventArgs e)
        {
            culture = new CultureInfo("en-CA");

            changeCulture();
        }

        private void francaisToolStripMenuItem_Click(object sender, EventArgs e)
        {
            culture = new CultureInfo("fr-CA");
            changeCulture();
        }

        void changeCulture()
        {

            Thread.CurrentThread.CurrentCulture = culture;
            Thread.CurrentThread.CurrentUICulture = culture;
            foreach (ToolStripMenuItem item in menuStrip1.Items) // here i changed the var item to ToolStripItem
            {
                resources.ApplyResources(item, item.Name, Thread.CurrentThread.CurrentUICulture);
                foreach (ToolStripDropDownItem b in item.DropDownItems)
                {
                    resources.ApplyResources(b, b.Name, Thread.CurrentThread.CurrentUICulture);
                }
            }
            Invalidate();
        }

        private void sortieToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void highScoresToolStripMenuItem_Click(object sender, EventArgs e)
        {
            HighScoreForm hs = new HighScoreForm(culture);
            hs.Show();
        }

        private void loadGameToolStripMenuItem_Click(object sender, EventArgs e)
        {
            timer1.Stop();
            pause = true;
            LoadGameForm lg = new LoadGameForm(culture, this);
            lg.Show();
        }

        public void startTimer()
        {
            timer1.Start();
        }

        public void loadGame(SavedState StatePassed)
        {

            MessageBox.Show(string.Format(Properties.Resources.gameLoaded, StatePassed.StateName));
            StateGlobal = StatePassed;
            score = StatePassed.HighScore;

            DBContext DB = new DBContext();
            //SpaceInvaders_Models.SavedState ss = DB.SavedState.Where(i => i.SavedStateId == StatePassed.SavedStateId).Single();
            //level = ss.
            DB.JetSet.Load();
            LoadedJet = DB.JetSet.Where(i => i.State.SavedStateId == StatePassed.SavedStateId).Single();
            jet = new Jet(this.DisplayRectangle, 100, 100);


            DB.UfoSet.Load();
            LoadedUfos = DB.UfoSet.Where(i => i.State.SavedStateId == StatePassed.SavedStateId).ToList();

            ufos.Clear();

            foreach (SpaceInvaders_Models.UFO ufo in LoadedUfos)
            {
                ufos.Add(new UFO(this.DisplayRectangle, ufo.Row, ufo.Offset, ufo.Difficulty, ufo.Height, ufo.Width, ufoImages[0], true));
            }//End foreach

            instruction += StatePassed.StateName;
            LoadedUfos.Clear();
            timer1.Start();
        }//End loading games

        private void saveGameToolStripMenuItem_Click(object sender, EventArgs e)
        {
            timer1.Stop();
            pause = true;
            string gameName = Microsoft.VisualBasic.Interaction.InputBox(Properties.Resources.saveGame, Properties.Resources.saveGameTitle, "");

            if (gameName != "")
            {
                try
                {
                    if (StateGlobal == null)
                    {
                        SavedState Save = new SavedState();
                        Save.HighScore = score;
                        Save.CurrentDate = DateTime.Now;
                        Save.StateName = gameName;

                        SpaceInvaders_Models.Jet JetSave = new SpaceInvaders_Models.Jet();
                        JetSave.Height = jet.Height;
                        JetSave.Width = jet.Width;
                        JetSave.State = Save;

                        DBContext DB = new DBContext();
                        DB.SavedState.Add(Save);
                        DB.JetSet.Add(JetSave);
                        DB.SaveChanges();

                        foreach (UFO ufo in ufos)
                        {
                            SpaceInvaders_Models.UFO UfoSave = new SpaceInvaders_Models.UFO();
                            UfoSave.Row = ufo.YPosition;
                            UfoSave.Offset = ufo.XPosition;
                            UfoSave.Difficulty = level;
                            UfoSave.Height = ufo.ObjHeight;
                            UfoSave.Width = ufo.ObjWidth;
                            UfoSave.State = Save;
                            DB.UfoSet.Add(UfoSave);
                            DB.SaveChanges();
                        }//End foreach

                        MessageBox.Show(Properties.Resources.gameSaved + gameName);
                    }
                    else
                    {
                        DBContext DB = new DBContext();
                        SavedState Save = DB.SavedState.SingleOrDefault(b => b.SavedStateId == StateGlobal.SavedStateId);
                        Save.HighScore = score;
                        Save.CurrentDate = DateTime.Now;
                        DB.SaveChanges();

                        DB.UfoSet.Load();
                        LoadedUfos = DB.UfoSet.Where(i => i.State.SavedStateId == Save.SavedStateId).ToList();

                        foreach (SpaceInvaders_Models.UFO ufo in LoadedUfos)
                        {
                            DB.UfoSet.Remove(ufo);
                            DB.SaveChanges();
                        }//End foreach
                   
                        foreach (UFO ufo in ufos)
                        {
                            SpaceInvaders_Models.UFO UfoSave = new SpaceInvaders_Models.UFO();
                            UfoSave.Row = ufo.YPosition;
                            UfoSave.Offset = ufo.XPosition;
                            UfoSave.Difficulty = level;
                            UfoSave.Height = ufo.ObjHeight;
                            UfoSave.Width = ufo.ObjWidth;
                            UfoSave.State = Save;
                            DB.UfoSet.Add(UfoSave);
                            DB.SaveChanges();
                        }//End foreach
          
                        MessageBox.Show(Properties.Resources.gameSaved + gameName);
                    }//End if
                }
                catch (Exception ex)
                {
                    MessageBox.Show(Properties.Resources.gameSaveFailed);
                }//End try catch
            }//End if
            timer1.Start();
        }//End save

    }//End form
}//EOF
