package josegamerpt.realscoreboard.nms;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;

/**
 * @author Ghast
 * @since 01-May-20
 */
public class NMS {

    public static int getPing(Player player) {
        Object handle = getPlayerHandle(player);
        if (handle != null) {
            try {
                Field ping = handle.getClass().getDeclaredField("ping");
                ping.setAccessible(true);
                return ping.getInt(handle);
                //return (int) handle.getClass().getField("ping").get(handle);
            } catch (Exception e) {
                // Why Print Errors on Ping?
                return -1;
            }
        } else {
            return -1;
        }
    }

    public static Object getPlayerHandle(Player player) {
        try {
            Method handleMethod = player.getClass().getMethod("getHandle");
            return handleMethod.invoke(player);
        } catch (Exception e) {
            Bukkit.getLogger().log(Level.SEVERE, "[Reflection] Unable to getHandle of " + player.getName());
            return null;
        }
    }

}
