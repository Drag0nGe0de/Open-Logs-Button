package com.dragongeode.openlogsbutton.util;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;

public final class LogsOpener {

    private static final Logger LOGGER = LoggerFactory.getLogger("OpenLogsButton/LogsOpener");

    private LogsOpener() {
    }

    public static Path openLogsFolder() {
        Path gameDir = FabricLoader.getInstance().getGameDir()
                .toAbsolutePath().normalize();
        Path logs = gameDir.resolve("logs");
        try {
            if (!Files.isDirectory(logs)) {
                Files.createDirectories(logs);
            }
            Util.getPlatform().openFile(logs.toFile());
            LOGGER.info("Opened the logs folder: {}", logs);
            return logs;
        } catch (Throwable primary) {
            try {
                Util.getPlatform().openFile(gameDir.toFile());
                LOGGER.info("logs/ unavailable ({}), opened the game directory: {}",
                        primary.toString(), gameDir);
                return gameDir;
            } catch (Throwable fallback) {
                fallback.addSuppressed(primary);
                LOGGER.warn("Open Logs Folder: neither {} nor {} could be opened",
                        logs, gameDir, fallback);
                return null;
            }
        }
    }

    public static String describeTarget() {
        Path gameDir = FabricLoader.getInstance().getGameDir()
                .toAbsolutePath().normalize();
        return gameDir.resolve("logs").toString();
    }
}
