package com.ryscape;

import com.google.inject.Provides;
import javax.inject.Inject;

import com.ryscape.sound.Sound;
import com.ryscape.sound.SoundEngine;
import com.ryscape.sound.SoundFileManager;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import okhttp3.OkHttpClient;

import java.util.concurrent.ScheduledExecutorService;

@Slf4j
@PluginDescriptor(
	name = "RyScape"
)
public class RyScapePlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private RyScapeConfig config;

	@Inject
	private SoundEngine soundEngine;

	@Inject
	private ScheduledExecutorService executor;

	@Inject
	private OkHttpClient okHttpClient;

	@Override
	protected void startUp() throws Exception
	{
		log.info("RyScape started!");
		executor.submit(() -> SoundFileManager.prepareSoundFiles(okHttpClient));
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("RyScape stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "Ry", "Welcome to Ry Scape.", null);
			soundEngine.playClip(Sound.LOGIN, executor);
		}
	}

	@Provides
	RyScapeConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(RyScapeConfig.class);
	}
}
