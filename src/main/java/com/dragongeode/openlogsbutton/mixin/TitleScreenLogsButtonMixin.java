package com.dragongeode.openlogsbutton.mixin;

import com.dragongeode.openlogsbutton.gui.LogsFolderButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenLogsButtonMixin extends Screen {

    protected TitleScreenLogsButtonMixin() {
        super(null);
        throw new UnsupportedOperationException();
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void openlogs$addLogsFolderButton(CallbackInfo ci) {
        int anchorX = this.width / 2 - 124;
        int rowY = this.height / 4 + 144;

        for (Object child : this.children()) {
            if (child instanceof AbstractWidget widget
                    && widget.getX() == anchorX && widget.getHeight() <= 24) {
                rowY = widget.getY();
                break;
            }
        }

        this.addRenderableWidget(LogsFolderButton.create(anchorX - 24, rowY));
    }
}
