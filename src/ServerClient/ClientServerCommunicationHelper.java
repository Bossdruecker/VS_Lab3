package ServerClient;


/**
* ServerClient/ClientServerCommunicationHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ServerClient.idl
* Montag, 29. April 2019 um 12:06:01 Mitteleuropäische Sommerzeit
*/

abstract public class ClientServerCommunicationHelper
{
  private static String  _id = "IDL:ServerClient/ClientServerCommunication:1.0";

  public static void insert (org.omg.CORBA.Any a, ServerClient.ClientServerCommunication that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static ServerClient.ClientServerCommunication extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (ServerClient.ClientServerCommunicationHelper.id (), "ClientServerCommunication");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static ServerClient.ClientServerCommunication read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ClientServerCommunicationStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, ServerClient.ClientServerCommunication value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static ServerClient.ClientServerCommunication narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ServerClient.ClientServerCommunication)
      return (ServerClient.ClientServerCommunication)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ServerClient._ClientServerCommunicationStub stub = new ServerClient._ClientServerCommunicationStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static ServerClient.ClientServerCommunication unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ServerClient.ClientServerCommunication)
      return (ServerClient.ClientServerCommunication)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ServerClient._ClientServerCommunicationStub stub = new ServerClient._ClientServerCommunicationStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
