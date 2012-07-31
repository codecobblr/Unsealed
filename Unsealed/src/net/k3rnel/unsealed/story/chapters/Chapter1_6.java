package net.k3rnel.unsealed.story.chapters;

import net.k3rnel.unsealed.Unsealed;
import net.k3rnel.unsealed.story.characters.Citizen;
import net.k3rnel.unsealed.story.characters.Kid;
import net.k3rnel.unsealed.story.characters.Lidia;
import net.k3rnel.unsealed.story.characters.Snake;
import net.k3rnel.unsealed.story.helpers.MapCharacter;

public class Chapter1_6 extends AbstractChapter {


    /**
     * Chapter One: New Girl in Town
     * @param game
     */
    public Chapter1_6(Unsealed game) {
        super(game);
        mapname="TownOne";
    }

    @Override
    public void show() {
        super.show();

        tmpChar = new Lidia(getAtlas());
        tmpChar.setPosition(815,1300);
        tmpChar.updateAnimation();
        characters.add(tmpChar);

        tmpChar = new Kid(getAtlas(),0);
        tmpChar.setPosition(822,1230);
        tmpChar.setDirection(MapCharacter.dirUp);
        characters.add(tmpChar);

        tmpChar = new Kid(getAtlas(),1);
        tmpChar.setDirection(MapCharacter.dirUp);
        tmpChar.setPosition(922,1230);
        characters.add(tmpChar);

        tmpChar = new Kid(getAtlas(),2);
        tmpChar.setPosition(728,1230);
        tmpChar.setDirection(MapCharacter.dirUp);
        characters.add(tmpChar);

        tmpChar = new Citizen(getAtlas(),0);
        tmpChar.setPosition(700,1180);
        tmpChar.setDirection(MapCharacter.dirUp);
        characters.add(tmpChar);

        tmpChar = new Citizen(getAtlas(),0);
        tmpChar.setPosition(780,1180);
        tmpChar.setDirection(MapCharacter.dirUp);
        characters.add(tmpChar);

        tmpChar = new Citizen(getAtlas(),0);
        tmpChar.setPosition(860,1180);
        tmpChar.setDirection(MapCharacter.dirUp);
        characters.add(tmpChar);

        tmpChar = new Citizen(getAtlas(),0);
        tmpChar.setPosition(940,1180);
        tmpChar.setDirection(MapCharacter.dirUp);
        characters.add(tmpChar);

        tmpChar = new Citizen(getAtlas(),0);
        tmpChar.setPosition(990,1230);
        tmpChar.setDirection(MapCharacter.dirUp);
        characters.add(tmpChar);
        tmpChar = new Citizen(getAtlas(),0);
        tmpChar.setPosition(910,1120);
        tmpChar.setDirection(MapCharacter.dirUp);
        characters.add(tmpChar);
        tmpChar = new Citizen(getAtlas(),0);
        tmpChar.setPosition(830,1120);
        tmpChar.setDirection(MapCharacter.dirUp);
        characters.add(tmpChar);
        tmpChar = new Citizen(getAtlas(),0);
        tmpChar.setPosition(750,1120);
        tmpChar.setDirection(MapCharacter.dirUp);
        characters.add(tmpChar);
        tmpChar = new Citizen(getAtlas(),0);
        tmpChar.setPosition(650,1230);
        tmpChar.setDirection(MapCharacter.dirUp);
        characters.add(tmpChar);

        tmpChar = new Citizen(getAtlas(),10);
        tmpChar.setPosition(630,900);
        tmpChar.setDirection(MapCharacter.dirUp);
        tmpChar.setWalking(true);
        characters.add(tmpChar);
        
        tmpChar = new Snake(getAtlas());
        tmpChar.setPosition(700,300);
        tmpChar.setDirection(MapCharacter.dirUp);
        tmpChar.setWalking(true);
        characters.add(tmpChar);
        
        tmpChar = new Snake(getAtlas());
        tmpChar.setPosition(740,500);
        tmpChar.setDirection(MapCharacter.dirLeft);
        tmpChar.setWalking(true);
        characters.add(tmpChar);
        
        tmpChar = new Snake(getAtlas());
        tmpChar.setPosition(500,500);
        tmpChar.setDirection(MapCharacter.dirRight);
        tmpChar.setWalking(true);
        characters.add(tmpChar);


    }
    @Override
    public void render(float delta) {
        super.render(delta);

        stage.getSpriteBatch().begin();

        //This is probably the bestest "Scene Director" ever made. 
        //Valve should totally hire me. 
        for(MapCharacter character : characters){
            if(character instanceof Lidia){
                switch(act){
                    case 0:
                        centerCamera(character);
                        dialog.setText("Townsfolk Terranova: You have to teach us how to do that!");
                        dialog.setVisible(true);
                        break;
                    case 1:
                        dialog.setText("Townsfolk Ramsey: Those are some great fighting skills. Miss Terius, please teach us, or at least teach our children");
                        break;
                    case 2:
                        dialog.setText("Townsfolk Hashbang: We owe you a great debt Miss Terius");
                        break;
                    case 3:
                        dialog.setText("Lidia: You're welcome, everyone. Now, let's begin the first lesson of Spellcasting...");
                        break;
                    case 4:
                        dialog.setText("Lidia: Everyone can be a Spellcaster if you desire it. \n" +
                        		"Now that the Pixie has been released, magic will start flowing more naturally.\n");
                        break;
                    case 5:
                        dialog.setText("Lidia: Magic is a very powerful tool, and it's important that it remains Free to use and study\n." +
                        		"That's the reason the Pixie was sealed for so long. Only a few select elders knew about the connection between Pixies and magic\n" +
                        		"They did not pass on their secrets, and in time, the use of magic was completely forgotten. ");
                        break;
                    case 6:
                        dialog.setText("Lidia: That's why the Free Spellweaver Foundation set a few basic rules to ensure that the knowledge is preserved. \n" +
                        		"The First Freedom is the Freedom to use magic. Using magic should not be forbidden. It should be encouraged!\n" +
                        		"The Second Freedom is the Freedom to study the spell. This is a requirement to weave new, more powerful spells");
                        break;
                    case 7:
                        dialog.setText("Lidia: The Third Freedom is the Freedom to teach others how to do Spells. \n" +
                        		"And last but not least, The Final Freedom is the Freedom to share the Spells you weave");
                        break;
                    case 8:
                        dialog.setText("Lidia: These are the basic rules, and I'm free to answer any questions you might have. ");
                        break;
                    case 9:
                        dialog.setVisible(false);
                        game.setScreen( new Chapter1_7( game ) );
                        break;
                }
            }
            character.act(delta);
            if(character.isVisible())
                character.draw(stage.getSpriteBatch(), 1);
        }
        stage.getSpriteBatch().end();
        
        if(dialog.isVisible()){
            hud.act(delta);
            hud.draw();
        }
    }   

  
}