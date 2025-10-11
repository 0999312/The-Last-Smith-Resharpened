package cn.mmf.tls.se;

import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.entity.EntityAbstractSummonedSword;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.joml.Vector3f;

@EventBusSubscriber
public class SectumsempraSE extends SpecialEffect {
    public static final Vector3f COLOR_WHITE = new Vector3f(1f, 1f, 1f);
    public static final Vector3f COLOR_GREY = new Vector3f(0.25f, 0.25f, 0.25f);
    public static final DustParticleOptions PARTICLE_WHITE = new DustParticleOptions(COLOR_WHITE, 0.35F);
    public static final DustParticleOptions PARTICLE_GREY = new DustParticleOptions(COLOR_GREY, 0.5F);

    public static final String SECTUMSEMPRA_BOOSTED = "last_smith.ss.boosted";

    public SectumsempraSE(int requestLevel, boolean isCopiable, boolean isRemovable) {
        super(requestLevel, isCopiable, isRemovable);
    }

    @SubscribeEvent
    public static void onSummonedSwordOnHitEntityEvent(SlashBladeEvent.SummonedSwordOnHitEntityEvent event) {
        EntityAbstractSummonedSword summonedSword = event.getSummonedSword();
        if (summonedSword.getShooter() instanceof ServerPlayer player) {
            ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
            stack.getCapability(CapabilitySlashBlade.BLADESTATE).ifPresent((state) -> {
                Entity target = event.getTarget();
                doSectumsempra(state, player, target, summonedSword);

            });

        }
    }

    private static boolean doSectumsempra(ISlashBladeState state, ServerPlayer player, Entity target, EntityAbstractSummonedSword summonedSword) {
        if (!state.hasSpecialEffect(TLSSpecialEffectRegistry.SECTUMSEMPRA_SE.getId())) return false;
        int level = player.experienceLevel;
        if (!SpecialEffect.isEffective(TLSSpecialEffectRegistry.SECTUMSEMPRA_SE.get(), level)) return false;

        ServerLevel serverLevel = player.serverLevel();
        Vec3 startPos = player.position();
        Vec3 endPos = target.position().add(0, 0.5d, 0);

        drawParticleCurve(serverLevel, startPos, endPos, PARTICLE_WHITE, 96);
        drawParticleCurve(serverLevel, startPos, endPos, PARTICLE_GREY, 64);

        if (!summonedSword.getPersistentData().contains(SECTUMSEMPRA_BOOSTED)){
            summonedSword.getPersistentData().putBoolean(SECTUMSEMPRA_BOOSTED, true);
            summonedSword.setDamage(summonedSword.getDamage() + 1.0f);
            if (target instanceof LivingEntity living) {
                float extraDamage1 = living.getHealth() * 0.04f + 1f;
                living.invulnerableTime = 0;
                living.hurt(player.damageSources().indirectMagic(summonedSword, player), extraDamage1);

                float extraDamage2 = 1f;
                living.invulnerableTime = 0;
                living.hurt(player.damageSources().indirectMagic(summonedSword, player), extraDamage2);
            }
        }

        return true;

    }

    public static void drawParticleCurve(ServerLevel level, Vec3 start, Vec3 end, ParticleOptions particle, int baseDensity) {
        // 起点随机偏移
        double startOffsetRange = 2.0d;
        double startX = start.x() + ((level.getRandom().nextDouble() - 0.5d) * startOffsetRange);
        double startY = start.y() + (0.5d + (level.getRandom().nextDouble() - 0.5d) * startOffsetRange);
        double startZ = start.z() + ((level.getRandom().nextDouble() - 0.5d) * startOffsetRange);

        // 中点
        double midX = (startX + end.x()) / 2.0d;
        double midY = (startY + end.y()) / 2.0d;
        double midZ = (startZ + end.z()) / 2.0d;

        // 计算起点到终点的距离
        double distance = Math.sqrt(
                Math.pow(end.x() - startX, 2d) +
                        Math.pow(end.y() - startY, 2d) +
                        Math.pow(end.z() - startZ, 2d)
        );

        // 距离越远粒子越多
        double distanceFactor = Math.min(distance / 10.0d, 1.0d);

        int adjustedDensity = (int) (baseDensity * (0.3d + 0.7d * distanceFactor));

        // 距离越近，弯曲度越高
        double curvatureFactor = Math.max(2.0d - distanceFactor * 1.8d, 0.1d);
        double maxOffset = distance * curvatureFactor;
        double offsetX = (level.getRandom().nextDouble() - 0.5d) * maxOffset;
        double offsetY = (level.getRandom().nextDouble() - 0.2d) * maxOffset * 0.8d;
        double offsetZ = (level.getRandom().nextDouble() - 0.5d) * maxOffset;

        // 控制点
        double controlX = midX + offsetX;
        double controlY = midY + offsetY;
        double controlZ = midZ + offsetZ;

        double step = 1.0d / adjustedDensity;

        for (double t = 0d; t <= 1d; t += step) {
            double oneMinusT = 1d - t;
            double oneMinusTSquared = oneMinusT * oneMinusT;
            double tSquared = t * t;
            double twoOneMinusTt = 2d * oneMinusT * t;

            double x = oneMinusTSquared * (startX + 0.5d) +
                    twoOneMinusTt * controlX +
                    tSquared * (end.x() + 0.5d);
            double y = oneMinusTSquared * (startY + 0.5d) +
                    twoOneMinusTt * controlY +
                    tSquared * (end.y() + 0.5d);
            double z = oneMinusTSquared * (startZ + 0.5d) +
                    twoOneMinusTt * controlZ +
                    tSquared * (end.z() + 0.5d);

            level.sendParticles(
                    particle,
                    x, y, z,
                    2,
                    0.0d, 0.0d, 0.0d,
                    10.0d
            );
        }
    }
}
