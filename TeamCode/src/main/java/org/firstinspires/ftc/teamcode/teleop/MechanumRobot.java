package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.GamepadEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import org.firstinspires.ftc.teamcode.systems.ExampleSystem;
import org.firstinspires.ftc.teamcode.systems.BallIntake;
import org.firstinspires.ftc.teamcode.systems.BallShooter;
import org.firstinspires.ftc.teamcode.systems.HopperDoor;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name="Mechanum Robot")
public class MechanumRobot extends OpMode {
    private FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();
    public GamepadEx driver;
    public MecanumDrive drive;
    public ExampleSystem exampleSystem;
    public BallIntake ballIntake;
    public BallShooter ballShooter;
    public HopperDoor hopperDoor;


    @Override
    public void init() {
        driver=new GamepadEx(gamepad1);
        drive=new MecanumDrive(hardwareMap, new Pose2d(0,0,0));
        exampleSystem = new ExampleSystem(hardwareMap);
//        ballIntake = new BallIntake(hardwareMap);
//        ballShooter = new BallShooter(hardwareMap);
//        hopperDoor = new HopperDoor(hardwareMap);

        runningActions.add(drive.driveAction(driver));
//        runningActions.add(hopperDoor.runServo());
    }

    @Override
    public void loop() {
        TelemetryPacket packet = new TelemetryPacket();
        driver.update();

        //add actions as needed here, eg:
        if(driver.getButton(GamepadEx.Button.A).justPressed){
            runningActions.add(drive.toggleSlowMode());
        }

//        if(driver.getButton(GamepadEx.Button.B).justPressed){
//            runningActions.add(ballIntake.toggleIntake());
//        }
//
//        if(driver.getButton(GamepadEx.Button.DPAD_LEFT).justPressed){
//            runningActions.add(ballShooter.modeOff());
//        }
//        if(driver.getButton(GamepadEx.Button.DPAD_DOWN).justPressed){
//            runningActions.add(ballShooter.modeLow());
//        }
//        if(driver.getButton(GamepadEx.Button.DPAD_RIGHT).justPressed){
//            runningActions.add(ballShooter.modeMed());
//        }
//        if(driver.getButton(GamepadEx.Button.DPAD_UP).justPressed){
//            runningActions.add(ballShooter.modeHigh());
//        }
//
//        if(driver.getButton(GamepadEx.Button.X).justPressed){
//            runningActions.add(hopperDoor.toggleOpen());
//        }
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

        }
    }
}
