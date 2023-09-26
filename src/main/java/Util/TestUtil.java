package Util;

import java.util.Random;

public class TestUtil {
    public static String generateRandomEmail() {
        Random random = new Random();
        String email = "mamaomidaghiceste" + random.nextLong() + "gmail.com";
        System.out.println("Used email is: " + email);
        return email;

    }
}
