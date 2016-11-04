package kr.mocha.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import kr.mocha.TerrorManager;

/**
 * Created by mocha on 16. 11. 4.
 */
public class CleanChatCommand extends Command{
    public CleanChatCommand(){
        super("clean", "채팅을 청소합니다.", "/clean", new String[]{"cc","청소"});
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(sender.isOp()||!sender.isPlayer())
	    for(int i = 0;i < 30; i++)
                TerrorManager.getInstance().getServer().broadcastMessage(" ");
        else
            for(int i = 0;i < 30; i++)
                sender.sendMessage(" ");
        return false;
    }
}
