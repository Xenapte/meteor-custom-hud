package xenapte.customhud.hud;

import static meteordevelopment.meteorclient.MeteorClient.mc;

import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.hud.HudElementInfo;
// import meteordevelopment.starscript;
import xenapte.customhud.Addon;

public class WeatherDisplay extends CustomTextHUD {
    public static final HudElementInfo<WeatherDisplay> INFO = new HudElementInfo<>(Addon.HUD_GROUP, "weather-display", "Weather display.", WeatherDisplay::new);
    protected final SettingGroup sgGeneral = settings.getDefaultGroup();

    public WeatherDisplay() {
        super(INFO);
    }

    public static String getWeather() {
        if (mc.world == null)
            return "";
        if (mc.world.isThundering())
            return "Thunderstorm";
        else if (mc.world.isRaining())
            return "Raining";
        return "Clear";
    }

    @Override
    public String getText() {
        return getWeather();
    }
}
