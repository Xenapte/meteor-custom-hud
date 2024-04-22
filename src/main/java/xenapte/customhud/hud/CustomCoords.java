package xenapte.customhud.hud;

import meteordevelopment.meteorclient.settings.BoolSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.settings.StringSetting;
import meteordevelopment.meteorclient.systems.hud.HudElementInfo;
// import meteordevelopment.starscript;
import xenapte.customhud.Addon;
import xenapte.customhud.functions.CoordinateFormatter;

public class CustomCoords extends CustomTextHUD {
    public static final HudElementInfo<CustomCoords> INFO = new HudElementInfo<>(Addon.HUD_GROUP, "custom-coords", "Custom coords display.", CustomCoords::new);
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

    public CustomCoords() {
        super(INFO);
    }

    @Override
    public String getText() {
        return CoordinateFormatter.format(coordsFormat.get(), oppositeDimension.get());
    }
}
