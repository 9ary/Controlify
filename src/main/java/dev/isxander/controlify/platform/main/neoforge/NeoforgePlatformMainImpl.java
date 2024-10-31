//? if forgelike {
package dev.isxander.controlify.platform.main.neoforge;

import dev.isxander.controlify.api.entrypoint.ControlifyEntrypoint;
import dev.isxander.controlify.platform.Environment;
import dev.isxander.controlify.platform.main.PlatformMainUtilImpl;
import dev.isxander.controlify.platform.main.events.CommandRegistrationCallbackEvent;
import dev.isxander.controlify.platform.main.events.HandshakeCompletionEvent;
import dev.isxander.controlify.platform.main.events.PlayerJoinedEvent;
import dev.isxander.controlify.platform.network.ControlifyPacketCodec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
//? if neoforge {
/*import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
*///?} else {
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
//?}

import java.nio.file.Path;
import java.util.Arrays;
import java.util.ServiceLoader;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class NeoforgePlatformMainImpl implements PlatformMainUtilImpl {
    @Override
    public void registerCommandRegistrationCallback(CommandRegistrationCallbackEvent callback) {
        //? if neoforge {
        /*NeoForge.EVENT_BUS.<RegisterCommandsEvent>addListener(e -> {
        *///?} else {
        MinecraftForge.EVENT_BUS.<RegisterCommandsEvent>addListener(e -> {
        //?}
            callback.onRegister(e.getDispatcher(), e.getBuildContext(), e.getCommandSelection());
        });
    }

    @Override
    public void registerInitPlayConnectionEvent(PlayerJoinedEvent event) {
        //? if neoforge {
        /*NeoForge.EVENT_BUS.<PlayerEvent.PlayerLoggedInEvent>addListener(e -> {
        *///?} else {
        MinecraftForge.EVENT_BUS.<PlayerEvent.PlayerLoggedInEvent>addListener(e -> {
        //?}
            event.onInit((ServerPlayer) e.getEntity());
        });
    }

    @Override
    public boolean isModLoaded(String... modIds) {
        return Arrays.stream(modIds).anyMatch(ModList.get()::isLoaded);
    }

    @Override
    public Path getGameDir() {
        return FMLPaths.GAMEDIR.get();
    }

    @Override
    public Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public boolean isDevEnv() {
        return !FMLEnvironment.production;
    }

    @Override
    public Environment getEnv() {
        return switch (FMLEnvironment.dist) {
            case CLIENT -> Environment.CLIENT;
            case DEDICATED_SERVER -> Environment.SERVER;
        };
    }

    @Override
    public String getControlifyVersion() {
        return ModList.get().getModFileById("controlify").versionString();
    }

    @Override
    public void applyToControlifyEntrypoint(Consumer<ControlifyEntrypoint> entrypointConsumer) {
        ServiceLoader.load(ControlifyEntrypoint.class).forEach(entrypointConsumer);
    }

    @Override
    public <I, O> void setupServersideHandshake(ResourceLocation handshakeId, ControlifyPacketCodec<I> serverBoundCodec, ControlifyPacketCodec<O> clientBoundCodec, Supplier<O> packetCreator, HandshakeCompletionEvent<I> completionEvent) {
        // TODO
    }

    @Override
    public <T> Supplier<T> deferredRegister(Registry<T> registry, ResourceLocation id, Supplier<? extends T> registrant) {
        //? if neoforge {
        /*return DeferredRegister.create(registry, id.getNamespace()).register(id.getPath(), registrant);
        *///?} else {
        return DeferredRegister.create((IForgeRegistry) registry, id.getNamespace()).register(id.getPath(), registrant);
        //?}
    }

    private IEventBus getModEventBus() {
        //? if neoforge {
        /*return ModLoadingContext.get().getActiveContainer().getEventBus();
        *///?} else {
        return FMLJavaModLoadingContext.get().getModEventBus();
        //?}
    }
}
//?}
