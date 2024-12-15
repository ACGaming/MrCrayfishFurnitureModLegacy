package com.mrcrayfish.furniture.tileentity;

import com.mrcrayfish.furniture.gui.inventory.ISimpleInventory;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityCookieJar extends TileEntity implements ISimpleInventory
{
    @Override
    public int getSize()
    {
        return getBlockType().getMetaFromState(getWorld().getBlockState(pos));
    }

    @Override
    public ItemStack getItem(int i)
    {
        return new ItemStack(Items.COOKIE);
    }

    @Override
    public void clear()
    {
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
    {
        return oldState.getBlock() != newState.getBlock();
    }
}
