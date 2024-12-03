package org.firstinspires.ftc.teamcode.auto;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.VelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.systems.Bucket;
import org.firstinspires.ftc.teamcode.systems.Elevator;
import org.firstinspires.ftc.teamcode.systems.ExampleSystem;

@Autonomous(name="Two Block")
public class TwoBlock extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-30, -61.5, Math.toRadians(0)));
        ExampleSystem exampleSystem = new ExampleSystem(hardwareMap);
        Elevator elevator = new Elevator(hardwareMap);
        Bucket bucket = new Bucket(hardwareMap);
        waitForStart();

        Actions.runBlocking(drive.actionBuilder(drive.pose)
                //elevator up arms out
                        .stopAndAdd()
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.TOP.ticks,false))
                .strafeTo(new Vector2d(-50, -60))
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.TOP.ticks))
                //bucket dump then retract
                //elevator down
                .strafeToLinearHeading( new Vector2d(-50, -50), Math.toRadians(90))
                //intake and forward
                .strafeTo(new Vector2d(-50, -45))
                //go to bucket
                .strafeToLinearHeading( new Vector2d(-56.5, -56.5), Math.toRadians(45))
                .strafeToLinearHeading( new Vector2d(-58, -50), Math.toRadians(90))
                //intake and forward
                .strafeTo(new Vector2d(-58, -45))
                .strafeToLinearHeading( new Vector2d(-56.5, -56.5), Math.toRadians(45))
                //park the robot
                .strafeTo( new Vector2d(45, -55))
                .build());
    }
}
