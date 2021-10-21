package Blockette;
import java.security.MessageDigest; //to access sha256 algo

public class StringUtil {
	//applies sha256 to a string and returns result
	public static String applysha256(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        //applies sha256 to our input,
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer(); //contains hash as hexadecimal
			for( int i=0; i< hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
 				if(hex.length() == 1) hexString.append('0');
 				hexString.append(hex);
			    }
			return hexString.toString();
		    }
		catch(Exception e) {
			   throw new RuntimeException(e);
		}
	}

}
