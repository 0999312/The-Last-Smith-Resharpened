package cn.mmf.tls;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * 2026-10-06:18-55 Reworked into a real common config.
 *  - enchantments: effect values for Spirit Slash / Spirit Absorption.
 *  - specialEffects: effective level requirement and per-level bonus for every TLS SE.
 * 2026-10-06:18-55 改造为真正可用的公共配置。
 *  - enchantments：灵魂切裂 / 灵魂汲取的效果数值。
 *  - specialEffects：每个 TLS 特殊效果（SE）的生效等级要求与每级加成。
 */
@EventBusSubscriber(modid = TheLastSmith.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // ─── Enchantments ───
    public static final ModConfigSpec.IntValue SPIRIT_SLASH_BONUS_PER_LEVEL = BUILDER
            .comment("Kill count added per level of the Spirit Slash enchantment when killing a mob.",
                     "灵魂切裂：击杀生物时每级附魔额外增加的击杀数。")
            .translation("last_smith.config.spirit_slash_bonus_per_level")
            .defineInRange("enchantments.spiritSlashBonusPerLevel", 1, 0, 1000);

    public static final ModConfigSpec.DoubleValue SPIRIT_ABSORPTION_BONUS_PER_LEVEL = BUILDER
            .comment("Proud soul gain multiplier bonus per level of the Spirit Absorption enchantment (0.2 = +20% per level).",
                     "灵魂汲取：每级附魔额外增加的魂灵获取倍率（0.2 表示每级 +20%）。")
            .translation("last_smith.config.spirit_absorption_bonus_per_level")
            .defineInRange("enchantments.spiritAbsorptionBonusPerLevel", 0.2D, 0.0D, 100.0D);

    // ─── Special Effects (dotted paths create the "specialEffects" section) ───
    public static final ModConfigSpec.IntValue EXTRA_SHARPNESS_REQUEST_LEVEL = BUILDER
            .defineInRange("specialEffects.extraSharpnessRequestLevel", 15, 0, 1000);
    public static final ModConfigSpec.DoubleValue EXTRA_SHARPNESS_BONUS = BUILDER
            .defineInRange("specialEffects.extraSharpnessBonus", 0.1D, 0.0D, 100.0D);
    public static final ModConfigSpec.IntValue EXTREME_SHARPNESS_REQUEST_LEVEL = BUILDER
            .defineInRange("specialEffects.extremeSharpnessRequestLevel", 30, 0, 1000);
    public static final ModConfigSpec.DoubleValue EXTREME_SHARPNESS_BONUS = BUILDER
            .defineInRange("specialEffects.extremeSharpnessBonus", 0.2D, 0.0D, 100.0D);

    public static final ModConfigSpec.IntValue EXTRA_POWER_REQUEST_LEVEL = BUILDER
            .defineInRange("specialEffects.extraPowerRequestLevel", 15, 0, 1000);
    public static final ModConfigSpec.DoubleValue EXTRA_POWER_BONUS = BUILDER
            .defineInRange("specialEffects.extraPowerBonus", 0.1D, 0.0D, 100.0D);
    public static final ModConfigSpec.IntValue BEWITCHED_POWER_REQUEST_LEVEL = BUILDER
            .defineInRange("specialEffects.bewitchedPowerRequestLevel", 30, 0, 1000);
    public static final ModConfigSpec.DoubleValue BEWITCHED_POWER_BONUS = BUILDER
            .defineInRange("specialEffects.bewitchedPowerBonus", 0.2D, 0.0D, 100.0D);

    public static final ModConfigSpec.IntValue SMITE_REQUEST_LEVEL = BUILDER
            .defineInRange("specialEffects.smiteRequestLevel", 15, 0, 1000);
    public static final ModConfigSpec.DoubleValue SMITE_BONUS = BUILDER
            .defineInRange("specialEffects.smiteBonus", 1.0D, 0.0D, 100.0D);
    public static final ModConfigSpec.IntValue EXTRA_SMITE_REQUEST_LEVEL = BUILDER
            .defineInRange("specialEffects.extraSmiteRequestLevel", 30, 0, 1000);
    public static final ModConfigSpec.DoubleValue EXTRA_SMITE_BONUS = BUILDER
            .defineInRange("specialEffects.extraSmiteBonus", 2.0D, 0.0D, 100.0D);
    public static final ModConfigSpec.IntValue FUSHIGIRI_REQUEST_LEVEL = BUILDER
            .defineInRange("specialEffects.fushigiriRequestLevel", 30, 0, 1000);
    public static final ModConfigSpec.DoubleValue FUSHIGIRI_BONUS = BUILDER
            .defineInRange("specialEffects.fushigiriBonus", 3.0D, 0.0D, 100.0D);

    public static final ModConfigSpec.IntValue SECTUMSEMPRA_REQUEST_LEVEL = BUILDER
            .defineInRange("specialEffects.sectumsempraRequestLevel", 40, 0, 1000);
    public static final ModConfigSpec.DoubleValue SECTUMSEMPRA_HEALTH_RATIO = BUILDER
            .defineInRange("specialEffects.sectumsempraHealthRatio", 0.04D, 0.0D, 100.0D);
    public static final ModConfigSpec.DoubleValue SECTUMSEMPRA_FLAT_DAMAGE = BUILDER
            .defineInRange("specialEffects.sectumsempraFlatDamage", 1.0D, 0.0D, 100.0D);

    public static final ModConfigSpec SPEC = BUILDER.build();

    // ─── Cached values (updated on config load) ───
    public static int spiritSlashBonusPerLevel = 1;
    public static double spiritAbsorptionBonusPerLevel = 0.2D;

    public static int extraSharpnessRequestLevel = 15;
    public static double extraSharpnessBonus = 0.1D;
    public static int extremeSharpnessRequestLevel = 30;
    public static double extremeSharpnessBonus = 0.2D;
    public static int extraPowerRequestLevel = 15;
    public static double extraPowerBonus = 0.1D;
    public static int bewitchedPowerRequestLevel = 30;
    public static double bewitchedPowerBonus = 0.2D;
    public static int smiteRequestLevel = 15;
    public static double smiteBonus = 1.0D;
    public static int extraSmiteRequestLevel = 30;
    public static double extraSmiteBonus = 2.0D;
    public static int fushigiriRequestLevel = 30;
    public static double fushigiriBonus = 3.0D;
    public static int sectumsempraRequestLevel = 40;
    public static double sectumsempraHealthRatio = 0.04D;
    public static double sectumsempraFlatDamage = 1.0D;

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent event) {
        if (event.getConfig().getSpec() != SPEC) {
            return;
        }
        spiritSlashBonusPerLevel = SPIRIT_SLASH_BONUS_PER_LEVEL.get();
        spiritAbsorptionBonusPerLevel = SPIRIT_ABSORPTION_BONUS_PER_LEVEL.get();

        extraSharpnessRequestLevel = EXTRA_SHARPNESS_REQUEST_LEVEL.get();
        extraSharpnessBonus = EXTRA_SHARPNESS_BONUS.get();
        extremeSharpnessRequestLevel = EXTREME_SHARPNESS_REQUEST_LEVEL.get();
        extremeSharpnessBonus = EXTREME_SHARPNESS_BONUS.get();
        extraPowerRequestLevel = EXTRA_POWER_REQUEST_LEVEL.get();
        extraPowerBonus = EXTRA_POWER_BONUS.get();
        bewitchedPowerRequestLevel = BEWITCHED_POWER_REQUEST_LEVEL.get();
        bewitchedPowerBonus = BEWITCHED_POWER_BONUS.get();
        smiteRequestLevel = SMITE_REQUEST_LEVEL.get();
        smiteBonus = SMITE_BONUS.get();
        extraSmiteRequestLevel = EXTRA_SMITE_REQUEST_LEVEL.get();
        extraSmiteBonus = EXTRA_SMITE_BONUS.get();
        fushigiriRequestLevel = FUSHIGIRI_REQUEST_LEVEL.get();
        fushigiriBonus = FUSHIGIRI_BONUS.get();
        sectumsempraRequestLevel = SECTUMSEMPRA_REQUEST_LEVEL.get();
        sectumsempraHealthRatio = SECTUMSEMPRA_HEALTH_RATIO.get();
        sectumsempraFlatDamage = SECTUMSEMPRA_FLAT_DAMAGE.get();
    }
}
