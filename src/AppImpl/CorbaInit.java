package AppImpl;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;

public class CorbaInit
{
        private String[] args;
        private ORB orb;
        private POA rootpoa;
        private NamingContextExt ncRef;


    public CorbaInit(String... args) {
        this.args = args;
    }

    public ORB getOrb() {
        return orb;
    }

    public POA getRootpoa() {
        return rootpoa;
    }

    public NamingContextExt getNcRef() {
        return ncRef;
    }

    public CorbaInit init() throws InvalidName, AdapterInactive
    {
        // create and initialize the ORB
        orb = ORB.init(args, null);

        // get reference to rootpoa & activate the POAManager
        rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        rootpoa.the_POAManager().activate();

        // get the root naming context
        // NameService invokes the name service
        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

        // Use NamingContextExt which is part of the Interoperable
        // Naming Service (INS) specification.
        ncRef = NamingContextExtHelper.narrow(objRef);
        return this;
    }
}
