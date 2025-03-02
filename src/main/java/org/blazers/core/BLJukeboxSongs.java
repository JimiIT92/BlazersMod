package org.blazers.core;

import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.RegistryKey;
import org.blazers.BlazersMod;
import org.hendrix.helper.RegistryKeyHelper;

/**
 * {@link BlazersMod Blazers Mod} {@link JukeboxSong Jukebox Songs}
 */
public final class BLJukeboxSongs {

    //#region Jukebox Songs

    public static final RegistryKey<JukeboxSong> SURVIVAL = RegistryKeyHelper.jukeboxSong("survival");
    public static final RegistryKey<JukeboxSong> ENDERMAN_VS_BLAZE = RegistryKeyHelper.jukeboxSong("enderman_vs_blaze");

    //#endregion

}