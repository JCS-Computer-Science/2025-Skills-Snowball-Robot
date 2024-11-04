package com.example.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


public class MeepMeep {
    public static void main(String[] args) {

        com.noahbres.meepmeep.MeepMeep meepMeep = new com.noahbres.meepmeep.MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(18,18)
                .build();
        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-30, -61.5, Math.toRadians(90)))
                .strafeToLinearHeading(new Vector2d(-58,-58),Math.toRadians(45))
                .waitSeconds(4)
                .strafeToLinearHeading(new Vector2d(-34,-36),Math.toRadians(180))
                .strafeToLinearHeading(new Vector2d(-35,-28.5),Math.toRadians(180))
                .waitSeconds(3)
                .strafeToLinearHeading(new Vector2d(-55,-55),Math.toRadians(45))
                .waitSeconds(4)
                .strafeToLinearHeading(new Vector2d(-44,-36),Math.toRadians(180))
                .strafeToLinearHeading(new Vector2d(-45,-28.5),Math.toRadians(180))
                .waitSeconds(3)
                .strafeToLinearHeading(new Vector2d(-55,-55),Math.toRadians(45))
                .waitSeconds(4)
                .strafeToLinearHeading(new Vector2d(-55,-28.5),Math.toRadians(180))
                .waitSeconds(3)
                .strafeToLinearHeading(new Vector2d(-55,-55),Math.toRadians(45))
                .build());
        meepMeep.setBackground(com.noahbres.meepmeep.MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_LIGHT)
                .setDarkMode(false)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}