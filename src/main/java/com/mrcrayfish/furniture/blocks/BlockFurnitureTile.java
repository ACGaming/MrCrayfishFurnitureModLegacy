package com.mrcrayfish.furniture.blocks;

import java.util.stream.IntStream;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.mrcrayfish.furniture.gui.inventory.ISimpleInventory;
import com.mrcrayfish.furniture.util.InventoryUtil;

public abstract class BlockFurnitureTile extends BlockFurniture implements ITileEntityProvider
{
    public BlockFurnitureTile(Material material)
    {
        super(material);
        this.hasTileEntity = true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos)
    {
        TileEntity tileEntity = world.getTileEntity(pos);
        return Container.calcRedstone(tileEntity);
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        TileEntity tileEntity = world.getTileEntity(pos);
        if(tileEntity instanceof IInventory)
        {
            IInventory inv = (IInventory) tileEntity;
            InventoryHelper.dropInventoryItems(world, pos, inv);
            IntStream.range(0, inv.getSizeInventory())
                .filter(i -> !inv.getStackInSlot(i).isEmpty())
                .forEach(i -> inv.setInventorySlotContents(i, ItemStack.EMPTY));
        }
        if(tileEntity instanceof ISimpleInventory)
        {
            ISimpleInventory inv = (ISimpleInventory) tileEntity;
            InventoryUtil.dropInventoryItems(world, pos, inv);
            inv.clear();
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity != null && tileentity.receiveClientEvent(id, param);
    }
}
