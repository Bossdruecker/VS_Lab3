package AppImpl;

import ChatApp.ChatHistoryMethods;
import ChatApp.ChatHistoryMethodsHelper;
import ChatApp.ChatServer;
import ChatApp.ChatServerHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.PortableServer.POA;

public class ChatServerApp
{
    static final String CHAT_SERVER_NAME = "ChatServer";
    static final String CHAT_HISTORY_SERVER_NAME = "ChatHistoryServer";

    public static void main(String args[]) {
        try {
            CorbaInit corbaInitializer = new CorbaInit(args).init();
            ORB orb = corbaInitializer.getOrb();
            POA rootpoa = corbaInitializer.getRootpoa();
            NamingContextExt ncRef = corbaInitializer.getNcRef();

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
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }

        System.out.println("ChatServer Exiting ...");

    }
}
