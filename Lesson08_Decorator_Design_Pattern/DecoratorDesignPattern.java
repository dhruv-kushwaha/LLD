package Lesson08_Decorator_Design_Pattern;

interface Character {
    String getAbilities();
}

class Mario implements Character {
    @Override
    public String getAbilities() {
        return "Mario";
    }
}

abstract class CharacterDecorator implements Character {
    protected Character character;

    public CharacterDecorator(Character character) {
        this.character = character;
    }
}

class HeightUpDecorator extends CharacterDecorator {

    public HeightUpDecorator(Character character) {
        super(character);
    }

    @Override
    public String getAbilities() {
        return character.getAbilities() + " with HeightUp";
    }
}

class StarPowerDecorator extends CharacterDecorator {

    public StarPowerDecorator(Character character) {
        super(character);
    }

    @Override
    public String getAbilities() {
        return character.getAbilities() + " with StarPower";
    }
}

class GunPowerDecorator extends CharacterDecorator {

    public GunPowerDecorator(Character character) {
        super(character);
    }

    @Override
    public String getAbilities() {
        return character.getAbilities() + " with GunPower";
    }
}

public class DecoratorDesignPattern {
    public static void main(String[] args) {
        var mario = new Mario();
        var marioWithHeightUp = new HeightUpDecorator(mario);
        var marioWithHeightUpAndGunPower = new GunPowerDecorator(marioWithHeightUp);

        System.out.println(marioWithHeightUpAndGunPower.getAbilities());
    }
}
