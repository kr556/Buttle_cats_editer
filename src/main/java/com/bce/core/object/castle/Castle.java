package com.bce.core.object.castle;

import com.bce.core.object.Assign;
import com.bce.core.io.ImageReader;
import com.bce.core.io.JsonReader;
import com.bce.core.object.Character;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Castle {
    private BufferedImage img;
    /**
     * 体力。-1なら無限
     */
    private int hp;
    private String name;
    private Assign assign;
    private CastleCharcterField character;

    public Castle(Assign assign, String castle_name) {
        JsonReader jr;
        try {
            jr = new JsonReader("obj/castles/" + castle_name + "/" + castle_name + ".json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        name = castle_name;
        hp = jr.readTree("hp").asInt();
        img = ImageReader.read("obj/castles/" + castle_name + "/" + castle_name + ".png");
        this.assign = assign;
    }

    public BufferedImage getImage() {
        return img;
    }

    public boolean hit(int damage) {
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
        }
        return hp != 0;
    }

    @Override
    public String toString() {
        return "castle." + name + " |> [ hp: " + hp + ']';
    }

    public interface CastleCharcterField {
        Character get();
    }
}
