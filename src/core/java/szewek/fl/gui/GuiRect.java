package szewek.fl.gui;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Rectangle with X and Y values
 */
@OnlyIn(Dist.CLIENT)
public class GuiRect {
	public final int x1, y1, x2, y2;

	public GuiRect(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public GuiRect grow(int i) {
		return new GuiRect(x1 - i, y1 - i, x2 + i, y2 + i);
	}
}
