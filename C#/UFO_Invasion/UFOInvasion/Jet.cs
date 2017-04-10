using System.Drawing;

namespace UFOInvasion
{
    public class Jet : GameObject
    {
        public enum Direction { Left, Right }

        Image OtherImage;

        /// <summary>
        /// constructor for Jet
        /// </summary>
        /// <param name="MovingBounds"></param>
        public Jet(Rectangle MovingBounds,int height, int width) : base(MovingBounds,height,width, @"res\f15topview.gif")
        {
            ImageBounds.X = (MovingBounds.Width / 2) - (ImageBounds.Width / 2);
            ImageBounds.Y = MovingBounds.Height - 150;
        }

        public void Move(Direction dir)
        {
            //move right
            if (dir == Direction.Right)
            {
                //as long as within screen dimensions, move right
                ImageBounds.X = (ImageBounds.Right >= MovingBounds.Right) ? MovingBounds.Right - ImageBounds.Width : ImageBounds.X + 20;
            }
            else
            {
                //as long as within screen dimensions, move left
                ImageBounds.X = (ImageBounds.Left < 20) ? 0 : ImageBounds.X - 20;
            }
        }

        public void Explode()
        {
            //increase size
            ImageBounds.Height *= 2;
            ImageBounds.Width *= 2;
            //replace jet with explosion
            ObjectImage = Image.FromFile(@"res\explosion.png");
        }
    }
}
