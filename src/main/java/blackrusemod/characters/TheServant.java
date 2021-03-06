package blackrusemod.characters;

import java.util.ArrayList;

import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.abstracts.CustomPlayer;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.TheServantEnum;
import blackrusemod.screens.VisionScreen;

public class TheServant extends CustomPlayer {
	public static final int ENERGY_PER_TURN = 3;
	
	public static final String[] orbTextures = {
			"img/char/servant/orb/layer1.png",
			"img/char/servant/orb/layer2.png",
			"img/char/servant/orb/layer3.png",
			"img/char/servant/orb/layer4.png",
			"img/char/servant/orb/layer5.png",
			"img/char/servant/orb/layer6.png",
			"img/char/servant/orb/layer1d.png",
			"img/char/servant/orb/layer2d.png",
			"img/char/servant/orb/layer3d.png",
			"img/char/servant/orb/layer4d.png",
			"img/char/servant/orb/layer5d.png",
	};
	
	public static final float[] layerSpeeds = {
			80.0F, 40.0F, -40.0F, 20.0F, 0.0F,
			16.0F, 8.0F, -8.0F, 5.0F, 0.0F,
	};
	
	public TheServant(String name, PlayerClass setClass) {
		super(name, setClass, orbTextures, "img/char/servant/orb/vfx.png", layerSpeeds, null, null);
		
		initializeClass(BlackRuseMod.makePath(BlackRuseMod.SERVANT_MAIN), 
				BlackRuseMod.makePath(BlackRuseMod.SERVANT_SHOULDER_2),
				BlackRuseMod.makePath(BlackRuseMod.SERVANT_SHOULDER_1),
				BlackRuseMod.makePath(BlackRuseMod.SERVANT_CORPSE), 
				getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));
		BlackRuseMod.vs = new VisionScreen();
	}
	
	@Override
	public void applyEndOfTurnTriggers() {
		for (AbstractPower p : this.powers) {
			p.atEndOfTurn(true);
		}
	}

	public static ArrayList<String> getStartingDeck() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("Strike_S");
		retVal.add("Strike_S");
		retVal.add("Strike_S");
		retVal.add("Strike_S");
		retVal.add("Defend_S");
		retVal.add("Defend_S");
		retVal.add("Defend_S");
		retVal.add("Defend_S");
		retVal.add("KidneyShot");
		retVal.add("Exchange");
		return retVal;
	}
	
	public static ArrayList<String> getStartingRelics() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("Uniform");
		UnlockTracker.markRelicAsSeen("Uniform");
		return retVal;
	}
	
	public static CharSelectInfo getLoadout() {
		if (Settings.language == GameLanguage.ZHS || Settings.language == GameLanguage.ZHT) {
			return new CharSelectInfo("凛光侍从", "恶魔们的侍从。擅长杀戮与家务。 NL 随身携带着一千零一把刀刃。",
				65, 65, 0, 99, 5, TheServantEnum.THE_SERVANT, getStartingRelics(), getStartingDeck(), false);
		}
		else {
			return new CharSelectInfo("The Servant", "A servant of demons. Perfected at killing and housekeeping. NL Holds a thousand and one blades.",
				65, 65, 0, 99, 5, TheServantEnum.THE_SERVANT, getStartingRelics(), getStartingDeck(), false);
		}
	}
}