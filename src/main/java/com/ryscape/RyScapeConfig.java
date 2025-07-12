package com.ryscape;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("Sounds")
public interface RyScapeConfig extends Config
{

	@ConfigItem(
		keyName = "volume",
		name = "Volume",
		description = "Volume of sound effects"
	)
	default int volume() {
		return 100;
	}
}
