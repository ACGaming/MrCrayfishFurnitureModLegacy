package com.mrcrayfish.furniture.gui.containers;

import com.mrcrayfish.furniture.advancement.Triggers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerModernMailBox extends Container
{
    private IInventory modernmailBoxInventory;

    public ContainerModernMailBox(IInventory playerInventory, IInventory modernmailBoxInventory)
    {
        this.modernmailBoxInventory = modernmailBoxInventory;
        modernmailBoxInventory.openInventory(null);

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                this.addSlotToContainer(new Slot(modernmailBoxInventory, j + i * 3, 62 + j * 18, 16 + i * 18)
                {
                    @Override
                    public ItemStack onTake(EntityPlayer player, ItemStack stack)
                    {
                        Triggers.trigger(Triggers.PLAYER_GOT_MAIL, player);
                        return super.onTake(player, stack);
                    }
                });
            }
        }

        for(int i = 0; i < 3; i++)
    {
        for(int j = 0; j < 9; ++j)
        {
            this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, j * 18 + 8, i * 18 + 94));
        }
    }

        for(int i = 0; i < 9; i++)
    {
        this.addSlotToContainer(new Slot(playerInventory, i, i * 18 + 8, 152));
    }
}

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.modernmailBoxInventory.isUsableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNum)
    {
        ItemStack itemCopy = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(slotNum);

        if(slot != null && slot.getHasStack())
        {
            ItemStack item = slot.getStack();
            itemCopy = item.copy();

            if(slotNum < 9)
            {
                if(!this.mergeItemStack(item, 9, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if(!this.mergeItemStack(item, 0, 9, false))
            {
                return ItemStack.EMPTY;
            }

            if(item.getCount() == 0)
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemCopy;
    }

    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);
        this.modernmailBoxInventory.closeInventory(player);
    }
}