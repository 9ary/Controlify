package dev.isxander.controlify.controller.input;

import net.minecraft.resources.ResourceLocation;

import java.util.Optional;
import java.util.Set;

public class DeadzoneControllerStateView implements ControllerStateView {
    private final ControllerStateView view;
    private final InputComponent input;

    public DeadzoneControllerStateView(ControllerStateView view, InputComponent input) {
        this.view = view;
        this.input = input;
    }

    @Override
    public boolean isButtonDown(ResourceLocation button) {
        return view.isButtonDown(button);
    }

    @Override
    public Set<ResourceLocation> getButtons() {
        return view.getButtons();
    }

    @Override
    public float getAxisState(ResourceLocation axis) {
        return view.getAxisState(axis);
    }

    @Override
    public Set<ResourceLocation> getAxes() {
        return view.getAxes();
    }

    @Override
    public float getAxisResting(ResourceLocation axis) {
        return view.getAxisResting(axis);
    }

    @Override
    public HatState getHatState(ResourceLocation hat) {
        return view.getHatState(hat);
    }

    @Override
    public Set<ResourceLocation> getHats() {
        return view.getHats();
    }
}
