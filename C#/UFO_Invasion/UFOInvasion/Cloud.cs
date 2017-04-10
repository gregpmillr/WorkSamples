using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UFOInvasion
{
    public class Cloud : GameObject
    {

        public Cloud(Rectangle gameplayArea, int velocity, int location, int height, int width,string imgPath) : base(gameplayArea,height,width,imgPath)
        {
            ImageBounds.X = location;
            ImageBounds.Y = -500;

            YVelocity = velocity;
        }
    }
}
