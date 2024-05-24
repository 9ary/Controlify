package dev.isxander.controlify.compatibility;

import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public abstract class CompatMixinPlugin implements IMixinConfigPlugin {
    private final boolean compatEnabled;

    protected CompatMixinPlugin() {
        this.compatEnabled = FabricLoader.getInstance().isModLoaded(this.getModId());
    }

    public abstract String getModId();

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        System.out.println(getModId() + compatEnabled);
        return compatEnabled;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        return List.of();
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig() {
        return "";
    }
}