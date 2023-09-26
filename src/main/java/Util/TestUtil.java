package Util;

import java.util.Random;

public class TestUtil {
    public static String generateRandomEmail() {
        Random random = new Random();
        String email = "mrslollie" + random.nextLong() + "@mail.com";
        System.out.println("Used email is: " + email);
        return email;

    }
    public static Integer generateRandomPhoneNr() {
        Random random= new Random();
        int x = random.nextInt();
        System.out.println("Used phone number is: " + x);
        return x;
    }
}
