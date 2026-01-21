package pk.ajneb97.tasks;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.Bukkit;
import pk.ajneb97.PlayerKits2;

import java.util.concurrent.TimeUnit;

public class PlayerDataSaveTask {

	private PlayerKits2 plugin;
	private boolean end;
    private ScheduledTask task;

	public PlayerDataSaveTask(PlayerKits2 plugin) {
		this.plugin = plugin;
		this.end = false;
	}
	
	public void end() {
		end = true;
        if(task != null){
            task.cancel();
        }
	}
	
	public void start(int minutes) {
		long seconds = minutes*60L;
		task = Bukkit.getAsyncScheduler().runAtFixedRate(plugin, t -> {
            execute();
		}, 0L, seconds, TimeUnit.SECONDS);
	}
	
	public void execute() {
		plugin.getConfigsManager().getPlayersConfigManager().saveConfigs();
	}
}
