namespace SpaceInvaders_Models.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class New : DbMigration
    {
        public override void Up()
        {
            DropColumn("dbo.SavedStates", "CurrentDate");
        }
        
        public override void Down()
        {
            AddColumn("dbo.SavedStates", "CurrentDate", c => c.DateTime(nullable: false));
        }
    }
}
