package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.BacklashAction;
import blackrusemod.patches.AbstractCardEnum;

public class SpecialFormula extends CustomCard {
	public static final String ID = "SpecialFormula";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int HEAL = 5;
	
	public SpecialFormula() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.SPECIAL_FORMULA), COST, DESCRIPTION,
				AbstractCard.CardType.SKILL, AbstractCardEnum.SILVER,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = HEAL;
		this.exhaust = true;
		this.tags.add(AbstractCard.CardTags.HEALING);
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
		AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new BacklashAction(1));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new SpecialFormula();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(3);
		}
	}
}