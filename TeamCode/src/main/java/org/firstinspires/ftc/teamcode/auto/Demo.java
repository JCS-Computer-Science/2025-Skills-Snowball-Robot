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
                .lineToX(20)
                .build()
        );
    }
}
