namespace SpaceInvaders_Models.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class NameChange : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.SavedStates", "StateName", c => c.String(nullable: false));
        }
        
        public override void Down()
        {
            DropColumn("dbo.SavedStates", "StateName");
        }
    }
}
