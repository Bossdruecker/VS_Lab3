package HistoryServer;

/**
* HistoryServer/HistoryServerCommunicationHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ServerClient.idl
* Montag, 29. April 2019 um 12:06:01 Mitteleurop�ische Sommerzeit
*/

public final class HistoryServerCommunicationHolder implements org.omg.CORBA.portable.Streamable
{
  public HistoryServer.HistoryServerCommunication value = null;

  public HistoryServerCommunicationHolder ()
  {
  }

  public HistoryServerCommunicationHolder (HistoryServer.HistoryServerCommunication initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = HistoryServer.HistoryServerCommunicationHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    HistoryServer.HistoryServerCommunicationHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return HistoryServer.HistoryServerCommunicationHelper.type ();
  }

}