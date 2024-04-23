package xenapte.customhud.hud;

import static meteordevelopment.meteorclient.MeteorClient.mc;

import java.util.IllegalFormatException;

import meteordevelopment.meteorclient.settings.BoolSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.settings.StringSetting;
import meteordevelopment.meteorclient.systems.hud.HudElementInfo;
import meteordevelopment.meteorclient.utils.player.PlayerUtils;
import meteordevelopment.meteorclient.utils.world.Dimension;
// import meteordevelopment.starscript;
import xenapte.customhud.Addon;

public class CoordinateFormatter extends CustomTextHUD {
    public static final HudElementInfo<CoordinateFormatter> INFO = new HudElementInfo<>(Addon.HUD_GROUP, "custom-coords", "Custom coords display.", CoordinateFormatter::new);
    protected final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<String> coordsFormat = sgGeneral.add(new StringSetting.Builder()
        .name("coords-format")
        .description("Coordinates format (see java.util.Formatter).")
        .defaultValue("%.2f, %.2f, %.2f")
        .build()
    );

    private final Setting<Boolean> oppositeDimension = sgGeneral.add(new BoolSetting.Builder()
        .name("opposite-dimension")
        .description("")
        .defaultValue(false)
        .build()
    );

    public CoordinateFormatter() {
        super(INFO);
    }
    
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

    @Override
    public String getText() {
        return CoordinateFormatter.format(coordsFormat.get(), oppositeDimension.get());
    }
}
