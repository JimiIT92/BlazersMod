package org.blazers.entity.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.EntityRenderState;

/**
 * The {@link EntityRenderState Spear Entity Render State}
 */
@Environment(EnvType.CLIENT)
public class SpearEntityRenderState extends EntityRenderState {
    /**
     * The {@link Float Spear Pitch}
     */
    public float pitch;
    /**
     * The {@link Float Spear Yaw}
     */
    public float yaw;
}