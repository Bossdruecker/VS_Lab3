package ChatApp;

/*
* ChatApp/MessageReceiverHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ChatApp.idl
* Mittwoch, 8. Mai 2019 um 13:11:20 Mitteleurop�ische Sommerzeit
*/


//client and server interface
public final class MessageReceiverHolder implements org.omg.CORBA.portable.Streamable
{
  public ChatApp.MessageReceiver value = null;

  public MessageReceiverHolder ()
  {
  }

  public MessageReceiverHolder (ChatApp.MessageReceiver initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ChatApp.MessageReceiverHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ChatApp.MessageReceiverHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ChatApp.MessageReceiverHelper.type ();
  }

}
