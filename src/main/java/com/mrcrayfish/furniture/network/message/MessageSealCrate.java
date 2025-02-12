package com.mrcrayfish.furniture.network.message;

import com.mrcrayfish.furniture.gui.containers.ContainerComputer;
import com.mrcrayfish.furniture.gui.containers.ContainerCrate;
import com.mrcrayfish.furniture.tileentity.TileEntityCrate;
import io.netty.buffer.ByteBuf;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSealCrate implements IMessage, IMessageHandler<MessageSealCrate, IMessage>
{
    private int x, y, z;

    public MessageSealCrate() {}

    public MessageSealCrate(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    @Override
    public IMessage onMessage(MessageSealCrate message, MessageContext ctx)
    {
        World world = ctx.getServerHandler().player.world;
        BlockPos pos = new BlockPos(message.x, message.y, message.z);
        if(!world.isAreaLoaded(pos, 0))
            return null;

        Container container = ctx.getServerHandler().player.openContainer;
        if(!(container instanceof ContainerCrate))
            return null;

        TileEntity tileEntity = world.getTileEntity(pos);
        if(tileEntity instanceof TileEntityCrate)
        {
            TileEntityCrate crate = (TileEntityCrate) tileEntity;
            if(((ContainerCrate) container).getCrateInventory() != crate)
                return null;
            crate.seal();
        }
        return null;
    }
}
