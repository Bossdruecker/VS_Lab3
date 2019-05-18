package ChatApp;


/**
* ChatApp/ChatHistoryMethodsHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ChatApp.idl
* Samstag, 18. Mai 2019 um 15:26:06 Mitteleuropäische Sommerzeit
*/

abstract public class ChatHistoryMethodsHelper
{
  private static String  _id = "IDL:ChatApp/ChatHistoryMethods:1.0";

  public static void insert (org.omg.CORBA.Any a, ChatApp.ChatHistoryMethods that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static ChatApp.ChatHistoryMethods extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (ChatApp.ChatHistoryMethodsHelper.id (), "ChatHistoryMethods");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static ChatApp.ChatHistoryMethods read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ChatHistoryMethodsStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, ChatApp.ChatHistoryMethods value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static ChatApp.ChatHistoryMethods narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ChatApp.ChatHistoryMethods)
      return (ChatApp.ChatHistoryMethods)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ChatApp._ChatHistoryMethodsStub stub = new ChatApp._ChatHistoryMethodsStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static ChatApp.ChatHistoryMethods unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ChatApp.ChatHistoryMethods)
      return (ChatApp.ChatHistoryMethods)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ChatApp._ChatHistoryMethodsStub stub = new ChatApp._ChatHistoryMethodsStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
