package net.template.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin
{
	@Inject(at = @At("TAIL"), method = "onDeath")
	private void onPlayerDeath(final DamageSource damageSource, final CallbackInfo info)
	{
		ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
		int posX = (int) (Math.floor(Math.random() * 50001) - 25000);
		int posY = 200;
		int posZ = (int) (Math.floor(Math.random() * 50001) - 25000);
		while (true)
		{
			BlockPos new_pos = new BlockPos(posX, posY, posZ);
			BlockState blockState = player.getServerWorld().getBlockState(new_pos);
			if (blockState.isOf(Blocks.AIR)) {posY -= 1;}
			else {posY += 1; break;}
		}
		BlockPos new_spawn = new BlockPos(posX, posY, posZ);
		player.setSpawnPoint(ServerWorld.OVERWORLD, new_spawn, 0.0F, true, false);
	}
}