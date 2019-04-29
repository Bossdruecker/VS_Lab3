package HistoryServer;


/**
* HistoryServer/HistoryServerCommunicationHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ServerClient.idl
* Montag, 29. April 2019 um 12:06:01 Mitteleuropäische Sommerzeit
*/

abstract public class HistoryServerCommunicationHelper
{
  private static String  _id = "IDL:HistoryServer/HistoryServerCommunication:1.0";

  public static void insert (org.omg.CORBA.Any a, HistoryServer.HistoryServerCommunication that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static HistoryServer.HistoryServerCommunication extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (HistoryServer.HistoryServerCommunicationHelper.id (), "HistoryServerCommunication");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static HistoryServer.HistoryServerCommunication read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_HistoryServerCommunicationStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, HistoryServer.HistoryServerCommunication value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static HistoryServer.HistoryServerCommunication narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof HistoryServer.HistoryServerCommunication)
      return (HistoryServer.HistoryServerCommunication)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      HistoryServer._HistoryServerCommunicationStub stub = new HistoryServer._HistoryServerCommunicationStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static HistoryServer.HistoryServerCommunication unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof HistoryServer.HistoryServerCommunication)
      return (HistoryServer.HistoryServerCommunication)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      HistoryServer._HistoryServerCommunicationStub stub = new HistoryServer._HistoryServerCommunicationStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
