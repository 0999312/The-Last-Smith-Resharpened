package cn.mmf.tls.combo;

import cn.mmf.tls.TheLastSmith;
import cn.mmf.tls.slasharts.SakuraBlisteringSwords;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.init.DefaultResources;
import mods.flammpfeil.slashblade.registry.combo.ComboState;
import mods.flammpfeil.slashblade.slasharts.JudgementCut;
import mods.flammpfeil.slashblade.util.AttackManager;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ComboStateRegistry {
    public static final DeferredRegister<ComboState> COMBO_STATE = DeferredRegister.create(ComboState.REGISTRY_KEY,
            TheLastSmith.MODID);
    public static final DeferredHolder<ComboState, ComboState> ODACHI_COMBO_A1 = COMBO_STATE.register("odachi_combo_a1",
            ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(17, entity -> TheLastSmith.prefix("odachi_combo_a2")))
                    .nextOfTimeout(entity -> TheLastSmith.prefix("odachi_combo_a1_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(3, (entityIn) ->
                                    AttackManager.doSlash(entityIn, -30, Vec3.ZERO, false, AttackManager.isPowered(entityIn),
                                            AttackManager.isPowered(entityIn) ? 1.5F : 1.1F)
                            )
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build);

    public static final DeferredHolder<ComboState, ComboState> ODACHI_COMBO_A1_END = COMBO_STATE.register("odachi_combo_a1_end",
            ComboState.Builder.newInstance().startAndEnd(459, 488).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(entity -> SlashBlade.prefix("none"))
                    .nextOfTimeout(entity -> SlashBlade.prefix("none"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(0, AttackManager::playQuickSheathSoundAction).build())
                    .releaseAction(ComboState::releaseActionQuickCharge)::build);

    public static final DeferredHolder<ComboState, ComboState> ODACHI_COMBO_A2 = COMBO_STATE.register("odachi_combo_a2",
            ComboState.Builder.newInstance().startAndEnd(814, 839).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(8, entity -> TheLastSmith.prefix("odachi_combo_a3")))
                    .nextOfTimeout(entity -> TheLastSmith.prefix("odachi_combo_a2_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(0, (entityIn) ->
                                    AttackManager.doSlash(entityIn, 180 + 75, Vec3.ZERO, false, AttackManager.isPowered(entityIn),
                                            AttackManager.isPowered(entityIn) ? 1.5F : 1.1F)
                            ).build())
                    .addHitEffect(StunManager::setStun)::build);
    public static final DeferredHolder<ComboState, ComboState> ODACHI_COMBO_A2_END = COMBO_STATE.register("odachi_combo_a2_end",
            ComboState.Builder.newInstance().startAndEnd(839, 877).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation).next(entity -> SlashBlade.prefix("none"))
                    .nextOfTimeout(entity -> TheLastSmith.prefix("odachi_combo_a2_end2"))::build);
    public static final DeferredHolder<ComboState, ComboState> ODACHI_COMBO_A2_END2 = COMBO_STATE.register("odachi_combo_a2_end2",
            ComboState.Builder.newInstance().startAndEnd(877, 894).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation).next(entity -> SlashBlade.prefix("none"))
                    .nextOfTimeout(entity -> SlashBlade.prefix("none"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(0, AttackManager::playQuickSheathSoundAction).build())
                    .releaseAction(ComboState::releaseActionQuickCharge)::build);

    public static final DeferredHolder<ComboState, ComboState> ODACHI_COMBO_A3 = COMBO_STATE.register("odachi_combo_a3",
            ComboState.Builder.newInstance().startAndEnd(900, 1013).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(33, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> TheLastSmith.prefix("odachi_combo_a3_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(15, (entityIn) ->
                                    AttackManager.doSlash(entityIn, 35, Vec3.ZERO, false, true,
                                            AttackManager.isPowered(entityIn) ? 1.6F : 1.1F))
                            .put(17, (entityIn) -> AttackManager.doSlash(entityIn, 40, true, true))
                            .put(19, (entityIn) -> AttackManager.doSlash(entityIn, 30, true, true))
                            // 2026-10-06:18-45 Finisher: release the vanilla Judgement Cut (dimension slash).
                            // 2026-10-06:18-45 收招：释放原版「次元斩」。
                            .put(21, (entityIn) -> JudgementCut.doJudgementCut(entityIn))
                            .build())
                    // 2026-10-06:16-30 Removed the UserPoseOverrider-based rotation; the API no longer exists in 1.21.1
                    // and player rotation is handled by SlashBlade itself.
                    // 2026-10-06:16-30 移除了基于 UserPoseOverrider 的旋转；该 API 在 1.21.1 已被移除，
                    // 玩家旋转现由拔刀剑本体处理。
                    .addHitEffect(StunManager::setStun)::build);
    public static final DeferredHolder<ComboState, ComboState> ODACHI_COMBO_A3_END = COMBO_STATE.register("odachi_combo_a3_end",
            ComboState.Builder.newInstance().startAndEnd(1013, 1061).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation).next(entity -> SlashBlade.prefix("none"))
                    .nextOfTimeout(entity -> SlashBlade.prefix("none"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(0, AttackManager::playQuickSheathSoundAction).build())
                    .releaseAction(ComboState::releaseActionQuickCharge)::build);

    public static final DeferredHolder<ComboState, ComboState> FUSHIGIRI_LEFT = COMBO_STATE.register("fushigiri_left",
            ComboState.Builder.newInstance().startAndEnd(400, 415).priority(50)
                    .next((entity) -> TheLastSmith.prefix("fushigiri_left"))
                    .nextOfTimeout(entity -> TheLastSmith.prefix("fushigiri_right"))
                    .clickAction((entityIn) -> AttackManager.doSlash(entityIn, -30F, Vec3.ZERO, false, true, 1.5F))
                    // 2026-10-06:16-30 UserPoseOverrider resetRot removed (API gone in 1.21.1).
                    // 2026-10-06:16-30 移除 UserPoseOverrider resetRot（1.21.1 API 已不存在）。
                    .addHitEffect(StunManager::setStun)::build);

    public static final DeferredHolder<ComboState, ComboState> FUSHIGIRI_RIGHT = COMBO_STATE.register("fushigiri_right",
            ComboState.Builder.newInstance().startAndEnd(1816, 1859).priority(50)
                    .next((entity) -> SlashBlade.prefix("none"))
                    .nextOfTimeout(entity -> TheLastSmith.prefix("fushigiri_finish"))
                    .clickAction((entityIn) -> AttackManager.doSlash(entityIn, 30F, Vec3.ZERO, false, true, 1.5F))
                    // 2026-10-06:16-30 UserPoseOverrider resetRot removed (API gone in 1.21.1).
                    // 2026-10-06:16-30 移除 UserPoseOverrider resetRot（1.21.1 API 已不存在）。
                    .addHitEffect(StunManager::setStun)::build);

    public static final DeferredHolder<ComboState, ComboState> FUSHIGIRI_FINISH = COMBO_STATE.register("fushigiri_finish",
            ComboState.Builder.newInstance().startAndEnd(1859, 1886).priority(50)
                    .next(entity -> SlashBlade.prefix("none"))
                    .nextOfTimeout(entity -> SlashBlade.prefix("none"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(0, AttackManager::playQuickSheathSoundAction).build())
                    // 2026-10-06:16-30 UserPoseOverrider resetRot removed (API gone in 1.21.1).
                    // 2026-10-06:16-30 移除 UserPoseOverrider resetRot（1.21.1 API 已不存在）。
                    .releaseAction(ComboState::releaseActionQuickCharge)::build);

    // 注意：新版拔刀剑疑似修改了ComboState中elapsed属性的默认值，该值为0
    // 且在传入的ticks == elapsed时，该action不会被执行
    // 所以第一个put进入的tick必须大于0，否则该action将永远不会被执行
    public static final DeferredHolder<ComboState, ComboState> IAI_CROSS = COMBO_STATE.register("iai_cross",
            ComboState.Builder.newInstance().startAndEnd(200, 218)
                    .priority(50)
                    .next(ComboState.TimeoutNext.buildFromFrame(9,
                            entity -> SlashBlade.prefix("iai_cross")))
                    .nextOfTimeout(entity -> TheLastSmith.prefix("iai_cross_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(1, (entityIn) -> AttackManager.doSlash(entityIn, -22.5F, false, true, 0.75f))
                            .put(7, (entityIn) -> AttackManager.doSlash(entityIn, 180 - 82.5F, false, true, 0.75f))
                            .build())
                    .addHitEffect(StunManager::setStun)::build);

    public static final DeferredHolder<ComboState, ComboState> IAI_CROSS_END = COMBO_STATE.register("iai_cross_end",
            ComboState.Builder.newInstance().startAndEnd(218, 281).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation).next(entity -> SlashBlade.prefix("none"))
                    .nextOfTimeout(entity -> TheLastSmith.prefix("iai_cross_end2"))::build);

    public static final DeferredHolder<ComboState, ComboState> IAI_CROSS_END2 = COMBO_STATE.register("iai_cross_end2",
            ComboState.Builder.newInstance().startAndEnd(281, 306).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation).next(entity -> SlashBlade.prefix("none"))
                    .nextOfTimeout(entity -> SlashBlade.prefix("none"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(0, AttackManager::playQuickSheathSoundAction).build())
                    .releaseAction(ComboState::releaseActionQuickCharge)::build);

    public static final DeferredHolder<ComboState, ComboState> SAKURA_BLISTERING_SWORDS = COMBO_STATE.register(
            "sakura_blistering_swords",
            ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> TheLastSmith.prefix("sakura_blistering_swords_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -30F, Vec3.ZERO, false, false, 0.1F))
                            .put(3, (entityIn) -> SakuraBlisteringSwords.summonSwords(entityIn, false, 4)).build())
                    .addHitEffect(StunManager::setStun)::build);

    public static final DeferredHolder<ComboState, ComboState> SAKURA_BLISTERING_SWORDS_END = COMBO_STATE.register(
            "sakura_blistering_swords_end",
            ComboState.Builder.newInstance().startAndEnd(459, 488).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation).next(entity -> SlashBlade.prefix("none"))
                    .nextOfTimeout(entity -> SlashBlade.prefix("none"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(0, AttackManager::playQuickSheathSoundAction).build())
                    .releaseAction(ComboState::releaseActionQuickCharge)::build);
}
