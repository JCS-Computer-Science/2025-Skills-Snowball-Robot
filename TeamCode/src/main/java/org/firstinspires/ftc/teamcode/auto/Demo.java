package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.systems.ExampleSystem;

@Autonomous(name="Demo Auto")
public class Demo extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0,0,0));
        ExampleSystem exampleSystem = new ExampleSystem(hardwareMap);

        waitForStart();

//        Actions.runBlocking(
//                drive.actionBuilder(drive.pose) //see https://rr.brott.dev/docs/v1-0/builder-ref/ for available actions
//                        .splineTo(new Vector2d(10, 0), Math.PI / 2)
//                        .build());
        Actions.runBlocking(drive.actionBuilder(drive.pose)
                .lineToX(30)
                .stopAndAdd(exampleSystem.setServo(1))
                .waitSeconds(1)
                .stopAndAdd(exampleSystem.setServo(0))
                .lineToX(0)
                .build()
        );
    }
}
