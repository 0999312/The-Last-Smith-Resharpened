package cn.mmf.tls.event;

import cn.mmf.tls.TheLastSmith;
import cn.mmf.tls.item.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = TheLastSmith.MODID)
public class HardeningEventsHandler {
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onCauldronClickedEvent(PlayerInteractEvent.RightClickBlock event) {
        if (event.getLevel().isClientSide()) return;

        Player player = event.getEntity();
        ItemStack heldItem = event.getItemStack();
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        if (heldItem.getItem().equals(ItemRegistry.BLADE_UNFINISHED_3.get())) {
            handler(event, player, heldItem, level, pos, state,
                    ItemRegistry.BLADE_UNFINISHED_4.get().getDefaultInstance());
        } else if (heldItem.getItem().equals(ItemRegistry.BLADE_SAKURA_UNFINISHED_3.get())) {
            handler(event, player, heldItem, level, pos, state,
                    ItemRegistry.BLADE_SAKURA_UNFINISHED_4.get().getDefaultInstance());
        }
    }

    private static void handler(PlayerInteractEvent.RightClickBlock event,
                                Player player,
                                ItemStack heldItem,
                                Level level,
                                BlockPos pos,
                                BlockState state,
                                ItemStack result) {
        if (state.getBlock() == Blocks.WATER_CAULDRON) {
            int currentLevel = state.getValue(BlockStateProperties.LEVEL_CAULDRON);
//            ItemStack cooled = ItemRegistry.BLADE_UNFINISHED_4.get().getDefaultInstance();
            if (player.isCreative()) {
                heldItem.shrink(1);
                player.getInventory().add(result);
            } else {
                heldItem.shrink(1);
                player.setItemInHand(event.getHand(), result);
                currentLevel -= 1;
            }

            BlockState newState = currentLevel == 0
                    ? Blocks.CAULDRON.defaultBlockState()
                    : state.setValue(BlockStateProperties.LEVEL_CAULDRON, currentLevel);
            level.setBlock(pos, newState, 3);
            level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);

            // 可选：产生烟雾粒子
            level.addParticle(ParticleTypes.SMOKE,
                    pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5,
                    0.0D, 0.1D, 0.0D);
        } else {
            net.minecraft.network.chat.Component message =
                    net.minecraft.network.chat.Component
                            .translatable("message.last_smith.cauldron_empty");
            player.displayClientMessage(message, true);
        }
    }
}
