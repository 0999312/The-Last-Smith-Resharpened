package cn.mmf.tls.slasharts;

import mods.flammpfeil.slashblade.RegistryEvents;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.BladeStateAccess;
import mods.flammpfeil.slashblade.entity.EntityBlisteringSwords;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class SakuraBlisteringSwords {
    public static void summonSwords(LivingEntity playerIn, boolean critical, double damage) {
        int colorCode = BladeStateAccess.of(playerIn.getMainHandItem())
                .map(state -> state.getColorCode()).orElse(0xFF3333FF);
        summonSwords(playerIn, colorCode, critical, damage, 4);
    }

    public static void summonSwords(LivingEntity playerIn, int colorCode, boolean critical, double damage, int baseCount) {
        if (playerIn.level().isClientSide())
            return;

        BladeStateAccess.of(playerIn.getMainHandItem()).ifPresent((state) -> {

            Level worldIn = playerIn.level();

            int rank = playerIn.getData(CapabilityConcentrationRank.RANK_POINT.get())
                    .getRank(worldIn.getGameTime()).level;
            int count = baseCount + rank;

            for (int i = 0; i < count; i++) {
                // 2026-10-06:17-10 Fixed NPE: the SlashBlade entity requires a registered EntityType,
                // passing null crashed in Entity#<init>. Use RegistryEvents.BlisteringSwords.
                // 2026-10-06:17-10 修复空指针：拔刀剑实体需要已注册的 EntityType，传入 null 会在 Entity 构造时崩溃，
                // 改用 RegistryEvents.BlisteringSwords。
                EntityBlisteringSwords ss = new EntityBlisteringSwords(RegistryEvents.BlisteringSwords,
                        worldIn);

                ss.setPos(playerIn.position());
                ss.setIsCritical(critical);
                ss.setOwner(playerIn);
                ss.setColor(colorCode);
                ss.setRoll(0);
                ss.setDamage(damage);
                // force riding
                ss.startRiding(playerIn, true);

                ss.setDelay(i);

                worldIn.addFreshEntity(ss);

                playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
            }
        });
    }
}
