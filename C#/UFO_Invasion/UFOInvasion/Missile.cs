using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UFOInvasion
{
    public class Missile : GameObject
    {

        public Missile(Rectangle gameplayArea, int jetXValue, int jetYValue, int Height, int Width, string ImagePath) : base(gameplayArea, Height, Width, ImagePath)
        {
            ImageBounds.X = jetXValue + 40;
            ImageBounds.Y = jetYValue;

            //set vertical velocity, negative means up
            YVelocity = -20;
        }
    }
}
