using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UFOInvasion
{
    public class Laser : GameObject
    {
        public Laser(Rectangle gameplayArea, int Velocity, int ufoXValue, int ufoYValue, int height, int width,string ImagePath) : base(gameplayArea, height, width, ImagePath)
        {
            ImageBounds.X = ufoXValue + 40;
            ImageBounds.Y = ufoYValue + 75;

            YVelocity = Velocity;
        }
    }
}
