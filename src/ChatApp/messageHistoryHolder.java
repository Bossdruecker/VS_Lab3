package ChatApp;


/*
* ChatApp/messageHistoryHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ChatApp.idl
* Mittwoch, 8. Mai 2019 um 13:11:20 Mitteleurop�ische Sommerzeit
*/

public final class messageHistoryHolder implements org.omg.CORBA.portable.Streamable
{
  public ChatApp.HistoryMessage value[] = null;

  public messageHistoryHolder ()
  {
  }

  public messageHistoryHolder (ChatApp.HistoryMessage[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ChatApp.messageHistoryHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ChatApp.messageHistoryHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ChatApp.messageHistoryHelper.type ();
  }

}
