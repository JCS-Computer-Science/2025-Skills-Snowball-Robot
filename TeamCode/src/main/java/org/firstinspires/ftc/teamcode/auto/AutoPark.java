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
import org.firstinspires.ftc.teamcode.systems.Claw;
import org.firstinspires.ftc.teamcode.systems.Elevator;
import org.firstinspires.ftc.teamcode.systems.ExampleSystem;
import org.firstinspires.ftc.teamcode.systems.Intake;
import org.firstinspires.ftc.teamcode.systems.SlidingArm;
import org.firstinspires.ftc.teamcode.systems.SwingingArm;

@Autonomous(name="Auto with Claw and Park")
public class AutoPark extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Vector2d bucketPos = new Vector2d(-56.5, -56.5);
        Double bucketHeading = Math.toRadians(45);
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-38, -61.5, Math.toRadians(0)));
        ExampleSystem exampleSystem = new ExampleSystem(hardwareMap);
        SwingingArm swingingArm = new SwingingArm(hardwareMap);
        SlidingArm slidingArm = new SlidingArm(hardwareMap);
        Elevator elevator = new Elevator(hardwareMap);
        Bucket bucket = new Bucket(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        Claw claw = new Claw(hardwareMap);
        waitForStart();
        Actions.runBlocking(drive.actionBuilder(drive.pose)
                .stopAndAdd(swingingArm.setPosition(-1070))
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.TOP.ticks))
                .strafeToLinearHeading(bucketPos,bucketHeading)
                .stopAndAdd(bucket.setServo(0))
                .stopAndAdd(bucket.setServo(0))
                .waitSeconds(2)
                .stopAndAdd(bucket.setServo(0.58))
                //elevator down and move to block 2
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.BOTTOM.ticks))
                .strafeToLinearHeading(new Vector2d(-53, -42.5),Math.toRadians(90))
                .waitSeconds(0.5)
                .stopAndAdd(claw.setServo(0.3))
                .waitSeconds(0.5)
                .stopAndAdd(swingingArm.setPosition(-30))
                .strafeToLinearHeading(bucketPos,bucketHeading)
                .stopAndAdd(claw.setServo(1))
                .waitSeconds(0.5)
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.TOP.ticks))
                .waitSeconds(3)
                .stopAndAdd(bucket.setServo(0))
                .waitSeconds(1)
                .stopAndAdd(bucket.setServo(0.58))
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.BOTTOM.ticks))
                .stopAndAdd(swingingArm.setPosition(-1070))
                .strafeToLinearHeading(new Vector2d(-60, -42.5),Math.toRadians(90))
                .waitSeconds(0.5)
                .stopAndAdd(claw.setServo(0.3))
                .waitSeconds(0.5)
                .stopAndAdd(swingingArm.setPosition(-30))
                .strafeToLinearHeading(bucketPos,bucketHeading)
                .stopAndAdd(claw.setServo(1))
                .waitSeconds(0.5)
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.TOP.ticks))
                .waitSeconds(3)
                .stopAndAdd(bucket.setServo(0))
                .waitSeconds(1)
                .stopAndAdd(bucket.setServo(0.58))
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.BOTTOM.ticks))
                .stopAndAdd(swingingArm.setPosition(-1160))
                .strafeToLinearHeading(new Vector2d(-46, -28),Math.toRadians(180))
                .stopAndAdd(intake.setServo(0))
                .stopAndAdd(slidingArm.setPosition(700, true))
                .waitSeconds(1.3)
                .stopAndAdd(intake.setServo(0.5))
                .stopAndAdd(swingingArm.setPosition(-10))
                .stopAndAdd(slidingArm.setPosition(30))
                .strafeToLinearHeading(bucketPos,bucketHeading)
                .stopAndAdd(intake.setServo(1))
                .waitSeconds(1)
                .stopAndAdd(intake.setServo(0.5))
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.TOP.ticks))
                .waitSeconds(3)
                .stopAndAdd(bucket.setServo(0))
                .waitSeconds(1)
                .stopAndAdd(bucket.setServo(0.58))
                .strafeToLinearHeading(new Vector2d(45, -55),Math.toRadians(0))
                .build());
    }
}
