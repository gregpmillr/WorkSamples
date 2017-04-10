using System.ComponentModel.DataAnnotations;

namespace SpaceInvaders_Models
{
    public class UFO
    {
        [Key]
        public int UfoId { get; set; }
        [Required]
        public int Row { get; set; }
        public int Offset { get; set; }
        public int Difficulty { get; set; }
        public int Height { get; set; } 
        public int Width { get; set; }
        public virtual SavedState State { get; set; }
        
    }//End class UFO
}//EOF
