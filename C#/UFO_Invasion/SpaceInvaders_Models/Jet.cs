using System.ComponentModel.DataAnnotations;

namespace SpaceInvaders_Models
{
    public class Jet
    {
        [Key]
        public int JetId { get; set; }
        [Required]
        public int Height { get; set; }
        public int Width { get; set; }
        public virtual SavedState State { get; set; }
    }//End Jet class
}//EOF
