//? if forgelike {
package dev.isxander.controlify.platform.network.neoforge;

import dev.isxander.controlify.platform.network.C2SNetworkApi;
import dev.isxander.controlify.platform.network.ControlifyPacketCodec;
import dev.isxander.controlify.platform.network.PacketPayload;
import net.minecraft.resources.ResourceLocation;
//? if neoforge {
/*import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
*///?} else {
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
//?}

public class C2SNetworkApiNeoforge implements C2SNetworkApi {
    public static final C2SNetworkApiNeoforge INSTANCE = new C2SNetworkApiNeoforge();

    @Override
    public <T> void sendPacket(ResourceLocation channel, T packet) {
        // TODO
    }

    @Override
    public <T> PacketPayload createPayload(ResourceLocation channel, T packet) {
        return null;
    }

    @Override
    public <T> void listenForPacket(ResourceLocation channel, PacketListener<T> listener) {
        // TODO
    }

    @Override
    public <T> void registerPacket(ResourceLocation channel, ControlifyPacketCodec<T> handler) {
        // TODO
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
