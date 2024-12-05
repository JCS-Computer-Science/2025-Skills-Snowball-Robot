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
import org.firstinspires.ftc.teamcode.systems.Intake;
import org.firstinspires.ftc.teamcode.systems.SlidingArm;
import org.firstinspires.ftc.teamcode.systems.SwingingArm;

@Autonomous(name="Two Block")
public class TwoBlock extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-30, -61.5, Math.toRadians(0)));
        ExampleSystem exampleSystem = new ExampleSystem(hardwareMap);
        SwingingArm swingingArm = new SwingingArm(hardwareMap);
        SlidingArm slidingArm = new SlidingArm(hardwareMap);
        Elevator elevator = new Elevator(hardwareMap);
        Bucket bucket = new Bucket(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        waitForStart();
        Actions.runBlocking(drive.actionBuilder(drive.pose)
                //elevator up arms out
                .stopAndAdd(swingingArm.setPosition(-1160, false))
                .stopAndAdd(slidingArm.setPosition(700, false))
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.TOP.ticks,false))
                .strafeTo(new Vector2d(-50, -60))
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.TOP.ticks))
                //bucket dump then retract
                .stopAndAdd(bucket.setServo(0))
                .waitSeconds(1)
                .stopAndAdd(bucket.setServo(0.58))
                //elevator down
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.BOTTOM.ticks,false))
                .stopAndAdd(swingingArm.setPosition(-1160))
                .stopAndAdd(slidingArm.setPosition(700))
                .strafeToLinearHeading( new Vector2d(-49, -50), Math.toRadians(90))
                //intake and forward
                .stopAndAdd(intake.setServo(0))
                .strafeTo(new Vector2d(-50, -45))
                .waitSeconds(1)
                .stopAndAdd(intake.setServo(0.5))
                //go to bucket
                .stopAndAdd(swingingArm.setPosition(-10))
                .stopAndAdd(slidingArm.setPosition(30))
                .strafeToLinearHeading( new Vector2d(-54, -56.5), Math.toRadians(45))
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.BOTTOM.ticks))
                .stopAndAdd(intake.setServo(1))
                .waitSeconds(2)
                .stopAndAdd(intake.setServo(0.5))
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.TOP.ticks))
                .stopAndAdd(slidingArm.setPosition(700,false))
                .stopAndAdd(swingingArm.setPosition(-1160,false))
                .stopAndAdd(bucket.setServo(0))
                .waitSeconds(1)
                .stopAndAdd(bucket.setServo(0.58))
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.BOTTOM.ticks, false))
                .strafeToLinearHeading( new Vector2d(-58, -50), Math.toRadians(90))
                //intake and forward
                .stopAndAdd(intake.setServo(0))
                .strafeTo(new Vector2d(-58, -45))
                .waitSeconds(1)
                .stopAndAdd(intake.setServo(0.5))
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.BOTTOM.ticks))
                .stopAndAdd(slidingArm.setPosition(30,false))
                .stopAndAdd(swingingArm.setPosition(-10))
                .stopAndAdd(slidingArm.setPosition(30))
                .waitSeconds(0.5)
                .stopAndAdd(intake.setServo(1))
                .waitSeconds(1)
                .stopAndAdd(intake.setServo(0.5))
                .strafeToLinearHeading( new Vector2d(-54, -56.5), Math.toRadians(45))
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.TOP.ticks))
                .stopAndAdd(bucket.setServo(0))
                .waitSeconds(1)
                .stopAndAdd(bucket.setServo(0.58))
                //park the robot
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.BOTTOM.ticks, false))
                .strafeTo( new Vector2d(45, -55))
                .build());
    }
}
