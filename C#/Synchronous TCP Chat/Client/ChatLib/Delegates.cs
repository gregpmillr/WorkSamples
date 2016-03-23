namespace ChatLib
{
    /// <summary>
    /// Declare delegate for message handler.
    /// </summary>
    /// <param name="sender">Object sending</param>
    /// <param name="e">Instance of ListenForMessageEventArgs</param>
    public delegate void ListenForMessageHandler(object sender, ListenForMessageEventArgs e);
}