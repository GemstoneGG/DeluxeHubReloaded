package fun.lewisdev.deluxehub.module.modules.chat.customcommands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import fun.lewisdev.deluxehub.command.CustomCommand;
import fun.lewisdev.deluxehub.config.Messages;
import org.bukkit.entity.Player;

/**
 * @author Elmar Blume - 23/10/2024
 */
@CommandAlias("%custom-commands")
public final class CustomCommandWrapper extends BaseCommand {

	private final CustomCommands module;

	public CustomCommandWrapper(CustomCommands module) {
		this.module = module;
	}

	@Default
	public void onDefault(Player player) {
		if (module.inDisabledWorld(player.getLocation())) return;

		String command = this.getExecCommandLabel().toLowerCase();

		for (CustomCommand customCommand : module.getCommands()) {
			if (customCommand.getAliases().stream().anyMatch(alias -> alias.equals(command))) {
				if (customCommand.getPermission() != null) if (!player.hasPermission(customCommand.getPermission())) {
					Messages.CUSTOM_COMMAND_NO_PERMISSION.send(player);
					return;
				}
				module.getPlugin().getActionManager().executeActions(player, customCommand.getActions());
			}
		}

	}
}
