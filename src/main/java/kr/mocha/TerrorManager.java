package kr.mocha;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerItemHeldEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import kr.mocha.command.BanItemCommand;
import kr.mocha.command.BanMessageCommand;
import kr.mocha.command.CleanChatCommand;

import java.util.Set;

/**
 * Created by mocha on 16. 11. 2.
 */
public class TerrorManager extends PluginBase implements Listener{
    public static TerrorManager instance;
    public Config BanMsg, BanItem;

    @Override
    public void onEnable() {
        instance = this;
        getDataFolder().mkdirs();
        BanItem = new Config(getDataFolder()+"/BanItem.yml", Config.YAML);
        BanMsg = new Config(getDataFolder()+"/BanMsg.yml", Config.YAML);
        this.getServer().getCommandMap().register("banmessage", new BanMessageCommand());
        this.getServer().getCommandMap().register("banitem", new BanItemCommand());
	this.getServer().getCommandMap().register("clean", new CleanChatCommand());
        this.getServer().getPluginManager().registerEvents(this, this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.save();
        super.onDisable();
    }
    /*event*/
    @EventHandler(priority = EventPriority.HIGH)
    public void onHeld(PlayerItemHeldEvent event){
        int id = event.getItem().getId();

        if(id==46||id==10||id==11||id==51||id==259||id==327||id==407){
            event.setCancelled();
            event.getPlayer().sendMessage(TextFormat.RED+"이것은 사용할 수 없습니다.");
            event.getPlayer().getInventory().remove(event.getItem());
            return;
        }
        if(BanItem.getAll().values().contains(id)){
            event.setCancelled();
            event.getPlayer().sendMessage(TextFormat.RED+"이것은 사용할 수 없습니다.");
            event.getPlayer().getInventory().remove(event.getItem());
            return;
        }
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onTouch(PlayerInteractEvent event){
        int id = event.getItem().getId();

        if(id==46||id==10||id==11||id==51||id==259||id==327||id==407){
            event.setCancelled();
            event.getPlayer().sendMessage(TextFormat.RED+"이것은 사용할 수 없습니다.");
            event.getPlayer().getInventory().remove(event.getItem());
            return;
        }
        if(BanItem.getAll().values().contains(id)){
            event.setCancelled();
            event.getPlayer().sendMessage(TextFormat.RED+"이것은 사용할 수 없습니다.");
            event.getPlayer().getInventory().remove(event.getItem());
            return;
        }
    }
    @EventHandler
    public void onChat(PlayerChatEvent event){
        if(BanMsg.exists(event.getMessage())){
            event.setCancelled();
            event.getPlayer().sendMessage(TextFormat.RED+"금지어입니다.");
            return;
        }
        if(isDefaultBannedMessage(event.getMessage())){
            event.setCancelled();
            event.getPlayer().sendMessage(TextFormat.RED+"금지어입니다.");
            return;
        }
    }

    /*Ban Message utils*/
    public boolean addBanMessage(String message){
        try{
            BanMsg.set(message, true);
            this.save();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public boolean delBanMessage(String message){
        try{
            BanMsg.remove(message);
            this.save();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public Set<String> getBanMessageList(){
        return BanMsg.getKeys();
    }

    /*Ban Item utils*/
    public boolean addBanItem(int item){
        try{
            BanItem.set(String.valueOf(item), item);
            this.save();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public boolean delBanItem(int item){
        try{
            BanMsg.remove(String.valueOf(item));
            this.save();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public Set<String> getBanItemList(){
        return BanItem.getKeys();
    }

    /*other*/
    public void save(){
        BanItem.save();
        BanMsg.save();
    }
    public static TerrorManager getInstance(){
        return instance;
    }
    private boolean isDefaultBannedMessage(String message){
        if(message.contains("씨")&&message.contains("발"))
            return true;
        if(message.contains("시")&&message.contains("발"))
            return true;
        if(message.contains("새")&&message.contains("끼"))
            return true;
        if(message.contains("느")&&message.contains("금"))
            return true;
        if(message.contains("니")&&message.contains("엄"))
            return true;
        if(message.contains("엠")&&message.contains("창"))
            return true;
        if(message.contains("불")&&message.contains("알"))
            return true;
        if(message.contains("부")&&message.contains("랄"))
            return true;
        if(message.contains("보")&&message.contains("지"))
            return true;
        if(message.contains("봉")&&message.contains("지"))
            return true;
        else return false;
    }
}
