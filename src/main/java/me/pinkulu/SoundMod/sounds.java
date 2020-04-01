package me.pinkulu.SoundMod;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Mod(modid = Sounds.MODID, version = Sounds.VERSION, name = Sounds.NAME)
public class Sounds {

    static final String MODID = "soundsmod";
    public static final String VERSION = "1.0";
    public static final String NAME = "SoundsMod";

    private SoundPlayer soundplayer = new SoundPlayer();


    @Mod.EventHandler
    public void onInitialization(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(soundplayer);
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new HelpCommand());
        ClientCommandHandler.instance.registerCommand(new KillToggleCommand());
        ClientCommandHandler.instance.registerCommand(new DeathToggleCommand());
        loadConfig();


    }

    public static void saveConfig(){
        try {
            File file = new File("SoundsMod", "config.json");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            JsonWriter writer = new JsonWriter(new FileWriter(file, false));
            writeJson(writer);
            writer.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void loadConfig() {
        try {
            File file = new File("SoundsMod", "config.json");
            if (!file.exists()) {
                file.createNewFile();
            }
            readJson(file);
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }
    private void readJson(File file) throws Throwable{
        JsonParser parser = new JsonParser();
        JsonObject json  = parser.parse(new FileReader(file)).getAsJsonObject();
    }


    public static void writeJson(JsonWriter writer) throws IOException {
        writer.setIndent(" "); // this enabled pretty print
        writer.beginObject();
        writer.name("Sounds");
        writer.beginObject();

        writer.name("oofKill").value(SoundPlayer.oofKill);
        writer.name("oofDeath").value(SoundPlayer.oofDeath);

        writer.name("boomerKill").value(SoundPlayer.boomerKill);
        writer.name("boomerDeath").value(SoundPlayer.boomerDeath);

        writer.name("bruhKill").value(SoundPlayer.bruhKill);
        writer.name("bruhDeath").value(SoundPlayer.bruhDeath);

        writer.name("quackKill").value(SoundPlayer.quackKill);
        writer.name("quackDeath").value(SoundPlayer.quackDeath);

        writer.name("gotchaKill").value(SoundPlayer.gotchaKill);

        writer.endObject();
        writer.endObject();
    }
}
