package fr.theyoungpegasus.metrocraftdodo;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.FaceAttachable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.block.data.Rotatable;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerListener implements Listener {
	
	/*@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
	        Block button = event.getClickedBlock();
	        if (button.getType() == Material.STONE_BUTTON) {
	        	BlockData buttonData = button.getBlockData();
	        	FaceAttachable buttonFaces = (FaceAttachable) buttonData;
	        	FaceAttachable.AttachedFace buttonFaceAttached = buttonFaces.getAttachedFace();
	        	if (buttonFaceAttached.toString() == "FLOOR") {
	        		Block behind = button.getRelative(BlockFace.UP.getOppositeFace());
	        		if (behind.getType() == Material.GRAY_CONCRETE) {
	        			BlockFace f = ((Rotatable) buttonData).getRotation();
	        			Block front = button.getRelative(BlockFace.f.getOppositeFace());
	        			Bukkit.broadcastMessage(front.getType().toString());
	        		}
	        	}
	        }
		}
    }*/
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
	        Block button = event.getClickedBlock();
	        if (button.getType() == Material.STONE_BUTTON) {
		        Block blockBelow1 = button.getRelative(0, -1, 0);
		        Block blockBelow2 = button.getRelative(0, -2, 0);
		        if (blockBelow1.getType() == Material.POWERED_RAIL && blockBelow2.getType() == Material.BLACK_CONCRETE) {
		        	Location locationBlockBelow1 = blockBelow1.getLocation();
		        	World world = button.getWorld();
		        	Minecart cart = world.spawn(locationBlockBelow1 ,Minecart.class);
		        	Player player = event.getPlayer();
		        	cart.addPassenger(player);
		        }
	        }
		}
    }
}
