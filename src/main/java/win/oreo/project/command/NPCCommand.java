package win.oreo.project.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import win.oreo.project.Project;
import win.oreo.project.util.npc.NPC;
import win.oreo.project.util.npc.NPCUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NPCCommand implements CommandExecutor {
    private Project plugin;

    public NPCCommand() {
        this.plugin = JavaPlugin.getPlugin(Project.class);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("messages.no-player");
            return false;
        }
        if (sender.hasPermission("administrators")) {
            if (args.length == 0) {
                //sender.sendMessage("/npc list | open (name) | tp (name) | add (name) (size) | skin (name) (skinOwner) | remove (name) / (all) | edit (name) | size (name)");
            } else {
                switch (args[0]) {
                    case "save" :
                        player.sendMessage("messages.npc.save");
                        break;
                    case "skin" :
                        if (args.length == 3) {
                            setSkin(player, args[1], args[2]);
                            player.sendMessage("messages.npc.skin");
                        }
                        break;
                    case "add" :
                        if (args.length == 2) {
                            summon(player, args[1]);
                            player.sendMessage("messages.npc.add-default");
                        }
                        else {
                            Bukkit.dispatchCommand(player, "npc");
                        }
                        break;
                    case "remove" :
                        if (args.length == 2) {
                            remove(player, args[1]);
                            player.sendMessage("messages.npc.remove");
                        } else {
                            Bukkit.dispatchCommand(player, "npc");
                        }
                        break;
                    case "list" :
                        if (args.length == 1) {
                            printNPCList(sender);
                        } else {
                            Bukkit.dispatchCommand(sender, "npc");
                        }
                        break;
                    case "autoLoad", "auto" :
                        //TODO parsing from NPC DataFile Folder
                        break;
                    default:
                        Bukkit.dispatchCommand(player, "npc");
                        break;
                }
            }
        }
        else {
            sender.sendMessage("messages.no-permission");
        }
        return false;
    }

    public void printNPCList(CommandSender sender) {
        for (NPC npc : NPCUtil.NPCSet) {
            sender.sendMessage(npc.getName());
        }
    }

    public void setSkin(CommandSender sender, String name, String skin) { //TODO 스킨생성후 추가하는 기능
        NPC player = NPCUtil.getNPC(name);
        if (player == null) {
            Bukkit.dispatchCommand(sender, "npc");
            return;
        }
    }

    public void summon(CommandSender sender, String name) {
        summon(sender, name, "", "");
    }

    public void summon(CommandSender sender, String name, String value, String signature) {
        Location loc = ((Player)sender).getLocation();
        if (NPC.summon(name, loc.getX(), loc.getY(), loc.getZ(), UUID.randomUUID(), value, signature)) {
            sender.sendMessage("succeed to add new npc!");
        } else {
            sender.sendMessage("failed to add new npc!");
        }
    }

    private void remove(CommandSender sender, String name) {
        if (name.equalsIgnoreCase("All")) {
            List<NPC> copy = new ArrayList<>(NPCUtil.NPCSet);
            for (NPC player : copy) {
                player.removePlayer();
            }
            sender.sendMessage("removed all npc!");
        } else {
            NPC npcPlayer = NPCUtil.getNPC(name);

            if (npcPlayer != null) {
                sender.sendMessage("removed npc : " + npcPlayer.getName());
                npcPlayer.removePlayer();
            } else {
                sender.sendMessage("failed to remove npc : " + name);
            }
        }
    }

    public boolean checkPermission(CommandSender sender) {
        return !sender.hasPermission("administrators");
    }
}