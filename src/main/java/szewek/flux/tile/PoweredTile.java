package szewek.flux.tile;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import szewek.flux.tile.part.MachineEnergy;

import javax.annotation.Nullable;

public abstract class PoweredTile extends TileEntity implements ITickableTileEntity {
	protected final MachineEnergy energy = new MachineEnergy(500_000);
	protected final ForgeConfigSpec.IntValue energyUse;

	public PoweredTile(TileEntityType tileEntityTypeIn, ForgeConfigSpec.IntValue energyUse) {
		super(tileEntityTypeIn);
		this.energyUse = energyUse;
	}

	@Override
	public void read(BlockState blockState, CompoundNBT compound) {
		super.read(blockState, compound);
		energy.readNBT(compound);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		energy.writeNBT(compound);
		return compound;
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
		if (!removed && CapabilityEnergy.ENERGY == cap) {
			return energy.lazyCast();
		} else {
			return super.getCapability(cap, side);
		}
	}

	@Override
	public void remove() {
		super.remove();
		energy.invalidate();
	}
}
