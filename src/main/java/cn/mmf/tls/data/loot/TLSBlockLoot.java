package cn.mmf.tls.data.loot;

import cn.mcmod_mmf.mmlib.data.loot.AbstartctBlockLoot;
import cn.mmf.tls.block.BlockRegistry;
import net.minecraft.core.HolderLookup;

public class TLSBlockLoot extends AbstartctBlockLoot {

	public TLSBlockLoot(HolderLookup.Provider provider) {
		super(provider);
	}

	@Override
	public void addTables() {
		dropSelf(BlockRegistry.RESEARCH_TABLE.get());
	}

}
