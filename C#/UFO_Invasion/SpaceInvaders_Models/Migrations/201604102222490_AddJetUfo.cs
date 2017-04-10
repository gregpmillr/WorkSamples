namespace SpaceInvaders_Models.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AddJetUfo : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Jets",
                c => new
                    {
                        JetId = c.Int(nullable: false, identity: true),
                        Height = c.Int(nullable: false),
                        Width = c.Int(nullable: false),
                        State_SavedStateId = c.Int(),
                    })
                .PrimaryKey(t => t.JetId)
                .ForeignKey("dbo.SavedStates", t => t.State_SavedStateId)
                .Index(t => t.State_SavedStateId);
            
            CreateTable(
                "dbo.UFOes",
                c => new
                    {
                        UfoId = c.Int(nullable: false, identity: true),
                        Row = c.Int(nullable: false),
                        Offset = c.Int(nullable: false),
                        Difficulty = c.Int(nullable: false),
                        Height = c.Int(nullable: false),
                        Width = c.Int(nullable: false),
                        State_SavedStateId = c.Int(),
                    })
                .PrimaryKey(t => t.UfoId)
                .ForeignKey("dbo.SavedStates", t => t.State_SavedStateId)
                .Index(t => t.State_SavedStateId);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.UFOes", "State_SavedStateId", "dbo.SavedStates");
            DropForeignKey("dbo.Jets", "State_SavedStateId", "dbo.SavedStates");
            DropIndex("dbo.UFOes", new[] { "State_SavedStateId" });
            DropIndex("dbo.Jets", new[] { "State_SavedStateId" });
            DropTable("dbo.UFOes");
            DropTable("dbo.Jets");
        }
    }
}
