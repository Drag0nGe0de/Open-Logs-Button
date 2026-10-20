package com.dragongeode.openlogsbutton.gui;

import com.dragongeode.openlogsbutton.util.LogsOpener;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public final class LogsFolderButton {

    public static final Identifier ICON =
            Identifier.fromNamespaceAndPath("open-logs-button", "icon/logs_folder");

    private LogsFolderButton() {
    }

    public static SpriteIconButton create(int x, int y) {
        SpriteIconButton button = SpriteIconButton
                .builder(Component.literal("Open Logs Folder"),
                        btn -> LogsOpener.openLogsFolder(), true)
                .width(20)
                .sprite(ICON, 16, 16)
                .withTootip()
                .build();
        button.setX(x);
        button.setY(y);
        return button;
    }
}
