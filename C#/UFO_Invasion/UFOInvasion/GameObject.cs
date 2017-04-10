using System;
using System.Collections.Generic;
using System.Linq;
using System.Drawing;
using System.Text;
using System.Threading.Tasks;

namespace UFOInvasion
{
    /// <summary>
    /// Superclass for all Game Objects
    /// </summary>
    public abstract class GameObject
    {
        public Image ObjectImage;
        public Rectangle ImageBounds;
        public Rectangle MovingBounds;
        public int YVelocity { get; set; }

        public virtual void Draw(Graphics graphics)
        {
            graphics.DrawImage(ObjectImage, ImageBounds);
        }

        public GameObject(Rectangle MovingBounds,int height, int width, string imgPath)
        {
            this.MovingBounds = MovingBounds;
            ObjectImage = Image.FromFile(imgPath);

            //set dimensions
            ImageBounds.Height = height;
            ImageBounds.Width = width;
        }

        public virtual void Move()
        {
            ImageBounds.Y += YVelocity;
        }

        public int XPosition
        {
            get
            {
                return ImageBounds.X;
            }
            set
            {
                ImageBounds.X = value;
            }
        }

        public int YPosition
        {
            get
            {
                return ImageBounds.Y;
            }

            set
            {
                ImageBounds.Y = value;
            }
        }
        public int ObjWidth
        {
            get
            {
                return ImageBounds.Width;
            }

            set
            {
                ImageBounds.Width = value;
            }
        }
        public int ObjHeight
        {
            get
            {
                return ImageBounds.Height;
            }

            set
            {
                ImageBounds.Height = value;
            }
        }



        public Rectangle Rectangle
        {
            get
            {
                return ImageBounds;
            }
        }

        public int Width
        {
            get { return ObjectImage.Width; }
        }

        public int Height
        {
            get { return ObjectImage.Height; }
        }

        public int Left
        {
            get { return ImageBounds.Left; }
        }

        public int Right
        {
            get { return ImageBounds.Right; }
        }

        public int Top
        {
            get { return ImageBounds.Top; }
        }

        public int Bottom
        {
            get { return ImageBounds.Bottom; }
        }

    }
}
