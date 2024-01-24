package Login;

public class Constants {
    public static String FACEBOOK_APP_ID = "801476171386686";
    public static String FACEBOOK_APP_SECRET = "7372287ea3c01ed44c2e68e466af8067";
    public static String FACEBOOK_REDIRECT_URL = "http://localhost:8080/demoProject_war/login-facebook";
    public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";


    public static String GOOGLE_CLIENT_ID = "529302459286-47e0gd36hgu0evknkun46lbsq7ddoalb.apps.googleusercontent.com";
    public static String GOOGLE_CLIENT_SECRET = "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";
    public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/demoProject_war/login-google";
    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static String GOOGLE_GRANT_TYPE = "authorization_code";
}
