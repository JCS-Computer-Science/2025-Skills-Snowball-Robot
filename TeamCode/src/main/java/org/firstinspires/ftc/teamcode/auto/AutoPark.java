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

@Autonomous(name="Auto with Park")
public class AutoPark extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Vector2d bucketPos = new Vector2d(-56.4, -55.4);
        Double bucketHeading = Math.toRadians(45);
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-38, -61.5, Math.toRadians(0)));
        ExampleSystem exampleSystem = new ExampleSystem(hardwareMap);
        SwingingArm swingingArm = new SwingingArm(hardwareMap);
        SlidingArm slidingArm = new SlidingArm(hardwareMap);
        Elevator elevator = new Elevator(hardwareMap);
        Bucket bucket = new Bucket(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        waitForStart();
        Actions.runBlocking(drive.actionBuilder(drive.pose)
                //start elevator up swinging down
                .stopAndAdd(swingingArm.setPosition(-1160, false))
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.TOP.ticks,false))
                //got to score position elevator up and score block 1
                .strafeToLinearHeading(new Vector2d(-58.7, -58),bucketHeading)
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.TOP.ticks))
                .stopAndAdd(bucket.setServo(0))
                .waitSeconds(1.5)
                .stopAndAdd(bucket.setServo(0.58))
                //elevator down and move to block 2
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.BOTTOM.ticks,false))
                .strafeToLinearHeading(new Vector2d(-29, -33),Math.toRadians(163))
                //grab block 2
                .stopAndAdd(intake.setServo(0))
                .stopAndAdd(slidingArm.setPosition(700, true))
                .waitSeconds(0.8)
                .stopAndAdd(intake.setServo(0.5))
                //reset arms and put block in robot bucket
                .stopAndAdd(swingingArm.setPosition(-10, false))
                .stopAndAdd(slidingArm.setPosition(30, false))
                .strafeToLinearHeading(bucketPos,bucketHeading)
                .stopAndAdd(intake.setServo(1))
                .waitSeconds(0.8)
                .stopAndAdd(intake.setServo(0.5))
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.TOP.ticks,false))
                .waitSeconds(2.5)
                .stopAndAdd(bucket.setServo(0))
                .waitSeconds(1)
                .stopAndAdd(bucket.setServo(0.58))
                .stopAndAdd(swingingArm.setPosition(-1160, false))
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.BOTTOM.ticks, false))
                .strafeToLinearHeading(new Vector2d(-32, -27),Math.toRadians(180))
                .stopAndAdd(intake.setServo(0))
                .stopAndAdd(slidingArm.setPosition(700, true))
                .waitSeconds(1)
                .stopAndAdd(intake.setServo(0.5))
                .stopAndAdd(swingingArm.setPosition(-10, false))
                .stopAndAdd(slidingArm.setPosition(30,false))
                .strafeToLinearHeading(bucketPos,bucketHeading)
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.BOTTOM.ticks, true))
                .stopAndAdd(intake.setServo(1))
                .waitSeconds(0.8)
                .stopAndAdd(intake.setServo(0.5))
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.TOP.ticks))
                .stopAndAdd(bucket.setServo(0))
                .waitSeconds(1)
                .stopAndAdd(bucket.setServo(0.58))
                .stopAndAdd(elevator.setPosition(Elevator.POSITION.BOTTOM.ticks,false))
                .strafeToLinearHeading(new Vector2d(45, -58.5),Math.toRadians(0))
                .build());
    }
}
