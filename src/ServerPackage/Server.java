package ServerPackage;

import ServerClient.ClientServerCommunicationPOA;
import ServerClient.Receiver;
import org.omg.CORBA.ORB;

public class Server extends ClientServerCommunicationPOA {

    private ORB orb;

    public void setOrb(ORB orb_val)
    {
        orb = orb_val;
    }

    @Override
    public void sendMessage(String nickname, String message) {

    }

    @Override
    public void register(String nickname, Receiver receive) {

    }

    @Override
    public void unregister(String nickname) {

    }
}
