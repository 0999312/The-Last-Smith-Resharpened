package cn.mmf.tls.data.builtin;

import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import org.apache.commons.compress.utils.Lists;

import cn.mmf.tls.TheLastSmith;
import cn.mmf.tls.se.TLSSpecialEffectRegistry;
import cn.mmf.tls.slasharts.TLSSlashArtsRegistry;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.client.renderer.CarryType;
import mods.flammpfeil.slashblade.item.SwordType;
import mods.flammpfeil.slashblade.registry.SlashArtsRegistry;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.slashblade.EnchantmentDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.PropertiesDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.RenderDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class TLSSlashBladeRegistry {
    //    楼观剑
    public static final ResourceKey<SlashBladeDefinition> ROUKANKEN = register("roukanken");
    //    白楼剑
    public static final ResourceKey<SlashBladeDefinition> HAKUROUKEN = register("hakurouken");

    //    对应的狱界剑
    public static final ResourceKey<SlashBladeDefinition> ROUKANKEN_NETHER = register("roukanken_nether");
    public static final ResourceKey<SlashBladeDefinition> HAKUROUKEN_NETHER = register("hakurouken_nether");

    //    天云剑
//    速风
    public static final ResourceKey<SlashBladeDefinition> AMAGUMO_KAZE = register("amagumo_kaze");
    //    残云
    public static final ResourceKey<SlashBladeDefinition> AMAGUMO_KUMO = register("amagumo_kumo");
    //    无念
    public static final ResourceKey<SlashBladeDefinition> AMAGUMO_MUNIN = register("amagumo_munin");

    //    无名大太刀
    public static final ResourceKey<SlashBladeDefinition> NAMELESS_ODACHI = register("nameless_odachi");
    //    神乐村正
    public static final ResourceKey<SlashBladeDefinition> MURAMASA_KAGURA = register("muramasa_kagura");

    //    驱魔刃
//    樱
    public static final ResourceKey<SlashBladeDefinition> EXORCISM_SAKURA = register("exorcism_sakura");
    //    杏
    public static final ResourceKey<SlashBladeDefinition> EXORCISM_GINKGO = register("exorcism_ginkgo");
    //    雪影
    public static final ResourceKey<SlashBladeDefinition> EXORCISM_YUKI = register("exorcism_yuki");

    //    邪魔刃
//    双
    public static final ResourceKey<SlashBladeDefinition> EVIL_KATAWARE = register("evil_kataware");
    //    修罗
    public static final ResourceKey<SlashBladeDefinition> EVIL_SHURA = register("evil_shura");

    //    文始，未完成
    public static final ResourceKey<SlashBladeDefinition> BUNSHI = registerEnergyBlade("bunshi");
    //    樱剪
    public static final ResourceKey<SlashBladeDefinition> SAKURAGIRI = register("sakuragiri");

    //    阎魔刀
    public static final ResourceKey<SlashBladeDefinition> YAMATO = register("yamato_neo");
    //    胧村正
    public static final ResourceKey<SlashBladeDefinition> OBORO_MURAMASA = register("oboro_muramasa");

    //    楔丸
    public static final ResourceKey<SlashBladeDefinition> KUSABIMARU = register("kusabimaru");
    //    不死斩
    public static final ResourceKey<SlashBladeDefinition> FUSHIGIRI = register("fushigiri");

    //    永贞
    public static final ResourceKey<SlashBladeDefinition> NAGASADA = register("nagasada");

    //    竹光
    //    上品竹光
    public static final ResourceKey<SlashBladeDefinition> BAMBOO_TOP = register("bamboo_top");
    //    隐者竹光
    public static final ResourceKey<SlashBladeDefinition> BAMBOO_HERMIT = register("bamboo_hermit");
    //    上品银纸竹光
    public static final ResourceKey<SlashBladeDefinition> SILVERBAMBOO_TOP = register("silverbamboo_top");
    //    金纸竹光
    public static final ResourceKey<SlashBladeDefinition> GOLDENBAMBOO = register("goldenbamboo");
    //    血纸竹光
    public static final ResourceKey<SlashBladeDefinition> SILVERBAMBOO_BLOOD = register("silverbamboo_blood");

    //    将军木
    public static final ResourceKey<SlashBladeDefinition> SAGEQUOIA = register("sagequoia");

    //    鞘付武器
    public static final ResourceKey<SlashBladeDefinition> SWEAPON = register("sweapon");
    //    初代拔刀剑？
    public static final ResourceKey<SlashBladeDefinition> SLASHBLADE = register("slashblade");

    //    伊布之刃
    public static final ResourceKey<SlashBladeDefinition> EIEVUI = register("eievui");

    //    SJAP联动武器，暂未找到译名
    public static final ResourceKey<SlashBladeDefinition> NIHILBX = registerSJAP("nihilbx");
    public static final ResourceKey<SlashBladeDefinition> NIHILUL = registerSJAP("nihilul");
    public static final ResourceKey<SlashBladeDefinition> CRIMSONCHERRY = registerSJAP("crimsoncherry");

    //    马娘拔刀剑联动武器 (依赖: net.tracen.blades_derby 模组)
//    2026-06-13:17-40: 独立为 blades_derby 联动命名空间
//    天云「无光」速子
    public static final ResourceKey<SlashBladeDefinition> UMA_TACHYON = registerDerby("uma_tachyon");
    //    数码「马娘狂热」
    public static final ResourceKey<SlashBladeDefinition> UMA_DIGITAL = registerDerby("uma_digital");
    //    茶座「漆黑高楼」
    public static final ResourceKey<SlashBladeDefinition> UMA_CAFE = registerDerby("uma_cafe");
    //    米浴「追猎者」
    public static final ResourceKey<SlashBladeDefinition> UMA_RICE_SHOWER = registerDerby("uma_rice_shower");

    public static void registerAll(BootstrapContext<SlashBladeDefinition> bootstrap) {

        bootstrap.register(EIEVUI,
                new SlashBladeDefinition(TheLastSmith.prefix("eievui"),
                        RenderDefinition.Builder
                                .newInstance()
                                .standbyRenderType(CarryType.PSO2)
                                .textureName(TheLastSmith.prefix("model/named/eievui/texture.png"))
                                .modelName(TheLastSmith.prefix("model/named/eievui/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(5.0F).maxDamage(113)
                                .build(),
                        Lists.newArrayList(),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(SWEAPON,
                new SlashBladeDefinition(TheLastSmith.prefix("sweapon"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/sweapon/texture.png"))
                                .modelName(TheLastSmith.prefix("model/sweapon/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.SEALED))
                                .baseAttackModifier(4.0F).maxDamage(50)
                                .build(),
                        Lists.newArrayList(),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(SLASHBLADE,
                new SlashBladeDefinition(TheLastSmith.prefix("slashblade"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(SlashBlade.prefix("model/named/yamato.png"))
                                .modelName(TheLastSmith.prefix("model/slashblade/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(4.0F).maxDamage(50)
                                .build(),
                        Lists.newArrayList(),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(OBORO_MURAMASA,
                new SlashBladeDefinition(TheLastSmith.prefix("oboro_muramasa"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/muramasa/texture_oboro.png"))
                                .modelName(TheLastSmith.prefix("model/named/smith/model.obj"))
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(11.0F).maxDamage(70)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_POWER.getId())
                                .slashArtsType(SlashArtsRegistry.VOID_SLASH.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.UNBREAKING), 2),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.POWER), 1),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SHARPNESS), 2)),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(SAGEQUOIA,
                new SlashBladeDefinition(TheLastSmith.prefix("sagequoia"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/sagequoia/texture.png"))
                                .modelName(TheLastSmith.prefix("model/named/sagequoia/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(6.0F).maxDamage(113)
                                .slashArtsType(SlashArtsRegistry.WAVE_EDGE.getId())
                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SMITE), 3),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SHARPNESS), 3),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.UNBREAKING), 3)),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(BAMBOO_TOP,
                new SlashBladeDefinition(TheLastSmith.prefix("bamboo_top"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/bamboolight/bamboo_top.png"))
                                .modelName(SlashBlade.prefix("model/blade.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(4.0F).maxDamage(70)
                                .build(),
                        Lists.newArrayList(),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(BAMBOO_HERMIT,
                new SlashBladeDefinition(TheLastSmith.prefix("bamboo_hermit"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/bamboolight/bamboo_top.png"))
                                .modelName(SlashBlade.prefix("model/named/yamato.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(6.0F).maxDamage(75)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(SlashArtsRegistry.VOID_SLASH.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.UNBREAKING), 2),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.POWER), 1),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SMITE), 2)),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(SILVERBAMBOO_TOP,
                new SlashBladeDefinition(TheLastSmith.prefix("silverbamboo_top"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/bamboolight/silverbamboo_top.png"))
                                .modelName(SlashBlade.prefix("model/named/yamato.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(5.0F).maxDamage(70)
                                .build(),
                        Lists.newArrayList(),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(GOLDENBAMBOO,
                new SlashBladeDefinition(TheLastSmith.prefix("goldenbamboo"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/bamboolight/goldenbamboo.png"))
                                .modelName(SlashBlade.prefix("model/named/yamato.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(6.0F).maxDamage(33)
                                .addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_POWER.getId())
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SOUL_SPEED), 2),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.POWER), 1),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.UNBREAKING), 1),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SMITE), 3)),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(SILVERBAMBOO_BLOOD,
                new SlashBladeDefinition(TheLastSmith.prefix("silverbamboo_blood"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/bamboolight/silverbamboo_blood.png"))
                                .modelName(SlashBlade.prefix("model/named/yamato.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(5.0F).maxDamage(50)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_POWER.getId())
                                .slashArtsType(SlashArtsRegistry.CIRCLE_SLASH.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.UNBREAKING), 1),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.POWER), 1),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SHARPNESS), 2)),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(NAGASADA,
                new SlashBladeDefinition(TheLastSmith.prefix("nagasada"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/nagasada/texture.png"))
                                .modelName(TheLastSmith.prefix("model/nagasada/model.obj"))
                                .standbyRenderType(CarryType.DEFAULT)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(4.0F).maxDamage(100)
                                .build(),
                        Lists.newArrayList(),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(KUSABIMARU,
                new SlashBladeDefinition(TheLastSmith.prefix("kusabimaru"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/sekiro/kusabimaru.png"))
                                .modelName(TheLastSmith.prefix("model/named/sekiro/kusabimaru.obj"))
                                .effectColor(0XEEC0C0C0)
                                .standbyRenderType(CarryType.KATANA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(5.0F).maxDamage(70)
                                .slashArtsType(TLSSlashArtsRegistry.IAI_CROSS.getId())
                                .build(),
                        Lists.newArrayList(),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(FUSHIGIRI,
                new SlashBladeDefinition(TheLastSmith.prefix("fushigiri"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(TheLastSmith.prefix("model/named/sekiro/fushigiri.png"))
                                .modelName(TheLastSmith.prefix("model/named/sekiro/fushigiri.obj"))
                                .effectColor(0XFF8C0000)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance().baseAttackModifier(10.0F)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .addSpecialEffect(TLSSpecialEffectRegistry.FUSHIGIRI_SE.getId())
                                .slashArtsType(TLSSlashArtsRegistry.FUSHIGIRI.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.UNBREAKING), 2),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SHARPNESS), 2),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.POWER), 5),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SMITE), 4)),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(YAMATO,
                new SlashBladeDefinition(TheLastSmith.prefix("yamato_neo"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(TheLastSmith.prefix("model/named/yamato_neo/yamato_neo.png"))
                                .modelName(TheLastSmith.prefix("model/named/yamato_neo/yamato_neo.obj")).build(),
                        PropertiesDefinition.Builder.newInstance().baseAttackModifier(9.0F)
                                .addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_POWER.getId())
                                .addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_SHARPNESS.getId())
                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SOUL_SPEED), 2),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.POWER), 5),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.FEATHER_FALLING), 4)),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(NAMELESS_ODACHI,
                new SlashBladeDefinition(TheLastSmith.prefix("nameless_odachi"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/odachi/odachi.png"))
                                .modelName(TheLastSmith.prefix("model/named/odachi/model.obj"))
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(5.0F).maxDamage(70)
                                // 2026-10-06:18-45 Give the odachi the vanilla Judgement Cut SA
                                // so the special-attack key releases the original dimension slash.
                                // 2026-10-06:18-45 为大太刀挂载原版「次元斩」斩击技，使斩击技键可释放原版次元斩。
                                .slashArtsType(SlashArtsRegistry.JUDGEMENT_CUT.getId())
                                .build(),
                        Lists.newArrayList(),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(MURAMASA_KAGURA,
                new SlashBladeDefinition(TheLastSmith.prefix("muramasa_kagura"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/odachi/murasama_kagura.png"))
                                .modelName(TheLastSmith.prefix("model/named/odachi/model.obj"))
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(8.0F).maxDamage(70)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                // 2026-10-06:18-50 Only the Nameless Odachi gets Judgement Cut; keep the upgraded odachi's own SA.
                                // 2026-10-06:18-50 仅无名大太刀使用「次元斩」，上级大太刀保留其原有斩击技。
                                .slashArtsType(SlashArtsRegistry.DRIVE_HORIZONTAL.getId())
                                .build(),
                        Lists.newArrayList(),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(BUNSHI,
                new SlashBladeDefinition(
                        // 2026-10-06:18-20 Use the FE-chargeable HF item (energyblade:forge_energy_blade) so BUNSHI
                        // gets the energy bar / FE storage, matching the HF Blades mechanic.
                        // 2026-10-06:18-20 使用可充能的 HF 物品 energyblade:forge_energy_blade，使文始获得能量条/FE 存储，
                        // 与高频刀机制一致。
                        ResourceLocation.fromNamespaceAndPath("energyblade", "forge_energy_blade"),
                        // 2026-10-06:17-40 Name must share the registry-key namespace so the mod_loaded condition
                        // and the blade translation key work correctly.
                        // 2026-10-06:17-40 名称需与注册键命名空间一致，mod_loaded 条件与刀的翻译键才能正确工作。
                        ResourceLocation.fromNamespaceAndPath("energyblade", "bunshi"),
                        RenderDefinition.Builder
                                .newInstance()
                                .effectColor(0xffb7c5)
                                .textureName(TheLastSmith.prefix("model/named/rf_roukan/texture.png"))
                                .modelName(TheLastSmith.prefix("model/named/rf_roukan/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(6.0F).maxDamage(70)
                                .addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_SHARPNESS.getId())
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(TLSSlashArtsRegistry.Transmigration_Slash.getId())
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SHARPNESS), 3),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.UNBREAKING), 2),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SOUL_SPEED), 2)
                        ),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(SAKURAGIRI,
                new SlashBladeDefinition(TheLastSmith.prefix("sakuragiri"),
                        RenderDefinition.Builder
                                .newInstance()
                                .effectColor(0xffb7c5)
                                .textureName(TheLastSmith.prefix("model/named/bot_roukan/texture.png"))
                                .modelName(TheLastSmith.prefix("model/named/bot_roukan/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(2.0F).maxDamage(70)

                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(SlashArtsRegistry.DRIVE_VERTICAL.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SHARPNESS), 2)),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(AMAGUMO_KAZE,
                new SlashBladeDefinition(TheLastSmith.prefix("amagumo_kaze"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/smith/texture_wind.png"))
                                .modelName(TheLastSmith.prefix("model/named/smith/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(10.0F).maxDamage(70)
                                .addSpecialEffect(TLSSpecialEffectRegistry.EXTREME_SHARPNESS.getId())
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(SlashArtsRegistry.SAKURA_END.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SOUL_SPEED), 2),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SHARPNESS), 2),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.FEATHER_FALLING), 4)),
                        TheLastSmith.prefix("slashblade")
                ));
        bootstrap.register(AMAGUMO_KUMO,
                new SlashBladeDefinition(TheLastSmith.prefix("amagumo_kumo"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/smith/texture_cloud.png"))
                                .modelName(SlashBlade.prefix("model/named/agito.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(10.0F).maxDamage(70)
                                .addSpecialEffect(TLSSpecialEffectRegistry.BEWITCHED_POWER.getId())
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(SlashArtsRegistry.WAVE_EDGE.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SHARPNESS), 1),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.POWER), 3),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SWEEPING_EDGE), 1)),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(AMAGUMO_MUNIN,
                new SlashBladeDefinition(TheLastSmith.prefix("amagumo_munin"),
                        RenderDefinition.Builder
                                .newInstance()
                                .effectColor(0xff1111)
                                .textureName(TheLastSmith.prefix("model/named/smith/texture_final.png"))
                                .modelName(TheLastSmith.prefix("model/named/smith/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(17.0F).maxDamage(100)
                                .addSpecialEffect(TLSSpecialEffectRegistry.EXTREME_SHARPNESS.getId())
                                .addSpecialEffect(TLSSpecialEffectRegistry.BEWITCHED_POWER.getId())
                                .addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_SMITE.getId())
                                .addSpecialEffect(SpecialEffectsRegistry.WITHER_EDGE.getId())
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(SlashArtsRegistry.VOID_SLASH.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SHARPNESS), 5),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SOUL_SPEED), 2),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.POWER), 5),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.FEATHER_FALLING), 4),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SWEEPING_EDGE), 3)
                        ),
                        TheLastSmith.prefix("slashblade")
                ));
        // 2026-10-06:17-40 UMA collaboration weapons are always generated here; each entry carries a
        // neoforge:mod_loaded(blades_derby) condition so it is skipped when the mod is absent.
        // 2026-10-06:17-40 马娘联动刀始终在此生成；每个条目带有 neoforge:mod_loaded(blades_derby) 条件，
        // 未安装该模组时会被跳过。
        bootstrap.register(UMA_TACHYON,
                new SlashBladeDefinition(ResourceLocation.fromNamespaceAndPath("blades_derby", "uma_tachyon"),
                            RenderDefinition.Builder
                                    .newInstance()
                                    //.effectColor(0xff1111)
                                    .textureName(TheLastSmith.prefix("model/named/uma/tachyon_blade.png"))
                                    .modelName(TheLastSmith.prefix("model/named/smith/model.obj")).build(),
                            PropertiesDefinition.Builder.newInstance()
                                    .baseAttackModifier(17.0F).maxDamage(100)
                                    .addSpecialEffect(TLSSpecialEffectRegistry.EXTREME_SHARPNESS.getId())
                                    .addSpecialEffect(TLSSpecialEffectRegistry.BEWITCHED_POWER.getId())
                                    .addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_SMITE.getId())
                                    // 2026-10-06:17-40 Use the blades_derby special effect (id referenced without a compile dependency).
                                    // 2026-10-06:17-40 使用 blades_derby 的特殊效果（直接引用 id，避免编译依赖）。
                                    .addSpecialEffect(ResourceLocation.fromNamespaceAndPath("blades_derby", "uma_special"))
                                    .defaultSwordType(List.of(SwordType.BEWITCHED))
                                    .slashArtsType(SlashArtsRegistry.VOID_SLASH.getId())
                                    .build(),
                            List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SHARPNESS), 5),
                                    new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SOUL_SPEED), 2),
                                    new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.UNBREAKING), 3),
                                    new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.POWER), 5),
                                    new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.FEATHER_FALLING), 4),
                                    new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SWEEPING_EDGE), 3)
                            ),
                            TheLastSmith.prefix("slashblade")
                    ));

            bootstrap.register(UMA_RICE_SHOWER,
                    new SlashBladeDefinition(ResourceLocation.fromNamespaceAndPath("blades_derby", "uma_rice_shower"),
                            RenderDefinition.Builder
                                    .newInstance()
                                    //.effectColor(0xffb7c5)
                                    .textureName(TheLastSmith.prefix("model/named/uma/rice_shower_blade.png"))
                                    .modelName(TheLastSmith.prefix("model/named/smith/model.obj"))
                                    .build(),
                            PropertiesDefinition.Builder.newInstance()
                                    .baseAttackModifier(5.0F).maxDamage(70)
                                    .addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_POWER.getId())
                                    .addSpecialEffect(ResourceLocation.fromNamespaceAndPath("blades_derby", "uma_special"))
                                    .defaultSwordType(List.of(SwordType.BEWITCHED))
                                    .slashArtsType(TLSSlashArtsRegistry.SAKURA_BLISTERING_SWORDS.getId())
                                    .build(),
                            List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.POWER), 2)),
                            TheLastSmith.prefix("slashblade")
                    ));

            bootstrap.register(UMA_DIGITAL,
                    new SlashBladeDefinition(ResourceLocation.fromNamespaceAndPath("blades_derby", "uma_digital"),
                            RenderDefinition.Builder
                                    .newInstance()
                                    .effectColor(0xffb7c5)
                                    .textureName(TheLastSmith.prefix("model/named/uma/digital_blade.png"))
                                    .modelName(SlashBlade.prefix("model/named/sange/sange.obj"))
                                    .build(),
                            PropertiesDefinition.Builder.newInstance()
                                    .baseAttackModifier(5.0F).maxDamage(70)
                                    .addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_SHARPNESS.getId())
                                    .addSpecialEffect(ResourceLocation.fromNamespaceAndPath("blades_derby", "uma_special"))
                                    .defaultSwordType(List.of(SwordType.BEWITCHED))
                                    .slashArtsType(TLSSlashArtsRegistry.SAKURA_BLISTERING_SWORDS.getId())
                                    .build(),
                            List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SHARPNESS), 2)),
                            TheLastSmith.prefix("slashblade")
                    ));

            bootstrap.register(UMA_CAFE,
                    new SlashBladeDefinition(ResourceLocation.fromNamespaceAndPath("blades_derby", "uma_cafe"),
                            RenderDefinition.Builder
                                    .newInstance()
                                    //.effectColor(0xffb7c5)
                                    .textureName(TheLastSmith.prefix("model/named/uma/cafe_blade.png"))
                                    .modelName(SlashBlade.prefix("model/named/muramasa/muramasa.obj"))
                                    .build(),
                            PropertiesDefinition.Builder.newInstance()
                                    .baseAttackModifier(5.0F).maxDamage(70)
                                    .addSpecialEffect(TLSSpecialEffectRegistry.SMITE_SE.getId())
                                    .addSpecialEffect(ResourceLocation.fromNamespaceAndPath("blades_derby", "uma_special"))
                                    .defaultSwordType(List.of(SwordType.BEWITCHED))
                                    .slashArtsType(TLSSlashArtsRegistry.SAKURA_BLISTERING_SWORDS.getId())
                                    .build(),
                            List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SMITE), 2)),
                            TheLastSmith.prefix("slashblade")
                    ));

        bootstrap.register(EXORCISM_SAKURA,
                new SlashBladeDefinition(TheLastSmith.prefix("exorcism_sakura"),
                        RenderDefinition.Builder
                                .newInstance()
                                .effectColor(0xffb7c5)
                                .textureName(TheLastSmith.prefix("model/named/sakura/texture.png"))
                                .modelName(TheLastSmith.prefix("model/named/sakura/model.obj"))
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(5.0F).maxDamage(70)
                                .addSpecialEffect(TLSSpecialEffectRegistry.SMITE_SE.getId())
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(TLSSlashArtsRegistry.SAKURA_BLISTERING_SWORDS.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SMITE), 2)),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(EXORCISM_GINKGO,
                new SlashBladeDefinition(TheLastSmith.prefix("exorcism_ginkgo"),
                        RenderDefinition.Builder
                                .newInstance()
                                .effectColor(0xFFDD32)
                                .textureName(TheLastSmith.prefix("model/named/sakura/texture_1.png"))
                                .modelName(TheLastSmith.prefix("model/named/sakura/model.obj"))
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(5.0F).maxDamage(70)
                                .addSpecialEffect(TLSSpecialEffectRegistry.SMITE_SE.getId())
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(TLSSlashArtsRegistry.SAKURA_BLISTERING_SWORDS.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SMITE), 2)),
                        TheLastSmith.prefix("slashblade")
                ));
        bootstrap.register(EXORCISM_YUKI,
                new SlashBladeDefinition(TheLastSmith.prefix("exorcism_yuki"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/sakura/texture_2.png"))
                                .modelName(TheLastSmith.prefix("model/named/sakura/model.obj"))
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(5.0F).maxDamage(70)
                                .addSpecialEffect(TLSSpecialEffectRegistry.SMITE_SE.getId())
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(TLSSlashArtsRegistry.SAKURA_BLISTERING_SWORDS.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SMITE), 2)),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(EVIL_KATAWARE,
                new SlashBladeDefinition(TheLastSmith.prefix("evil_kataware"),
                        RenderDefinition.Builder
                                .newInstance()
                                .effectColor(0x990000)
                                .textureName(TheLastSmith.prefix("model/named/sakura/texture_3.png"))
                                .modelName(TheLastSmith.prefix("model/named/sakura/model.obj"))
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(6.0F).maxDamage(70)
                                .addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_POWER.getId())
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(SlashArtsRegistry.CIRCLE_SLASH.getId())
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.POWER), 2),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SHARPNESS), 1)
                        ),
                        TheLastSmith.prefix("slashblade")
                ));
        bootstrap.register(EVIL_SHURA,
                new SlashBladeDefinition(TheLastSmith.prefix("evil_shura"),
                        RenderDefinition.Builder
                                .newInstance()
                                .effectColor(0x6B0000)
                                .textureName(TheLastSmith.prefix("model/named/sakura/texture_4.png"))
                                .modelName(TheLastSmith.prefix("model/named/sakura/model.obj"))
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(8.0F).maxDamage(70)
                                .addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_POWER.getId())
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(SlashArtsRegistry.WAVE_EDGE.getId())
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.POWER), 2),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SHARPNESS), 2),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.LOOTING), 1)
                        ),
                        TheLastSmith.prefix("slashblade")
                ));


        bootstrap.register(ROUKANKEN,
                new SlashBladeDefinition(TheLastSmith.prefix("roukanken"),
                        RenderDefinition.Builder
                                .newInstance()
                                .effectColor(0xffb7c5)
                                .textureName(TheLastSmith.prefix("model/named/roukan/texture.png"))
                                .modelName(TheLastSmith.prefix("model/named/roukan/model.obj"))
                                .standbyRenderType(CarryType.RNINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(11.0F).maxDamage(70)
                                .addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_SHARPNESS.getId())
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(TLSSlashArtsRegistry.Transmigration_Slash.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SHARPNESS), 3)),
                        TheLastSmith.prefix("slashblade")
                ));
        bootstrap.register(HAKUROUKEN,
                new SlashBladeDefinition(TheLastSmith.prefix("hakurouken"),
                        RenderDefinition.Builder
                                .newInstance()
                                .effectColor(0xF2F2F2)
                                .textureName(TheLastSmith.prefix("model/named/hakurou/texture.png"))
                                .modelName(TheLastSmith.prefix("model/named/hakurou/model.obj"))
                                .standbyRenderType(CarryType.KATANA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(3.0F).maxDamage(70)
                                .addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_SMITE.getId())
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(SlashArtsRegistry.DRIVE_VERTICAL.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SMITE), 3)),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(ROUKANKEN_NETHER,
                new SlashBladeDefinition(TheLastSmith.prefix("roukanken_nether"),
                        RenderDefinition.Builder
                                .newInstance()
                                .effectColor(0xffb7c5)
                                .textureName(TheLastSmith.prefix("model/named/roukan/texture_tx.png"))
                                .modelName(TheLastSmith.prefix("model/named/roukan/model.obj"))
                                .standbyRenderType(CarryType.RNINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(13.0F).maxDamage(70)
                                .addSpecialEffect(TLSSpecialEffectRegistry.EXTREME_SHARPNESS.getId())
                                .addSpecialEffect(SpecialEffectsRegistry.WITHER_EDGE.getId())
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(TLSSlashArtsRegistry.Transmigration_Slash.getId())
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SHARPNESS), 4),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.POWER), 2),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.FIRE_ASPECT), 1)
                        ),
                        TheLastSmith.prefix("slashblade")
                ));
        bootstrap.register(HAKUROUKEN_NETHER,
                new SlashBladeDefinition(TheLastSmith.prefix("hakurouken_nether"),
                        RenderDefinition.Builder
                                .newInstance()
                                .effectColor(0xF2F2F2)
                                .textureName(TheLastSmith.prefix("model/named/hakurou/texture_tx.png"))
                                .modelName(TheLastSmith.prefix("model/named/hakurou/model.obj"))
                                .standbyRenderType(CarryType.KATANA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(4.0F).maxDamage(70)
                                .addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_SMITE.getId())
                                .addSpecialEffect(SpecialEffectsRegistry.WITHER_EDGE.getId())
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(SlashArtsRegistry.DRIVE_VERTICAL.getId())
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SMITE), 4),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.POWER), 2),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SHARPNESS), 1),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.FIRE_ASPECT), 1)
                        ),
                        TheLastSmith.prefix("slashblade")
                ));

        bootstrap.register(NIHILUL,
                new SlashBladeDefinition(TheLastSmith.prefix("nihilul"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(TheLastSmith.prefix("model/nihil/nihilul.png"))
                                .modelName(TheLastSmith.prefix("model/nihil/nihil.obj"))
                                .standbyRenderType(CarryType.KATANA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(12.0F)
                                .maxDamage(70)
                                .addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_SHARPNESS.getId())
                                .slashArtsType(SlashArtsRegistry.WAVE_EDGE.getId())
                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SHARPNESS), 5),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SMITE), 3),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.BANE_OF_ARTHROPODS), 2),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.FIRE_ASPECT), 2),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.LOOTING), 3))));

        bootstrap.register(CRIMSONCHERRY,
                new SlashBladeDefinition(TheLastSmith.prefix("crimsoncherry"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(TheLastSmith.prefix("model/nihil/crimsoncherry.png"))
                                .modelName(TheLastSmith.prefix("model/nihil/nihil.obj"))
                                .standbyRenderType(CarryType.KATANA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(11.0F)
                                .maxDamage(65)
                                .addSpecialEffect(TLSSpecialEffectRegistry.SMITE_SE.getId())
                                .slashArtsType(SlashArtsRegistry.SAKURA_END.getId())
                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SHARPNESS), 5),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SMITE), 3),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.BANE_OF_ARTHROPODS), 3),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.FIRE_ASPECT), 2))));

        bootstrap.register(NIHILBX,
                new SlashBladeDefinition(TheLastSmith.prefix("nihilbx"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(TheLastSmith.prefix("model/nihil/nihil_bx.png"))
                                .modelName(TheLastSmith.prefix("model/nihil/nihil.obj"))
                                .standbyRenderType(CarryType.KATANA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(14.0F)
                                .maxDamage(240)
                                .addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_SHARPNESS.getId())
                                .addSpecialEffect(TLSSpecialEffectRegistry.SMITE_SE.getId())
                                .addSpecialEffect(TLSSpecialEffectRegistry.EXTRA_POWER.getId())
                                .addSpecialEffect(SpecialEffectsRegistry.WITHER_EDGE.getId())
                                .slashArtsType(SlashArtsRegistry.SAKURA_END.getId())
                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SHARPNESS), 5),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.POWER), 3),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.SMITE), 5),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.BANE_OF_ARTHROPODS), 5),
                                new EnchantmentDefinition(getEnchantmentID(bootstrap, Enchantments.FIRE_ASPECT), 2))));
    }

    private static ResourceKey<SlashBladeDefinition> registerEnergyBlade(String id) {
        // 2026-10-06:17-40 Fixed namespace: the energyblade mod id is "energyblade" (no underscore).
        // 2026-10-06:17-40 修正命名空间：energyblade 模组 id 为 "energyblade"（无下划线）。
        return ResourceKey.create(SlashBladeDefinition.REGISTRY_KEY,
                ResourceLocation.fromNamespaceAndPath("energyblade", id));
    }

    private static Holder<Enchantment> getEnchantmentID(BootstrapContext<SlashBladeDefinition> bootstrap, ResourceKey<Enchantment> enchantment) {
        return bootstrap.lookup(Registries.ENCHANTMENT).getOrThrow(enchantment);
    }

    private static ResourceKey<SlashBladeDefinition> register(String id) {
        return ResourceKey.create(SlashBladeDefinition.REGISTRY_KEY, TheLastSmith.prefix(id));
    }

    private static ResourceKey<SlashBladeDefinition> registerSJAP(String id) {
        /*new ResourceLocation("slashblade_addon", id)*/
        return ResourceKey.create(SlashBladeDefinition.REGISTRY_KEY,
                /*new ResourceLocation("slashblade_addon", id)*/
                ResourceLocation.fromNamespaceAndPath("slashblade_addon", id)
        );
    }

    // 2026-06-13:17-40: blades_derby 联动武器独立命名空间注册
    private static ResourceKey<SlashBladeDefinition> registerDerby(String id) {
        return ResourceKey.create(SlashBladeDefinition.REGISTRY_KEY,
                ResourceLocation.fromNamespaceAndPath("blades_derby", id));
    }
}
