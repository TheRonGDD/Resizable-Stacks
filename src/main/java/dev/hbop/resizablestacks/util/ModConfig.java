package dev.hbop.resizablestacks.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.hbop.resizablestacks.ResizableStacks;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/** Lightweight configuration loader used while owo-lib has no Minecraft 26.3 build. */
public final class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final Map<String, Integer> stackSizes;

    private ModConfig(Map<String, Integer> stackSizes) {
        this.stackSizes = Collections.unmodifiableMap(new LinkedHashMap<>(stackSizes));
    }

    public Map<String, Integer> stackSizes() {
        return stackSizes;
    }

    public static ModConfig load() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve(ResizableStacks.MOD_ID + ".json5");
        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path.getParent());
                try (Writer writer = Files.newBufferedWriter(path)) {
                    GSON.toJson(Map.of("stackSizes", Map.of()), writer);
                }
            } catch (IOException exception) {
                throw new IllegalStateException("Unable to create " + path, exception);
            }
        }

        try (Reader reader = Files.newBufferedReader(path)) {
            JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
            Map<String, Integer> values = new LinkedHashMap<>();
            JsonObject entries = root.has("stackSizes") && root.get("stackSizes").isJsonObject()
                    ? root.getAsJsonObject("stackSizes")
                    : new JsonObject();
            entries.entrySet().forEach(entry -> values.put(entry.getKey(), entry.getValue().getAsInt()));
            ResizableStacks.LOGGER.info("Loaded {} configured stack-size overrides", values.size());
            return new ModConfig(values);
        } catch (Exception exception) {
            throw new IllegalStateException("Unable to read " + path, exception);
        }
    }
}
