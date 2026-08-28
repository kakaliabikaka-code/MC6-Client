package com.mc6.client;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.Gui;
import java.awt.Color;
import java.io.IOException;

public class PinkClickGUI extends GuiScreen {
    private int startX = 100;
    private int startY = 100;
    private int widthVal = 260;
    private int heightVal = 200;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        Gui.drawRect(startX, startY, startX + widthVal, startY + 20, new Color(20, 20, 20, 240).getRGB());
        Gui.drawRect(startX, startY + 20, startX + widthVal, startY + heightVal, new Color(30, 30, 30, 220).getRGB());

        Gui.drawRect(startX, startY + 20, startX + widthVal, startY + 22, new Color(255, 20, 147).getRGB());

        fontRendererObj.drawStringWithShadow("MC6 Client - ClickGUI", startX + 6, startY + 6, new Color(255, 182, 193).getRGB());

        int moduleY = startY + 30;
        for (Module m : MC6Client.INSTANCE.getModuleManager().getModules()) {
            boolean hovered = mouseX >= startX + 10 && mouseX <= startX + widthVal - 10 && mouseY >= moduleY && mouseY <= moduleY + 20;
            
            int bgColor = hovered ? new Color(50, 50, 50, 200).getRGB() : new Color(40, 40, 40, 150).getRGB();
            Gui.drawRect(startX + 10, moduleY, startX + widthVal - 10, moduleY + 20, bgColor);

            fontRendererObj.drawStringWithShadow(m.getName(), startX + 15, moduleY + 6, -1);

            String status = m.isToggled() ? "AÇIK" : "KAPALI";
            int statusColor = m.isToggled() ? new Color(255, 20, 147).getRGB() : new Color(150, 150, 150).getRGB();
            fontRendererObj.drawStringWithShadow(status, startX + widthVal - 45, moduleY + 6, statusColor);

            moduleY += 25;
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (mouseButton == 0) {
            int moduleY = startY + 30;
            for (Module m : MC6Client.INSTANCE.getModuleManager().getModules()) {
                if (mouseX >= startX + 10 && mouseX <= startX + widthVal - 10 && mouseY >= moduleY && mouseY <= moduleY + 20) {
                    m.toggle();
                }
                moduleY += 25;
            }
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean doesGuiScreenPauseGame() {
        return false;
    }
}
