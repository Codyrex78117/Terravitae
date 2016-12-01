package com.codyrex.terravitae.server.block;

import com.codyrex.terravitae.Terravitae;
import com.codyrex.terravitae.server.api.BlockEntity;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BlockHandler {
    public static final List<Block> BLOCKS = new ArrayList<>();

    public static void register() {
        try {
            for (Field field : BlockHandler.class.getDeclaredFields()) {
                Object obj = field.get(null);
                if (obj instanceof Block) {
                    BlockHandler.registerBlock((Block) obj);
                } else if (obj instanceof Block[]) {
                    for (Block block : (Block[]) obj) {
                        BlockHandler.registerBlock(block);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void registerBlock(Block block) {
        String name = block.getUnlocalizedName().substring("tile.".length());
        ResourceLocation identifier = new ResourceLocation(Terravitae.MODID, name);
        GameRegistry.register(block, identifier);
        GameRegistry.register(new ItemBlock(block), identifier);
        BLOCKS.add(block);
        if (block instanceof BlockEntity) {
            GameRegistry.registerTileEntity(((BlockEntity) block).getEntity(), Terravitae.MODID + "." + name);
        }
    }
}
