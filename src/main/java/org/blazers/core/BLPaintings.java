package org.blazers.core;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.blazers.BlazersMod;

/**
 * {@link org.blazers.BlazersMod Blazers Mod} {@link net.minecraft.world.entity.decoration.PaintingVariant Paintings}
 */
public final class BLPaintings {

    /**
     * {@link net.minecraft.world.entity.decoration.PaintingVariant Paintings} {@link DeferredRegister<net.minecraft.world.entity.decoration.PaintingVariant> Registry}
     */
    public static final DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, BlazersMod.MOD_ID);

    //#region Paintings

    public static RegistryObject<PaintingVariant> ERENBLAZE = registerPainting("erenblaze", 32);
    public static RegistryObject<PaintingVariant> SURVIVAL = registerPainting("survival", 16, 32);
    public static RegistryObject<PaintingVariant> BRUH = registerSmallPainting("bruh");
    public static RegistryObject<PaintingVariant> EBL_CHEER = registerSmallPainting("ebl_cheer");
    public static RegistryObject<PaintingVariant> EBL_FLEX = registerSmallPainting("ebl_flex");
    public static RegistryObject<PaintingVariant> EBL_SHH = registerSmallPainting("ebl_shh");
    public static RegistryObject<PaintingVariant> EBL_SHREK = registerSmallPainting("ebl_shrek");
    public static RegistryObject<PaintingVariant> EBL_WOW = registerSmallPainting("ebl_wow");
    public static RegistryObject<PaintingVariant> EBL_SMILE = registerSmallPainting("ebl_smile");
    public static RegistryObject<PaintingVariant> EBL_BAIT = registerSmallPainting("ebl_bait");
    public static RegistryObject<PaintingVariant> EBL_POG = registerSmallPainting("ebl_pog");
    public static RegistryObject<PaintingVariant> EBL_MON = registerSmallPainting("ebl_mon");
    public static RegistryObject<PaintingVariant> EBL_K = registerSmallPainting("ebl_k");
    public static RegistryObject<PaintingVariant> EBL_SHY = registerSmallPainting("ebl_shy");
    public static RegistryObject<PaintingVariant> EBL_MIA = registerSmallPainting("ebl_mia");
    public static RegistryObject<PaintingVariant> EBL_CAPE = registerSmallPainting("ebl_cape");
    public static RegistryObject<PaintingVariant> EBL_MID = registerSmallPainting("ebl_mid");
    public static RegistryObject<PaintingVariant> EBL_CHUG = registerSmallPainting("ebl_chug");
    public static RegistryObject<PaintingVariant> EBL_PERV = registerSmallPainting("ebl_perv");
    public static RegistryObject<PaintingVariant> EBL_TROLL = registerSmallPainting("ebl_troll");
    public static RegistryObject<PaintingVariant> EBL_10 = registerSmallPainting("ebl_10");
    public static RegistryObject<PaintingVariant> EBL_LURK = registerSmallPainting("ebl_lurk");
    public static RegistryObject<PaintingVariant> EBL_PALM = registerSmallPainting("ebl_palm");
    public static RegistryObject<PaintingVariant> EBL_NO = registerSmallPainting("ebl_no");
    public static RegistryObject<PaintingVariant> EBL_GRM = registerSmallPainting("ebl_grm");
    public static RegistryObject<PaintingVariant> EBL_HYPE = registerSmallPainting("ebl_hype");
    public static RegistryObject<PaintingVariant> EBL_RBT = registerSmallPainting("ebl_rbt");
    public static RegistryObject<PaintingVariant> EBL_200 = registerSmallPainting("ebl_200");
    public static RegistryObject<PaintingVariant> EBL_BAN = registerSmallPainting("ebl_ban");
    public static RegistryObject<PaintingVariant> EBL_HMM = registerSmallPainting("ebl_hmm");
    public static RegistryObject<PaintingVariant> EBL_LOVE = registerSmallPainting("ebl_love");
    public static RegistryObject<PaintingVariant> EBL_PNO = registerSmallPainting("ebl_pno");
    public static RegistryObject<PaintingVariant> EBL_STONKS = registerSmallPainting("ebl_stonks");
    public static RegistryObject<PaintingVariant> EBL_WTF = registerSmallPainting("ebl_wtf");
    public static RegistryObject<PaintingVariant> EBL_GAO = registerSmallPainting("ebl_gao");
    public static RegistryObject<PaintingVariant> EBL_NTL = registerSmallPainting("ebl_ntl");
    public static RegistryObject<PaintingVariant> EBL_CLIP = registerSmallPainting("ebl_clip");
    public static RegistryObject<PaintingVariant> EBL_COP = registerSmallPainting("ebl_cop");
    public static RegistryObject<PaintingVariant> EBL_CRINGE = registerSmallPainting("ebl_cringe");
    public static RegistryObject<PaintingVariant> EBL_CHAD = registerSmallPainting("ebl_chad");
    public static RegistryObject<PaintingVariant> EBL_F = registerSmallPainting("ebl_f");
    public static RegistryObject<PaintingVariant> EBL_TVB = registerSmallPainting("ebl_tvb");
    public static RegistryObject<PaintingVariant> EBL_420 = registerSmallPainting("ebl_420");
    public static RegistryObject<PaintingVariant> EBL_TRIGGERED = registerSmallPainting("ebl_triggered");
    public static RegistryObject<PaintingVariant> EBL_LOL = registerSmallPainting("ebl_lol");
    public static RegistryObject<PaintingVariant> EBL_FEAR = registerSmallPainting("ebl_fear");
    public static RegistryObject<PaintingVariant> EBL_PTT = registerSmallPainting("ebl_ptt");
    public static RegistryObject<PaintingVariant> EBL_RAGE = registerSmallPainting("ebl_rage");
    public static RegistryObject<PaintingVariant> EBL_ZZZ = registerSmallPainting("ebl_zzz");
    public static RegistryObject<PaintingVariant> EBL_STOMP = registerSmallPainting("ebl_stomp");
    public static RegistryObject<PaintingVariant> EBL_UNC = registerSmallPainting("ebl_unc");
    public static RegistryObject<PaintingVariant> EBL_GG = registerSmallPainting("ebl_gg");
    public static RegistryObject<PaintingVariant> EBL_DERP = registerSmallPainting("ebl_derp");
    public static RegistryObject<PaintingVariant> EBL_FOOD = registerSmallPainting("ebl_food");
    public static RegistryObject<PaintingVariant> EBL_TH = registerSmallPainting("ebl_th");
    public static RegistryObject<PaintingVariant> EBL_SAD = registerSmallPainting("ebl_sad");
    public static RegistryObject<PaintingVariant> EBL_GA = registerSmallPainting("ebl_ga");
    public static RegistryObject<PaintingVariant> EBL_CLOWN = registerSmallPainting("ebl_clown");
    public static RegistryObject<PaintingVariant> EBL_EVIL = registerSmallPainting("ebl_evil");
    public static RegistryObject<PaintingVariant> EBL_PRIDE = registerSmallPainting("ebl_pride");
    public static RegistryObject<PaintingVariant> EBL_TITAN = registerSmallPainting("ebl_titan");
    public static RegistryObject<PaintingVariant> EBL_L = registerSmallPainting("ebl_l");
    public static RegistryObject<PaintingVariant> EBL_KILL = registerSmallPainting("ebl_kill");
    public static RegistryObject<PaintingVariant> EBL_HI = registerSmallPainting("ebl_hi");
    public static RegistryObject<PaintingVariant> EBL_GIFT = registerSmallPainting("ebl_gift");
    public static RegistryObject<PaintingVariant> EBL_KEK = registerPainting("ebl_kek", 32);
    public static RegistryObject<PaintingVariant> EBL_INSIDE = registerPainting("ebl_inside", 32);
    public static RegistryObject<PaintingVariant> EBL_GUN = registerPainting("ebl_gun", 32);
    public static RegistryObject<PaintingVariant> EBL_BONK = registerPainting("ebl_bonk", 32);
    public static RegistryObject<PaintingVariant> EBL_IH = registerPainting("ebl_ih", 32);
    public static RegistryObject<PaintingVariant> EBL_EM = registerPainting("ebl_em", 48);
    public static RegistryObject<PaintingVariant> EBL_FURRY = registerPainting("ebl_furry", 48);
    public static RegistryObject<PaintingVariant> EBL_UWU = registerPainting("ebl_uwu", 48);
    public static RegistryObject<PaintingVariant> EBL_SCREAM = registerPainting("ebl_scream", 48);
    public static RegistryObject<PaintingVariant> EBL_DP = registerPainting("ebl_dp", 48);

    //#endregion

    /**
     * Register a {@link PaintingVariant painting} sized 16x16
     *
     * @param name {@link String Painting Name}
     * @return {@link RegistryObject<PaintingVariant> Registered Painting}
     */
    private static RegistryObject<PaintingVariant> registerSmallPainting(final String name) {
        return registerPainting(name, 16);
    }

    /**
     * Register a {@link PaintingVariant painting} with the same
     * {@link Integer Width} and {@link Integer Height}
     *
     * @param name {@link String Painting Name}
     * @param size {@link Integer Painting Size}
     * @return {@link RegistryObject<PaintingVariant> Registered Painting}
     */
    private static RegistryObject<PaintingVariant> registerPainting(final String name, final int size) {
        return registerPainting(name, size, size);
    }

    /**
     * Register a {@link PaintingVariant painting}
     *
     * @param name {@link String Painting Name}
     * @param width {@link Integer Painting Width}
     * @param height {@link Integer Painting Height}
     * @return {@link RegistryObject<PaintingVariant> Registered Painting}
     */
    private static RegistryObject<PaintingVariant> registerPainting(final String name, final int width, final int height) {
        return PAINTINGS.register(name, () -> new PaintingVariant(width, height));
    }

    /**
     * Register the {@link BlazersMod Blazers Mod} {@link PaintingVariant Paintings}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(IEventBus eventBus) {
        PAINTINGS.register(eventBus);
    }

}