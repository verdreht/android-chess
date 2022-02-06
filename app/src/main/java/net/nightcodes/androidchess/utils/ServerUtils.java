package net.nightcodes.androidchess.utils;

import net.nightcodes.androidchess.Constants;

public class ServerUtils {

    public static boolean isServer() {
        return Constants.getServer() != null && Constants.getServer().getServerSocket() != null;
    }

}
