package cn.mmf.tls.data;

import cn.mcmod_mmf.mmlib.data.AbstractBlockStateProvider;
import cn.mmf.tls.TheLastSmith;
import cn.mmf.tls.block.BlockRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


public class TLSBlockStateProvider extends AbstractBlockStateProvider {

    public TLSBlockStateProvider(PackOutput gen, ExistingFileHelper exFileHelper) {
        super(gen, TheLastSmith.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        horizontalBlock(BlockRegistry.RESEARCH_TABLE.get(),
                models().getExistingFile(ResourceLocation.fromNamespaceAndPath(TheLastSmith.MODID, "block/research_table")));

    }

}
