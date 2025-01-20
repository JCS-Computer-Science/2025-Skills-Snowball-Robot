package com.example.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


public class MeepMeep {
    public static void main(String[] args) {

        Vector2d bucketPos = new Vector2d(-58.5, -57.5);
        Double bucketHeading = Math.toRadians(45);

        com.noahbres.meepmeep.MeepMeep meepMeep = new com.noahbres.meepmeep.MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(18,18)
                .build();
        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-38, -61.5, Math.toRadians(0)))
                .strafeToLinearHeading(bucketPos,bucketHeading)
                .strafeToLinearHeading(new Vector2d(-53, -41),Math.toRadians(90))
                .strafeToLinearHeading(bucketPos,bucketHeading)
                .strafeToLinearHeading(new Vector2d(-60, -41),Math.toRadians(90))
                .strafeToLinearHeading(bucketPos,bucketHeading)
                .strafeToLinearHeading(new Vector2d(-46, -28),Math.toRadians(180))
                .strafeToLinearHeading(bucketPos,bucketHeading)
                .strafeToLinearHeading(new Vector2d(45, -55),Math.toRadians(0))
                .build());
                meepMeep.setBackground(com.noahbres.meepmeep.MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_LIGHT)
                .setDarkMode(false)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}