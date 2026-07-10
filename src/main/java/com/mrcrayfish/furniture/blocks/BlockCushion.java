package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.util.SeatUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BlockCushion extends Block
{
    private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0);

    public BlockCushion(Material material, String id)
    {
        super(material);
        this.setHardness(0.5F);
        this.setSoundType(SoundType.CLOTH);
        this.setCreativeTab(MrCrayfishFurnitureMod.tabFurniture);
        this.setTranslationKey(id);
        this.setRegistryName(id);
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BOUNDING_BOX;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!playerIn.isSneaking())
        {
            if(SeatUtil.sitOnBlock(worldIn, pos.getX(), pos.getY(), pos.getZ(), playerIn, 5 * 0.0625))
            {
                worldIn.updateComparatorOutputLevel(pos, this);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onLanded(World worldIn, Entity entityIn)
    {
        if(entityIn.isSneaking())
        {
            super.onLanded(worldIn, entityIn);
        }
        else if(entityIn.motionY < 0.0D)
        {
            entityIn.motionY = -entityIn.motionY * 0.8D;

            if(!(entityIn instanceof EntityLivingBase))
            {
                entityIn.motionY *= 0.8D;
            }
        }
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entity, boolean p_185477_7_)
    {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, BOUNDING_BOX);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add("It's really comfortable !");
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
}
