package kr.mocha.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import kr.mocha.TerrorManager;

/**
 * Created by mocha on 16. 11. 2.
 */
public class BanItemCommand extends Command{
    public TerrorManager manager = TerrorManager.getInstance();

    public BanItemCommand(){
        super("banitem","아이템의 사용을 밴 합니다.","/banitem <add|del|list>",new String[]{"banit"});
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        try {
            switch (args[0]) {
                case "add":
                    if(sender.hasPermission("banItem.cmd")){
                        if(!checkInt(args[1])) {
                            sender.sendMessage(TextFormat.RED+"숫자만 가능합니다. 데미지는 지원하지 않습니다.");
                            sender.sendMessage(TextFormat.RED+"ex) 10(O), 351:3(X)");
                            return false;
                        }
                        manager.addBanItem(Integer.parseInt(args[1]));
                        sender.sendMessage(TextFormat.DARK_BLUE+"아이템을 밴하셨습니다.");
                    }else sender.sendMessage(TextFormat.RED+"명령어의 권한이 없습니다.");
                    break;
                case "del":
                    if(sender.hasPermission("banItem.cmd")){
                        if(!checkInt(args[1])){
                            sender.sendMessage(TextFormat.RED+"숫자만 가능합니다. 데미지는 지원하지 않습니다.");
                            sender.sendMessage(TextFormat.RED+"ex) 10(O), 351:3(X)");
                            return false;
                        }
                        manager.delBanItem(Integer.parseInt(args[1]));
                        sender.sendMessage(TextFormat.DARK_BLUE+"아이템을 언밴하셨습니다.");
                    }else sender.sendMessage(TextFormat.RED+"명령어의 권한이 없습니다.");
                    break;
                case "list":
                    String result = "";
                    for(String s : manager.getBanItemList())
                        result += s+", ";
                    sender.sendMessage("=== Banned Item ===");
                    sender.sendMessage(TextFormat.GREEN+result);
                    return true;
                default:
                    break;
            }
        }catch (ArrayIndexOutOfBoundsException e){
            sender.sendMessage(TextFormat.RED+this.getUsage());
            return false;
        }
        return false;
    }
    private boolean checkInt(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch (Exception e){
            return  false;
        }
    }
}
