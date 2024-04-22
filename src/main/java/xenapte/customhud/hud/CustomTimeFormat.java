package xenapte.customhud.hud;

import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.settings.StringSetting;
import meteordevelopment.meteorclient.systems.hud.HudElementInfo;
// import meteordevelopment.starscript;
import xenapte.customhud.Addon;
import xenapte.customhud.functions.TimeFormatter;

public class CustomTimeFormat extends CustomTextHUD {
    public static final HudElementInfo<CustomTimeFormat> INFO = new HudElementInfo<>(Addon.HUD_GROUP, "custom-time-format", "Custom time format.", CustomTimeFormat::new);
    protected final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<String> timeFormat = sgGeneral.add(new StringSetting.Builder()
        .name("time-format")
        .description("Time format (see java.text.SimpleDateFormat).")
        .defaultValue("YYYY-MM-dd HH:mm:ss")
        .build()
    );

    public CustomTimeFormat() {
        super(INFO);
    }

    @Override
    public String getText() {
        return TimeFormatter.format(timeFormat.get());
    }
}
