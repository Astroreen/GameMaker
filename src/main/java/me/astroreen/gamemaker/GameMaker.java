package me.astroreen.gamemaker;

import me.astroreen.astrolibs.api.bukkit.listener.ListenerManager;
import me.astroreen.astrolibs.api.bukkit.logger.LoggerFactory;
import me.astroreen.astrolibs.api.common.AstroLibsAPI;
import me.astroreen.astrolibs.api.common.PluginTemplate;
import me.astroreen.astrolibs.api.common.config.file.Configuration;
import me.astroreen.astrolibs.api.common.logger.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class GameMaker extends JavaPlugin implements PluginTemplate {

  private static GameMaker instance;
  private static Logger log;
  private static Configuration config;

  @Override
  public void onEnable() {
    log = LoggerFactory.create(this);
    ListenerManager.setup(this, null, log);

    try {
      config =
          AstroLibsAPI.getInstance()
              .createConfigurationFile(
                  getClass().getClassLoader().getResourceAsStream("config.yml"),
                  new File(getInstance().getDataFolder(), "config.yml"));
    } catch (IOException e) {
      log.error("Could not create configuration file. Default values will be used instead.", e);
    }

    instance = this; // the moment everything works
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }

  public static GameMaker getInstance() {
    return instance;
  }

  public static Logger log() {
    return log;
  }

  public static Configuration getConfiguration() {
    return config;
  }
}
