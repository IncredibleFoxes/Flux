package szewek.flux.tile;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.ForgeConfigSpec;
import szewek.fl.util.spatial.SpatialWalker;

abstract class BlockInteractingTile extends PoweredTile {
	protected final SpatialWalker walker;
	protected boolean disabled;

	public BlockInteractingTile(TileEntityType teType, SpatialWalker walker, ForgeConfigSpec.IntValue energyUse) {
		super(teType, energyUse);
		this.walker = walker;
	}

	@Override
	public void read(BlockState blockState, CompoundNBT compound) {
		super.read(blockState, compound);
		walker.read(compound);
		disabled = compound.getBoolean("Disabled");
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		walker.write(compound);
		compound.putBoolean("Disabled", disabled);
		return compound;
	}
}
