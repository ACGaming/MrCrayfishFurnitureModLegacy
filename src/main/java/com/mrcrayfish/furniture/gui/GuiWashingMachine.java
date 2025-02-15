package com.mrcrayfish.furniture.gui;

import com.mrcrayfish.furniture.gui.containers.ContainerWashingMachine;
import com.mrcrayfish.furniture.network.PacketHandler;
import com.mrcrayfish.furniture.network.message.MessageWashingMachine;
import com.mrcrayfish.furniture.tileentity.TileEntityWashingMachine;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

public class GuiWashingMachine extends GuiContainer
{
    private static final ResourceLocation gui = new ResourceLocation("cfm:textures/gui/washingmachine.png");
    private TileEntityWashingMachine tileEntityWashingMachine;

    private GuiButton button_start;

    public GuiWashingMachine(InventoryPlayer inventoryPlayer, TileEntityWashingMachine tileEntityWashingMachine)
    {
        super(new ContainerWashingMachine(inventoryPlayer, tileEntityWashingMachine));
        this.tileEntityWashingMachine = tileEntityWashingMachine;
        this.xSize = 176;
        this.ySize = 228;
    }

    @Override
    public void initGui()
    {
        super.initGui();
        Keyboard.enableRepeatEvents(false);
        buttonList.clear();

        int posX = width / 2;
        int posY = height / 2;

        button_start = new GuiButton(0, posX - 35, posY - 109, 32, 20, I18n.format("cfm.button.start"));
        buttonList.add(button_start);
    }

    @Override
    protected void actionPerformed(GuiButton guibutton)
    {
        if(!guibutton.enabled)
        {
            return;
        }

        if(guibutton.id == 0)
        {
            if(!tileEntityWashingMachine.isWashing())
            {
                PacketHandler.INSTANCE.sendToServer(new MessageWashingMachine(0, tileEntityWashingMachine.getPos().getX(), tileEntityWashingMachine.getPos().getY(), tileEntityWashingMachine.getPos().getZ()));
            }
            else
            {
                PacketHandler.INSTANCE.sendToServer(new MessageWashingMachine(1, tileEntityWashingMachine.getPos().getX(), tileEntityWashingMachine.getPos().getY(), tileEntityWashingMachine.getPos().getZ()));
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);

        if(isPointInRegion(37, 9, 11, 11, mouseX, mouseY))
        {
            if(tileEntityWashingMachine.isWashing())
            {
                drawHoveringText(I18n.format("cfm.gui.run"), mouseX, mouseY);
            }
            else
            {
                drawHoveringText(I18n.format("cfm.gui.stop"), mouseX, mouseY);
            }
        }

        if(isPointInRegion(129, 30, 10, 73, mouseX, mouseY))
        {
            drawHoveringText(I18n.format("cfm.gui.soap_level", tileEntityWashingMachine.timeRemaining), mouseX, mouseY);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRenderer.drawString(I18n.format("container.inventory"), 8, 135, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(gui);
        int l = (width - xSize) / 2;
        int i1 = (height - ySize) / 2;
        this.drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);

        if(tileEntityWashingMachine.isWashing())
        {
            int superMode = tileEntityWashingMachine.superMode ? 20 : 50;
            int percent = (tileEntityWashingMachine.progress % superMode) * 73 / superMode;
            drawTexturedModalRect((l + 34), (i1 + 104) - percent, 176, 73 - percent, 16, percent);
        }

        int percent = tileEntityWashingMachine.timeRemaining * 73 / 5000;
        int superMode = tileEntityWashingMachine.superMode ? 20 : 0;
        drawTexturedModalRect((l + 129), (i1 + 103) - percent, 192 + superMode, 0, 10, percent);

        drawTexturedModalRect((l + 129), (i1 + 30), 202, 0, 10, 73);

        if(tileEntityWashingMachine.isWashing())
        {
            button_start.displayString = I18n.format("cfm.button.stop");
            drawColour(l + 37, i1 + 9, 11, 11, -16711936);
        }
        else
        {
            button_start.displayString = I18n.format("cfm.button.start");
            drawColour(l + 37, i1 + 9, 11, 11, -65280);
        }
    }

    public void drawColour(int x, int y, int width, int height, int par4)
    {
        drawRect(x, y, x + width, y + height, par4);
        GlStateManager.color(1, 1, 1, 1);
    }
}
