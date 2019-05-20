package corbaChatServer;

import ChatApp.ChatHistoryMethods;
import ChatApp.ChatHistoryMethodsHelper;
import ChatApp.ChatServer;
import ChatApp.ChatServerHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class ChatServerApp {

    static final String CHAT_SERVER_NAME = "ChatServer";
    static final String CHAT_HISTORY_SERVER_NAME = "ChatHistoryServer";

    public static void main(String[] args)
    {
        try
        {
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);

            // get reference to rootpoa & activate the POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // get the root naming context
            // NameService invokes the name service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            // Use NamingContextExt which is part of the Interoperable
            // Naming Service (INS) specification.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            ChatServerImpl chatServerImpl = new ChatServerImpl();
            ChatHistoryMethods chatHistoryServer = ChatHistoryMethodsHelper.narrow(ncRef.resolve_str(CHAT_HISTORY_SERVER_NAME));
            chatServerImpl.setHistoryServer(chatHistoryServer);

            // bind the Object Reference in Naming
            // get object reference from the servant
            ChatServer href = ChatServerHelper.narrow(rootpoa.servant_to_reference(chatServerImpl));
            NameComponent path[] = ncRef.to_name(CHAT_SERVER_NAME);
            ncRef.rebind(path, href);

            System.out.println("ChatServer ready and waiting ...");

            // wait for invocations from clients
            orb.run();
            System.out.println("ChatHistoryServer ready and waiting ...");
        }
        catch (Exception e)
        {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }

        System.out.println("ChatServer Exiting ...");
    }
}
