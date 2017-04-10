using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UFOInvasion
{
    public class UFO : GameObject
    {

        public int XVelocity { get; set; }

      //  public UFO(Rectangle gameplayArea, int row, int offset, int difficulty, int Height, int Width, string ImagePath, bool Load) : base(gameplayArea, Height, (int)(gameplayArea.Width / 16), ImagePath)
        public UFO(Rectangle gameplayArea, int row, int offset, int difficulty, int Height, int Width, string ImagePath, bool Load) : base(gameplayArea, Height, Width, ImagePath)
        {


            //set difficulty modifier
            double modifier = 1 + (0.5 * difficulty);

            //set screen size modifier
            int screenSizeModifier = (int)(gameplayArea.Width / 16);

            //set placement of ufo based on screen size
            if (Load)
            {
          
                ImageBounds.X = offset;
                ImageBounds.Y = row;
            }
            else
            {
                ImageBounds.X = (int)(screenSizeModifier + (offset * 1.5 * screenSizeModifier));
                ImageBounds.Y = 100 + (row * 150);
          
            }


            //set velocity based on difficulty
            XVelocity = (int)(3 * modifier);
            YVelocity = 20;
        }

        public virtual void Move()
        {
            ImageBounds.X += XVelocity;
        }

        public void DropDown()
        {
            ImageBounds.Y += YVelocity;
        }

    }
}
