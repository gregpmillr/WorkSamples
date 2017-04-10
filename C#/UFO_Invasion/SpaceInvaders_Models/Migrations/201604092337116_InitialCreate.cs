namespace SpaceInvaders_Models.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class InitialCreate : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.SavedStates",
                c => new
                    {
                        SavedStateId = c.Int(nullable: false, identity: true),
                        CurrentDate = c.DateTime(nullable: false),
                        HighScore = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.SavedStateId);
        }
        
        public override void Down()
        {
            DropTable("dbo.SavedStates");
        }
    }
}
