package ChatApp;


/**
* ChatApp/ChatServerOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ChatApp.idl
* Samstag, 18. Mai 2019 um 15:26:06 Mitteleuropäische Sommerzeit
*/

public interface ChatServerOperations  extends ChatApp.MessageReceiverOperations
{
  boolean login (String clientName, ChatApp.ChatClient chatClient);
  boolean logout (String clientName);
} // interface ChatServerOperations
