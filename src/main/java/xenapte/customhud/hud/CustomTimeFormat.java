package xenapte.customhud.hud;

import java.util.Date;
import java.text.SimpleDateFormat;

import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.settings.StringSetting;
import meteordevelopment.meteorclient.systems.hud.HudElementInfo;
// import meteordevelopment.starscript;
import xenapte.customhud.Addon;

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
        var format = new SimpleDateFormat(timeFormat.get());
        return format.format(new Date());
    }
}
