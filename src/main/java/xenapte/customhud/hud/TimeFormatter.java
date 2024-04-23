package xenapte.customhud.hud;

import java.text.SimpleDateFormat;
import java.util.Date;

import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.settings.StringSetting;
import meteordevelopment.meteorclient.systems.hud.HudElementInfo;
// import meteordevelopment.starscript;
import xenapte.customhud.Addon;

public class TimeFormatter extends CustomTextHUD {
    public static final HudElementInfo<TimeFormatter> INFO = new HudElementInfo<>(Addon.HUD_GROUP, "custom-time-format", "Custom time format.", TimeFormatter::new);
    protected final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<String> timeFormat = sgGeneral.add(new StringSetting.Builder()
        .name("time-format")
        .description("Time format (see java.text.SimpleDateFormat).")
        .defaultValue("YYYY-MM-dd HH:mm:ss")
        .build()
    );

    public TimeFormatter() {
        super(INFO);
    }

    public static String format(String fmt) {
        try {
            var formatter = new SimpleDateFormat(fmt);
            return formatter.format(new Date());
        }
        catch (IllegalArgumentException e) {
            return e.toString();
        }
    }

    @Override
    public String getText() {
        return format(timeFormat.get());
    }
}
