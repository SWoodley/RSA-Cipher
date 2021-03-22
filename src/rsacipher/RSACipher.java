/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsacipher;
import java.math.*;
import java.util.*;
import java.security.*;

/**
 *
 * @author Rig
 */
public class RSACipher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Random rand = new Random();
        
        int bitLength = 2048;
        
        BigInteger p = BigInteger.probablePrime(bitLength, rand); //select random prime value for p and q
        BigInteger q = BigInteger.probablePrime(bitLength, rand);
        
        Scanner input = new Scanner(System.in);
        
        BigInteger n = p.multiply(q); //n = p * q
        
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)); //phi = (p-1)(q-1)
        
        BigInteger e;
        
        do {
            e= new BigInteger(phi.bitLength(), rand);  // test random e values of same bitlength
                    
        } while (e.compareTo(BigInteger.ONE) <=0
                || e.compareTo(phi) >=0 // 1 < e < phi
                || !e.gcd(phi).equals(BigInteger.ONE));// e is coprime with phi and n

        BigInteger d = e.modInverse(phi); // d * e(mod phi) = 1 derive d
                
        System.out.print("Please input message to be encrypted: "); //query message msg to encrypt
        String msg = input.nextLine();
        
        BigInteger message = new BigInteger(msg.getBytes());//message as bigint
        
        BigInteger encryptedMessage = message.modPow(e, n); //encrypt message
        
        BigInteger decryptedMessage = encryptedMessage.modPow(d, n); //decrypt message
        
        System.out.print("\nPublic key: (" + e + " , " + n + ")");
        System.out.print("\n\nYour encrypted message is: " + new String(encryptedMessage.toByteArray()) );
        System.out.print("\n\nYour decrypted message is: " + new String(decryptedMessage.toByteArray()) + "\n" );
        
    }
    
}
