package com.example.mdb_spring_boot.util;

import com.example.mdb_spring_boot.model.Chest;
import com.example.mdb_spring_boot.model.Skin;
import com.example.mdb_spring_boot.model.UserSkin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

public class DrawingMachine {
    private double totalWeights;
    private final Random random;

    public DrawingMachine(){
        this.totalWeights = 0.0;
        this.random = new Random();
    }

    public UserSkin getRandomSkin(Chest chest){
        this.getTotalWeights(chest.getSkins());

        double randomNumber = random.nextDouble() * totalWeights;

        UserSkin randomSkin = null;

        double sum = 0.0;
        for (Skin skin : chest.getSkins()){
            sum += skin.getOdds();
            if (randomNumber < sum) {
                double wear = this.random.nextDouble();
                int pattern = this.random.nextInt(999) + 1;
                double price = BigDecimal.valueOf(skin.getBasePrice() / Math.max(wear, 0.05) + pattern / 10.0)
                        .setScale(2, RoundingMode.HALF_UP).doubleValue();

                randomSkin = new UserSkin(
                        skin.getName(),
                        skin.getType(),
                        wear,
                        pattern,
                        price,
                        skin.getId()
                );
                break;
            }
        }

        return randomSkin;
    }

    private void getTotalWeights(List<Skin> skins){
        this.totalWeights = 0.0;
        for (Skin skin : skins)
            totalWeights += skin.getOdds();
    }
}
