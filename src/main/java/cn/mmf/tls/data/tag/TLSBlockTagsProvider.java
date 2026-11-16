package cn.mmf.tls.data.tag;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import cn.mmf.tls.TheLastSmith;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class TLSBlockTagsProvider extends BlockTagsProvider {

    public TLSBlockTagsProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, 
            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TheLastSmith.MODID, existingFileHelper);

    }

    @Override
    protected void addTags(@NotNull Provider provider) {

    }

}
