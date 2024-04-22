package xenapte.customhud.hud;

import meteordevelopment.meteorclient.settings.BoolSetting;
import meteordevelopment.meteorclient.settings.ColorSetting;
import meteordevelopment.meteorclient.settings.DoubleSetting;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.hud.HudElement;
import meteordevelopment.meteorclient.systems.hud.HudElementInfo;
import meteordevelopment.meteorclient.systems.hud.HudRenderer;
import meteordevelopment.meteorclient.utils.render.color.SettingColor;
// import meteordevelopment.starscript;

public class CustomTextHUD extends HudElement {
    protected final SettingGroup sgGeneral = settings.getDefaultGroup();
    protected final SettingGroup sgDisplay = settings.createGroup("Display");

    protected final Setting<SettingColor> color = sgDisplay.add(new ColorSetting.Builder()
            .name("color")
            .description("")
            .defaultValue(SettingColor.WHITE)
            .build()
    );

    protected final Setting<Boolean> shadow = sgDisplay.add(new BoolSetting.Builder()
            .name("shadow")
            .description("")
            .defaultValue(true)
            .build()
    );

    protected final Setting<Double> scale = sgDisplay.add(new DoubleSetting.Builder()
            .name("scale")
            .description("")
            .defaultValue(1.0)
			.min(1)
			.sliderRange(1, 5)
			.build()
    );

    protected final Setting<Integer> updateDelay = sgDisplay.add(new IntSetting.Builder()
            .name("update-delay")
            .description("Update frequency in ticks.")
            .defaultValue(1)
            .min(1)
            .sliderRange(1, 20)
            .build()
    );

    public CustomTextHUD(HudElementInfo<?> INFO) {
        super(INFO);
    }

    private String text = "";
    private int updateTimer = 0;

    public String getText() {
        return "";
    }

    private void updateText() {
        text = getText();
    }

    @Override
    public void tick(HudRenderer renderer) {
        updateTimer ++;
        if (updateTimer < updateDelay.get())
            return;
        updateText();
        updateTimer = 0;
        var scaleValue = scale.get();
        setSize(renderer.textWidth(text, true) * scaleValue * scaleValue, renderer.textHeight(true) * scaleValue * scaleValue);
    }

    @Override
    public void render(HudRenderer renderer) {
        renderer.text(text, x, y, color.get(), shadow.get(), scale.get());
    }
}
