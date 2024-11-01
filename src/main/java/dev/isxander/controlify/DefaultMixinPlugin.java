package dev.isxander.controlify;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class DefaultMixinPlugin implements IMixinConfigPlugin {
    private final boolean areModsLoading;

    public DefaultMixinPlugin() {
        boolean areModsLoading = true;
        //? if fabric {
        /*net.fabricmc.loader.api.FabricLoader fabricLoader = net.fabricmc.loader.api.FabricLoader.getInstance();
        *///?} elif neoforge {
        /*net.neoforged.fml.loading.LoadingModList loadingModList = net.neoforged.fml.loading.LoadingModList.get();
        *///?} elif forge {
        net.minecraftforge.fml.loading.LoadingModList loadingModList = net.minecraftforge.fml.loading.LoadingModList.get();
        //?}
        for (final String modId : this.getModIds()) {
            //? if fabric {
            /*areModsLoading = areModsLoading && fabricLoader.isModLoaded(modId);
            *///?} elif forgelike {
            areModsLoading = areModsLoading && loadingModList.getModFileById(modId) != null;
            //?}
        }
        this.areModsLoading = areModsLoading;
    }

    protected static final String CONTROLIFY_MOD_ID = "controlify";
    protected static final String YACL3_MOD_ID = "yet_another_config_lib_v3";
    protected static final List<String> REQUIRED_MOD_IDS = List.of(CONTROLIFY_MOD_ID, YACL3_MOD_ID);

    protected Iterable<String> getModIds() {
        return REQUIRED_MOD_IDS;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return areModsLoading;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig() {
        return null;
    }
}
