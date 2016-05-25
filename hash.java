// Hashing library useful for quicker hashing operations than those
// built into interpreted languages like Ruby (YMMV)
//
// Based on information found at various sites
// and combined into a useful Java file
//
// Feel free to port this to whatever you like.
// Just make a pull request and I'll add it to the repo.
//
// Pass arguments as follows: 
// java hash <FILENAME/PATH> <NUMBER FOR HASHING ALGO>
//
// Numbers for Hashes
// 1 = MD5
// 2 = SHA-256
//
// Results will be printed to the console.
// Use whatever method you like to get it.

import java.io.*;
import java.security.MessageDigest;

public class hash {    
  // Constants
  final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
  
  public static void main(String[] args) throws Exception {
    String file = args[0];
    int algo = Integer.parseInt(args[1]);

    // Pick Hashing Algorithm
    // and print hash to console
    if (algo == 1) {
      String algorithm = "MD5";
      String hash = bytesToHex(hash(file, algorithm));
      System.out.println(hash);
    }
    else if (algo == 2) {
      String algorithm = "SHA-256";
      String hash = bytesToHex(hash(file, algorithm));
      System.out.println(hash);
    }
      
  }
  
  // Turns bytes into Hex Values
  public static String bytesToHex(byte[] bytes) {
  char[] hexChars = new char[bytes.length * 2];
  for (int i = 0; i < bytes.length; i++ ) {
    int v = bytes[i] & 0xFF;
    hexChars[i * 2] = hexArray[v >>> 4];
    hexChars[i * 2 + 1] = hexArray[v & 0x0F];
  }
  return new String(hexChars);
  }

  // Hashing function
  public static byte[] hash(String filename, String algorithm) throws Exception {
  InputStream fis =  new FileInputStream(filename);
  try {
    byte[] buffer = new byte[1024];
    // Algo choce is used here
    MessageDigest complete = MessageDigest.getInstance(algorithm); 
    int numRead;
    do {
    numRead = fis.read(buffer);
    if (numRead > 0) {
      complete.update(buffer, 0, numRead);
    }
    } while (numRead != -1);
    return complete.digest();
  }
  finally {
    fis.close();
  }
  }
}
