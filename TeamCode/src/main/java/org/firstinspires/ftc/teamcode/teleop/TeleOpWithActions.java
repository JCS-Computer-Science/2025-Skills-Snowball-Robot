package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.GamepadEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.systems.Bucket;
import org.firstinspires.ftc.teamcode.systems.Elevator;
import org.firstinspires.ftc.teamcode.systems.ExampleSystem;
import org.firstinspires.ftc.teamcode.systems.Intake;
import org.firstinspires.ftc.teamcode.systems.SlidingArm;
import org.firstinspires.ftc.teamcode.systems.SwingingArm;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name="TeleOpWithActionsDemo")
public class TeleOpWithActions extends OpMode {
    private FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();
    public GamepadEx driver, operator;
    public MecanumDrive drive;
    public ExampleSystem exampleSystem;
    public SlidingArm slidingArm;
    public Elevator elevator;

    public SwingingArm swingingArm;
    public Intake intake;
    public Bucket bucket;

    @Override
    public void init() {
        driver=new GamepadEx(gamepad1);
        operator=new GamepadEx(gamepad2);
        drive=new MecanumDrive(hardwareMap, new Pose2d(0,0,0));
        exampleSystem = new ExampleSystem(hardwareMap);
        slidingArm = new SlidingArm(hardwareMap);
        elevator = new Elevator(hardwareMap);
        swingingArm = new SwingingArm(hardwareMap);
        intake = new Intake(hardwareMap);
        bucket = new Bucket(hardwareMap);
        runningActions.add(drive.driveAction(driver));
        runningActions.add(intake.intakeControl(operator.gamepad));

    }

    @Override
    public void loop() {
        TelemetryPacket packet = new TelemetryPacket();
        driver.update();
        operator.update();
        packet.put("slidingArmPosition", slidingArm.getPosition());
        packet.put("elevatorPosition", elevator.getPosition());
        telemetry.addData("swingingArmPosition", swingingArm.getPosition());
        telemetry.addData("elevatorPosition", elevator.getPosition());
        //add actions as needed here, eg:
        if(operator.getButton(GamepadEx.Button.A).justPressed){
            runningActions.add(bucket.setServo(0.8));
        }
        if(operator.getButton(GamepadEx.Button.B).justPressed){
            runningActions.add(bucket.setServo(0));
        }
        if(operator.getButton(GamepadEx.Button.X).justPressed){
            runningActions.add(slidingArm.setPosition(700));
        }
        if(operator.getButton(GamepadEx.Button.Y).justPressed){
            runningActions.add(slidingArm.setPosition(5));
        }
        if(operator.getButton(GamepadEx.Button.DPAD_UP).justPressed){
            runningActions.add(elevator.setPosition(-11200));
        }
        if(operator.getButton(GamepadEx.Button.DPAD_DOWN).justPressed){
            runningActions.add(elevator.setPosition(-400));
        }
        if(operator.getButton(GamepadEx.Button.LEFT_BUMPER).justPressed){
            runningActions.add(swingingArm.setPosition(10));
        }
        if(operator.getButton(GamepadEx.Button.RIGHT_BUMPER).justPressed){
            runningActions.add(swingingArm.setPosition(-314));
        }

        updateActions(packet);
    }

    private void updateActions(TelemetryPacket packet){
        List<Action> newActions = new ArrayList<>();
        for(Action action : runningActions){
            action.preview(packet.fieldOverlay());
            if(action.run(packet)){
                newActions.add(action);
            }
            runningActions = newActions;

            dash.sendTelemetryPacket(packet);
        }
    }
}
