package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.VelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.systems.Bucket;
import org.firstinspires.ftc.teamcode.systems.Elevator;
import org.firstinspires.ftc.teamcode.systems.ExampleSystem;

@Autonomous(name="Demo Auto")
public class Demo extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0,0,0));
        ExampleSystem exampleSystem = new ExampleSystem(hardwareMap);
        Elevator elevator = new Elevator(hardwareMap);
        Bucket bucket = new Bucket(hardwareMap);
        waitForStart();

//
        Actions.runBlocking(drive.actionBuilder(drive.pose)
                .stopAndAdd(elevator.setPosition(-11200))
                .lineToX(-20)
                .stopAndAdd(bucket.setServo(0))
                .waitSeconds(2)
                .stopAndAdd(bucket.setServo(0.7))
                .waitSeconds(1)
                .lineToX(-10)
                .stopAndAdd(elevator.setPosition(0))
                .lineToX(40)
                .lineToX(85)
                .build()
        );
    }
}
