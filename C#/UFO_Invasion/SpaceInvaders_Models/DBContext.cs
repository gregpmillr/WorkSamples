using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.Entity;

namespace SpaceInvaders_Models
{
    public class DBContext : DbContext
    {
        public DBContext() : base("Server=tcp:spaceinvadersnscc.database.windows.net,1433;Database=spaceinvaders;User ID=spaceadmin;Password=Rootroot1234;Encrypt=True;TrustServerCertificate=False;Connection Timeout=30;")
        {
            
            Database.SetInitializer(new DropCreateDatabaseIfModelChanges<DBContext>());
        }

        public DbSet<SavedState> SavedState { get; set; }
        public DbSet<UFO> UfoSet { get; set; }
        public DbSet<Jet> JetSet { get; set; }
    }
}
