package xenapte.customhud.hud;

import static meteordevelopment.meteorclient.MeteorClient.mc;

import java.util.IllegalFormatException;

import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.StringSetting;
import meteordevelopment.meteorclient.systems.hud.HudElementInfo;
// import meteordevelopment.starscript;
import xenapte.customhud.Addon;

public class LastDeathLocation extends CustomTextHUD {
    public static final HudElementInfo<LastDeathLocation> INFO = new HudElementInfo<>(Addon.HUD_GROUP, "last-death-location", "Displays your last death location.", LastDeathLocation::new);
    protected final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<String> locationFormat = sgGeneral.add(new StringSetting.Builder()
        .name("location-format")
        .description("Format for dimension and coords (see java.util.Formatter).")
        .defaultValue("[%s] %d, %d, %d")
        .build()
    );

    public LastDeathLocation() {
        super(INFO);
    }

    public enum Axis { X, Y, Z };

    public static int getPos(Axis axis) {
        if (mc.player == null)
            return 0;
        var deathLocation = mc.player.getLastDeathPos();
        if (!deathLocation.isPresent())
            return 0;
        var pos = deathLocation.get().pos();
        switch (axis) {
            case X: return pos.getX();
            case Y: return pos.getY();
            case Z: return pos.getZ();
        }
        return 0;
    }

    public static String getDimension() {
        if (mc.player == null)
            return "";
        var deathLocation = mc.player.getLastDeathPos();
        if (!deathLocation.isPresent())
            return "None";
        return deathLocation.get().dimension().getValue().toString().replaceFirst("^minecraft:", "");
    }

    public static String format(String fmt) {
        if (mc.player == null)
            return "";
        var deathLocation = mc.player.getLastDeathPos();
        if (!deathLocation.isPresent())
            return "None";
        var pos = deathLocation.get().pos();
        try {
            return String.format(fmt, 
                    deathLocation.get().dimension().getValue().toString().replaceFirst("^minecraft:", ""),
                    pos.getX(), pos.getY(), pos.getZ()
            );
        }
        catch (IllegalFormatException e) {
            return e.toString();
        }
    }

    @Override
    public String getText() {
        return format(locationFormat.get());
    }
}
