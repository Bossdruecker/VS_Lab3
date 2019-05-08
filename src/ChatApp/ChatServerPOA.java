package ChatApp;


/*
* ChatApp/ChatServerPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ChatApp.idl
* Mittwoch, 8. Mai 2019 um 13:11:20 Mitteleurop�ische Sommerzeit
*/

public abstract class ChatServerPOA extends org.omg.PortableServer.Servant
 implements ChatApp.ChatServerOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("login", new java.lang.Integer (0));
    _methods.put ("logout", new java.lang.Integer (1));
    _methods.put ("receiveMessage", new java.lang.Integer (2));
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
       case 0:  // ChatApp/ChatServer/login
       {
         String clientName = in.read_string ();
         ChatApp.ChatClient chatClient = ChatApp.ChatClientHelper.read (in);
         boolean $result = false;
         $result = this.login (clientName, chatClient);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 1:  // ChatApp/ChatServer/logout
       {
         String clientName = in.read_string ();
         boolean $result = false;
         $result = this.logout (clientName);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 2:  // ChatApp/MessageReceiver/receiveMessage
       {
         String clientName = in.read_string ();
         String chatMessage = in.read_string ();
         this.receiveMessage (clientName, chatMessage);
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
    "IDL:ChatApp/ChatServer:1.0", 
    "IDL:ChatApp/MessageReceiver:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ChatServer _this() 
  {
    return ChatServerHelper.narrow(
    super._this_object());
  }

  public ChatServer _this(org.omg.CORBA.ORB orb) 
  {
    return ChatServerHelper.narrow(
    super._this_object(orb));
  }


} // class ChatServerPOA
