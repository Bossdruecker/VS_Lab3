package AppImpl;

import ChatApp.ChatClient;
import ChatApp.ChatClientHelper;
import ChatApp.ChatServer;
import ChatApp.ChatServerHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.PortableServer.POA;

import java.util.Scanner;

public class ChatClientApp
{
    static final String EXIT_COMMAND = "!Exit";

    static final String CHAT_SERVER_NAME = "ChatServer";


    public static void main(String args[]) {
        try {
            CorbaInit corbaInitializer = new CorbaInit(args).init();
            ORB orb = corbaInitializer.getOrb();
            POA rootpoa = corbaInitializer.getRootpoa();
            NamingContextExt ncRef = corbaInitializer.getNcRef();


            ChatServer chatServer = ChatServerHelper.narrow(ncRef.resolve_str(CHAT_SERVER_NAME));

            System.out.println("Obtained a handle on server object: " + chatServer);


            ChatClientImpl chatClientImpl = new ChatClientImpl();
            ChatClient chatClient = ChatClientHelper.narrow(rootpoa.servant_to_reference(chatClientImpl));

            Scanner in = new Scanner(System.in);
            boolean loginSuccess;
            do {
                System.out.println("Enter nickname: ");
                chatClientImpl.setClientName(in.nextLine());
                loginSuccess = chatServer.login(chatClientImpl.getClientName(), chatClient);

                if (loginSuccess == false) {
                    System.out.println("Login failed, Nickname already exists.");
                }

            } while (loginSuccess == false);

            String message;
            do {
                message = in.nextLine();

                if (EXIT_COMMAND.equals(message)) {
                    chatServer.logout(chatClientImpl.getClientName());
                } else {
                    chatServer.receiveMessage(chatClientImpl.getClientName(), message);
                }
            } while (!EXIT_COMMAND.equals(message));
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }
}
