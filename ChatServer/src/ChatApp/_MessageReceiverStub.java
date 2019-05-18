package ChatApp;


/**
* ChatApp/_MessageReceiverStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ChatApp.idl
* Samstag, 18. Mai 2019 um 15:27:18 Mitteleuropäische Sommerzeit
*/


//client and server interface
public class _MessageReceiverStub extends org.omg.CORBA.portable.ObjectImpl implements ChatApp.MessageReceiver
{

  public void receiveMessage (String clientName, String chatMessage)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("receiveMessage", true);
                $out.write_string (clientName);
                $out.write_string (chatMessage);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                receiveMessage (clientName, chatMessage        );
            } finally {
                _releaseReply ($in);
            }
  } // receiveMessage

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:ChatApp/MessageReceiver:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _MessageReceiverStub
