package ServerClient;


/*
* ServerClient/ReceiverPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ServerClient.idl
* Montag, 29. April 2019 um 12:06:01 Mitteleurop�ische Sommerzeit
*/

public abstract class ReceiverPOA extends org.omg.PortableServer.Servant
 implements ServerClient.ReceiverOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("newMessage", new java.lang.Integer (0));
    _methods.put ("newUser", new java.lang.Integer (1));
    _methods.put ("removeUser", new java.lang.Integer (2));
    _methods.put ("newHistory", new java.lang.Integer (3));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // ServerClient/Receiver/newMessage
       {
         String nickname = in.read_string ();
         String message = in.read_string ();
         this.newMessage (nickname, message);
         out = $rh.createReply();
         break;
       }

       case 1:  // ServerClient/Receiver/newUser
       {
         String nickname = in.read_string ();
         this.newUser (nickname);
         out = $rh.createReply();
         break;
       }

       case 2:  // ServerClient/Receiver/removeUser
       {
         String nickname = in.read_string ();
         this.removeUser (nickname);
         out = $rh.createReply();
         break;
       }

       case 3:  // ServerClient/Receiver/newHistory
       {
         ServerClient.Message history[] = ServerClient.HistoryHelper.read (in);
         this.newHistory (history);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:ServerClient/Receiver:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Receiver _this() 
  {
    return ReceiverHelper.narrow(
    super._this_object());
  }

  public Receiver _this(org.omg.CORBA.ORB orb) 
  {
    return ReceiverHelper.narrow(
    super._this_object(orb));
  }


} // class ReceiverPOA
