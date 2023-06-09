/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package eptid_calculator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.checkerframework.checker.units.qual.A;
import org.opensaml.xml.util.Base64;

public class App {
    public static void main(String[] args) {
        String sourceId = args[0];
        String salt = "saltsaltsalt";
        String entityId = "https://SOME_GREAT_SHIBBOLETH_SP/shibboleth-sp";

        try {
            // the code below comes from: https://github.com/brainysmith/shibboleth-common/blob/master/src/main/java/edu/internet2/middleware/shibboleth/common/attribute/resolver/provider/dataConnector/ComputedIDDataConnector.java#L136-L140

            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(entityId.getBytes()); // SP's entityId
            md.update((byte) '!');
            md.update(sourceId.getBytes()); // uid
            md.update((byte) '!');
 
            String eptid = Base64.encodeBytes(md.digest(salt.getBytes()));
            System.out.println(sourceId + " " + eptid);
 
        } catch (NoSuchAlgorithmException e) {
            System.err.println("JVM error, SHA-1 hash is not supported.");
        }
    }
}
