package fun.lewisdev.deluxehub.module.modules.chat.customcommands;

import co.aikar.commands.PaperCommandManager;
import fun.lewisdev.deluxehub.DeluxeHubPlugin;
import fun.lewisdev.deluxehub.command.CustomCommand;
import fun.lewisdev.deluxehub.module.Module;
import fun.lewisdev.deluxehub.module.ModuleType;

import java.util.List;
import java.util.stream.Collectors;

public class CustomCommands extends Module {

    private List<CustomCommand> commands;

    public CustomCommands(DeluxeHubPlugin plugin) {
        super(plugin, ModuleType.CUSTOM_COMMANDS);
    }

    @Override
    public void onEnable() {
        final PaperCommandManager commandManager = new PaperCommandManager(this.getPlugin());
        commands = getPlugin().getCommandManager().getCustomCommands();

        commandManager.getCommandReplacements().addReplacement("%custom-commands",
                this.commands.stream().map(CustomCommand::getAliases)
                        .flatMap(List::stream).collect(Collectors.joining("|")));
        commandManager.registerCommand(new CustomCommandWrapper(this));
    }

    @Override
    public void onDisable() {
    }

    public List<CustomCommand> getCommands() {
        return commands;
    }
}
