using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;

namespace SpaceInvaders_Models
{
    public class SavedState
    {
        [Key]
        public int SavedStateId { get; set; }
        [Required]
        public string StateName { get; set; }
        public DateTime CurrentDate { get; set; }
        public int HighScore { get; set; }
        public virtual ICollection<UFO> UfoList { get; set; }
        public virtual ICollection<Jet> JetList { get; set; }
    }
}
