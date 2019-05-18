package corbaHistoryServer;

import ChatApp.ChatHistoryMethods;
import ChatApp.ChatHistoryMethodsHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.Scanner;

public class HistoryServerApp {
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

            // create servant and register it with the ORB
            System.out.println("Enter HistorySize:");
            Scanner in = new Scanner(System.in);
            int historySize = in.nextInt();
            HistoryServerImpl historyRepo = new HistoryServerImpl(historySize);

            // get object reference from the servant
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(historyRepo);
            ChatHistoryMethods href = ChatHistoryMethodsHelper.narrow(ref);

            // bind the Object Reference in Naming
            String name = "ChatHistoryServer";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, href);

            System.out.println("ChatHistoryServer ready and waiting ...");

            // wait for invocations from clients
            orb.run();
        }
        catch (Exception e)
        {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }

        System.out.println("HelloServer Exiting ...");
    }
}
