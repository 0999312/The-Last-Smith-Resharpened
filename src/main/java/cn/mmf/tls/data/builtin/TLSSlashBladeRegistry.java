package cn.mmf.tls.data.builtin;

import java.util.List;

import org.apache.commons.compress.utils.Lists;

import cn.mmf.tls.TheLastSmith;
import cn.mmf.tls.slasharts.TLSSlashArtsRegistry;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.client.renderer.CarryType;
import mods.flammpfeil.slashblade.item.SwordType;
import mods.flammpfeil.slashblade.registry.SlashArtsRegistry;
import mods.flammpfeil.slashblade.registry.slashblade.EnchantmentDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.PropertiesDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.RenderDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.registries.ForgeRegistries;

public class TLSSlashBladeRegistry {
    public static final ResourceKey<SlashBladeDefinition> ROUKANKEN = register("roukanken");
    public static final ResourceKey<SlashBladeDefinition> HAKUROUKEN = register("hakurouken");

    public static final ResourceKey<SlashBladeDefinition> ROUKANKEN_NETHER = register("roukanken_nether");
    public static final ResourceKey<SlashBladeDefinition> HAKUROUKEN_NETHER = register("hakurouken_nether");
    
    public static final ResourceKey<SlashBladeDefinition> AMAGUMO_KAZE = register("amagumo_kaze");
    public static final ResourceKey<SlashBladeDefinition> AMAGUMO_KUMO = register("amagumo_kumo");
    public static final ResourceKey<SlashBladeDefinition> AMAGUMO_MUNIN = register("amagumo_munin");
    
    public static final ResourceKey<SlashBladeDefinition> NAMELESS_ODACHI = register("nameless_odachi");
    public static final ResourceKey<SlashBladeDefinition> MURAMASA_KAGURA = register("muramasa_kagura");
    
    public static final ResourceKey<SlashBladeDefinition> EXORCISM_SAKURA = register("exorcism_sakura");
    public static final ResourceKey<SlashBladeDefinition> EXORCISM_GINKGO = register("exorcism_ginkgo");
    public static final ResourceKey<SlashBladeDefinition> EXORCISM_YUKI = register("exorcism_yuki");
    
    public static final ResourceKey<SlashBladeDefinition> EVIL_KATAWARE = register("evil_kataware");
    public static final ResourceKey<SlashBladeDefinition> EVIL_SHURA = register("evil_shura");
    
    public static final ResourceKey<SlashBladeDefinition> BUNSHI = register("bunshi");
    public static final ResourceKey<SlashBladeDefinition> SAKURAGIRI = register("sakuragiri");
    
    public static final ResourceKey<SlashBladeDefinition> YAMATO = register("yamato_neo");
    
    public static final ResourceKey<SlashBladeDefinition> KUSABIMARU = register("kusabimaru");
    public static final ResourceKey<SlashBladeDefinition> FUSHIGIRI = register("fushigiri");
    
    public static final ResourceKey<SlashBladeDefinition> NAGASADA = register("nagasada");
    
    public static final ResourceKey<SlashBladeDefinition> BAMBOO_TOP = register("bamboo_top");
    public static final ResourceKey<SlashBladeDefinition> BAMBOO_HERMIT = register("bamboo_hermit");
    public static final ResourceKey<SlashBladeDefinition> SILVERBAMBOO_TOP = register("silverbamboo_top");
    public static final ResourceKey<SlashBladeDefinition> GOLDENBAMBOO = register("goldenbamboo");
    public static final ResourceKey<SlashBladeDefinition> SILVERBAMBOO_BLOOD = register("silverbamboo_blood");
    
