package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ThrowKnivesAction;
import blackrusemod.patches.AbstractCardEnum;

public class KidneyShot extends CustomCard {
	public static final String ID = "KidneyShot";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 4;
	private static final int THROW = 2;

	public KidneyShot() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.KIDNEY_SHOT), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.BASIC,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = THROW;
		this.tags.add(BaseModCardTags.GREMLIN_MATCH);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		for (int i = 0; i < this.magicNumber; i++)
			AbstractDungeon.actionManager.addToBottom(new ThrowKnivesAction(p, m, new DamageInfo(p, this.baseDamage, this.damageTypeForTurn), "Weakened"));
	}

	public AbstractCard makeCopy() {
		return new KidneyShot();
	}
	
	public void applyPowers() {
		this.baseDamage = ATTACK_DMG;
		if (AbstractDungeon.player.hasPower("SilverBladesPower")) 
			this.baseDamage += AbstractDungeon.player.getPower("SilverBladesPower").amount;
		super.applyPowers();
		if (AbstractDungeon.player.hasPower("SilverBladesPower"))
			this.isDamageModified = true;
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}