package kr.mocha.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import kr.mocha.TerrorManager;

/**
 * Created by mocha on 16. 11. 2.
 */
public class BanMessageCommand extends Command{
    public TerrorManager manager = TerrorManager.getInstance();

    public BanMessageCommand(){
        super("banmessage","메시지를 밴합니다.","/banmessage <add|del|list>",new String[]{"banmsg"});
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        try {
            switch (args[0]) {
                case "add":
                case "a":
                    if (sender.hasPermission("banMessage.cmd")) {
                        manager.addBanMessage(args[1]);
                        sender.sendMessage(TextFormat.DARK_BLUE + "추가하였습니다.");
                        return true;
                    } else sender.sendMessage(TextFormat.RED + "명령어의 권한이 없습니다.");
                    break;
                case "del":
                case "d":
                    if (sender.hasPermission("banMessage.cmd")) {
                        manager.delBanMessage(args[1]);
                        sender.sendMessage(TextFormat.DARK_BLUE + "삭제하였습니다.");
                        return true;
                    } else sender.sendMessage(TextFormat.RED + "명령어의 권한이 없습니다.");
                    break;
                case "list":
                    String result = "";
                    for (String s : manager.getBanMessageList())
                        result += s + ", ";
                    sender.sendMessage("=== Banned Message ===");
                    sender.sendMessage(TextFormat.GREEN+result);
                    return true;
                default:
                    sender.sendMessage(TextFormat.RED + this.getUsage());
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            sender.sendMessage(TextFormat.RED+this.getUsage());
            return false;
        }
        return false;
    }
}
