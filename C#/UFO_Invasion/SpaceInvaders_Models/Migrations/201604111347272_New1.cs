namespace SpaceInvaders_Models.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class New1 : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.SavedStates", "CurrentDate", c => c.DateTime(nullable: false));
        }
        
        public override void Down()
        {
            DropColumn("dbo.SavedStates", "CurrentDate");
        }
    }
}
