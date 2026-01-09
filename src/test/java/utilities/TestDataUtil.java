package utilities;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class TestDataUtil {
	
	
	  public static String firstName() {
	        return "User" + random(4);
	    }

	    public static String lastName() {
	        return "Test" + random(4);
	    }

	    public static String email() {
	        return "user" + random(6) + "@gmail.com";
	    }

	    public static String phone() {
	        return String.valueOf(
	            ThreadLocalRandom.current().nextLong(1000000000L, 9999999999L)
	        );
	    }

	    public static String password() {
	        return "Pwd@" + random(6);
	    }

	    private static String random(int length) {
	        return UUID.randomUUID().toString()
	                .replace("-", "")
	                .substring(0, length);
	    }

}
