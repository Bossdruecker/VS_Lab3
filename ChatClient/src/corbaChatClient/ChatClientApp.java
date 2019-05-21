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

    static String EXIT_CMD = "!Exit";
    static String SERVER = "ChatServer";
    static String HISTORY_CMD = "!History";

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

            ChatServer chatServer = ChatServerHelper.narrow(ncRef.resolve_str(SERVER));
            //System.out.println("Obtained a handle on server object: " + chatServer);

            ChatClientImpl chatClientImpl = new ChatClientImpl();
            ChatClient chatClient = ChatClientHelper.narrow(rootpoa.servant_to_reference(chatClientImpl));

            Scanner in = new Scanner(System.in);
            boolean loginSuccess = false;

            //solange Login nicht bestätigt wird wird ein neuer Nickname gefragt
            while (loginSuccess == false)
            {
                System.out.println("Bitte geben Sie ihren Nickname an: ");
                chatClientImpl.setClientName(in.nextLine());
                loginSuccess = chatServer.login(chatClientImpl.getNickname(), chatClient);

                if (loginSuccess == false)
                {
                    System.out.println("Login fehlgeschlagen, Nickname existiert! Bitte neuen Nickname eingeben.");
                }
            }
            System.out.println("Sie sind jetzt angemeldet");
            chatServer.receiveMessage(chatClientImpl.getNickname(), HISTORY_CMD);

            //message überprüfen auf !Exit Command bei jeder Nachricht
            String message = "";
            while (!EXIT_CMD.equals(message))
            {
                message = in.nextLine();

                if (EXIT_CMD.equals(message))
                {
                    chatServer.logout(chatClientImpl.getNickname());
                }
                else
                {
                    chatServer.receiveMessage(chatClientImpl.getNickname(), message);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }
}
