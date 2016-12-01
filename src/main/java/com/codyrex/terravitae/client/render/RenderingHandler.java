package com.codyrex.terravitae.client.render;

import com.codyrex.terravitae.Terravitae;
import com.codyrex.terravitae.server.api.DefaultRenderedItem;
import com.codyrex.terravitae.server.api.IgnoreRenderProperty;
import com.codyrex.terravitae.server.api.SubtypeRenderedItem;
import com.codyrex.terravitae.server.block.BlockHandler;
import com.codyrex.terravitae.server.item.ItemHandler;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderingHandler {
    private static final Minecraft MINECRAFT = Minecraft.getMinecraft();

    public void onPreInit() {
        for (Block block : BlockHandler.BLOCKS) {
            if (block instanceof IgnoreRenderProperty) {
                IProperty<?>[] ignoredProperties = ((IgnoreRenderProperty) block).getIgnoredProperties();
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(ignoredProperties).build());
            }
        }

        for (Block block : BlockHandler.BLOCKS) {
            if (block instanceof DefaultRenderedItem) {
                this.registerBlockRenderer(block, ((DefaultRenderedItem) block).getResource(block.getUnlocalizedName().substring("tile.".length())), "inventory");
            }
        }

        for (Item item : ItemHandler.ITEMS) {
            String name = item.getUnlocalizedName().substring("item.".length());
            if (item instanceof DefaultRenderedItem) {
                this.registerItemRenderer(item, ((DefaultRenderedItem) item).getResource(name), "inventory");
            } else if (item instanceof SubtypeRenderedItem) {
                SubtypeRenderedItem subtypeItem = (SubtypeRenderedItem) item;
                int[] subtypes = subtypeItem.getUsedSubtypes();
                for (int metadata : subtypes) {
                    this.registerItemRenderer(item, metadata, subtypeItem.getResource(name, metadata), "inventory");
                }
            }
        }
    }

    public void onInit() {
    }

    public void onPostInit() {
    }

    public void registerItemRenderer(Item item, String path, String type) {
        this.registerItemRenderer(item, 0, path, type);
    }

    public void registerItemRenderer(Item item, int meta, String path, String type) {
        ModelResourceLocation resource = new ModelResourceLocation(Terravitae.MODID + ":" + path, type);
        ModelLoader.setCustomModelResourceLocation(item, meta, resource);
    }

    public void registerBlockRenderer(Block block, int meta, String path, String type) {
        this.registerItemRenderer(Item.getItemFromBlock(block), meta, path, type);
    }

    public void registerBlockRenderer(Block block, final String path, final String type) {
        this.registerBlockRenderer(block, 0, path, type);
    }
}
