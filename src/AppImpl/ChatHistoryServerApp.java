package AppImpl;

import ChatApp.ChatHistoryMethods;
import ChatApp.ChatHistoryMethodsHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.PortableServer.POA;

import java.util.Scanner;

public class ChatHistoryServerApp
{
    public static void main(String args[]) {
        try {
            CorbaInit corbaInitializer = new CorbaInit(args).init();
            ORB orb = corbaInitializer.getOrb();
            POA rootpoa = corbaInitializer.getRootpoa();
            NamingContextExt ncRef = corbaInitializer.getNcRef();

            // create servant and register it with the ORB
            System.out.println("Enter HistorySize:");
            Scanner in = new Scanner(System.in);
            int historySize = in.nextInt();
            ChatHistoryServerImpl historyRepo = new ChatHistoryServerImpl(historySize);


            // get object reference from the servant
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(historyRepo);
            ChatHistoryMethods href = ChatHistoryMethodsHelper.narrow(ref);

            // get the root naming context
            // NameService invokes the name service
            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references("NameService");

            // bind the Object Reference in Naming
            String name = "ChatHistoryServer";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, href);

            System.out.println("ChatHistoryServer ready and waiting ...");

            // wait for invocations from clients
            orb.run();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }

        System.out.println("HelloServer Exiting ...");

    }
}
