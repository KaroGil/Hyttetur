package inf112.skeleton.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class Power {
    private String name;
    private String description;
    private int level;
    private int rarity;
    private Image picture;


    public Power(String name, String description, int level, Image picture, int rarity) {
        this.name = name;
        this.description = description;
        this.level = level;
        this.picture = picture;
        this.rarity = rarity;
    }


    /**
     * Making default powers / power ups
     */

    public static Power ATTACK_SPEED(int level) {
        String name = "Syringe";
        String desc = "Increases attack speed of your regular fire";
        Image image = new Image(new Texture("assets/hud/attack_speed_power.png"));
        int rarity = 0;

        return new Power(name, desc, level, image,rarity);
    }

    public static Power ATTACK_DAMAGE(int level) {
        String name = "Shattering";
        String desc = "Slightly increases all damage";
        Image image = new Image(new Texture("assets/hud/attack_damage_power.png"));
        int rarity = 0;

        return new Power(name, desc, level, image, rarity);
    }

    public static Power FREEZER_RACE(int level) {
        String name = "Freezer Race";
        Image image = new Image(new Texture("assets/hud/freezer_race_power.png"));
        String desc = "";
        int rarity = 1;
        if (level == 1) {
            desc = "Shoots out small cocktails periodically that creates small puddles that slow enemies";
        }
        if (level == 2) {
            desc = "Shoots freezer bottles more frequently";
        }
        if (level == 3) {
            desc = "Puddles last longer";
        }
        if (level == 4) {
            desc = "Shoots freezer bottles more frequently";
        }
        if (level == 5) {
            desc = "Deals more damage";
        }
        if (level == 6) {
            desc = "Shoots freezer bottles more frequently";
        }


        return new Power(name, desc, level, image, rarity);
    }

    public static Power SHOTGUN(int level) {
        String name = "Shotgun";
        Image image = new Image(new Texture("assets/hud/shotgun_power.png"));
        String desc = "";
        int rarity = 1;
        if (level == 1) {
            desc = "Switches out your beer bottles for 3 beer cans that do less damage";
        }
        if (level == 2) {
            desc = "Deals more damage";
        }
        if (level == 3) {
            desc = "+ 1 beer can";
        }
        if (level == 4) {
            desc = "Deals more damage";
        }
        if (level == 5) {
            desc = "+ 1 beer can";
        }

        return new Power(name, desc, level, image, rarity);
    }

    public static Power MOVEMENT_SPEED(int level) {
        String name = "Rainbow Shoes";
        String desc = "Increases movement speed";
        Image image = new Image(new Texture("assets/hud/movement_speed_power.png"));
        int rarity = 0;

        return new Power(name, desc, level, image, rarity);
    }

    public static Power ACIDIC_CIDER(int level) {
        String name = "Acidic Cider";
        Image image = new Image(new Texture("assets/hud/acidic_cider_power.png"));
        String desc = "";
        int rarity = 1;
        if (level == 1) {
            desc = "Occasionally shoots out a poisonous cider where you're aiming";
        }
        if (level == 2) {
            desc = "Poison lasts longer";
        }
        if (level == 3) {
            desc = "+ 1 cider can";
        }
        if (level == 4) {
            desc = "Shoots cider cans more frequently";
        }
        if (level == 5) {
            desc = "+ 1 cider can";
        }

        return new Power(name, desc, level, image, rarity);
    }

    public static Power PRESSURE_CHAMPAGNE(int level) {
        String name = "Pressure Champagne";
        Image image = new Image(new Texture("assets/hud/champagne_power.png"));
        String desc = "";
        int rarity = 2;
        if (level == 1) {
            desc = "Shoots a high speed cork \n       that has a 10% chance to critically strike";
        }
        if (level == 2) {
            desc = "Deals more damage";
        }
        if (level == 3) {
            desc = "Critical chance increased to 20%";
        }
        if (level == 4) {
            desc = "Shoots corks more frequently";
        }
        if (level == 5) {
            desc = "Deals more damage";
        }

        return new Power(name, desc, level, image, rarity);
    }


    /**
     * gets the name of the power
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * gets the description of the power
     * @return description
     */
    public String getDescription() {
        return description;
    }


    /**
     * gets the level of the power
     * @return level
     */
    public int getLevel() {
        return level;
    }


    /**
     * gets the rarity of the power
     * @return rarity
     */
    public int getRarity() {
        return rarity;
    }


    /**
     * gets the picture corresponding to the power
     * @return picture
     */
    public Image getPicture() {
        return picture;
    }
}
