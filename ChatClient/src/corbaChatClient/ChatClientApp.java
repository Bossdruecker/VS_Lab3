package corbaChatClient;

import ChatApp.ChatClient;
import ChatApp.ChatClientHelper;
import ChatApp.ChatServer;
import ChatApp.ChatServerHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.Scanner;

public class ChatClientApp {

    static final String EXIT_COMMAND = "!Exit";
    static final String CHAT_SERVER_NAME = "ChatServer";

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

            ChatServer chatServer = ChatServerHelper.narrow(ncRef.resolve_str(CHAT_SERVER_NAME));

            System.out.println("Obtained a handle on server object: " + chatServer);


            ChatClientImpl chatClientImpl = new ChatClientImpl();
            ChatClient chatClient = ChatClientHelper.narrow(rootpoa.servant_to_reference(chatClientImpl));

            Scanner in = new Scanner(System.in);
            boolean loginSuccess;
            do {
                System.out.println("Enter nickname: ");
                chatClientImpl.setClientName(in.nextLine());
                loginSuccess = chatServer.login(chatClientImpl.getName(), chatClient);

                if (loginSuccess == false) {
                    System.out.println("Login failed, Nickname already exists.");
                }

            } while (loginSuccess == false);

            String message;
            do {
                message = in.nextLine();

                if (EXIT_COMMAND.equals(message)) {
                    chatServer.logout(chatClientImpl.getName());
                } else {
                    chatServer.receiveMessage(chatClientImpl.getName(), message);
                }
            } while (!EXIT_COMMAND.equals(message));
        }
        catch (Exception e)
        {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }
}
