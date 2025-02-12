package com.mrcrayfish.furniture.gui.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBin extends Container
{
    private IInventory binInventory;

    public ContainerBin(IInventory playerInventory, IInventory binInventory)
    {
        this.binInventory = binInventory;
        this.binInventory.openInventory(null);

        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                this.addSlotToContainer(new Slot(binInventory, j + i * 3, j * 18 + 62, i * 18 + 18));
            }
        }

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, j * 18 + 8, i * 18 + 115));
            }
        }

        for(int i = 0; i < 9; i++)
        {
            this.addSlotToContainer(new Slot(playerInventory, i, i * 18 + 8, 173));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slot)
    {
        ItemStack var3 = ItemStack.EMPTY;
        Slot var4 = this.inventorySlots.get(slot);

        if(var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if(slot < 12)
            {
                if(!this.mergeItemStack(var5, 12, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if(!this.mergeItemStack(var5, 0, 12, false))
            {
                return ItemStack.EMPTY;
            }

            if(var5.getCount() == 0)
            {
                var4.putStack(ItemStack.EMPTY);
            }
            else
            {
                var4.onSlotChanged();
            }
        }

        return var3;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return this.binInventory.isUsableByPlayer(entityPlayer);
    }

    @Override
    public void onContainerClosed(EntityPlayer entityPlayer)
    {
        super.onContainerClosed(entityPlayer);
        binInventory.closeInventory(entityPlayer);
    }

    public IInventory getBinInventory()
    {
        return binInventory;
    }
}
