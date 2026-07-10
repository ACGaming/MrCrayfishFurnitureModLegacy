package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;
import com.mrcrayfish.furniture.advancement.Triggers;
import com.mrcrayfish.furniture.tileentity.TileEntityModernMailBox;
import com.mrcrayfish.furniture.util.TileEntityUtil;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockModernMailBox extends BlockFurnitureTile
{

    public BlockModernMailBox(Material material, String id)
    {
        super(material, id);
        this.setSoundType(SoundType.METAL);
        this.setHardness(1.0F);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        if(placer instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) placer;
            if(!world.isRemote)
            {
                player.sendMessage(new TextComponentTranslation("cfm.message.mailbox_ownerrequest"));
            }
            Triggers.trigger(Triggers.PLACE_MAIL_BOX, player);
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        TileEntity tile_entity = worldIn.getTileEntity(pos);
        if(!worldIn.isRemote)
        {
            if(tile_entity instanceof TileEntityModernMailBox)
            {
                TileEntityModernMailBox tileEntityModernMailBox = (TileEntityModernMailBox) tile_entity;
                if(!tileEntityModernMailBox.hasOwner())
                {
                    tileEntityModernMailBox.setOwner(playerIn);
                    playerIn.sendMessage(new TextComponentTranslation("cfm.message.mailbox_ownerget", TextFormatting.YELLOW + playerIn.getName()));
                    TileEntityUtil.markBlockForUpdate(worldIn, pos);
                    return true;
                }

                tileEntityModernMailBox.tryAndUpdateName(playerIn);

                if(tileEntityModernMailBox.canOpen(playerIn))
                {
                    playerIn.openGui(MrCrayfishFurnitureMod.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
                }
                else
                {
                    playerIn.sendMessage(new TextComponentTranslation("cfm.message.mailbox_belong", TextFormatting.YELLOW + tileEntityModernMailBox.getOwner()));
                }
            }
        }
        return true;
    }

    @Override
    public float getExplosionResistance(Entity entity)
    {
        if(entity instanceof EntityCreeper || entity instanceof EntityTNTPrimed)
        {
            return 1000;
        }
        return super.getExplosionResistance(entity);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityModernMailBox();
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos)
    {
        TileEntityModernMailBox mailbox = (TileEntityModernMailBox) world.getTileEntity(pos);
        return mailbox.getMailCount() > 0 ? 1 : 0;
    }
}
