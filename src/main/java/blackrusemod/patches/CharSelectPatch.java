package blackrusemod.patches;

import java.lang.reflect.Field;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.helpers.SaveHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;

@SpirePatch(cls="com.megacrit.cardcrawl.screens.charSelect.CharacterOption", method="updateHitbox")
public class CharSelectPatch {

    @SpireInsertPatch(rloc=64)
    public static void Insert(Object __obj_instance) {
        Field maxAscensionLevel;
        try {
            Prefs pref = null;
            AbstractPlayer.PlayerClass chosenClass = CardCrawlGame.chosenCharacter;
            if (chosenClass.toString() == "THE_SERVANT") {
                pref = SaveHelper.getPrefs("THE_SERVANT");
                CardCrawlGame.sound.playV("ATTACK_DAGGER_6", 1.75f);
                CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);

                CharacterOption obj = (CharacterOption) __obj_instance;
                maxAscensionLevel = obj.getClass().getDeclaredField("maxAscensionLevel");
                maxAscensionLevel.setAccessible(true);

                CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel = pref.getInteger("ASCENSION_LEVEL", 1);
                if (20 < CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel) {
                    CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel = 20;
                }
                maxAscensionLevel.set(obj, pref.getInteger("ASCENSION_LEVEL", 1));
                if (20 < maxAscensionLevel.getInt(obj)) {
                    maxAscensionLevel.set(obj, 20);
                }
                int ascensionLevel = CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel;
                if (ascensionLevel > maxAscensionLevel.getInt(obj)) {
                    ascensionLevel = maxAscensionLevel.getInt(obj);
                }
                if (ascensionLevel > 0) {
                    CardCrawlGame.mainMenuScreen.charSelectScreen.ascLevelInfoString = CharacterSelectScreen.A_TEXT[ascensionLevel - 1];
                }
                else {
                    CardCrawlGame.mainMenuScreen.charSelectScreen.ascLevelInfoString = "";
                }
            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
