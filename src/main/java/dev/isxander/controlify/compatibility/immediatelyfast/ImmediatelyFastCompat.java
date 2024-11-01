//? if immediately-fast {
package dev.isxander.controlify.compatibility.immediatelyfast;

import net.raphimc.immediatelyfastapi.ImmediatelyFastApi;

public class ImmediatelyFastCompat {
    public static void beginHudBatching() {
        //? if <1.21.2
        ImmediatelyFastApi.getApiImpl().getBatching().beginHudBatching();
    }

    public static void endHudBatching() {
        //? if <1.21.2
        ImmediatelyFastApi.getApiImpl().getBatching().endHudBatching();
    }
}
//?}
