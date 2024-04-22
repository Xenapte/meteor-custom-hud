package xenapte.customhud.functions;

import static meteordevelopment.meteorclient.MeteorClient.mc;

import java.util.IllegalFormatException;

import meteordevelopment.meteorclient.utils.player.PlayerUtils;
import meteordevelopment.meteorclient.utils.world.Dimension;

public class CoordinateFormatter {
    public static String format(String fmt, boolean oppositeDimension) {
        if (mc.player == null || PlayerUtils.getDimension() == Dimension.End)
            return "";
        double x = mc.player.getX(), y = mc.player.getY(), z = mc.player.getZ();
        if (oppositeDimension) {
            switch (PlayerUtils.getDimension()) {
                case Overworld:
                    x /= 8; z /= 8;
                    break;
                case Nether:
                    x *= 8; z *= 8;
                    break;
                default:
                    break;
            }
        }
        try {
            return String.format(fmt, x, y, z);
        }
        catch (IllegalFormatException e) {
            return e.toString();
        }
    }
}
