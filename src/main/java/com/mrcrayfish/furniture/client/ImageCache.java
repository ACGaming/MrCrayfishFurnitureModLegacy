package com.mrcrayfish.furniture.client;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.mrcrayfish.furniture.MrCrayfishFurnitureMod;

/**
 * Author: MrCrayfish, ACGaming
 */
@SideOnly(Side.CLIENT)
public final class ImageCache
{
    public static final ImageCache INSTANCE = new ImageCache();

    private final File cache;
    private final Map<String, Texture> cacheMap = new ConcurrentHashMap<>();

    private ImageCache()
    {
        cache = new File(Minecraft.getMinecraft().mcDataDir, "photo-frame-cache");
        cache.mkdir();
        this.init();
    }

    @Nullable
    public Texture get(String url)
    {
        if (url == null)
        {
            return null;
        }
        Texture texture = cacheMap.get(url);
        if (texture != null)
        {
            return texture;
        }
        return cacheMap.get(url);
    }

    public void add(String url, File file)
    {
        if (!cacheMap.containsKey(url))
        {
            Texture texture = new Texture(file);
            cacheMap.put(url, texture);
        }
    }

    public boolean add(String url, byte[] data)
    {
        try
        {
            if (!cacheMap.containsKey(url))
            {
                String id = DigestUtils.sha1Hex(url.getBytes());
                File image = new File(getCache(), id);
                FileUtils.writeByteArrayToFile(image, data);
                Texture texture = new Texture(image);
                cacheMap.put(url, texture);
                Minecraft.getMinecraft().addScheduledTask(texture::update);
            }
            return true;
        }
        catch (IOException e)
        {
            MrCrayfishFurnitureMod.logger().warn(e);
        }
        return false;
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event)
    {
        if (event.phase == TickEvent.Phase.START)
        {
            this.tick();
        }
    }

    public boolean loadCached(String url)
    {
        if (cacheMap.containsKey(url))
        {
            return true;
        }
        String id = DigestUtils.sha1Hex(url.getBytes());
        File file = new File(getCache(), id);
        if (file.exists())
        {
            this.add(url, file);
            return true;
        }
        return false;
    }

    public boolean isCached(String url)
    {
        return cacheMap.containsKey(url);
    }

    public File getCache()
    {
        cache.mkdir();
        return cache;
    }

    private void init()
    {
        try
        {
            FileUtils.writeStringToFile(new File(cache, "!read-me.txt"), "This is a cache for images that are displayed in photo frames (in MrCrayfish's Furniture Mod) in order to speed up load time.\nIt is safe to delete the entire folder in case you are running out of space, however it will mean that all images will have to be downloaded again.", StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            MrCrayfishFurnitureMod.logger().warn(e);
        }
    }

    private void tick()
    {
        cacheMap.values().forEach(Texture::update);
    }
}
