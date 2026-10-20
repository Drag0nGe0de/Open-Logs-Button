package com.dragongeode.openlogsbutton.mixin;

import com.dragongeode.openlogsbutton.gui.LogsFolderButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PauseScreen.class)
public abstract class PauseScreenLogsButtonMixin extends Screen {

    protected PauseScreenLogsButtonMixin() {
        super(null);
        throw new UnsupportedOperationException();
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void openlogs$addLogsFolderButton(CallbackInfo ci) {
        int lowestBottom = -1;
        for (Object child : this.children()) {
            if (!(child instanceof AbstractWidget widget)) continue;
            if (widget.getWidth() < 150) continue;
            lowestBottom = Math.max(lowestBottom, widget.getY() + widget.getHeight());
        }
        if (lowestBottom < 0) return;
        int x = this.width / 2 - 10;
        int y = lowestBottom + 8;
        if (y + 20 > this.height - 8) return;
        this.addRenderableWidget(LogsFolderButton.create(x, y));
    }
}
