import java.io.*;
import java.net.*;
class TCPServer
{
public static void main(String argv[]) throws Exception
{
String clientSentence;
String capitalizedSentence;
ServerSocket welcomeSocket = new ServerSocket(6789);
while(true)
{
Socket connectionSocket = welcomeSocket.accept();
BufferedReader inFromClient =
new BufferedReader(
new InputStreamReader(
connectionSocket.getInputStream()));
DataOutputStream outToClient =
new DataOutputStream(
connectionSocket.getOutputStream());
clientSentence = inFromClient.readLine();
capitalizedSentence = clientSentence.toUpperCase() + '\n';
outToClient.writeBytes(capitalizedSentence);
}
}
}
TCP client code:
import java.io.*;
import java.net.*;
class TCPClient
{
public static void main(String argv[]) throws Exception
{
String sentence;
String modifiedSentence;
BufferedReader inFromUser =
new BufferedReader(
new InputStreamReader(System.in));
Socket clientSocket = new Socket("localhost", 6789);
DataOutputStream outToServer =
new DataOutputStream(
clientSocket.getOutputStream());
BufferedReader inFromServer =
new BufferedReader(
new InputStreamReader(
clientSocket.getInputStream()));
sentence = inFromUser.readLine();
outToServer.writeBytes(sentence + '\n');
modifiedSentence = inFromServer.readLine();
System.out.println("FROM SERVER: " + modifiedSentence);
clientSocket.close();
}
}