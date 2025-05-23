package ui;

public class IPUtils {
    public static String getPublicIP() {
        try {
            java.net.URL url = new java.net.URL("https://api.ipify.org");
            java.io.BufferedReader in = new java.io.BufferedReader(
                new java.io.InputStreamReader(url.openStream())
            );
            String publicIP = in.readLine();
            in.close();
            return publicIP;
        } catch (Exception e) {
            return "inconnue";
        }
    }
}
