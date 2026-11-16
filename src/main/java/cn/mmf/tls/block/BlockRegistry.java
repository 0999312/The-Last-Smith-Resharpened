package cn.mmf.tls.block;

import cn.mmf.tls.TheLastSmith;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, TheLastSmith.MODID);
    public static final DeferredHolder<Block, Block> RESEARCH_TABLE = BLOCKS.register("research_table", ResearchTableBlock::new);
}
