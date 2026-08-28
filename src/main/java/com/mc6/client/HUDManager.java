package com.github.kakaliabikakacode.mc6client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Mouse;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class HUDManager {
    private Minecraft mc = Minecraft.getMinecraft();
    private List<Long> leftClicks = new ArrayList<>();
    private List<Long> rightClicks = new ArrayList<>();
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    @SubscribeEvent
    public void onTick(TickEvent.RenderTickEvent event) {
        if (mc.thePlayer == null || mc.currentScreen != null) return;

        ModuleManager mm = MC6Client.INSTANCE.getModuleManager();

        boolean leftDown = Mouse.isButtonDown(0);
        if (leftDown && !leftPressed) {
            leftClicks.add(System.currentTimeMillis());
        }
        leftPressed = leftDown;

        boolean rightDown = Mouse.isButtonDown(1);
        if (rightDown && !rightPressed) {
            rightClicks.add(System.currentTimeMillis());
        }
        rightPressed = rightDown;

        leftClicks.removeIf(a -> System.currentTimeMillis() - a > 1000);
        rightClicks.removeIf(a -> System.currentTimeMillis() - a > 1000);

        Module cpsModule = mm.getModuleByName("CPS Counter");
        if (cpsModule != null && cpsModule.isToggled()) {
            String cpsText = "CPS: L " + leftClicks.size() + " | R " + rightClicks.size();
            mc.fontRendererObj.drawStringWithShadow(cpsText, 5, 5, new Color(255, 105, 180).getRGB());
        }

        Module keysModule = mm.getModuleByName("Keystrokes");
        if (keysModule != null && keysModule.isToggled()) {
            drawKeystrokes();
        }
    }

    private void drawKeystrokes() {
        int x = 5;
        int y = 25;
        int size = 20;

        boolean w = mc.gameSettings.keyBindForward.isKeyDown();
        boolean a = mc.gameSettings.keyBindLeft.isKeyDown();
        boolean s = mc.gameSettings.keyBindBack.isKeyDown();
        boolean d = mc.gameSettings.keyBindRight.isKeyDown();
        boolean lmb = Mouse.isButtonDown(0);
        boolean rmb = Mouse.isButtonDown(1);

        Gui.drawRect(x + size + 2, y, x + (size * 2) + 2, y + size, w ? new Color(255, 20, 147, 180).getRGB() : new Color(0, 0, 0, 120).getRGB());
        mc.fontRendererObj.drawString("W", x + size + 8, y + 6, -1);

        Gui.drawRect(x, y + size + 2, x + size, y + (size * 2) + 2, a ? new Color(255, 20, 147, 180).getRGB() : new Color(0, 0, 0, 120).getRGB());
        mc.fontRendererObj.drawString("A", x + 7, y + size + 8, -1);

        Gui.drawRect(x + size + 2, y + size + 2, x + (size * 2) + 2, y + (size * 2) + 2, s ? new Color(255, 20, 147, 180).getRGB() : new Color(0, 0, 0, 120).getRGB());
        mc.fontRendererObj.drawString("S", x + size + 8, y + size + 8, -1);

        Gui.drawRect(x + (size * 2) + 4, y + size + 2, x + (size * 3) + 4, y + (size * 2) + 2, d ? new Color(255, 20, 147, 180).getRGB() : new Color(0, 0, 0, 120).getRGB());
        mc.fontRendererObj.drawString("D", x + (size * 2) + 10, y + size + 8, -1);

        Gui.drawRect(x, y + (size * 2) + 4, x + (int)(size * 1.5) + 1, y + (int)(size * 2.5) + 4, lmb ? new Color(255, 20, 147, 180).getRGB() : new Color(0, 0, 0, 120).getRGB());
        mc.fontRendererObj.drawString("LMB", x + 7, y + (size * 2) + 9, -1);

        Gui.drawRect(x + (int)(size * 1.5) + 3, y + (size * 2) + 4, x + (size * 3) + 4, y + (int)(size * 2.5) + 4, rmb ? new Color(255, 20, 147, 180).getRGB() : new Color(0, 0, 0, 120).getRGB());
        mc.fontRendererObj.drawString("RMB", x + (size * 2) + 8, y + (size * 2) + 9, -1);
    }
}