    public static void registerAll(BootstapContext<SlashBladeDefinition> bootstrap) {
    	
    	bootstrap.register(BAMBOO_TOP,
                new SlashBladeDefinition(TheLastSmith.prefix("bamboo_top"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/bamboolight/bamboo_top.png"))
                                .modelName(SlashBlade.prefix("model/blade.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(5.0F).maxDamage(70)
                        .build(),
                        Lists.newArrayList()));
    	
    	bootstrap.register(BAMBOO_HERMIT,
                new SlashBladeDefinition(TheLastSmith.prefix("bamboo_hermit"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/bamboolight/bamboo_top.png"))
                                .modelName(SlashBlade.prefix("model/named/yamato.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(5.0F).maxDamage(70)
                        .build(),
                        Lists.newArrayList()));
    	
    	bootstrap.register(SILVERBAMBOO_TOP,
                new SlashBladeDefinition(TheLastSmith.prefix("silverbamboo_top"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/bamboolight/silverbamboo_top.png"))
                                .modelName(SlashBlade.prefix("model/named/yamato.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(5.0F).maxDamage(70)
                        .build(),
                        Lists.newArrayList()));
    	
    	bootstrap.register(GOLDENBAMBOO,
                new SlashBladeDefinition(TheLastSmith.prefix("goldenbamboo"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/bamboolight/goldenbamboo.png"))
                                .modelName(SlashBlade.prefix("model/named/yamato.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(5.0F).maxDamage(70)
                        .build(),
                        Lists.newArrayList()));
    	bootstrap.register(SILVERBAMBOO_BLOOD,
                new SlashBladeDefinition(TheLastSmith.prefix("silverbamboo_blood"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/bamboolight/silverbamboo_blood.png"))
                                .modelName(SlashBlade.prefix("model/named/yamato.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(5.0F).maxDamage(70)
                        .build(),
                        Lists.newArrayList()));
    	
      	bootstrap.register(NAGASADA,
                new SlashBladeDefinition(TheLastSmith.prefix("nagasada"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/nagasada/texture.png"))
                                .modelName(TheLastSmith.prefix("model/nagasada/model.obj"))
                                .standbyRenderType(CarryType.DEFAULT)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                        .defaultSwordType(List.of(SwordType.SEALED))
                        .baseAttackModifier(4.0F).maxDamage(70)
                        .build(),
                        Lists.newArrayList()));
    	
      	bootstrap.register(KUSABIMARU,
                new SlashBladeDefinition(TheLastSmith.prefix("kusabimaru"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/sekiro/kusabimaru.png"))
                                .modelName(TheLastSmith.prefix("model/named/sekiro/kusabimaru.obj"))
                                .standbyRenderType(CarryType.KATANA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(5.0F).maxDamage(70)
                        .build(),
                        Lists.newArrayList()));
    	
        bootstrap.register(FUSHIGIRI,
                new SlashBladeDefinition(TheLastSmith.prefix("fushigiri"),
                        RenderDefinition.Builder.newInstance()
                        	.textureName(TheLastSmith.prefix("model/named/sekiro/fushigiri.png"))
                            .modelName(TheLastSmith.prefix("model/named/sekiro/fushigiri.obj"))
                            .effectColor(0XDD3E0000)
                            .standbyRenderType(CarryType.NINJA)
                            .build(),
                        PropertiesDefinition.Builder.newInstance().baseAttackModifier(9.0F)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(SlashArtsRegistry.SAKURA_END.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 2), 
                        		new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 4))));

        bootstrap.register(YAMATO,
                new SlashBladeDefinition(TheLastSmith.prefix("yamato_neo"),
                        RenderDefinition.Builder.newInstance().textureName(TheLastSmith.prefix("model/named/yamato_neo/yamato_neo.png"))
                                .modelName(TheLastSmith.prefix("model/named/yamato_neo/yamato_neo.obj")).build(),
                        PropertiesDefinition.Builder.newInstance().baseAttackModifier(7.0F)
                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SOUL_SPEED), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FALL_PROTECTION), 4))));
    	
    	bootstrap.register(NAMELESS_ODACHI,
                new SlashBladeDefinition(TheLastSmith.prefix("nameless_odachi"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/odachi/odachi.png"))
                                .modelName(TheLastSmith.prefix("model/named/odachi/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(5.0F).maxDamage(70)
                        .build(),
                        Lists.newArrayList()));
    	bootstrap.register(MURAMASA_KAGURA,
                new SlashBladeDefinition(TheLastSmith.prefix("muramasa_kagura"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/odachi/murasama_kagura.png"))
                                .modelName(TheLastSmith.prefix("model/named/odachi/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(5.0F).maxDamage(70)
                        .defaultSwordType(List.of(SwordType.BEWITCHED))
                        .slashArtsType(SlashArtsRegistry.CIRCLE_SLASH.getId())
                        .build(),
                        Lists.newArrayList()));
    	
    	bootstrap.register(BUNSHI,
                new SlashBladeDefinition(TheLastSmith.prefix("bunshi"),
                        RenderDefinition.Builder
                                .newInstance()
                                .effectColor(0xffb7c5)
                                .textureName(TheLastSmith.prefix("model/named/rf_roukan/texture_on.png"))
                                .modelName(TheLastSmith.prefix("model/named/rf_roukan/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(10.0F).maxDamage(70)
                        
                        .defaultSwordType(List.of(SwordType.BEWITCHED))
                        .slashArtsType(TLSSlashArtsRegistry.Transmigration_Slash.getId())
                        .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 2))));
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
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 2))));
    	
    	bootstrap.register(AMAGUMO_KAZE,
                new SlashBladeDefinition(TheLastSmith.prefix("amagumo_kaze"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/smith/texture_wind.png"))
                                .modelName(TheLastSmith.prefix("model/named/smith/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(5.0F).maxDamage(70)
                        
                        .defaultSwordType(List.of(SwordType.BEWITCHED))
                        .slashArtsType(SlashArtsRegistry.DRIVE_VERTICAL.getId())
                        .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 2))));
    	bootstrap.register(AMAGUMO_KUMO,
                new SlashBladeDefinition(TheLastSmith.prefix("amagumo_kumo"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/smith/texture_cloud.png"))
                                .modelName(SlashBlade.prefix("model/named/agito.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(5.0F).maxDamage(70)
                        
                        .defaultSwordType(List.of(SwordType.BEWITCHED))
                        .slashArtsType(SlashArtsRegistry.DRIVE_VERTICAL.getId())
                        .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 2))));
    	
    	bootstrap.register(AMAGUMO_MUNIN,
                new SlashBladeDefinition(TheLastSmith.prefix("amagumo_munin"),
                        RenderDefinition.Builder
                                .newInstance()
                                .effectColor(0xffb7c5)
                                .textureName(TheLastSmith.prefix("model/named/smith/texture_final.png"))
                                .modelName(TheLastSmith.prefix("model/named/smith/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(50.0F).maxDamage(70)
                        
                        .defaultSwordType(List.of(SwordType.BEWITCHED))
                        .slashArtsType(SlashArtsRegistry.DRIVE_VERTICAL.getId())
                        .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 2))));
    	bootstrap.register(EXORCISM_SAKURA,
                new SlashBladeDefinition(TheLastSmith.prefix("exorcism_sakura"),
                        RenderDefinition.Builder
                                .newInstance()
                                .effectColor(0xffb7c5)
                                .textureName(TheLastSmith.prefix("model/named/sakura/texture.png"))
                                .modelName(TheLastSmith.prefix("model/named/sakura/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(5.0F).maxDamage(70)
                        
                        .defaultSwordType(List.of(SwordType.BEWITCHED))
                        .slashArtsType(SlashArtsRegistry.DRIVE_VERTICAL.getId())
                        .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 2))));
    	
    	bootstrap.register(EXORCISM_GINKGO,
                new SlashBladeDefinition(TheLastSmith.prefix("exorcism_ginkgo"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/sakura/texture_1.png"))
                                .modelName(TheLastSmith.prefix("model/named/sakura/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(5.0F).maxDamage(70)
                        
                        .defaultSwordType(List.of(SwordType.BEWITCHED))
                        .slashArtsType(SlashArtsRegistry.DRIVE_VERTICAL.getId())
                        .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 2))));
    	bootstrap.register(EXORCISM_YUKI,
                new SlashBladeDefinition(TheLastSmith.prefix("exorcism_yuki"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/sakura/texture_2.png"))
                                .modelName(TheLastSmith.prefix("model/named/sakura/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(5.0F).maxDamage(70)
                        
                        .defaultSwordType(List.of(SwordType.BEWITCHED))
                        .slashArtsType(SlashArtsRegistry.DRIVE_VERTICAL.getId())
                        .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 2))));
    	
    	bootstrap.register(EVIL_KATAWARE,
                new SlashBladeDefinition(TheLastSmith.prefix("evil_kataware"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/sakura/texture_3.png"))
                                .modelName(TheLastSmith.prefix("model/named/sakura/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(5.0F).maxDamage(70)
                        
                        .defaultSwordType(List.of(SwordType.BEWITCHED))
                        .slashArtsType(SlashArtsRegistry.DRIVE_VERTICAL.getId())
                        .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 2))));
    	bootstrap.register(EVIL_SHURA,
                new SlashBladeDefinition(TheLastSmith.prefix("evil_shura"),
                        RenderDefinition.Builder
                                .newInstance()
                                .textureName(TheLastSmith.prefix("model/named/sakura/texture_4.png"))
                                .modelName(TheLastSmith.prefix("model/named/sakura/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(5.0F).maxDamage(70)
                        
                        .defaultSwordType(List.of(SwordType.BEWITCHED))
                        .slashArtsType(SlashArtsRegistry.DRIVE_VERTICAL.getId())
                        .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 2))));
    	
    	
    	bootstrap.register(ROUKANKEN,
                new SlashBladeDefinition(TheLastSmith.prefix("roukanken"),
                        RenderDefinition.Builder
                                .newInstance()
                                .effectColor(0xffb7c5)
                                .textureName(TheLastSmith.prefix("model/named/roukan/texture.png"))
                                .modelName(TheLastSmith.prefix("model/named/roukan/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(10.0F).maxDamage(70)
                        
                        .defaultSwordType(List.of(SwordType.BEWITCHED))
                        .slashArtsType(TLSSlashArtsRegistry.Transmigration_Slash.getId())
                        .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 2))));
    	bootstrap.register(HAKUROUKEN,
                new SlashBladeDefinition(TheLastSmith.prefix("hakurouken"),
                        RenderDefinition.Builder
                                .newInstance()
                                .effectColor(0xF2F2F2)
                                .textureName(TheLastSmith.prefix("model/named/hakurou/texture.png"))
                                .modelName(TheLastSmith.prefix("model/named/hakurou/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(2.0F).maxDamage(70)
                        
                        .defaultSwordType(List.of(SwordType.BEWITCHED))
                        .slashArtsType(SlashArtsRegistry.DRIVE_VERTICAL.getId())
                        .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 2))));
    	
    	bootstrap.register(ROUKANKEN_NETHER,
                new SlashBladeDefinition(TheLastSmith.prefix("roukanken_nether"),
                        RenderDefinition.Builder
                                .newInstance()
                                .effectColor(0xffb7c5)
                                .textureName(TheLastSmith.prefix("model/named/roukan/texture_tx.png"))
                                .modelName(TheLastSmith.prefix("model/named/roukan/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(10.0F).maxDamage(70)
                        
                        .defaultSwordType(List.of(SwordType.BEWITCHED))
                        .slashArtsType(TLSSlashArtsRegistry.Transmigration_Slash.getId())
                        .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 2))));
    	bootstrap.register(HAKUROUKEN_NETHER,
                new SlashBladeDefinition(TheLastSmith.prefix("hakurouken_nether"),
                        RenderDefinition.Builder
                                .newInstance()
                                .effectColor(0xF2F2F2)
                                .textureName(TheLastSmith.prefix("model/named/hakurou/texture_tx.png"))
                                .modelName(TheLastSmith.prefix("model/named/hakurou/model.obj")).build(),
                        PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(2.0F).maxDamage(70)
                        
                        .defaultSwordType(List.of(SwordType.BEWITCHED))
                        .slashArtsType(SlashArtsRegistry.DRIVE_VERTICAL.getId())
                        .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 2))));
	}
    
    private static ResourceLocation getEnchantmentID(Enchantment enchantment) {
        return ForgeRegistries.ENCHANTMENTS.getKey(enchantment);
    }
    
    private static ResourceKey<SlashBladeDefinition> register(String id) {
        ResourceKey<SlashBladeDefinition> loc = ResourceKey.create(SlashBladeDefinition.REGISTRY_KEY,
                TheLastSmith.prefix(id));
        return loc;
    }
}
